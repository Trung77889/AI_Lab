package k21;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
	    
	    Queue<Node> frontier = new LinkedList<>();
	    frontier.add(root);
	    
	    List<Node> explored = new ArrayList<Node>();
	    
	    while (!frontier.isEmpty()) {
	        Node currentNode = frontier.poll();
	        
	        if (currentNode.getLabel().equals(goal)) {
	            return currentNode;
	        }
	        
	        explored.add(currentNode);
	        
	        for (Edge edge : currentNode.getChildren()) {
	            Node child = edge.getEnd();
	            if (!explored.contains(child) && !frontier.contains(child)) {
	                child.setParent(currentNode);
	                frontier.add(child);
	            }
	        }
	    }
	    
	    return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		
		Queue<Node> frontier = new LinkedList<>();
        Node startNode = findNode(root, start); 
        if (startNode != null) {
            frontier.add(startNode); 
        } else {
            return null; 
        }
	    
	    List<Node> explored = new ArrayList<Node>();
	    
	    while (!frontier.isEmpty()) {
	        Node currentNode = frontier.poll();
	        
	        if (currentNode.getLabel().equals(start)) {
	        	startNode = currentNode;
	        }
	        
	        if (currentNode.getLabel().equals(goal)) {
	            return currentNode;
	        }
	        
	        explored.add(currentNode);
	        
	        for (Edge edge : currentNode.getChildren()) {
	            Node child = edge.getEnd();
	            if (!explored.contains(child) && !frontier.contains(child)) {
	                child.setParent(currentNode);
	                frontier.add(child);
	            }
	        }
	    }
	    
	    return null;
	}
	private Node findNode(Node root, String label) {
        if (root.getLabel().equals(label)) {
            return root;
        }

        for (Edge edge : root.getChildren()) {
            Node child = edge.getEnd();
            Node foundNode = findNode(child, label);
            if (foundNode != null) {
                return foundNode;
            }
        }

        return null;
    }
}
