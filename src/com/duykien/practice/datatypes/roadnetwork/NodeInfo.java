package com.duykien.practice.datatypes.roadnetwork;

public class NodeInfo {
	private int nodeId;
	private String nodeName;
	private double latitude;
	private double longitude;
	private double cost;

	public NodeInfo() {
		super();
	}

	public NodeInfo(int nodeId, String nodeName, double latitude, double longitude, double cost) {
		super();
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.cost = cost;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "NodeData [nodeId=" + nodeId + ", nodeName=" + nodeName + ", latitude=" + latitude + ", longitude="
				+ longitude + ", cost=" + cost + "]";
	}
	
}
