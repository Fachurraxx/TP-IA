package frsf.ia.grupo1;


	import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
	import frsf.cidisi.faia.solver.search.NTree;

	/**
	 * This class allows to define a function to be used by any
	 * informed search strategy, like A Star or Greedy.
	 */
	public class HeuristicaPlanta  implements IEstimatedCostFunction {

	    @Override
	    public double getEstimatedCost(NTree node) {
	        PlantaState plantaState = (PlantaState) node.getAgentState();	        
	        return (plantaState.getTotalZombies());
	    }
	}

