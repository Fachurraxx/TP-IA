package frsf.ia.grupo1;

import frsf.cidisi.faia.agent.Perception;
import java.util.Vector;

import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.solver.search.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import AccionesPlanta.IrAbajo;
import AccionesPlanta.IrArriba;
import AccionesPlanta.IrDerecha;
import AccionesPlanta.IrIzquierda;
import AccionesPlanta.Pelear;
import AccionesPlanta.Plantar;
import AccionesPlanta.Recargar;

public class Planta extends SearchBasedAgent {

	public Planta(int energiaPlanta, int totalZombies) {

		PlantaGoal goal = new PlantaGoal();

		PlantaState plantaState = new PlantaState(energiaPlanta, totalZombies);
		this.setAgentState(plantaState);

		// Create the operators
		Vector<SearchAction> operators = new Vector<SearchAction>();
		operators.addElement(new Pelear());
		operators.addElement(new Recargar());
		operators.addElement(new Plantar());
		operators.addElement(new IrAbajo());
		operators.addElement(new IrArriba());
		operators.addElement(new IrDerecha());
		operators.addElement(new IrIzquierda());
		

		// Create the Problem which the Planta will resolve
		Problem problem = new Problem(goal, plantaState, operators);
		this.setProblem(problem);
	}
	
	public Planta(String[][] t, int row, int col, int e, int z) {
		
	}

	/**
	 * This method is executed by the simulator to ask the agent for an action.
	 */
	@Override
	public Action selectAction() {

		// Create the search strategy
		DepthFirstSearch strategy = new DepthFirstSearch();

//		IStepCostFunction costFunction = new FuncionCosto();
//		UniformCostSearch strategy = new UniformCostSearch(costFunction);
		/**
		 * Another search strategy examples:
		 * 
		 * Depth First Search: DepthFirstSearch strategy = new DepthFirstSearch();
		 * 
		 * Breath First Search: BreathFirstSearch strategy = new BreathFirstSearch();
		 * 
		 * Uniform Cost: IStepCostFunction costFunction = new CostFunction();
		 * UniformCostSearch strategy = new UniformCostSearch(costFunction);
		 * 
		 * A Star Search: IStepCostFunction cost = new CostFunction();
		 * IEstimatedCostFunction heuristic = new Heuristic(); AStarSearch strategy =
		 * new AStarSearch(cost, heuristic);
		 * 
		 * Greedy Search: IEstimatedCostFunction heuristic = new Heuristic();
		 * GreedySearch strategy = new GreedySearch(heuristic);
		 */

		// Create a Search object with the strategy
		Search searchSolver = new Search(strategy);

		/*
		 * Generate an XML file with the search tree. It can also be generated in other
		 * formats like PDF with PDF_TREE
		 */
		searchSolver.setVisibleTree(Search.EFAIA_TREE);
		
		// Set the Search searchSolver.
		this.setSolver(searchSolver);
		
		// Ask the solver for the best action
		Action selectedAction = null;

		
		System.out.println("Impresion antes del try");
		try {
			System.out.println("Impresion dentro del try");
			selectedAction = this.getSolver().solve(new Object[] { this.getProblem() });
			System.out.println("Impresion despues del solve");
		} catch (Exception ex) {
			System.out.println("Impresion en el catch");
			Logger.getLogger(Planta.class.getName()).log(Level.SEVERE, null, ex);
		}

		System.out.println("Impresion despues de todo");
		// Return the selected action
		return selectedAction;
	}

	/**
	 * This method is executed by the simulator to give the agent a perception. Then
	 * it updates its state.
	 * 
	 * @param p
	 */
	@Override
	public void see(Perception p) {
		this.getAgentState().updateState(p);
	}
}
