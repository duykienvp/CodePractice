package com.duykien.practice.algo.astar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import com.duykien.practice.datatypes.roadnetwork.LinkInfo;
import com.duykien.practice.datatypes.roadnetwork.LogicNetwork;
import com.duykien.practice.datatypes.roadnetwork.NodeInfo;
import com.duykien.practice.datatypes.roadnetwork.PathInfo;

public class AStar {
	public static PathInfo findShortestPathRoadNetwork(LogicNetwork network, NodeInfo startNode, NodeInfo endNode, AStarShortestPathHeuristics heutistics) {
		/*
		 * Cost from start along best known path.
		 */
		Map<NodeInfo, Double> gScore = new HashMap<>();
		/*
		 * Estimated total cost from start to goal through a node.
		 */
		Map<NodeInfo, Double> fScore = new HashMap<>(); 
		
		initialize(gScore, fScore, network, startNode, endNode, heutistics);
//		System.out.println(gScore.toString());
//		System.out.println(fScore.toString());
		
		/*
		 * The set of nodes already evaluated.
		 */
		Set<NodeInfo> closedSet = new HashSet<>(); 
		/*
		 * The set of tentative nodes to be evaluated, initially containing the start node
		 */
		Set<NodeInfo> openSet = new HashSet<>();
		openSet.add(startNode);
//		System.out.println(openSet.toString());
		/*
		 * Priority queue contains nodes in openSet sorted by length from the start node
		 */
		PriorityQueue<NodeInfo> openPriorityQueue = new PriorityQueue<>(new AStarNodeScoreComparator(fScore));
		openPriorityQueue.add(startNode);
//		System.out.println(openPriorityQueue.toString());
		/*
		 * The map of navigated nodes.
		 */
		Map<Integer, Integer> traceMap = new HashMap<>(); 
				
		/*
		 * The A* algorithm
		 */
		while (openPriorityQueue.isEmpty() == false) {
			NodeInfo current = openPriorityQueue.remove();
//			System.out.println("Current: " + current.toString());
			if (current.getNodeId() == endNode.getNodeId())
				return reconstructPath(endNode, traceMap, network);
			
			openSet.remove(current);
			closedSet.add(current);
			
			//update neighbors
			for (LinkInfo neighborLink : network.getLinks().get(current.getNodeId())) {
				// Ignore the neighbor which is already evaluated.
				NodeInfo neighborNode = network.getNodes().get(neighborLink.getEndNode());
//				System.out.println("neighborNode: " + neighborNode.toString());
				
				if (closedSet.contains(neighborNode.getNodeId())) 
					continue; 
				
				// length of this path.
				double tentativeGScore = gScore.get(current) + neighborLink.getCost();
				
				if (openSet.contains(neighborNode) == false) {
					// Discover a new node
					openSet.add(neighborNode);
				} else if (gScore.get(neighborNode.getNodeId()) <= tentativeGScore) {
					// This is not a better path.
					continue;
				}
				
//				System.out.println(" Put neighborNode: " + neighborNode.toString());
				// This path is the best until now. Record it!
				traceMap.put(neighborNode.getNodeId(), current.getNodeId());
				gScore.put(neighborNode, tentativeGScore);
				// length of this path + estimated length to the end node
				double tentativeFScore = tentativeGScore + heutistics.heuristics(neighborNode.getNodeId(), endNode.getNodeId());
				fScore.put(neighborNode, tentativeFScore);
				
				openPriorityQueue.add(neighborNode);
			}
		}
		
		return null;
	}
	
	/**
	 * Initialize gScore and fScore
	 * @param gScore
	 * @param fScore
	 * @param network
	 * @param startNode
	 * @param endNode
	 * @param heutistics
	 */
	public static void initialize(Map<NodeInfo, Double> gScore, Map<NodeInfo, Double> fScore, LogicNetwork network, NodeInfo startNode, NodeInfo endNode, AStarShortestPathHeuristics heutistics) {
		for (NodeInfo node: network.getNodes()) {
			gScore.put(node, Double.POSITIVE_INFINITY);
			fScore.put(node, Double.POSITIVE_INFINITY);
		}
		
		gScore.put(startNode, 0.0);
		fScore.put(startNode, heutistics.heuristics(startNode.getNodeId(), endNode.getNodeId()));
	}

	/**
	 * Reconstruct the path from start node to end node
	 * @param result
	 * @param endNode
	 * @param traceMap
	 * @return the path or {@code null} if error occurred
	 */
	public static PathInfo reconstructPath(NodeInfo endNode, Map<Integer, Integer> traceMap, LogicNetwork network) {
		PathInfo result = new PathInfo();
		int current = endNode.getNodeId();
		ArrayList<LinkInfo> tmpLinks = new ArrayList<>(); //the links in the reverse order
		
		while (traceMap.containsKey(current)) {
			int previous = traceMap.get(current);
			LinkInfo linkInfo = getLink(previous, current, network);
			tmpLinks.add(linkInfo);
			current = previous;
		}
		
		//put the links in the correct order
		for (int i = tmpLinks.size() - 1; 0 <= i; i--) {
			result.getLinks().add(tmpLinks.get(i));
		}
		//calculate values properties of the path
		double pathCost = 0.0;
		double attractiveness = 0.0;
		for (LinkInfo linkInfo : result.getLinks()) {
			pathCost += linkInfo.getCost();
			attractiveness += linkInfo.getAttractiveness();
			result.getNodes().add(network.getNodes().get(linkInfo.getStartNode()));
		}
		result.setPathCost(pathCost);
		result.setAttractiveness(attractiveness);
		result.setNumLinks(result.getLinks().size());
		result.setSimple(true);
		result.getNodes().add(endNode);
		
		return result;
	}
	
	/**
	 * Get link info of the link from start node to end node
	 * @param startNode
	 * @param endNode
	 * @param network
	 * @return the link info or {@code null} if error occurred
	 */
	public static LinkInfo getLink(int startNode, int endNode, LogicNetwork network) {
		if (network.getNodes().size() <= startNode || network.getNodes().size() <= endNode) 
			return null;
		for (LinkInfo linkInfo : network.getLinks().get(startNode)) {
			if (linkInfo.getEndNode() == endNode)
				return linkInfo;
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		NodeInfo nodeInfo0 = new NodeInfo(0, "start", 0, 0, 0);
		NodeInfo nodeInfo1 = new NodeInfo(1, "a", 0, 0, 0);
		NodeInfo nodeInfo2 = new NodeInfo(2, "b", 0, 0, 0);
		NodeInfo nodeInfo3 = new NodeInfo(3, "c", 0, 0, 0);
		NodeInfo nodeInfo4 = new NodeInfo(4, "d", 0, 0, 0);
		NodeInfo nodeInfo5 = new NodeInfo(5, "e", 0, 0, 0);
		NodeInfo nodeInfo6 = new NodeInfo(6, "end", 0, 0, 0);
		
		LinkInfo linkInfoStartA = new LinkInfo(0, "StartA", 1.5, 0, 0, 1);
		LinkInfo linkInfoAB = new LinkInfo(1, "AB", 2, 0, 1, 2);
		LinkInfo linkInfoBC = new LinkInfo(2, "BC", 3, 0, 2, 3);
		LinkInfo linkInfoCEnd = new LinkInfo(3, "CEnd", 4, 0, 3, 6);
		LinkInfo linkInfoStartD = new LinkInfo(4, "StartD", 2, 0, 0, 4);
		LinkInfo linkInfoDE = new LinkInfo(5, "DE", 3, 0, 4, 5);
		LinkInfo linkInfoEEnd = new LinkInfo(6, "EEnd", 2, 0, 5, 6);
		
		LogicNetwork network = new LogicNetwork();
		
		network.getNodes().add(nodeInfo0);
		network.getNodes().add(nodeInfo1);
		network.getNodes().add(nodeInfo2);
		network.getNodes().add(nodeInfo3);
		network.getNodes().add(nodeInfo4);
		network.getNodes().add(nodeInfo5);
		network.getNodes().add(nodeInfo6);
		
		// neighbors
		ArrayList<LinkInfo> startNeighbors = new ArrayList<>();
		startNeighbors.add(linkInfoStartA);
		startNeighbors.add(linkInfoStartD);
		network.getLinks().add(startNeighbors);
		
		ArrayList<LinkInfo> aNeighbors = new ArrayList<>();
		aNeighbors.add(linkInfoAB);
		network.getLinks().add(aNeighbors);
		
		ArrayList<LinkInfo> bNeighbors = new ArrayList<>();
		bNeighbors.add(linkInfoBC);
		network.getLinks().add(bNeighbors);
		
		ArrayList<LinkInfo> cNeighbors = new ArrayList<>();
		cNeighbors.add(linkInfoCEnd);
		network.getLinks().add(cNeighbors);
		
		ArrayList<LinkInfo> dNeighbors = new ArrayList<>();
		dNeighbors.add(linkInfoDE);
		network.getLinks().add(dNeighbors);
		
		ArrayList<LinkInfo> eNeighbors = new ArrayList<>();
		eNeighbors.add(linkInfoEEnd);
		network.getLinks().add(eNeighbors);
		
		
		PathInfo path = findShortestPathRoadNetwork(network, nodeInfo0, nodeInfo6, new TestDistanceHeuristics(network));
		System.out.println(path.toString());
	}
}
