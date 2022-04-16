package frsf.ia.grupo1;

import frsf.cidisi.faia.examples.search.pacman.PacmanAgent;
import frsf.cidisi.faia.examples.search.pacman.PacmanAgentState;
import frsf.cidisi.faia.examples.search.pacman.PacmanGoal;
import frsf.cidisi.faia.examples.search.pacman.actions.Eat;
import frsf.cidisi.faia.examples.search.pacman.actions.GoDown;
import frsf.cidisi.faia.examples.search.pacman.actions.GoLeft;
import frsf.cidisi.faia.examples.search.pacman.actions.Fight;
import frsf.cidisi.faia.examples.search.pacman.actions.GoUp;
import frsf.cidisi.faia.examples.search.pacman.actions.GoRight;
import frsf.cidisi.faia.agent.Perception;
import java.util.Vector;

import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.solver.search.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Planta extends SearchBasedAgent {

    public Planta() {

        // The Pacman Goal
        PacmanGoal goal = new PacmanGoal();

        // The Pacman Agent State
        PacmanAgentState pacmanState = new PacmanAgentState();
        this.setAgentState(pacmanState);

        // Create the operators
        Vector<SearchAction> operators = new Vector<SearchAction>();
        operators.addElement(new Eat());
        operators.addElement(new Fight());
        operators.addElement(new GoLeft());
        operators.addElement(new GoUp());
        operators.addElement(new GoRight());
        operators.addElement(new GoDown());

        // Create the Problem which the Pacman will resolve
        Problem problem = new Problem(goal, pacmanState, operators);
        this.setProblem(problem);
    }

    /**
     * This method is executed by the simulator to ask the agent for an action.
     */
    @Override
    public Action selectAction() {

        // Create the search strategy
        DepthFirstSearch strategy = new DepthFirstSearch();

        /**
         * Another search strategy examples:
         * 
         * Depth First Search:
         * DepthFirstSearch strategy = new DepthFirstSearch();
         * 
         * Breath First Search:
         * BreathFirstSearch strategy = new BreathFirstSearch();
         * 
         * Uniform Cost:
         * IStepCostFunction costFunction = new CostFunction();
         * UniformCostSearch strategy = new UniformCostSearch(costFunction);
         * 
         * A Star Search:
         * IStepCostFunction cost = new CostFunction();
         * IEstimatedCostFunction heuristic = new Heuristic();
         * AStarSearch strategy = new AStarSearch(cost, heuristic);
         * 
         * Greedy Search:
         * IEstimatedCostFunction heuristic = new Heuristic();
         * GreedySearch strategy = new GreedySearch(heuristic);
         */

        // Create a Search object with the strategy
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        searchSolver.setVisibleTree(Search.EFAIA_TREE);

        // Set the Search searchSolver.
        this.setSolver(searchSolver);

        // Ask the solver for the best action
        Action selectedAction = null;
        try {
            selectedAction =
                    this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(PacmanAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;
    }

    /**
     * This method is executed by the simulator to give the agent a perception.
     * Then it updates its state.
     * @param p
     */
    @Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);
    }
}
