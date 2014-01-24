package org.intercom.interview.WordChain;

public class HammingDistanceAlgorithm extends DistanceAlgorithm {

	public HammingDistanceAlgorithm(int minDistance, int maxDistance) {
		super.setMinDistance(minDistance);
		super.setMaxDistance(maxDistance);
	}
	
	public int getDistance(TrieNode aNode, TrieNode bNode) {
		return (Math.abs(aNode.getChar() - bNode.getChar()));
	}

	public boolean validMatch(TrieNode aNode, TrieNode bNode) {
		int stringDistance = getDistance(aNode, bNode);
		return (stringDistance <= getMaxDistance() && stringDistance >= getMinDistance());
	}

}
