package AccionesPlanta;

 import frsf.cidisi.faia.examples.search.pacman.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.grupo1.EstadoAmbiente;
import frsf.ia.grupo1.PlantaPerception;
import frsf.ia.grupo1.PlantaState;

public class IrAbajo extends SearchAction {

	  /**
     * See comments in the Eat class.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

    	 PlantaState estadoPlanta = (PlantaState) s;

         int posFila = estadoPlanta.getPosicionPlantaFila();
         int posColumna = estadoPlanta.getPosicionPlantaFila();

        /* La planta no puede ir abajo si esta en el limite inferior. La planta solo puede moverse si la celda esta vacia. Si la celda tiene un zombie, se le restan los soles*/
         if(posFila != 4) {
        	 if ( estadoPlanta.getTablero()[posFila+1][posColumna] == "e") {
        		 
        		 estadoPlanta.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.PLANTA_PERCEPTION);
        		 estadoPlanta.setTableroEnPosicion(posFila, posColumna, PlantaPerception.EMPTY_PERCEPTION);
        		 estadoPlanta.setPosicionPlantaColumna(posColumna);
        		 estadoPlanta.setPosicionPlantaFila(posFila+1);
        		 
        		 return estadoPlanta;
        		 
        	 }else if (estadoPlanta.getTablero()[posFila+1][posColumna].contains("z")) {
        		 
        		 //Poner que lo mata
        		 
        		String zombies = estadoPlanta.getTablero()[posFila+1][posColumna];
             	Integer cantSoles = Integer.parseInt(zombies.substring(zombies.length() - 1));
        		estadoPlanta.setEnergia(estadoPlanta.getEnergia() - (2*cantSoles));
        		return estadoPlanta;
        	 }
         }
         

        return estadoPlanta;
    }


    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

    	PlantaState estadoPlanta = (PlantaState) ast;
    	EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;

        int posFila = estadoAmbiente.getPosicionPlantaFila();
        int posColumna = estadoAmbiente.getPosicionPlantaFila();

        if(posFila != 4) {
       	 if ( estadoAmbiente.getTablero()[posFila+1][posColumna] == "e") {
       		 
       		estadoAmbiente.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.PLANTA_PERCEPTION);
       		estadoAmbiente.setTableroEnPosicion(posFila, posColumna, PlantaPerception.EMPTY_PERCEPTION);
       		estadoAmbiente.setPosicionPlantaColumna(posColumna);
       		estadoAmbiente.setPosicionPlantaFila(posFila+1);
       		
       		 estadoPlanta.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.PLANTA_PERCEPTION);
       		 estadoPlanta.setTableroEnPosicion(posFila, posColumna, PlantaPerception.EMPTY_PERCEPTION);
       		 estadoPlanta.setPosicionPlantaColumna(posColumna);
       		 estadoPlanta.setPosicionPlantaFila(posFila+1);
       		 
       		 return estadoAmbiente;
       		 
       	 }else if (estadoAmbiente.getTablero()[posFila+1][posColumna].contains("z")) {
       		 
       		String zombies = estadoAmbiente.getTablero()[posFila+1][posColumna];
            Integer cantSoles = Integer.parseInt(zombies.substring(zombies.length() - 1));
       		estadoPlanta.setEnergia(estadoPlanta.getEnergia() - (2*cantSoles));
       		estadoAmbiente.setEnergiaPlanta(estadoPlanta.getEnergia() - (2*cantSoles));
       		return estadoAmbiente;
       	 }
        }
        
        return null;
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
