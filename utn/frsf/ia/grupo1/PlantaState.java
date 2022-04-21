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

	public PlantaState() {
		tablero = new String[5][9];
		posicionPlantaFila = 2;
		posicionPlantaColumna = 0;
		energia = 0;// TODO check this energy
		this.initState();
	}

	public PlantaState(String[][] t, int row, int col, int e, int z) {
		tablero = t;
		posicionPlantaFila = row;
		posicionPlantaColumna = col;
		energia = e;
		totalZombies = z;
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
				this.energia, this.totalZombies);

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
		for (int i=0; i < columnSensor.length; i++) {
			tablero[row][i] = columnSensor[i];
		}

		String[] rowSensor = plantaPerception.getRowSensor();
		for (int i=0; i < rowSensor.length; i++) {
			tablero[i][col] = columnSensor[i];
		}

		energia = plantaPerception.getEnergy();
//        totalZombies = plantaPerceptio // TODO check if we send this as a perception

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

		this.setPosicionPlantaFila(2);
		this.setPosicionPlantaColumna(0);

		this.setEnergia(0);
		this.setTotalZombies(0);

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

//
//	    public PacmanAgentState() {
//	        world = new int[4][4];
//	        position = new int[2];
//	        energy = 0;
//	        this.initState();
//	    }

	/**
	 * This method clones the state of the agent. It's used in the search process,
	 * when creating the search tree.
	 */
//	    @Override
//	    public SearchBasedAgentState clone() {
//	        int[][] newWorld = new int[4][4];
//
//	        for (int row = 0; row < world.length; row++) {
//	            for (int col = 0; col < world.length; col++) {
//	                newWorld[row][col] = world[row][col];
//	            }
//	        }
//
//	        int[] newPosition = new int[2];
//	        newPosition[0] = position[0];
//	        newPosition[1] = position[1];
//
//	        PacmanAgentState newState = new PacmanAgentState(newWorld,
//	                this.getRowPosition(), this.getColumnPosition(), this.energy);
//
//	        return newState;
//	    }

	/**
	 * This method is used to update the Pacman State when a Perception is received
	 * by the Simulator.
	 */
//	    @Override
//	    public void updateState(Perception p) {
//	        PacmanPerception pacmanPerception = (PacmanPerception) p;
//
//	        int row = this.getRowPosition();
//	        int col = this.getColumnPosition();
//
//	        if (col == 0) {
//	            col = 3;
//	        } else {
//	            col = col - 1;
//	        }
//	        world[row][col] = pacmanPerception.getLeftSensor();
//
//	        row = this.getRowPosition();
//	        col = this.getColumnPosition();
//
//	        if (col == 3) {
//	            col = 0;
//	        } else {
//	            col = col + 1;
//	        }
//	        world[row][col] = pacmanPerception.getRightSensor();
//
//	        row = this.getRowPosition();
//	        col = this.getColumnPosition();
//
//	        if (row == 0) {
//	            row = 3;
//	        } else {
//	            row = row - 1;
//	        }
//	        world[row][col] = pacmanPerception.getTopSensor();
//
//
//	        row = this.getRowPosition();
//	        col = this.getColumnPosition();
//
//	        if (row == 3) {
//	            row = 0;
//	        } else {
//	            row = row + 1;
//	        }
//	        world[row][col] = pacmanPerception.getBottomSensor();
//
//	        energy = pacmanPerception.getEnergy();
//	    }
//
//	    /**
//	     * This method is optional, and sets the initial state of the agent.
//	     */
//	    @Override
//	    public void initState() {
//	        for (int row = 0; row < world.length; row++) {
//	            for (int col = 0; col < world.length; col++) {
//	                world[row][col] = PacmanPerception.UNKNOWN_PERCEPTION;
//	            }
//	        }
//	        
//	        this.setRowPosition(1);
//	        this.setColumnPosition(1);
//
//	        this.setEnergy(50);
//	    }
//
//	    /**
//	     * This method returns the String representation of the agent state.
//	     */
//	    @Override
//	    public String toString() {
//	        String str = "";
//
//	        str = str + " position=\"(" + getRowPosition() + "," + "" + getColumnPosition() + ")\"";
//	        str = str + " energy=\"" + energy + "\"\n";
//
//	        str = str + "world=\"[ \n";
//	        for (int row = 0; row < world.length; row++) {
//	            str = str + "[ ";
//	            for (int col = 0; col < world.length; col++) {
//	                if (world[row][col] == -1) {
//	                    str = str + "* ";
//	                } else {
//	                    str = str + world[row][col] + " ";
//	                }
//	            }
//	            str = str + " ]\n";
//	        }
//	        str = str + " ]\"";
//
//	        return str;
//	    }
//
//	    /**
//	     * This method is used in the search process to verify if the node already
//	     * exists in the actual search.
//	     */
//	    @Override
//	    public boolean equals(Object obj) {
//	        if (!(obj instanceof PacmanAgentState))
//	            return false;
//
//	        int[][] worldObj = ((PacmanAgentState) obj).getWorld();
//	        int[] positionObj = ((PacmanAgentState) obj).getPosition();
//
//	        for (int row = 0; row < world.length; row++) {
//	            for (int col = 0; col < world.length; col++) {
//	                if (world[row][col] != worldObj[row][col]) {
//	                    return false;
//	                }
//	            }
//	        }
//
//	        if (position[0] != positionObj[0] || position[1] != positionObj[1]) {
//	            return false;
//	        }
//	        
//	        return true;
//	    }

	// The following methods are Pacman-specific:

	public String[][] getTablero() {
		return tablero;
	}

	public void setTablero(String[][] tablero) {
		this.tablero = tablero;
	}

	public void setTableroEnPosicion(int row, int col, String value) {
		this.tablero[row][col] = value;
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

//	    public boolean isAllWorldKnown() {
//	        for (int row = 0; row < world.length; row++) {
//	            for (int col = 0; col < world.length; col++) {
//	                if (world[row][col] == PacmanPerception.UNKNOWN_PERCEPTION) {
//	                    return false;
//	                }
//	            }
//	        }
//	        
//	        return true;
//	    }
//
//	    public int getUnknownCellsCount() {
//	        int result = 0;
//
//	        for (int row = 0; row < world.length; row++) {
//	            for (int col = 0; col < world.length; col++) {
//	                if (world[row][col] == PacmanPerception.UNKNOWN_PERCEPTION) {
//	                    result++;
//	                }
//	            }
//	        }
//
//	        return result;
//	    }
//
//	    public int getRemainingFoodCount() {
//	        int result = 0;
//
//	        for (int row = 0; row < world.length; row++) {
//	            for (int col = 0; col < world.length; col++) {
//	                if (world[row][col] == PacmanPerception.FOOD_PERCEPTION) {
//	                    result++;
//	                }
//	            }
//	        }
//	        
//	        return result;
//	    }
//
//	    public boolean isNoMoreFood() {
//	        for (int row = 0; row < world.length; row++) {
//	            for (int col = 0; col < world.length; col++) {
//	                if (world[row][col] == PacmanPerception.FOOD_PERCEPTION) {
//	                    return false;
//	                }
//	            }
//	        }
//	        return true;
//	    }
//
//	    public int getVisitedCellsCount() {
//	        return visitedCells;
//	    }
//
//	    public void increaseVisitedCellsCount() {
//	        this.visitedCells = +20;
//	    }
}
