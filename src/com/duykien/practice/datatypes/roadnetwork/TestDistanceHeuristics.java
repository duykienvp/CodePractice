package com.duykien.practice.datatypes.roadnetwork;

import com.duykien.practice.algo.AStarShortestPathHeuristics;

public class TestDistanceHeuristics implements AStarShortestPathHeuristics {
	private LogicNetwork network;

	public TestDistanceHeuristics(LogicNetwork network) {
		this.network = network;
	}

	@Override
	public double heuristics(int startNode, int endNode) {
		switch (startNode) {
		case 1:
			return 4;
			
		case 2:
			return 4;
			
		case 3:
			return 4;
			
		case 4:
			return 4.5;
			
		case 5:
			return 2;

		default:
			return 0;
		}
	}

}