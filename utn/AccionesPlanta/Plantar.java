package AccionesPlanta;

 import frsf.cidisi.faia.examples.search.pacman.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.grupo1.EstadoAmbiente;
import frsf.ia.grupo1.PlantaPerception;
import frsf.ia.grupo1.PlantaState;

public class Plantar extends SearchAction {

	 @Override
	    public SearchBasedAgentState execute(SearchBasedAgentState s) {

	        PlantaState estadoPlanta = (PlantaState) s;

	        /* La planta puede plantar en una posicion distinta a la posicion actual, en una casilla vacia y debe tener 2 soles o mas*/
	        
	        if ( estadoPlanta.getEnergia() >=2 ) {        	
	        	for(int j=0; j<9; j++){
	        		for(int i=0; i<5; i++){
	        			if(estadoPlanta.getTablero()[i][j] == "e") {
	        			
	        				estadoPlanta.setEnergia(estadoPlanta.getEnergia() -1 );
	        				estadoPlanta.setTableroEnPosicion(i, j, PlantaPerception.GIRASOLES_PERCEPTION);
	        				
	        				j= 10;
	        				i=6;
	        			}      				
	        		}
	        	}

	        	return estadoPlanta;					        	
	        }
	        
	        return null;
	    }


	    @Override
	    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

	        EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
	        PlantaState estadoPlanta = (PlantaState) ast;
	        
	        if ( estadoAmbiente.getEnergiaPlanta() >=2 ) {        	
	        	for(int j=0; j<9; j++){
	        		for(int i=0; i<5; i++){
	        			if(estadoAmbiente.getTablero()[i][j] == "e") {
	        			
	        				estadoAmbiente.setEnergiaPlanta(estadoPlanta.getEnergia() -1 );
	        				estadoAmbiente.setTableroEnPosicion(i, j, PlantaPerception.GIRASOLES_PERCEPTION);
	        				estadoPlanta.setTableroEnPosicion(i, j, PlantaPerception.GIRASOLES_PERCEPTION);
	        				j= 10; i=6;
	        			}      				
	        		}
	        	}

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
