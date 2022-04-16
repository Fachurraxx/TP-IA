package frsf.ia.grupo1;

import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState {

    private String[][] tablero;
    private int[] posicionPlanta;
    private int energiaPlanta;
    private int totalZombies;

    public EstadoAmbiente(String[][] m) {
        tablero = m;
    }

    public EstadoAmbiente() {
        tablero = new String[5][9];
        this.initState();
    }

    /**
     * This method is used to setup the initial real tablero.
     */
    @Override
    public void initState() {

        // Sets all cells as empty
        for (int row = 0; row < tablero.length; row++) {
            for (int col = 0; col < tablero.length; col++) {
                tablero[row][col] = PlantaPerception.EMPTY_PERCEPTION;
            }
        }

        /* Sets some cells with enemies. */
//        Los zombies aparecen aleatoriamente en el escenario. La cantidad es aleatoria
//        entre 5 y 20 y se determina al inicio del juego. - 
        int numeroInicialDeZombies = getRandomNumber(5, 20);
        
//      Los zombies siempre inician en la celda mas alejada de la casa, y en el inicio del
//      juego puede que no estén todos los zombies en el escenario, sino que se van
//      sumando a medida que transcurre el tiempo
//      Numero de zombies que aparecen al principio es random, no puede haber mas de 5 porque como los
//        zombies aparecen siempre lo mas alejado tiene que ser en la ultima columna.
//        TODO a menos que sea en la celda LIBRE  mas alejada de la casa? pero para mi esto nos complica
        
        int zombiesInicio = getRandomNumber(1,5);
        
//      Set los primeros x zombies en la ultima columna empezando desde la row 0, el tipo de zombie que toca en casa posicion tambien es random
        
        for(int i=0;i<zombiesInicio;i++){
        	int tipoDeZombie = getRandomNumber(1, 5);
        	switch(tipoDeZombie) {
        	case 1:
        		tablero[i][8] = PlantaPerception.Z1_PERCEPTION;
        		break;
        	case 2:
        		tablero[i][8] = PlantaPerception.Z2_PERCEPTION;
        		break;
        	case 3:
        		tablero[i][8] = PlantaPerception.Z3_PERCEPTION;
        		break;
        	case 4:
        		tablero[i][8] = PlantaPerception.Z4_PERCEPTION;
        		break;
        	case 5:
        		tablero[i][8] = PlantaPerception.Z5_PERCEPTION;
        		break;
        	}
        }
        
        this.setTotalZombies(numeroInicialDeZombies);
        this.setPosicionPlanta(new int[]{2, 1});
//        En el inicio la planta recibe una cantidad aleatoria de entre 2 y 20 soles. 
        int energiaInicial = getRandomNumber(2, 20);
        this.setEnergiaPlanta(energiaInicial);
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
            	if(!tablero[row][col].contains("z") || !tablero[row][col].contains("x") || !tablero[row][col].contains("e")) {
            		int numeroDeSoles = Integer.parseInt(tablero[row][col]);
            		int nuevosSoles = getRandomNumber(1,3);
            		
            		tablero[row][col]=Integer.toString(numeroDeSoles + nuevosSoles);
            
            	}
            }
        }
    }

    public int[] getPosicionPlanta() {
        return posicionPlanta;
    }

    public void setPosicionPlanta(int[] posicionPlanta) {
        this.posicionPlanta = posicionPlanta;
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
    
    public String [][] getRow(int row) {
    	String [][] aux = new String[1][8];
    	for(int i=0;i<8;i++) {
    		aux[1][i]=tablero[row][i];
    	}
    	return aux;
	}
    
    public String [][] getColumn(int col) {
    	String [][] aux = new String[5][1];
    	for(int i=0;i<8;i++) {
    		aux[i][1]=tablero[i][col];
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

        str = str + "[ \n";
        for (int row = 0; row < tablero.length; row++) {
            str = str + "[ ";
            for (int col = 0; col < tablero.length; col++) {
                str = str + tablero[row][col] + " ";
            }
            str = str + " ]\n";
        }
        str = str + " ]";

        return str;
    }

}
