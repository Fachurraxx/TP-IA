package AccionesPlanta;

 import frsf.cidisi.faia.examples.search.pacman.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.grupo1.EstadoAmbiente;
import frsf.ia.grupo1.PlantaPerception;
import frsf.ia.grupo1.PlantaState;

public class IrIzquierda extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

    	 PlantaState estadoPlanta = (PlantaState) s;

         int posFila = estadoPlanta.getPosicionPlantaFila();
         int posColumna = estadoPlanta.getPosicionPlantaFila();

        /* La planta no puede ir derecha si esta en el limite derecho. La planta solo puede moverse si la celda esta vacia. Si la celda tiene un zombie, se le restan los soles*/
         if(posColumna != 8) {
        	 if ( estadoPlanta.getTablero()[posFila][posColumna-1] == "e") {
        		 
        		 estadoPlanta.setTableroEnPosicion(posFila, posColumna-1, PlantaPerception.PLANTA_PERCEPTION);
        		 estadoPlanta.setTableroEnPosicion(posFila, posColumna, PlantaPerception.EMPTY_PERCEPTION);
        		 estadoPlanta.setPosicionPlantaColumna(posColumna-1);
        		 estadoPlanta.setPosicionPlantaFila(posFila);
        		 
        		 return estadoPlanta;
        		 
        	 }else if (estadoPlanta.getTablero()[posFila][posColumna-1].contains("z")) {
        		 
        		String zombies = estadoPlanta.getTablero()[posFila][posColumna-1];
             	Integer cantSoles = Integer.parseInt(zombies.substring(zombies.length() - 1));
        		estadoPlanta.setEnergia(estadoPlanta.getEnergia() - (2*cantSoles));
        		return estadoPlanta;
        	 }
         }
         

        return null;
    }


    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

    	PlantaState estadoPlanta = (PlantaState) ast;
    	EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;

        int posFila = estadoAmbiente.getPosicionPlantaFila();
        int posColumna = estadoAmbiente.getPosicionPlantaFila();

       /* La planta no puede ir arriba si esta en el limite suiperior. La planta solo puede moverse si la celda esta vacia. Si la celda tiene un zombie, se le restan los soles*/
        if(posFila != 0) {
       	 if ( estadoAmbiente.getTablero()[posFila][posColumna-1] == "e") {
       		 
       		estadoAmbiente.setTableroEnPosicion(posFila, posColumna-1, PlantaPerception.PLANTA_PERCEPTION);
       		estadoAmbiente.setTableroEnPosicion(posFila, posColumna, PlantaPerception.EMPTY_PERCEPTION);
       		estadoAmbiente.setPosicionPlantaColumna(posColumna-1);
       		estadoAmbiente.setPosicionPlantaFila(posFila);
       		
       		 estadoPlanta.setTableroEnPosicion(posFila, posColumna-1, PlantaPerception.PLANTA_PERCEPTION);
       		 estadoPlanta.setTableroEnPosicion(posFila, posColumna, PlantaPerception.EMPTY_PERCEPTION);
       		 estadoPlanta.setPosicionPlantaColumna(posColumna-1);
       		 estadoPlanta.setPosicionPlantaFila(posFila);
       		 
       		 return estadoAmbiente;
       		 
       	 }else if (estadoAmbiente.getTablero()[posFila][posColumna-1].contains("z")) {
       		 
       		String zombies = estadoAmbiente.getTablero()[posFila][posColumna-1];
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
