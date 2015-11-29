package com.duykien.practice.datatypes.roadnetwork;

import java.util.ArrayList;

public class PathInfo {
	private double attractiveness; //total attractiveness of all links
	private double pathCost; //total cost of all links
	private int numLinks; //number of links
	private boolean simple; //is this a simple path?
	private ArrayList<LinkInfo> links; 
	private ArrayList<NodeInfo> nodes;
	public PathInfo() {
		super();
		links = new ArrayList<>();
		nodes = new ArrayList<>();
	}
	public double getPathCost() {
		return pathCost;
	}
	public void setPathCost(double pathCost) {
		this.pathCost = pathCost;
	}
	public int getNumLinks() {
		return numLinks;
	}
	public void setNumLinks(int numLinks) {
		this.numLinks = numLinks;
	}
	public boolean isSimple() {
		return simple;
	}
	public void setSimple(boolean simple) {
		this.simple = simple;
	}
	public ArrayList<LinkInfo> getLinks() {
		return links;
	}
	public void setLinks(ArrayList<LinkInfo> links) {
		this.links = links;
	}
	public ArrayList<NodeInfo> getNodes() {
		return nodes;
	}
	public void setNodes(ArrayList<NodeInfo> nodes) {
		this.nodes = nodes;
	}
	public double getAttractiveness() {
		return attractiveness;
	}
	public void setAttractiveness(double attractiveness) {
		this.attractiveness = attractiveness;
	}
	@Override
	public String toString() {
		return "PathInfo [attractiveness=" + attractiveness + ", pathCost=" + pathCost + ", numLinks=" + numLinks
				+ ", simple=" + simple + ", links=" + links + ", nodes=" + nodes + "]";
	}
	
}
