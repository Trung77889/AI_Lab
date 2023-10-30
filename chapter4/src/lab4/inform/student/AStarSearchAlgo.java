package lab4.inform.student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparatorByFn());
	    frontier.add(root);

	    ArrayList<Node> explored = new ArrayList<>();

	    while(!frontier.isEmpty()) {

	      Node current = frontier.poll();
	      explored.add(current);

	      if(current.getLabel().equals(goal)) {
	        return current; 
	      }

	      for(Edge edge: current.getChildren()) {
	        Node child = edge.getEnd();
	        double newG = current.getG() + edge.getWeight();
	        child.setG(newG);	        
	        double newF = newG + child.getH();

	        if(frontier.contains(child) && child.getF() < newG + child.getH())
	        {
	        	child.setParent(current);
	        	child.setF(newF);
	        }
	        if(!explored.contains(child) && !frontier.contains(child)) {
	        	child.setF(newF);
	            child.setParent(current);
	            frontier.add(child);

	        } 
	      }

	    }

	    return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		return null;
	}
	
	class NodeComparatorByFn implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
		Double h1 = o1.getF();
		Double h2 = o2.getF();
		int result = h1.compareTo(h2);
		if (result == 0)
		return o1.getLabel().compareTo(o2.getLabel());
		else
		return result;
		}
		}

}
