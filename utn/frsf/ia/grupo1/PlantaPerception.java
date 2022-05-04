package frsf.ia.grupo1;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;


public class PlantaPerception extends Perception {
//	matrizPosiciones se representa como una matriz de 5x9. Los zombies se representar�n como z1, z2, z3, z4, z5 siendo el sub�ndice la cantidad de soles que necesita la planta para matar a ese zombie.
//	Los girasoles se representar�n con la cantidad de soles que est�n presentes en su celda (0 a 99) donde 0 se refiere a un girasol sin ning�n sol producido. La celda donde se encuentra la planta se representa como p. 
//	La letra x la utilizaremos para representar una celda la cual no tenemos informaci�n y la letra e para representar una celda vac�a. 

	public static String Z1_PERCEPTION = "z1";
	public static String Z2_PERCEPTION = "z2";
	public static String Z3_PERCEPTION = "z3";
	public static String Z4_PERCEPTION = "z4";
	public static String Z5_PERCEPTION = "z5";
	public static String GIRASOLES_PERCEPTION = "0";
	public static String UNKNOWN_PERCEPTION = "x";
	public static String EMPTY_PERCEPTION = "e";
	public static String PLANTA_PERCEPTION = "p";

	private String[] rowSensor;
	private String[] columnSensor;
	private int energy;
	private int zombiesEnTablero;

	public PlantaPerception() {

	}

	public PlantaPerception(Agent agent, Environment environment) {
		super(agent, environment);
	}

	public void setRowSensor(String[] rowSensor) {
		this.rowSensor = rowSensor;
	}

	public String[] getRowSensor() {
		return rowSensor;
	}

	public void setColumnSensor(String[] columnSensor) {
		this.columnSensor = columnSensor;
	}

	public String[] getColumnSensor() {
		return columnSensor;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	public int getZombiesEnTablero() {
		return zombiesEnTablero;
	}

	public void setZombiesEnTablero(int zombiesEnTablero) {
		this.zombiesEnTablero = zombiesEnTablero;
	}

	@Override
	public String toString() {
		String str = "";
		str = str + " \n";
		str = str + "Fila: [ ";

		for (int col = 0; col < 9; col++) {
			str = str + this.getRowSensor()[col] + " ";
		}
		str = str + " ]\n";
		str = str + "Columna: [\n";

		for (int row = 0; row < 5; row++) {
			str = str + "          " + this.getColumnSensor()[row] + " \n";
		}
		str = str + "         ]\n";

		return str;
	}

	@Override
	public void initPerception(Agent agent, Environment environment) {
		// TODO Auto-generated method stub
		
	}

}
