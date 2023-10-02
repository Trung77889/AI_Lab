package agent_ABCD;

import java.util.Random;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;
	private Random random = new Random();
    private int score = 0;

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState, LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState, locCState, locDState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		this.agent = agent;
	    envState.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		String agentLocation = envState.getAgentLocation();
        LocationState locationState = envState.getLocationState(agentLocation);
        
        if(action.equals(SUCK_DIRT))
        {
        	envState.setLocationState(agentLocation, LocationState.CLEAN);
        	score += 500;
        }
        else if(action.equals(MOVE_LEFT)) {
        	String newLocation = getNewLocation(agentLocation, "LEFT");
            if (newLocation != null) {
                envState.setAgentLocation(newLocation);
            } else {
                score -= 100;
            }
            score -= 10;
        }
        else if (action.equals(MOVE_RIGHT)) {
            String newLocation = getNewLocation(agentLocation, "RIGHT");
            if (newLocation != null) {
                envState.setAgentLocation(newLocation);
            } else {
                score -= 100;
            }
            score -= 10;
        } else if (action.equals(MOVE_UP)) {
            String newLocation = getNewLocation(agentLocation, "UP");
            if (newLocation != null) {
                envState.setAgentLocation(newLocation);
            } else {
                score -= 100;
            }
            score -= 10;
        } else if (action.equals(MOVE_DOWN)) {
            String newLocation = getNewLocation(agentLocation, "DOWN");
            if (newLocation != null) {
                envState.setAgentLocation(newLocation);
            } else {
                score -= 100;
            }
            score -= 10;
        }
        	
        	
        return envState;
	}

	// Agent can't move ?
	private String getNewLocation(String currentLocation, String moveDirection) {
        String newLocation = currentLocation;

        if (currentLocation.equals(LOCATION_A)) {
            if (moveDirection.equals("LEFT")) {
                return null;
            } else if (moveDirection.equals("RIGHT")) {
                newLocation = LOCATION_B;
            } else if (moveDirection.equals("UP")) {
                return null;
            } else if (moveDirection.equals("DOWN")) {
                newLocation = LOCATION_C;
            }
        } else if (currentLocation.equals(LOCATION_B)) {
            if (moveDirection.equals("LEFT")) {
                newLocation = LOCATION_A;
            } else if (moveDirection.equals("RIGHT")) {
                newLocation = LOCATION_C;
            } else if (moveDirection.equals("UP")) {
                return null;
            } else if (moveDirection.equals("DOWN")) {
                newLocation = LOCATION_D;
            }
        } else if (currentLocation.equals(LOCATION_C)) {
            if (moveDirection.equals("LEFT")) {
                newLocation = LOCATION_B;
            } else if (moveDirection.equals("RIGHT")) {
                return null;
            } else if (moveDirection.equals("UP")) {
                newLocation = LOCATION_A;
            } else if (moveDirection.equals("DOWN")) {
                return null;
            }
        } else if (currentLocation.equals(LOCATION_D)) {
            if (moveDirection.equals("LEFT")) {
                newLocation = LOCATION_C;
            } else if (moveDirection.equals("RIGHT")) {
                return null;
            } else if (moveDirection.equals("UP")) {
                newLocation = LOCATION_B;
            } else if (moveDirection.equals("DOWN")) {
                return null;
            }
        }

        return newLocation;
    }

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
	    String agentLocation = envState.getAgentLocation();
	    LocationState locationState = envState.getLocationState(agentLocation);

	    return new Percept(agentLocation, locationState);
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

        if (es.getLocationState(LOCATION_A) == LocationState.CLEAN
                && es.getLocationState(LOCATION_B) == LocationState.CLEAN
                && es.getLocationState(LOCATION_C) == LocationState.CLEAN
                && es.getLocationState(LOCATION_D) == LocationState.CLEAN) {
            isDone = true;
        }

        es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
	
	public int getScore() {
		return this.score;
	}
}
