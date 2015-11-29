package com.duykien.practice.datatypes.roadnetwork;

import java.util.Comparator;

public class NodeInfoComparator implements Comparator<NodeInfo> {

	@Override
	public int compare(NodeInfo o1, NodeInfo o2) {
		if (o1.getNodeId() < o2.getNodeId()) return -1;
		if (o1.getNodeId() == o2.getNodeId()) return 0;
		return 1;
	}

}
