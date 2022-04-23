package AccionesPlanta;

 import frsf.cidisi.faia.examples.search.pacman.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.grupo1.EstadoAmbiente;
import frsf.ia.grupo1.PlantaPerception;
import frsf.ia.grupo1.PlantaState;

public class Pelear extends SearchAction {


    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        PlantaState estadoPlanta = (PlantaState) s;

        int posFila = estadoPlanta.getPosicionPlantaFila();
        int posColumna = estadoPlanta.getPosicionPlantaFila();

        /* La planta puede pelear si hay un zombie al lado y si tiene mas soles de los que necesita para pelear*/
       
        if ( estadoPlanta.getTablero()[posFila-1][posColumna].contains("z")) {
        	
        	String zombies = estadoPlanta.getTablero()[posFila-1][posColumna]; //z1 1
        	Integer cantSoles = estadoPlanta.getTipoZombie(zombies);
        	    	
        	if (cantSoles < estadoPlanta.getEnergia()) {
        		estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
        		estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()-1);
        		estadoPlanta.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.EMPTY_PERCEPTION);

			}
        	
        }else if ( estadoPlanta.getTablero()[posFila+1][posColumna].contains("z")) {
        	
        	String zombies = estadoPlanta.getTablero()[posFila+1][posColumna];
        	Integer cantSoles = estadoPlanta.getTipoZombie(zombies);
        	    	
        	if (cantSoles < estadoPlanta.getEnergia()) {
        		estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
        		estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()-1);
        		estadoPlanta.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.EMPTY_PERCEPTION);

			}
        }else if ( estadoPlanta.getTablero()[posFila][posColumna-1].contains("z")) {
        	
        	String zombies = estadoPlanta.getTablero()[posFila][posColumna-1];
        	Integer cantSoles = estadoPlanta.getTipoZombie(zombies);
        	    	
        	if (cantSoles < estadoPlanta.getEnergia()) {
        		estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
        		estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()-1);
        		estadoPlanta.setTableroEnPosicion(posFila+1, posColumna-1, PlantaPerception.EMPTY_PERCEPTION);

			}
        }else if ( estadoPlanta.getTablero()[posFila][posColumna+1].contains("z")) {
        	
        	String zombies = estadoPlanta.getTablero()[posFila][posColumna+1];
        	Integer cantSoles = estadoPlanta.getTipoZombie(zombies);
        	    	
        	if (cantSoles < estadoPlanta.getEnergia()) {
        		estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
        		estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()+1);
        		estadoPlanta.setTableroEnPosicion(posFila, posColumna+1, PlantaPerception.EMPTY_PERCEPTION);

			}
        }
        
        return estadoPlanta;
    }


    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
        PlantaState estadoPlanta = (PlantaState) ast;

        int posFila = estadoPlanta.getPosicionPlantaFila();
        int posColumna = estadoPlanta.getPosicionPlantaFila();

        /* La planta puede pelear si hay un zombie al lado y si tiene mas soles de los que necesita para pelear*/
       
        if ( estadoAmbiente.getTablero()[posFila-1][posColumna].contains("z")) {
        	
        	String zombies = estadoPlanta.getTablero()[posFila-1][posColumna];
        	Integer cantSoles = estadoPlanta.getTipoZombie(zombies);
        	    	
        	if (cantSoles < estadoAmbiente.getEnergiaPlanta()) {
        		estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() - cantSoles);
        		estadoAmbiente.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
        		estadoAmbiente.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
        		
        		estadoPlanta.setEnergia(estadoAmbiente.getEnergiaPlanta() - cantSoles);
        		estadoPlanta.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
        		estadoPlanta.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.EMPTY_PERCEPTION);

			}
        	
        }else if ( estadoAmbiente.getTablero()[posFila+1][posColumna].contains("z")) {
        	
        	String zombies = estadoAmbiente.getTablero()[posFila+1][posColumna];
        	Integer cantSoles = estadoPlanta.getTipoZombie(zombies);
        	    	
        	if (cantSoles < estadoAmbiente.getEnergiaPlanta()) {
        		estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() - cantSoles);
        		estadoAmbiente.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
        		estadoAmbiente.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
        		estadoPlanta.setEnergia(estadoAmbiente.getEnergiaPlanta() - cantSoles);
        		estadoPlanta.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
        		estadoPlanta.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.EMPTY_PERCEPTION);

			}
        }else if ( estadoAmbiente.getTablero()[posFila][posColumna-1].contains("z")) {
        	
        	String zombies = estadoAmbiente.getTablero()[posFila][posColumna-1];
        	Integer cantSoles = estadoPlanta.getTipoZombie(zombies);
        	    	
        	if (cantSoles < estadoAmbiente.getEnergiaPlanta()) {
        		estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() - cantSoles);
        		estadoAmbiente.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
        		estadoAmbiente.setTableroEnPosicion(posFila+1, posColumna-1, PlantaPerception.EMPTY_PERCEPTION);
        		estadoPlanta.setEnergia(estadoAmbiente.getEnergiaPlanta() - cantSoles);
        		estadoPlanta.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
        		estadoPlanta.setTableroEnPosicion(posFila+1, posColumna-1, PlantaPerception.EMPTY_PERCEPTION);

			}
        }else if ( estadoAmbiente.getTablero()[posFila][posColumna+1].contains("z")) {
        	
        	String zombies = estadoAmbiente.getTablero()[posFila][posColumna+1];
        	Integer cantSoles = estadoPlanta.getTipoZombie(zombies);
        	    	
        	if (cantSoles < estadoAmbiente.getEnergiaPlanta()) {
        		estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() - cantSoles);
        		estadoAmbiente.setTotalZombies(estadoAmbiente.getTotalZombies()+1);
        		estadoAmbiente.setTableroEnPosicion(posFila, posColumna+1, PlantaPerception.EMPTY_PERCEPTION);
        		estadoPlanta.setEnergia(estadoAmbiente.getEnergiaPlanta() - cantSoles);
        		estadoPlanta.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
        		estadoPlanta.setTableroEnPosicion(posFila, posColumna+1, PlantaPerception.EMPTY_PERCEPTION);

			}
        }
        
        return estadoAmbiente;
    }

    @Override
    public Double getCost() {
        return new Double(0);
    }

    @Override
    public String toString() {
        return "Pelear";
    }
}
