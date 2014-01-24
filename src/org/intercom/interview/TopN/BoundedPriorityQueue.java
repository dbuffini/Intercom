package org.intercom.interview.TopN;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class BoundedPriorityQueue<E> extends PriorityBlockingQueue<E> {

	private static final long serialVersionUID = 3734061813679261227L;
	private static int MAX_SIZE;
	private int elementsLeft;
	
	public BoundedPriorityQueue(int maxSize, Comparator<E> comparator) {
		super(maxSize, comparator);
		
		MAX_SIZE = (maxSize >= 0) ? maxSize : MAX_SIZE;
		elementsLeft = MAX_SIZE;
	}	

	@Override
	public boolean offer(E e) {
		boolean addStatus = true;
	
		if (elementsLeft > 0) {
			super.offer(e);
			elementsLeft--; 
		} else {
			if((this.comparator()).compare(e, this.peek()) <= 0) {
				addStatus = false;
			} else {
				poll(); // Ditch lowest element
				super.offer(e);
				elementsLeft--;
			}
		}
		
		return addStatus;
	}
}