package com.duykien.practice.algo.astar;

import java.util.Comparator;
import java.util.Map;

import com.duykien.practice.datatypes.roadnetwork.NodeInfo;

public class AStarNodeScoreComparator implements Comparator<NodeInfo> {
	private Map<NodeInfo, Double> fScore;

	public AStarNodeScoreComparator(Map<NodeInfo, Double> fScore) {
		this.fScore = fScore;
	}

	@Override
	public int compare(NodeInfo o1, NodeInfo o2) {
		Double f1 = fScore.get(o1);
		Double f2 = fScore.get(o2);
		
		if (f1 == null || f2 == null) 
			return 0;
		if (f1 < f2) 
			return -1;
		if (f2 < f1)
			return 1;
		return 0;
	}

}
