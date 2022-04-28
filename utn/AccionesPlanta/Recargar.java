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
        int posColumna = estadoPlanta.getPosicionPlantaColumna();

        /* La planta puede recargar si hay un numero entero al lado */
        
        if ( posFila > 0 && estadoPlanta.getTablero()[posFila-1][posColumna].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoPlanta.getTablero()[posFila-1][posColumna]);	        	    	
        	estadoPlanta.setEnergia(estadoPlanta.getEnergia() + cantSoles);
        	estadoPlanta.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.GIRASOLES_PERCEPTION);
        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
            return estadoPlanta;	        	
        }else if ( posFila < 4 && estadoPlanta.getTablero()[posFila+1][posColumna].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoPlanta.getTablero()[posFila+1][posColumna]);	        	    	
        	estadoPlanta.setEnergia(estadoPlanta.getEnergia() + cantSoles);
        	estadoPlanta.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.GIRASOLES_PERCEPTION);
        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
            return estadoPlanta;        	
        }else if (  posColumna > 0 && estadoPlanta.getTablero()[posFila][posColumna-1].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoPlanta.getTablero()[posFila][posColumna-1]);	        	    	
        	estadoPlanta.setEnergia(estadoPlanta.getEnergia() + cantSoles);
        	estadoPlanta.setTableroEnPosicion(posFila, posColumna-1, PlantaPerception.GIRASOLES_PERCEPTION);
        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
            return estadoPlanta;        	
        }else if ( posColumna < 8 && estadoPlanta.getTablero()[posFila][posColumna+1].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoPlanta.getTablero()[posFila][posColumna+1]);	        	    	
        	estadoPlanta.setEnergia(estadoPlanta.getEnergia() + cantSoles);
        	estadoPlanta.setTableroEnPosicion(posFila, posColumna+1, PlantaPerception.GIRASOLES_PERCEPTION);		
        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
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
       
        
        if ( posFila > 0 && estadoAmbiente.getTablero()[posFila-1][posColumna].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoAmbiente.getTablero()[posFila-1][posColumna]);	        	    	
        	estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() + cantSoles);
        	estadoAmbiente.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.GIRASOLES_PERCEPTION);
        	estadoPlanta.setEnergia(estadoAmbiente.getEnergiaPlanta() + cantSoles);
        	estadoPlanta.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
            return estadoAmbiente;
        }else if ( posFila < 4 && estadoAmbiente.getTablero()[posFila+1][posColumna].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoAmbiente.getTablero()[posFila+1][posColumna]);	        	    	
        	estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() + cantSoles);
        	estadoAmbiente.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.GIRASOLES_PERCEPTION);
        	estadoPlanta.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
        	estadoPlanta.setEnergia(estadoAmbiente.getEnergiaPlanta() + cantSoles);
        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
            return estadoAmbiente;      	
        }else if (  posColumna > 0 && estadoAmbiente.getTablero()[posFila][posColumna-1].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoAmbiente.getTablero()[posFila][posColumna-1]);	        	    	
        	estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() + cantSoles);
        	estadoAmbiente.setTableroEnPosicion(posFila, posColumna-1, PlantaPerception.GIRASOLES_PERCEPTION);
        	estadoPlanta.setTableroEnPosicion(posFila, posColumna-1, PlantaPerception.EMPTY_PERCEPTION);
        	estadoPlanta.setEnergia(estadoAmbiente.getEnergiaPlanta() + cantSoles);
        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
            return estadoAmbiente;        	
        }else if ( posColumna < 8 && estadoAmbiente.getTablero()[posFila][posColumna+1].matches("[+-]?\\d*(\\.\\d+)?") ) {
        	
        	Integer cantSoles = Integer.parseInt(estadoAmbiente.getTablero()[posFila][posColumna+1]);	        	    	
        	estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() + cantSoles);
        	estadoAmbiente.setTableroEnPosicion(posFila, posColumna+1, PlantaPerception.GIRASOLES_PERCEPTION);
        	estadoPlanta.setTableroEnPosicion(posFila, posColumna+1, PlantaPerception.EMPTY_PERCEPTION);
        	estadoPlanta.setEnergia(estadoAmbiente.getEnergiaPlanta() + cantSoles);				
        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
            return estadoAmbiente;
        }
       
        return null;
    }
    
    @Override
    public Double getCost() {
        return 1.0;
    }

    /**
     * See comments in the Eat class.
     */
    @Override
    public String toString() {
        return "Recargar";
    }
}
