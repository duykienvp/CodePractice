package com.duykien.practice.datatypes.roadnetwork;

import java.util.Comparator;

public class LinkInfoComparator implements Comparator<LinkInfo>{

	@Override
	public int compare(LinkInfo o1, LinkInfo o2) {
		if (o1.getStartNode() < o2.getStartNode()) return -1;
		if (o2.getStartNode() < o1.getStartNode()) return 1;
		//at this point, o2.getStartNode() == o1.getStartNode(), compare endNode
		if (o1.getEndNode() < o2.getEndNode()) return -1;
		if (o2.getEndNode() < o1.getEndNode()) return 1;
		
		return 0;
	}

}
