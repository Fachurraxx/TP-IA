package AccionesPlanta;

 import frsf.cidisi.faia.examples.search.pacman.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.grupo1.EstadoAmbiente;
import frsf.ia.grupo1.PlantaPerception;
import frsf.ia.grupo1.PlantaState;

public class Recargar extends SearchAction {

	@Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        PlantaState estadoPlanta = (PlantaState) s;

        int posFila = estadoPlanta.getPosicionPlantaFila();
        int posColumna = estadoPlanta.getPosicionPlantaFila();

        /* La planta puede recargar si hay un numero entero al lado */
        
        if ( estadoPlanta.getTablero()[posFila-1][posColumna].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoPlanta.getTablero()[posFila-1][posColumna]);	        	    	
        	estadoPlanta.setEnergia(estadoPlanta.getEnergia() + cantSoles);
        	estadoPlanta.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.GIRASOLES_PERCEPTION);
        	return estadoPlanta;
				        	
        }else if ( estadoPlanta.getTablero()[posFila+1][posColumna].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoPlanta.getTablero()[posFila+1][posColumna]);	        	    	
        	estadoPlanta.setEnergia(estadoPlanta.getEnergia() + cantSoles);
        	estadoPlanta.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.GIRASOLES_PERCEPTION);
        	return estadoPlanta;
				        	
        }else if ( estadoPlanta.getTablero()[posFila][posColumna-1].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoPlanta.getTablero()[posFila][posColumna-1]);	        	    	
        	estadoPlanta.setEnergia(estadoPlanta.getEnergia() + cantSoles);
        	estadoPlanta.setTableroEnPosicion(posFila, posColumna-1, PlantaPerception.GIRASOLES_PERCEPTION);
        	return estadoPlanta;
				        	
        }else if ( estadoPlanta.getTablero()[posFila][posColumna+1].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoPlanta.getTablero()[posFila][posColumna+1]);	        	    	
        	estadoPlanta.setEnergia(estadoPlanta.getEnergia() + cantSoles);
        	estadoPlanta.setTableroEnPosicion(posFila, posColumna+1, PlantaPerception.GIRASOLES_PERCEPTION);
        	return estadoPlanta;
				        	
        }
        
        return null;
    }


    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
        PlantaState estadoPlanta = (PlantaState) ast;

        int posFila = estadoPlanta.getPosicionPlantaFila();
        int posColumna = estadoPlanta.getPosicionPlantaFila();

        /* La planta puede pelear si hay un zombie al lado y si tiene mas soles de los que necesita para pelear*/
       
        
        if ( estadoAmbiente.getTablero()[posFila-1][posColumna].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoAmbiente.getTablero()[posFila-1][posColumna]);	        	    	
        	estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() + cantSoles);
        	estadoAmbiente.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.GIRASOLES_PERCEPTION);
        	estadoPlanta.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
        	return estadoAmbiente;
				        	
        }
        else if ( estadoAmbiente.getTablero()[posFila+1][posColumna].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoAmbiente.getTablero()[posFila+1][posColumna]);	        	    	
        	estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() + cantSoles);
        	estadoAmbiente.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.GIRASOLES_PERCEPTION);
        	estadoPlanta.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
        	return estadoAmbiente;
				        	
        }else if ( estadoAmbiente.getTablero()[posFila][posColumna-1].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoAmbiente.getTablero()[posFila][posColumna-1]);	        	    	
        	estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() + cantSoles);
        	estadoAmbiente.setTableroEnPosicion(posFila, posColumna-1, PlantaPerception.GIRASOLES_PERCEPTION);
        	estadoPlanta.setTableroEnPosicion(posFila, posColumna-1, PlantaPerception.EMPTY_PERCEPTION);
        	return estadoAmbiente;
				        	
        }else if ( estadoAmbiente.getTablero()[posFila][posColumna+1].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoAmbiente.getTablero()[posFila][posColumna+1]);	        	    	
        	estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() + cantSoles);
        	estadoAmbiente.setTableroEnPosicion(posFila, posColumna+1, PlantaPerception.GIRASOLES_PERCEPTION);
        	estadoPlanta.setTableroEnPosicion(posFila, posColumna+1, PlantaPerception.EMPTY_PERCEPTION);
        	return estadoAmbiente;					        	
        }
        
        return estadoAmbiente;
    }
    
    @Override
    public Double getCost() {
        return new Double(0);
    }

    /**
     * See comments in the Eat class.
     */
    @Override
    public String toString() {
        return "GoUp";
    }
}
