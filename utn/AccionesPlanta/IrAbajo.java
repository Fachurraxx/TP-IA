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

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {

		PlantaState estadoPlanta = (PlantaState) s;

		
		int row = estadoPlanta.getPosicionPlantaFila();
		int col = estadoPlanta.getPosicionPlantaColumna();
		String posicionPlantaValor = estadoPlanta.getTableroEnPosicion(row, col);
		
		// Check the limits of the world
        if (row == 4) {
            row = 0;
        } else {
            row = row + 1;
        }
        
        estadoPlanta.setPosicionPlantaFila(row);
        
        
        String tableroValor = estadoPlanta.getTableroEnPosicion(row, col);
        if (tableroValor == PlantaPerception.EMPTY_PERCEPTION || tableroValor == PlantaPerception.UNKNOWN_PERCEPTION ) {

        	estadoPlanta.setTableroEnPosicion(row, col, PlantaPerception.PLANTA_PERCEPTION);

        	if(posicionPlantaValor.contains("-")) {
        		posicionPlantaValor = posicionPlantaValor.split("-")[1];
        		estadoPlanta.setTableroEnPosicion(row-1, col, posicionPlantaValor);
        	}
        	else {
            	estadoPlanta.setTableroEnPosicion(row-1, col, PlantaPerception.EMPTY_PERCEPTION);
        	}
        }
        else{
        	 if(!tableroValor.contains("z")) {//p-12 
        		 estadoPlanta.setTableroEnPosicion(row, col,
             			PlantaPerception.PLANTA_PERCEPTION.concat("-").concat(tableroValor));
        	 }
        	 else {
        		 int tipoZombie = estadoPlanta.getTipoZombie(tableroValor);
        		 estadoPlanta.setEnergia(estadoPlanta.getEnergia() - (2 * tipoZombie));
        		 
        		 if (estadoPlanta.getEnergia()>1) {
        			 estadoPlanta.setTableroEnPosicion(row, col, PlantaPerception.PLANTA_PERCEPTION);
        		 }
        		 else {
        			 estadoPlanta.setTableroEnPosicion(row, col, PlantaPerception.PLANTA_PERCEPTION.concat("-").concat(tableroValor));
        		 }
        		
        		 
        	 }
        }
        
		return estadoPlanta;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {

		PlantaState estadoPlanta = (PlantaState) ast;
		EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;

		int row = estadoAmbiente.getPosicionPlantaFila();
		int col = estadoAmbiente.getPosicionPlantaColumna();
		String posicionPlantaValor = estadoPlanta.getTableroEnPosicion(row, col);


		// Check the limits of the world
        if (row == 4) {
            row = 0;
        } else {
            row = row + 1;
        }
        
        estadoPlanta.setPosicionPlantaFila(row);

        estadoAmbiente.setPosicionPlantaFila(row);
        
        //TODO hay que actualizar los estados cuando nos movemos? porque el pacman no lo hace?
        //si actualizamos en que estado(planta o ambiente) nos basamos para verificar que hay es esa posicion?
//        String tableroValor = estadoPlanta.getTableroEnPosicion(row, col);
//        if (tableroValor == PlantaPerception.EMPTY_PERCEPTION || tableroValor == PlantaPerception.UNKNOWN_PERCEPTION ) {
//
//        	estadoPlanta.setTableroEnPosicion(row, col, PlantaPerception.PLANTA_PERCEPTION);
//
//        	if(posicionPlantaValor.contains("-")) {
//        		posicionPlantaValor = posicionPlantaValor.split("-")[1];
//        		estadoPlanta.setTableroEnPosicion(row-1, col, posicionPlantaValor);
//        	}
//        	else {
//            	estadoPlanta.setTableroEnPosicion(row-1, col, PlantaPerception.EMPTY_PERCEPTION);
//        	}
//        }
//        else{
//        	 if(!tableroValor.contains("z")) {//p-12 
//        		 estadoPlanta.setTableroEnPosicion(row, col,
//             			PlantaPerception.PLANTA_PERCEPTION.concat("-").concat(tableroValor));
//        	 }
//        	 else {
//        		 int tipoZombie = estadoPlanta.getTipoZombie(tableroValor);
//        		 estadoPlanta.setEnergia(estadoPlanta.getEnergia() - (2 * tipoZombie));
//        		 
//        		 if (estadoPlanta.getEnergia()>1) {
//        			 estadoPlanta.setTableroEnPosicion(row, col, PlantaPerception.PLANTA_PERCEPTION);
//        		 }
//        		 else {
//        			 estadoPlanta.setTableroEnPosicion(row, col, PlantaPerception.PLANTA_PERCEPTION.concat("-").concat(tableroValor));
//        		 }
//        		
//        		 
//        	 }
//        }
        
        return estadoAmbiente;
	}

	@Override
	public Double getCost() {
		return new Double(0);
	}

	@Override
	public String toString() {
		return "GoUp";
	}
}
