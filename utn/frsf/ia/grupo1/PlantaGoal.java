package frsf.ia.grupo1;


	import frsf.cidisi.faia.agent.search.GoalTest;
	import frsf.cidisi.faia.state.AgentState;

	public class PlantaGoal  extends GoalTest {

	    @Override
	    public boolean isGoalState(AgentState agentState) {
	    	
	        if (  ((PlantaState) agentState).getTotalZombies()==0 && ((PlantaState) agentState).getEnergia() >=1  ) {
	            return true;
	        }
	        return false;
	    }
	}

	

