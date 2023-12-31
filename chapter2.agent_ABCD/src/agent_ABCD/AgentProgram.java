package agent_ABCD;

import java.util.Random;

import agent_ABCD.Environment.LocationState;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		if(p.getLocationState() == LocationState.DIRTY)
			return Environment.SUCK_DIRT;
		else {
			 // Location is CLEAN, choose a random action (UP, DOWN, LEFT, RIGHT)
	        Random random = new Random();
	        int randomIndex = random.nextInt(4);

	        switch (randomIndex) {
	            case 0:
	                return Environment.MOVE_UP;
	            case 1:
	                return Environment.MOVE_DOWN;
	            case 2:
	                return Environment.MOVE_LEFT;
	            case 3:
	                return Environment.MOVE_RIGHT;
	            default:
	                return Environment.MOVE_UP;
	        }
		}
	}
}