package frsf.ia.grupo1;


	import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
	import frsf.cidisi.faia.solver.search.NTree;

	/**
	 * This class allows to define a function to be used by any
	 * informed search strategy, like A Star or Greedy.
	 */
	public class HeuristicaPlanta  implements IEstimatedCostFunction {

	    /**
	     * It returns the estimated cost to reach the goal from a NTree node.
	     */
	    @Override
	    public double getEstimatedCost(NTree node) {
	        PlantaState plantaState = (PlantaState) node.getAgentState();
//	        
//	        System.out.println("ENERGIA: " + plantaState.getEnergia());
//	        Float energia =  (float) ( 1.0 /( (float) plantaState.getEnergia()));
//	        System.out.println("HEURISTICA:  " + plantaState.getZombiesQueVeo() + " ENERGIA:  " + energia);
//	        
	        return (plantaState.getZombiesQueVeo());// + energia);
	    }
	}

