package org.intercom.interview.TopN;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer leftSide, Integer rightSide) {
		return Integer.compare(leftSide,  rightSide);	
	}
}
