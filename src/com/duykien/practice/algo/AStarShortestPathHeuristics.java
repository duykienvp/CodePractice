package com.duykien.practice.algo;

public interface AStarShortestPathHeuristics {
	/**
	 * Heuristics for the distance from start node to end node
	 * @param startNode
	 * @param endNode
	 * @return the heuristics for the distance from start node to end node or {@code infinity} if error occurred
	 */
	public double heuristics(int startNode, int endNode);
}
