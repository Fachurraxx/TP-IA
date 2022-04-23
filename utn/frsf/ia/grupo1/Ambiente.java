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

		perception.initPerception(null, this);
		
		this.getEnvironmentState().setSoles();
		this.getEnvironmentState().updateZombies();		
		
		String[] sensorFila = this.getRow(this.getEnvironmentState().getPosicionPlantaFila());
		String[] sensorColumna = this.getColumn(this.getEnvironmentState().getPosicionPlantaColumna());
		
		int plantaCol = this.getEnvironmentState().getPosicionPlantaColumna();
		int plantaFila = this.getEnvironmentState().getPosicionPlantaFila();
		
		boolean obsDerecha = false;
		boolean obsIzquierda = false;
		boolean obsArriba = false;
		boolean obsAbajo = false;
		
		for(int i = plantaCol+1; i<=8; i++) {			
			if(obsDerecha) 	sensorFila[i] = PlantaPerception.UNKNOWN_PERCEPTION;
			if(sensorFila[i] != "e") obsDerecha = true;
		}
		
		for(int i = plantaCol-1; i>=0; i--) {	
			if(obsIzquierda) sensorFila[i] = PlantaPerception.UNKNOWN_PERCEPTION;
			if(sensorFila[i] != "e") obsIzquierda = true;
		}
		
		for(int i = plantaFila+1; i<=4; i++) {
			if(obsAbajo) 	sensorColumna[i] = PlantaPerception.UNKNOWN_PERCEPTION;
			if(sensorColumna[i] != "e") obsAbajo = true;
		}
		
		for(int i = plantaFila-1; i>=0; i--) {	
			if(obsArriba) sensorColumna[i] = PlantaPerception.UNKNOWN_PERCEPTION;
			if(sensorColumna[i] != "e") obsArriba = true;
		}
		
		perception.setRowSensor(sensorFila);
		perception.setColumnSensor(sensorColumna);
		
		

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
