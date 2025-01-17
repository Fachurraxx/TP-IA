package frsf.ia.grupo1;

import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState {

	String[][] tablero;
	int posicionPlantaFila;
	int posicionPlantaColumna;
	int energiaPlanta;
	int totalZombies;
	int missingZombies;
	int[][] posicionZombies;
	boolean zombieEnCasa;

	public EstadoAmbiente(String[][] m) {
		tablero = m;
	}

	public EstadoAmbiente() {
		tablero = new String[5][9]; 
		posicionZombies = new int[5][9];
		this.initState();
		System.out.println("EstadoAmbiente() ");
		System.out.println("Energia: " + energiaPlanta);
		System.out.println("Cant zombies: " + totalZombies);

		System.out.println(this.toString());
		System.out.println("---------------------------");
	}

	@Override
	public void initState() {

		// Sets all cells as empty
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 9; col++) {
				tablero[row][col] = PlantaPerception.EMPTY_PERCEPTION;
			}
		}

//		setTableroEnPosicion(2,0, PlantaPerception.PLANTA_PERCEPTION);
//		this.setPosicionPlantaFila(2);
//		this.setPosicionPlantaColumna(0);

		//Matriz Zombies
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 9; col++) {
				posicionZombies[row][col] = -1;
			}
		}

		int totalZombies = getRandomNumber(5, 20);
		this.setTotalZombies(totalZombies);

		int zombiesInicio = getRandomNumber(1, 5);
		this.agregarZombiesAlTablero(zombiesInicio);

		int missingZombies = totalZombies - zombiesInicio;
		this.setMissingZombies(missingZombies);

		this.setEnergiaPlanta(getRandomNumber(2, 20));

		this.setZombieEnCasa(false);
		
		
//		TEST
		//Set some girasoles
		setTableroEnPosicion(1,0, PlantaPerception.GIRASOLES_PERCEPTION);
		setTableroEnPosicion(3,0, PlantaPerception.GIRASOLES_PERCEPTION);
		setTableroEnPosicion(4,0, PlantaPerception.GIRASOLES_PERCEPTION);
		
		//set energia planta
		this.setEnergiaPlanta(2);
		
		setTableroEnPosicion(2,6, PlantaPerception.PLANTA_PERCEPTION);
		this.setPosicionPlantaFila(2);
		this.setPosicionPlantaColumna(6);


	}

	public void agregarZombiesAlTablero(int zombiesInicio) {

		for (int i = 0; i < zombiesInicio; i++) {
			int tipoDeZombie = getRandomNumber(1, 5);
			boolean filaVacia = false;
			int filaPorDondeCamina = getRandomNumber(0, 4);
			while (!filaVacia) {
				if (!tablero[filaPorDondeCamina][8].contains("z"))
					filaVacia = true;
				else
					filaPorDondeCamina = getRandomNumber(0, 4);
			}
//			Check if the plant is in that position.
//			If its then we just rest the energy to the plant 
//				if the energy is enough to kill it then we dont display it on the environment 
//				if the energy is not enough then we display 'p z4' for example to show the plant died in that position
//			If the plant is not on that position then we just add the zombie
			if(posicionPlantaColumna==8 && posicionPlantaFila==filaPorDondeCamina) {
				int newEnergiaPlanta = energiaPlanta - tipoDeZombie * 2;
				this.setEnergiaPlanta(newEnergiaPlanta);
				if(newEnergiaPlanta < 1) {
					setTableroEnPosicion(filaPorDondeCamina, 8, "p-z".concat(String.valueOf(tipoDeZombie)));
				}
			}
			else {
				switch (tipoDeZombie) {
				case 1:
					tablero[filaPorDondeCamina][8] = PlantaPerception.Z1_PERCEPTION;
					posicionZombies[filaPorDondeCamina][8] = 0;
					break;
				case 2:
					tablero[filaPorDondeCamina][8] = PlantaPerception.Z2_PERCEPTION;
					posicionZombies[filaPorDondeCamina][8] = 0;
					break;
				case 3:
					tablero[filaPorDondeCamina][8] = PlantaPerception.Z3_PERCEPTION;
					posicionZombies[filaPorDondeCamina][8] = 0;
					break;
				case 4:
					tablero[filaPorDondeCamina][8] = PlantaPerception.Z4_PERCEPTION;
					posicionZombies[filaPorDondeCamina][8] = 0;
					break;
				case 5:
					tablero[filaPorDondeCamina][8] = PlantaPerception.Z5_PERCEPTION;
					posicionZombies[filaPorDondeCamina][8] = 0;
					break;
				}
			}
			

		}

	}

	public String[][] getTablero() {
		return tablero;
	}

	public void setTablero(String[][] tablero) {
		this.tablero = tablero;
	}

	public void setTablero(int row, int col, String value) {
		this.tablero[row][col] = value;
	}

	public void setSoles() {
		// Check where we have a girasol and generate soles with random function from 1 till 3

		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 9; col++) {
				// basically if we dont have a zombie, the plant or empty then we have a
				// girasol(integer number)

				if (tablero[row][col] != "e" && tablero[row][col] != "p" && !tablero[row][col].contains("z")
						&& tablero[row][col] != "x") {

					int numeroDeSoles = Integer.parseInt(tablero[row][col]);
					int nuevosSoles = getRandomNumber(1, 3);

					tablero[row][col] = Integer.toString(numeroDeSoles + nuevosSoles);

				}
			}
		}
	}

	public void updateZombies() {
		// update zombies positions
		this.updatePosicionZombies();

		// add new zombies
		int numberZombiesLastColumn = getNumberOfZombiesLastColumn();
		int maxZombiesToAdd = 5 - numberZombiesLastColumn;

		if (maxZombiesToAdd > 0) {
			if (missingZombies < maxZombiesToAdd) {
				maxZombiesToAdd = missingZombies;
			}
			int zombiesToAdd = getRandomNumber(0, maxZombiesToAdd);
			this.agregarZombiesAlTablero(zombiesToAdd);

			this.setMissingZombies(missingZombies - zombiesToAdd);

		}
	}

	private void updatePosicionZombies() {

		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 9; col++) {
				if (tablero[row][col].contains("z")) {
					posicionZombies[row][col]++;

					boolean move = false;

					// si todavia no pasaron 3 ciclos puedo randomly choose to move or not
					if (posicionZombies[row][col] <= 2) {
						//TODO check if the previous is empty or nor. If its not empty then we ignore.
						//TODO chequear que no haya zombies adelante
						boolean celdaVacia = true;
						if(col!=0) {
							celdaVacia = previousZombiePositionEmpty(row, col);
						}
						if (moveRandomlly() && celdaVacia) {
							move = true;
						}
					} else {
						// Me tengo que mover porque pasaron 3 ciclos
						move = true;
					}

					if (move) {
						moveZombie(row, col);
					}
				}

			}
		}
	}


	private void moveZombie(int row, int col) {

		if (col != 0) {
			// check if the previous spot if empty if not move all one position
//			boolean previousPositionEmpty = previousZombiePositionEmpty(row, col);
//
//			if (!previousPositionEmpty) {
//				moveZombie(row, col - 1);
//			}

			posicionZombies[row][col] = -1;
			posicionZombies[row][col - 1] = 0;

//        	Si un zombie llega a la posici�n de un girasol, el girasol muere y desaparecen
//        	todos los soles que ten�a ese girasol: Esto no es un probelma porque unicamente sobreescribimos
//        	lo que hay en la posicion por un zombie

//        	Si la planta se mueve a una celda en la cual hay un zombie, la planta perder� una
//        	cantidad de soles multiplicando por dos a los valores de la Tabla 1: como hacemos en el caso de que el 
//        	zombie se mueve a la posicion donde esta la planta? solo le restamos energia a la planta y si tiene suficiente lo mata
//			sino pierde

			String zombie = tablero[row][col];
			
			if (posicionPlantaFila == row && posicionPlantaColumna == col - 1) {
				// make plant fight with the zombie but for twice the energy
				int tipoZombie = getTipoZombie(zombie);
				int newEnergiaPlanta = energiaPlanta - tipoZombie * 2;
				this.setEnergiaPlanta(newEnergiaPlanta);
				if(newEnergiaPlanta < 1) {
					setTableroEnPosicion(row, col - 1, "p-".concat(zombie));
				}else {
					posicionZombies[row][col-1] = -1;
				}
				
			}
			else {
				setTableroEnPosicion(row, col - 1, zombie);
			}

			setTableroEnPosicion(row, col, PlantaPerception.EMPTY_PERCEPTION);
		} else {
			// perder juego
			this.setZombieEnCasa(true);
			setTableroEnPosicion(row, col, "H");
		}
	}
	
	private int getTipoZombie(String tipoZombieString) {
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

	private boolean previousZombiePositionEmpty(int row, int col) {
		boolean isEmpty = false;
		// check if col-1 is empty
		int previousColumn = posicionZombies[row][col - 1];

		if (previousColumn == -1 && !tablero[row][col - 1].contains("z")) {
			isEmpty = true;
		}

		return isEmpty;

	}

	private int getNumberOfZombiesLastColumn() {
		int aux = 0;

		for (int i = 0; i < 5; i++) {
			if (this.tablero[i][8].contains("z")) {
				aux++;
			}
		}
		return aux;
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

	public int getEnergiaPlanta() {
		return energiaPlanta;
	}

	public void setEnergiaPlanta(int energiaPlanta) {
		this.energiaPlanta = energiaPlanta;
	}

	public int getTotalZombies() {
		return totalZombies;
	}

	public void setTotalZombies(int totalZombies) {
		this.totalZombies = totalZombies;
	}

	public int getMissingZombies() {
		return missingZombies;
	}

	public void setMissingZombies(int missingZombies) {
		this.missingZombies = missingZombies;
	}

	public boolean getZombieEnCasa() {
		return zombieEnCasa;
	}

	public void setZombieEnCasa(boolean zombieEnCasa) {
		this.zombieEnCasa = zombieEnCasa;
	}

	public String[] getRow(int row) {
		String[] aux = new String[9];
		for (int i = 0; i < 9; i++) {
			aux[i] = tablero[row][i];
		}
		return aux;
	}

	public String[] getColumn(int col) {
		String[] aux = new String[5];
		for (int i = 0; i < 5; i++) {
			aux[i] = tablero[i][col];
		}
		return aux;
	}

	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max + 1 - min)) + min);
		
	}
	
	public boolean moveRandomlly() {
		return Math.random() < 0.5;
	}

	/**
	 * String representation of the real tablero state.
	 */
	@Override
	public String toString() {
		String str = "";

		str = str + " \n";
		for (int row = 0; row < 5; row++) {
			str = str + "[ ";
			for (int col = 0; col < 9; col++) {
				str = str + tablero[row][col] + " ";
			}
			str = str + " ]\n";
		}
		str = str + " \n";

		for (int row = 0; row < 5; row++) {
			str = str + "[ ";
			for (int col = 0; col < 9; col++) {
				str = str + posicionZombies[row][col] + " ";
			}
			str = str + " ]\n";
		}

		return str;
	}
}
