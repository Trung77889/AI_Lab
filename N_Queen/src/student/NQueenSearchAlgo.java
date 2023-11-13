package student;

import java.util.List;

public class NQueenSearchAlgo {
	
	int stepClimbed = 0;
	int stepClimbedAfterRandomRestart = 0;
	int randomRestarts = 0;
	
	
	public Node execute(Node initialState) {
		Node current = initialState;
		while(true) {
			List<Node> candidates = current.generateAllCandidates();
			Node neighbor = findBestNode(candidates);
			if(neighbor.getH() >= current.getH())
				return current;
			current = neighbor;
			stepClimbed++;
		}
	}
	private Node findBestNode(List<Node> candidates) {
	   Node best = candidates.get(0);
	   for (Node candidate : candidates) {
	     if (candidate.getH() < best.getH()) {
	       best = candidate; 
	     }
	   }
	   return best;
	}
	public Node executeHillClimbingWithRandomRestart(Node initialState) {
		Node state = initialState;
		while(state.getH()!=0)
		{
		    state.generateBoard(); 
		    state = execute(state);
		    randomRestarts++;
		    stepClimbedAfterRandomRestart += stepClimbed;
		}
		return state;
	}
}
