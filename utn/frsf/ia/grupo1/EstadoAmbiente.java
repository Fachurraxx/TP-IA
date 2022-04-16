package frsf.ia.grupo1;

import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState {

     String[][] tablero;
     int posicionPlantaFila;
     int posicionPlantaColumna;
     int energiaPlanta;
     int totalZombies;

    public EstadoAmbiente(String[][] m) {
        tablero = m;
    }

    public EstadoAmbiente() {
        tablero = new String[5][9];
        this.initState();
    }


    @Override
    public void initState() {

        // Sets all cells as empty
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 9; col++) {
                tablero[row][col] = PlantaPerception.UNKNOWN_PERCEPTION;
            }
        }
                
        for (int col = 0; col < 9; col++) {
            tablero[2][col] = PlantaPerception.EMPTY_PERCEPTION;
        }
        
        for (int row = 0; row < 5; row++) {
                tablero[row][1] = PlantaPerception.EMPTY_PERCEPTION;
        }                     
        
//      Los zombies siempre inician en la celda mas alejada de la casa, y en el inicio del
//      juego puede que no estén todos los zombies en el escenario, sino que se van
//      sumando a medida que transcurre el tiempo
//      Numero de zombies que aparecen al principio es random, no puede haber mas de 5 porque como los
//        zombies aparecen siempre lo mas alejado tiene que ser en la ultima columna.
//        TODO a menos que sea en la celda LIBRE  mas alejada de la casa? pero para mi esto nos complica
        
        int zombiesInicio = getRandomNumber(1,5);
        agregarZombiesAlTablero(zombiesInicio);
        
        this.setTotalZombies(getRandomNumber(5, 20));
        this.setPosicionPlantaFila(2);
        this.setPosicionPlantaColumna(1);
        
        tablero[2][1] = PlantaPerception.PLANTA_PERCEPTION;
        
//        En el inicio la planta recibe una cantidad aleatoria de entre 2 y 20 soles. 
        
        this.setEnergiaPlanta(getRandomNumber(2, 20));
    }
    
    
    

    public void agregarZombiesAlTablero(int zombiesInicio) {
		
    	
    	for(int i=0; i<zombiesInicio; i++){
    		int tipoDeZombie = getRandomNumber(1, 5);
    		boolean filaVacia = false;
    		int filaPorDondeCamina = getRandomNumber(0, 4);
    		while(!filaVacia) {	
    			if(!tablero[filaPorDondeCamina][8].contains("z")) filaVacia=true; 
    			else filaPorDondeCamina = getRandomNumber(0, 4);
    		}
    		switch(tipoDeZombie) {
        	case 1:
        		tablero[filaPorDondeCamina][8] = PlantaPerception.Z1_PERCEPTION;
        		break;
        	case 2:
        		tablero[filaPorDondeCamina][8] = PlantaPerception.Z2_PERCEPTION;
        		break;
        	case 3:
        		tablero[filaPorDondeCamina][8] = PlantaPerception.Z3_PERCEPTION;
        		break;
        	case 4:
        		tablero[filaPorDondeCamina][8] = PlantaPerception.Z4_PERCEPTION;
        		break;
        	case 5:
        		tablero[filaPorDondeCamina][8] = PlantaPerception.Z5_PERCEPTION;
        		break;
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
    	//Check where we have a girasol and generate soles with random function from 1 till 3
    	
    	for (int row = 0; row < tablero.length; row++) {
            for (int col = 0; col < tablero.length; col++) {
            	//basically if we dont have a zombie, the plant or empty then we have a girasol(integer number)

            	
            	if(tablero[row][col] != "e" && tablero[row][col] != "p" && !tablero[row][col].contains("z") && tablero[row][col] != "x") {
            	
            		int numeroDeSoles = Integer.parseInt(tablero[row][col]);
            		int nuevosSoles = getRandomNumber(1,3);
        		
            		tablero[row][col]=Integer.toString(numeroDeSoles + nuevosSoles);
            		
            	}
            }
        }
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
        return energiaPlanta;
    }

    public void setTotalZombies(int totalZombies) {
        this.totalZombies = totalZombies;
    }
    
    public String [] getRow(int row) {
    	String [] aux = new String[9];
    	for(int i=0;i<9;i++) {
    		aux[i]=tablero[row][i];
    	}
    	return aux;
	}
    
    public String [] getColumn(int col) {
    	String [] aux = new String[5];
    	for(int i=0;i<5;i++) {
    		aux[i]=tablero[i][col];
    	}
    	return aux;
	}
    
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
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
        str = str + " ";

        return str;
    }

    public void setTableroEnPosicion(int row, int col, String value) {
        this.tablero[row][col] = value;
    }

}
