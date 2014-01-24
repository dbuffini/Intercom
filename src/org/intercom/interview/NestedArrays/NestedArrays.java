package org.intercom.interview.NestedArrays;
import java.util.ArrayList;


public class NestedArrays {

	public static void main(String[] args) {
		ArrayList<Object> inputArray = new ArrayList<Object>();
		
		ArrayList<Object> firstList = new ArrayList<>();
		firstList.add(16);
		firstList.add(166);
		firstList.add(1666);
		firstList.add(16666);
		
		ArrayList<Object> secondList = new ArrayList<Object>();
		secondList.add(7);
		secondList.add(42);
		
		ArrayList<Object> thirdList = new ArrayList<Object>();
		secondList.add(thirdList);
		
		inputArray.add(firstList);
		inputArray.add(4);
		inputArray.add(secondList);
		
		ArrayList<Object> outputList = new ArrayList<Object>(); 
		outputList = flattenArrays(inputArray, outputList);
		
		System.out.println("Flattened Array follows!!");
		for (int listCounter = 0; listCounter < outputList.size(); listCounter++) {
			Object currentObject = outputList.get(listCounter);
			if (currentObject instanceof Integer) {
				System.out.println("Element [" + listCounter + "] = [" + ((Integer)currentObject) + "]");
			}
		}		
	}

	@SuppressWarnings("unchecked")
	private static ArrayList<Object> flattenArrays(ArrayList<Object> inputArray, ArrayList<Object> outputList) {

		for (Object currentObject : inputArray) {
			if (currentObject instanceof ArrayList) {
				flattenArrays(((ArrayList<Object>)currentObject), outputList);
			} else if (currentObject instanceof Integer) {
				outputList.add((Integer)currentObject);
			} // else it's rubbish, throw it away
		}
		
		return outputList;
	}
	
	
	
	
}
