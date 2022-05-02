package frsf.ia.grupo1;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

/**
 * This class defines the 'isGoalState' method. It has the responsability of,
 * given the agent state, verify if it is a goal state. It is used by the search
 * process of the agent and by the simulator, to know when to stop.
 */
public class PlantaGoal extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {

//		if (((PlantaState) agentState).getTotalZombies() == 0 && ((PlantaState) agentState).getEnergia() >= 1) {
//			return true;
//		}
		

		
		return false;
	}

	@Override
	public boolean isGoalIntermedio(AgentState agentState) {
		
		if (((PlantaState) agentState).getZombiesQueVeo()==0) {
			return true;
		}
		return false;
	}
}
