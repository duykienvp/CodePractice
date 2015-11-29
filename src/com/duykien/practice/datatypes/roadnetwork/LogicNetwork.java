package com.duykien.practice.datatypes.roadnetwork;

import java.util.ArrayList;
import java.util.Collections;

public class LogicNetwork {
	private ArrayList<NodeInfo> nodes;
	private ArrayList<ArrayList<LinkInfo>> links;
	
	public LogicNetwork() {
		nodes = new ArrayList<>();
		links = new ArrayList<>();
	}
	
	public ArrayList<NodeInfo> getNodes() {
		return nodes;
	}
	
	public ArrayList<ArrayList<LinkInfo>> getLinks() {
		return links;
	}
	
	public void sortNodesByIds() {
		Collections.sort(nodes, new NodeInfoComparator());
	}
}
