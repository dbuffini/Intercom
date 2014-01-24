package org.intercom.interview.WordChain;

public abstract class DistanceAlgorithm {

	private int minDistance = 0;
	private int maxDistance = 0;
	
	public int getDistance(TrieNode aNode, TrieNode bNode) {
		return 0;
	}
	
	public boolean validMatch(TrieNode aNode, TrieNode bNode) {
		return false;
	}

	public int getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(int minDistance) {
		this.minDistance = minDistance;
	}

	public int getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}
}
