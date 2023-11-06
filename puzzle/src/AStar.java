import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar implements IPuzzleAlgo {

	@Override
	public Node execute(Puzzle model) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByF);
	    
	    frontier.add(model.getInitialState());
	    
	    ArrayList<Node> explored = new ArrayList<>();
	    
	    while (!frontier.isEmpty()) {
	    
	      Node current = frontier.poll();
	      
	      if (current.getH() == 0) {
	        return current;  
	      }
	      
	      explored.add(current);

	      for (Node child : model.getSuccessors(current)) {
	      
	        child.setG(current.getG() + 1);
	        child.setH(model.computeH1(child));
	        
	        if (!frontier.contains(child) && !explored.contains(child)) {
	        
	          frontier.add(child);        
	        }
	      }
	      
	    }
	    
	    return null;
	}

}
