package org.intercom.interview.WordChain;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MasterOfChains {

	private static final String DUMMY_INPUT = "ball";
	private static final String DUMMY_USER = "hole";
	private static final String DUMMY_DICTIONARY = "C://temp//dictionary.dic";

	public static void main(String[] args) throws IOException, Exception {

		String inputString;
		String userString;
		String filePath;

		try {
			inputString = isValidString(args[0]) ? args[0] : DUMMY_INPUT;
			userString = isValidString(args[1]) ? args[1] : DUMMY_USER;
			filePath = isValidFile(args[2]) ? args[2] : DUMMY_DICTIONARY;
		} catch (Exception e) {
			throw new IOException("Incorrect Input Parameters");
		}

		ChainFinder chainTree = new ChainFinder();
		chainTree.readDictionary(filePath);
		try {
			List<String> chainPath = chainTree.findPath(inputString, userString);
			if(chainPath != null && !chainPath.isEmpty()) {
				int chainElements = chainPath.size();

				System.out.println("Woots!! Path found!! You can sleep indoors tonight!!");				
				String pathOutput = "Path as follows: [ ";

				for (int stringCounter = 0; stringCounter < chainPath.size(); stringCounter++) {
					pathOutput += chainPath.get(stringCounter);
					if (stringCounter != (chainElements - 1)) {
						pathOutput += " -> ";
					} else {
						pathOutput += " ]";
					}
				}
				System.out.println(pathOutput);
				System.out.println("THANK YOU FOR YOUR COOPERATION. HAVE AN OCP DAY.");
			} else {
				System.out.println("No path found, you need to try harder. You're such a disappointment to me and your mother.");
			}
		} catch (Exception e) {
			throw new Exception("EHR MEHR GERD NEHHHERRRRR!!!", e);
		}
	}

	private static boolean isValidString(String inputString) {
		return (inputString != null && !inputString.isEmpty());
	}

	@SuppressWarnings("unused")
	private static boolean isValidFile(String fileName) {
		if (isValidString(fileName)) {
			try {
				Path dictionaryPath = Paths.get(fileName);	
				return true;
			} catch (InvalidPathException ipe) {
				return false;
			}
		}

		return false;
	}
}
