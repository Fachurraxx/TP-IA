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

		PlantaPerception perception = new PlantaPerception();

		perception.setRowSensor(this.getRow(this.getEnvironmentState().getPosicionPlantaFila()));
		perception.setColumnSensor(this.getColumn(this.getEnvironmentState().getPosicionPlantaColumna()));

		return perception;
	}

	@Override
	public String toString() {
		return environmentState.toString();
	}

	@Override
	public boolean agentFailed(Action actionReturned) {

		EstadoAmbiente estadoAmbiente = this.getEnvironmentState();

		int agentEnergy = estadoAmbiente.getEnergiaPlanta();

		boolean zombieEnCasa = estadoAmbiente.getZombieEnCasa();

		// If the agent has no energy, he failed
		if (agentEnergy < 1 || zombieEnCasa)
			return true;

		return false;
	}

	public String[] getRow(int row) {
		return ((EstadoAmbiente) this.environmentState).getRow(row);
	}

	public String[] getColumn(int col) {
		return ((EstadoAmbiente) this.environmentState).getColumn(col);
	}
}
