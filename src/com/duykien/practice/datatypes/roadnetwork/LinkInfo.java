package com.duykien.practice.datatypes.roadnetwork;

public class LinkInfo {
	private int id;
	private String name;
	private double cost; //in centimeters
	private double attractiveness; //based on number of images within a given distance
	private int startNode;
	private int endNode;

	public LinkInfo() {
		super();
	}

	public LinkInfo(int id, String name, double cost, double attractiveness, int startNode, int endNode) {
		super();
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.attractiveness = attractiveness;
		this.startNode = startNode;
		this.endNode = endNode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getAttractiveness() {
		return attractiveness;
	}

	public void setAttractiveness(double attractiveness) {
		this.attractiveness = attractiveness;
	}


	public int getStartNode() {
		return startNode;
	}


	public void setStartNode(int startNode) {
		this.startNode = startNode;
	}


	public int getEndNode() {
		return endNode;
	}


	public void setEndNode(int endNode) {
		this.endNode = endNode;
	}


	@Override
	public String toString() {
		return "LinkInfo [id=" + id + ", name=" + name + ", cost=" + cost + ", attractiveness=" + attractiveness
				+ ", startNode=" + startNode + ", endNode=" + endNode + "]";
	}
}
