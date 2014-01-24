package org.intercom.interview.WordChain;


public class TrieNode {

	private TrieNode parentNode;
	private TrieNode[] childNodes;
	private char baseCharacter;
	private boolean isLeaf; 
	private boolean isWord; 

	
	public TrieNode()
	{
		// SLIIIIGHTLY biased towards English here
		childNodes = new TrieNode[26];
		isLeaf = true;
		isWord = false;
	}
	
	
	public TrieNode(char baseCharacter) {
		this();
		this.baseCharacter = baseCharacter;
	}

	
	protected void addWord(String word)
	{
		isLeaf = false;
		// There's that bias again. Tsk.
		int charPos = word.charAt(0) - 'a';

		if (childNodes[charPos] == null)
		{
			childNodes[charPos] = new TrieNode(word.charAt(0));
			childNodes[charPos].parentNode = this;
		}

		if (word.length() > 1)
		{
			childNodes[charPos].addWord(word.substring(1));
		}
		else
		{
			childNodes[charPos].isWord = true;
		}
	}


	protected TrieNode getNode(char c)
	{
		return childNodes[c - 'a'];
	}
	
	
	public char getChar() {
		return baseCharacter;
	}


	public boolean findWord(String inputString)
	{
		boolean returnResult = false;
		
		if (isWord)
		{
			if(inputString.equals(toString())) {
				return true;
			}
		}

		//If any childNodes
		if (!isLeaf)
		{
			//Add any words belonging to any childNodes
			for (int i=0; i < childNodes.length; i++)
			{
				if (childNodes[i] != null)
				{
					returnResult = (childNodes[i].findWord(inputString));
					if (returnResult) return returnResult;
				}
			}
		}
		
		return returnResult; 
	}
	
	
	public String toString() {
		if (parentNode == null)
		{
			return "";
		} else {
			return parentNode.toString() + new String(new char[] {baseCharacter});
		}
	} 
}


