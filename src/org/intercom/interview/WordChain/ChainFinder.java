package org.intercom.interview.WordChain;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ChainFinder extends TrieNode {

	private static final Charset ENCODING = StandardCharsets.UTF_8;	
	private TrieNode dictionaryTree;
	private Hashtable<String,Integer> visitedNodes = new Hashtable<String,Integer>();
	private Stack<String> chainPath = new Stack<String>();
	private String endString;

	//private DistanceAlgorithm distanceAlgorithm;	


	public ChainFinder() {
		dictionaryTree = new TrieNode();
	}

	/*public ChainFinder(DistanceAlgorithm distanceAlgorithm) {
		this();
		this.distanceAlgorithm = (distanceAlgorithm != null) ? distanceAlgorithm : new HammingDistanceAlgorithm(1, 1);
	}*/

	public boolean readDictionary(String inputFile) {
		boolean insertStatus = true;

		Path path = Paths.get(inputFile);
		try (Scanner scanner =  new Scanner(path, ENCODING.name())) {
			while (scanner.hasNextLine()){
				dictionaryTree.addWord(scanner.nextLine());
			}      
		} catch (IOException e) {
			e.printStackTrace();
			insertStatus = false;
		}

		return insertStatus;		
	}

	public List<String> findPath(String inputString, String userString) throws Exception {

		if (!dictionaryTree.findWord(inputString) || !dictionaryTree.findWord(userString) || (inputString.length() != userString.length())) 
		{
			throw new Exception("Search Terms not applicable. Please try again. Input String [ " + inputString + " ], End String [ " + userString + "] ");
		}

		endString = userString;
		chainPath = new Stack<String>();

		if (check(inputString)) {
			chainPath.push(inputString);
		}

		visitedNodes.clear(); 

		return chainPath;
	}

	private boolean check(String inputString) {
		if (inputString.equals(endString)) return true;

		ConcurrentLinkedQueue<String> candidateQueue = generateCandidates(inputString);
		for (String currentString : candidateQueue)
		{

			if (visitedNodes.get(currentString) == null)
			{
				if (check(currentString))
				{
					chainPath.push(currentString);
					return true;
				}

				visitedNodes.put(currentString,1);
			}
		}

		return false;
	}

	private ConcurrentLinkedQueue<String> generateCandidates(String inputString)
	{
		visitedNodes.put(inputString,1);
		//make empty queue into which we will place valid words generated
		ConcurrentLinkedQueue<String> candidateQueue = new ConcurrentLinkedQueue<String>();
		StringBuilder inputBuilder = new StringBuilder(inputString);
		// Generate match possibilites 
		for (int i = 0; i < inputBuilder.length(); i++)
		{
			char temp = inputBuilder.charAt(i);
			for (char k = 97; k < 123; k++)
			{
				inputBuilder.setCharAt(i,k);
				// If word is in dictionary, add to candidates
				if (dictionaryTree.findWord(inputBuilder.toString())) {
					candidateQueue.add(inputBuilder.toString());
				}
			}
			inputBuilder.setCharAt(i,temp); //return to original state before next loop iteration
		}

		//INSERT: check all possible one character insertions at every index
		for (int i = 0; i <= inputBuilder.length(); i++)
		{
			for (char k = 97; k < 123; k++)
			{
				inputBuilder.insert(i,k);
				if (dictionaryTree.findWord(inputBuilder.toString())) {
					candidateQueue.add(inputBuilder.toString());
				}
				inputBuilder.deleteCharAt(inputString.length()); //return to original state
			}
		}

		for (int i = 0; i < inputBuilder.length(); i++)
		{
			char temp = inputBuilder.charAt(i);
			inputBuilder.deleteCharAt(i);
			if (dictionaryTree.findWord(inputBuilder.toString())) {
				candidateQueue.add(inputBuilder.toString());
			}
			inputBuilder.insert(i,temp); //return to original state
		}
		return candidateQueue;
	}

}
