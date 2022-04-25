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
	        	for(int col=0; col<9; col++){
	        		for(int row=0; row<5; row++){
	        			if(estadoPlanta.getTablero()[row][col] == "e") {
	        			
	        				estadoPlanta.setEnergia(estadoPlanta.getEnergia() -1 );
	        				estadoPlanta.setTableroEnPosicion(row, col, PlantaPerception.GIRASOLES_PERCEPTION);
	        				
	        				col= 10;
	        				row=6;
	        			}
	        			else if(estadoPlanta.getTablero()[row][col] == "p") {
		        			
	        				estadoPlanta.setEnergia(estadoPlanta.getEnergia() -1 );
	        				estadoPlanta.setTableroEnPosicion(row, col, "p-" + PlantaPerception.GIRASOLES_PERCEPTION);
	        				
	        				col= 10;
	        				row=6;
	        			}   
	        		}
	        	}			        	
	        }
	        estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
	        return estadoPlanta;
	    }


	    @Override
	    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

	        EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
	        PlantaState estadoPlanta = (PlantaState) ast;
	        
	        if ( estadoAmbiente.getEnergiaPlanta() >=2 ) {        	
	        	for(int col=0; col<9; col++){
	        		for(int row=0; row<5; row++){
	        			if(estadoAmbiente.getTablero()[row][col] == "e") {
	        			
	        				estadoAmbiente.setEnergiaPlanta(estadoPlanta.getEnergia() -1 );
	        				estadoAmbiente.setTableroEnPosicion(row, col, PlantaPerception.GIRASOLES_PERCEPTION);
	        				estadoPlanta.setEnergia(estadoPlanta.getEnergia() -1 );
	        				estadoPlanta.setTableroEnPosicion(row, col, PlantaPerception.GIRASOLES_PERCEPTION);
	        				col= 10;
	        				row=6;
	        			}
	        			else if(estadoAmbiente.getTablero()[row][col] == "p") {
		        			
	        				estadoAmbiente.setEnergiaPlanta(estadoPlanta.getEnergia() -1 );
	        				estadoAmbiente.setTableroEnPosicion(row, col, "p-" + PlantaPerception.GIRASOLES_PERCEPTION);
	        				estadoPlanta.setEnergia(estadoPlanta.getEnergia() -1 );
	        				estadoPlanta.setTableroEnPosicion(row, col, "p-" + PlantaPerception.GIRASOLES_PERCEPTION);
	        				col= 10;
	        				row=6;
	        			}   
	        		}
	        	}
				        	
	        }
	        estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
	        return estadoAmbiente;
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
        return "GoUp";
    }
}
