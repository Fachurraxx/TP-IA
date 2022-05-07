package frsf.ia.grupo1;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class FuncionCosto implements IStepCostFunction {

    /**
     * This method calculates the cost of the given NTree node.
     */
    @Override
    public double calculateCost(NTree node) {
        return ((PlantaState) node.getAgentState()).getCantidadAccionesRealizadas();
        
    }
}