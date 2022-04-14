package frsf.ia.grupo1;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.examples.search.pacman.PacmanEnvironmentState;
import frsf.cidisi.faia.examples.search.pacman.PacmanPerception;

public class AmbienteEnvironment extends Environment {

    public AmbienteEnvironment() {
        // Create the environment state
        this.environmentState = new AmbienteEnvironmentState();
    }

    @Override
    public AmbienteEnvironmentState getEnvironmentState() {
        return (AmbienteEnvironmentState) super.getEnvironmentState();
    }

    /**
     * This method is called by the simulator. Given the Agent, it creates
     * a new perception reading, for example, the agent position.
     * @param agent
     * @return A perception that will be given to the agent by the simulator.
     */
    @Override
    public Perception getPercept() {
        // Create a new perception to return
    	PlantaPerception perception = new PlantaPerception();
        
        // Get the actual position of the agent to be able to create the
        // perception
        int row = this.getEnvironmentState().getAgentPosition()[0];
        int col = this.getEnvironmentState().getAgentPosition()[1];

        // Set the perception sensors
        perception.setRowSensor(this.getRow(row));
        perception.setColumnSensor(this.getColumn(col));

        // Return the perception
        return perception;
    }

    

	@Override
    public String toString() {
        return environmentState.toString();
    }

    @Override
    public boolean agentFailed(Action actionReturned) {

        AmbienteEnvironmentState ambienteEnvironmentState =
                this.getEnvironmentState();

        int agentEnergy = ambienteEnvironmentState.getAgentEnergy();

        // If the agent has no energy, he failed
        if (agentEnergy < 1)
            return true;

        return false;
    }

    
    public String [][] getRow(int row) {
		return ((AmbienteEnvironmentState) this.environmentState)
                .getRow(row);
	}
    
    public String [][] getColumn(int col) {
		return ((AmbienteEnvironmentState) this.environmentState)
                .getColumn(col);
	}
}
