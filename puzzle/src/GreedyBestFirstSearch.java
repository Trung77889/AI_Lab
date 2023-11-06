import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirstSearch implements IPuzzleAlgo {

	@Override
	public Node execute(Puzzle model) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByH);
		ArrayList<Node> explored = new ArrayList<>();
		
		frontier.add(model.getInitialState());
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.getH() == 0)
				return current;
			else {
				explored.add(current);
				List<Node> children = model.getSuccessors(current);
				for(Node child : children) {
					if(!frontier.contains(child) && !explored.contains(child))
					{
						frontier.add(child);
						
					}
				}
			}
		}
		
		return null;
	}

}
