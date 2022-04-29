package frsf.ia.grupo1;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.pacman.PacmanAgentState;
import frsf.cidisi.faia.examples.search.pacman.PacmanPerception;

/**
 * Represent the internal state of the Plant.
 */
public class PlantaState extends SearchBasedAgentState {

	private String[][] tablero;
	int posicionPlantaFila;
	int posicionPlantaColumna;
	private int energia;
	private int totalZombies;
	int zombiesEnTablero;
	Double cantidadAccionesRealizadas = (double) 0;

	public PlantaState(int energiaPlanta, int  totalZombies, int zombiesTablero) {
		tablero = new String[5][9];
		
		this.setPosicionPlantaFila(2);
		this.setPosicionPlantaColumna(0);

		this.setEnergia(energiaPlanta);
		this.setTotalZombies(totalZombies);
		this.setZombiesEnTablero(zombiesTablero);
		this.initState();
	}

	public PlantaState(String[][] t, int row, int col, int e, int z, int zt) {
		tablero = t;
		posicionPlantaFila = row;
		posicionPlantaColumna = col;
		energia = e;
		totalZombies = z;
		zombiesEnTablero = zt;
	}

	/**
	 * This method is optional, and sets the initial state of the agent.
	 */
	@Override
	public void initState() {
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 9; col++) {
					tablero[row][col] = PlantaPerception.UNKNOWN_PERCEPTION;
			}
		}

	}

	
	/**
	 * This method clones the state of the agent. It's used in the search process,
	 * when creating the search tree.
	 */
	@Override
	public SearchBasedAgentState clone() {
		String[][] newWorld = new String[5][9];

		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 9; col++) {
				newWorld[row][col] = tablero[row][col];
			}
		}

		PlantaState newState = new PlantaState(newWorld, this.getPosicionPlantaFila(), this.getPosicionPlantaColumna(),
				this.getEnergia(), this.getTotalZombies(), this.getZombiesEnTablero());

		System.out.println(newState.toString());
		
		return newState;
	}

	/**
	 * This method is used to update the Planta State when a Perception is received
	 * by the Simulator.
	 */
	@Override
	public void updateState(Perception p) {
		
		PlantaPerception plantaPerception = (PlantaPerception) p;
	
		int row = this.getPosicionPlantaFila();
		int col = this.getPosicionPlantaColumna();

		String[] columnSensor = plantaPerception.getColumnSensor();
		String[] rowSensor = plantaPerception.getRowSensor();
		
		for (int i=0; i < columnSensor.length; i++) {
			tablero[i][col] = columnSensor[i];
		}	
		
		for (int i=0; i < rowSensor.length; i++) {
			tablero[row][i] = rowSensor[i];
		}

		energia = plantaPerception.getEnergy();
//        totalZombies = plantaPerceptio // TODO check if we send this as a perception

	}


	/**
	 * This method returns the String representation of the agent state.
	 */
	@Override
	public String toString() {
		String str = "";

		str = str + " position=\"(" + this.getPosicionPlantaFila() + "," + "" + this.getPosicionPlantaColumna() + ")\"";
		str = str + " energy=\"" + energia + "\"\n";
		str = str + " totalZombies=\"" + totalZombies + "\"\n";
		str = str + " ZombiesTablero=\"" + zombiesEnTablero + "\"\n";
		str = str + "world=\"[ \n";
		for (int row = 0; row <5; row++) {
			str = str + "[ ";
			for (int col = 0; col < 9; col++) {
				if (tablero[row][col] == "x") {
					str = str + "* ";
				} else {
					str = str + tablero[row][col] + " ";
				}
			}
			str = str + " ]\n";
		}
		str = str + " ]\"";

		return str;
	}

	/**
	 * This method is used in the search process to verify if the node already
	 * exists in the actual search.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlantaState))
			return false;

		String[][] worldObj = ((PlantaState) obj).getTablero();
		int posicionPlantaFilaObj = ((PlantaState) obj).getPosicionPlantaFila();
		int posicionPlantaColumnaObj = ((PlantaState) obj).getPosicionPlantaColumna();

		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 9; col++) {
				if (tablero[row][col] != worldObj[row][col]) {
					return false;
				}
			}
		}

		if (posicionPlantaFilaObj != posicionPlantaFila || posicionPlantaColumnaObj != posicionPlantaColumna) {
			return false;
		}

		return true;
	}

	public String[][] getTablero() {
		return tablero;
	}

	public void setTablero(String[][] tablero) {
		this.tablero = tablero;
	}

	public void setTableroEnPosicion(int row, int col, String value) {
		this.tablero[row][col] = value;
	}
	
	public String getTableroEnPosicion(int row, int col) {
		return tablero[row][col];
	}

	public int getPosicionPlantaFila() {
		return posicionPlantaFila;
	}

	public void setPosicionPlantaFila(int posicionPlantaFila) {
		this.posicionPlantaFila = posicionPlantaFila;
	}

	public int getPosicionPlantaColumna() {
		return posicionPlantaColumna;
	}

	public void setPosicionPlantaColumna(int posicionPlantaColumna) {
		this.posicionPlantaColumna = posicionPlantaColumna;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public int getTotalZombies() {
		return totalZombies;
	}

	public void setTotalZombies(int totalZombies) {
		this.totalZombies = totalZombies;
	}
	
	public int getTipoZombie(String tipoZombieString) {
		int tipoZombie = 0;

		switch (tipoZombieString) {
		case "z1":
			tipoZombie = 1;
			break;
		case "z2":
			tipoZombie = 2;
			break;
		case "z3":
			tipoZombie = 3;
			break;
		case "z4":
			tipoZombie = 4;
			break;
		case "z5":
			tipoZombie = 5;
			break;
		}

		return tipoZombie;
	}

	public Double getCantidadAccionesRealizadas() {
		return cantidadAccionesRealizadas;
	}

	public void setCantidadAccionesRealizadas(Double cantidadAccionesRealizadas) {
		this.cantidadAccionesRealizadas += cantidadAccionesRealizadas;
	}

	public int getZombiesEnTablero() {
		return zombiesEnTablero;
	}

	public void setZombiesEnTablero(int zombiesEnTablero) {
		this.zombiesEnTablero = zombiesEnTablero;
	}


	
	
	
}
