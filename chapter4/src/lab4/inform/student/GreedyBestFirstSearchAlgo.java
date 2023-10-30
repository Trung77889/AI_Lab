package lab4.inform.student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {

	@Override
		public Node execute(Node root, String goal) {
			PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparatorByHn());
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
			   
			    if(!explored.contains(child) && !frontier.contains(child)) {
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
	class NodeComparatorByHn implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
		Double h1 = o1.getH();
		Double h2 = o2.getH();
		int result = h1.compareTo(h2);
		if (result == 0)
		return o1.getLabel().compareTo(o2.getLabel());
		else
		return result;
		}
		}
}
