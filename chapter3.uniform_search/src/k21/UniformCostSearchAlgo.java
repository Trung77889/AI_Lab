package k21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble(Node::getPathCost));
		List<Node> explored = new ArrayList<>();
		
        frontier.add(root);

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();

            if (currentNode.getLabel().equals(goal)) {
                return currentNode;
            }

            explored.add(currentNode);

            for (Edge edge : currentNode.getChildren()) {
                Node child = edge.getEnd();
                double newPathCost = currentNode.getPathCost();

                if (!explored.contains(child) && !frontier.contains(child)) {
                    child.setParent(currentNode);
                    child.setPathCost(newPathCost);
                    frontier.add(child);
                } else if (frontier.contains(child) && newPathCost < child.getPathCost()) {
                    child.setParent(currentNode);
                    child.setPathCost(newPathCost);
                    frontier.remove(child);
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

}
