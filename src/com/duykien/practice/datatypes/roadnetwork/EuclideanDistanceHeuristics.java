package com.duykien.practice.datatypes.roadnetwork;

import com.duykien.practice.algo.AStarShortestPathHeuristics;

public class EuclideanDistanceHeuristics implements AStarShortestPathHeuristics {
	private LogicNetwork network;

	public EuclideanDistanceHeuristics(LogicNetwork network) {
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

		return Math.sqrt((startNodeInfo.getLatitude() - endNodeInfo.getLatitude())
				* (startNodeInfo.getLatitude() - endNodeInfo.getLatitude())
				+ (startNodeInfo.getLongitude() - endNodeInfo.getLongitude())
						* (startNodeInfo.getLongitude() - endNodeInfo.getLongitude()));
	}

}
