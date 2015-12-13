package com.duykien.practice.algo.astar;

import com.duykien.practice.datatypes.roadnetwork.LogicNetwork;
import com.duykien.practice.datatypes.roadnetwork.NodeInfo;

public class ManhattanDistanceHeuristics implements AStarShortestPathHeuristics {
	private LogicNetwork network;

	public ManhattanDistanceHeuristics(LogicNetwork network) {
		this.network = network;
	}

	@Override
	public double heuristics(int startNode, int endNode) {
		NodeInfo startNodeInfo = network.getNodes().get(startNode);
		NodeInfo endNodeInfo = network.getNodes().get(endNode);

		// if the retrieved nodes are not the provided nodes
		if (startNodeInfo.getNodeId() != startNode || endNodeInfo.getNodeId() != endNode) {
			return Double.POSITIVE_INFINITY;
		}
		
		return Math.abs(startNodeInfo.getLatitude() - endNodeInfo.getLatitude()) 
				+ Math.abs(startNodeInfo.getLongitude() - endNodeInfo.getLongitude());
	}

}