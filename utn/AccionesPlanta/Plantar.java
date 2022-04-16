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

	        int posFila = estadoPlanta.getPosicionPlantaFila();
	        int posColumna = estadoPlanta.getPosicionPlantaFila();

	        /* La planta puede plantar en una posicion distinta a la posicion actual, en una casilla vacia y debe tener 2 soles o mas*/
	        
	        if ( estadoPlanta.getEnergia() >=2 ) {
	        	
	        	Integer columnaAPlantar = 0;
	        	
	        	for(int i=0; i<5; i++){
	        		
	        	}
	        	
	        	Integer cantSoles = Integer.parseInt(estadoPlanta.getTablero()[posFila-1][posColumna]);	        	    	
	        	estadoPlanta.setEnergia(estadoPlanta.getEnergia() + cantSoles);
	        	estadoPlanta.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.GIRASOLES_PERCEPTION);
	        	
	        	
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
