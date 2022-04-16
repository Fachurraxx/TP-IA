package frsf.ia.grupo1;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.examples.search.pacman.PacmanEnvironmentState;
import frsf.cidisi.faia.examples.search.pacman.PacmanPerception;

public class Ambiente extends Environment {

    public Ambiente() {
        // Create the environment state
        this.environmentState = new EstadoAmbiente();
    }

    @Override
    public EstadoAmbiente getEnvironmentState() {
        return (EstadoAmbiente) super.getEnvironmentState();
    }


    @Override
    public Perception getPercept() {
        // Create a new perception to return
    	PlantaPerception perception = new PlantaPerception();
        
        // Get the actual position of the agent to be able to create the
        // perception

        int row = this.getEnvironmentState().getPosicionPlanta()[0];
        int col = this.getEnvironmentState().getPosicionPlanta()[1];

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

        EstadoAmbiente estadoAmbiente =
                this.getEnvironmentState();

        int agentEnergy = estadoAmbiente.getEnergiaPlanta();

        // If the agent has no energy, he failed
        if (agentEnergy < 1)
            return true;

        return false;
    }

    
    public String [] getRow(int row) {
		return ((EstadoAmbiente) this.environmentState)
                .getRow(row);
	}
    
    public String [] getColumn(int col) {
		return ((EstadoAmbiente) this.environmentState)
                .getColumn(col);
	}
}
