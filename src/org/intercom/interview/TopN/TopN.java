package org.intercom.interview.TopN;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Queue;
import java.util.Scanner;

public class TopN {

	private static String DUMMY_FILE = "C:\\temp.txt";
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	
	public static void main(String[] args) throws IOException {
		
		int userSize = validInteger(args[0]) ? Integer.parseInt(args[0]) : 42;
		Comparator<Integer> integerComparator = new IntegerComparator();
		Queue<Integer> boundedStore = new BoundedPriorityQueue<Integer>(userSize, integerComparator);
		
		String fileName = validString(args[1]) ? args[1] : DUMMY_FILE;
		
		Path path = Paths.get(fileName);
	    try (Scanner scanner =  new Scanner(path, ENCODING.name())) {
	      while (scanner.hasNextLine()){
	        //process each line in some way
	        String inputLine = scanner.nextLine();
	        if (validInteger(inputLine)) {
	        	boundedStore.add(Integer.parseInt(inputLine));
	        }
	      }      
	    }
	    
	    Integer[] outputArray = boundedStore.toArray(new Integer[boundedStore.size()]);
	    Arrays.sort(outputArray);
	    int elementCounter = (outputArray.length - 1);
	    System.out.println("BoundedStore Elements:");
	    while(elementCounter > 0) {
	    	 System.out.println(outputArray[elementCounter]);
	    	elementCounter--;
	    }
	}

	private static boolean validString(String inputString) {
		boolean isValid = false;
		
		if (inputString != null && !inputString.isEmpty()) {
			isValid = true;
		}
		
		return isValid;
	}
	
	private static boolean validInteger(String inputString) {
		if(validString(inputString)) {
			try {
				Integer.parseInt(inputString);
			} catch (NumberFormatException nfe) {
				return false;
			}
			
			return true;
		}
		return false;
	}
}
