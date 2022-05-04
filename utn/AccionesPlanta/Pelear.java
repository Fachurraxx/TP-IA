package AccionesPlanta;

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
        int posColumna = estadoPlanta.getPosicionPlantaColumna();
		String posArriba= null;
		String posAbajo= null;
		String posDerecha=null;
		String posIzquierda=null;

		if (posFila!=0){
			posArriba=estadoPlanta.getTablero()[posFila-1][posColumna];
		}
		if (posFila!=4){
			posAbajo =estadoPlanta.getTablero()[posFila+1][posColumna];
		}
		if (posColumna!=0){
			posIzquierda=estadoPlanta.getTablero()[posFila][posColumna-1];
		}
		if (posColumna!=8){
			posDerecha=estadoPlanta.getTablero()[posFila][posColumna+1];
		}

		if (posAbajo!= null && posAbajo.contains("z")){
			Integer cantSoles = estadoPlanta.getTipoZombie(posAbajo);

			if (cantSoles < estadoPlanta.getEnergia()) {
				estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
				estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()-1);
				estadoPlanta.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
				estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
				estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
			}
			estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
			return estadoPlanta;
		} else if (posArriba!= null && posArriba.contains("z")) {
			Integer cantSoles = estadoPlanta.getTipoZombie(posArriba);

			if (cantSoles < estadoPlanta.getEnergia()) {
				estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
				estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()-1);
				estadoPlanta.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
				estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
				estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
			}
			estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
			return estadoPlanta;
		} else if (posIzquierda!= null && posIzquierda.contains("z")) {
			Integer cantSoles = estadoPlanta.getTipoZombie(posIzquierda);

			if (cantSoles < estadoPlanta.getEnergia()) {
				estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
				estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()-1);
				estadoPlanta.setTableroEnPosicion(posFila, posColumna-1, PlantaPerception.EMPTY_PERCEPTION);
				estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
				estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
			}
			estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
			return estadoPlanta;
		} else if (posDerecha!= null && posDerecha.contains("z")) {
			Integer cantSoles = estadoPlanta.getTipoZombie(posDerecha);

			if (cantSoles < estadoPlanta.getEnergia()) {
				estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
				estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()-1);
				estadoPlanta.setTableroEnPosicion(posFila, posColumna+1, PlantaPerception.EMPTY_PERCEPTION);
				estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
				estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
			}
			estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
			return estadoPlanta;

		}
		return null;



		/* La planta puede pelear si hay un zombie al lado y si tiene mas soles de los que necesita para pelear*/
//
//        if ( posFila > 0 && estadoPlanta.getTablero()[posFila-1][posColumna].contains("z")) {
//
//        	String zombies = estadoPlanta.getTablero()[posFila-1][posColumna]; //z1 1
//        	Integer cantSoles = estadoPlanta.getTipoZombie(zombies);
//
//        	if (cantSoles < estadoPlanta.getEnergia()) {
//        		estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
//        		estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()-1);
//        		estadoPlanta.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
//        		estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
//        		estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
//			}
//        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
//            return estadoPlanta;
//        }else if ( posFila < 4 && estadoPlanta.getTablero()[posFila+1][posColumna].contains("z") ) {
//
//        	String zombies = estadoPlanta.getTablero()[posFila+1][posColumna];
//        	Integer cantSoles = estadoPlanta.getTipoZombie(zombies);
//
//        	if (cantSoles < estadoPlanta.getEnergia()) {
//        		estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
//        		estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()-1);
//        		estadoPlanta.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
//        		estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
//        		estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
//			}
//        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
//            return estadoPlanta;
//        }else if (  posColumna > 0 && estadoPlanta.getTablero()[posFila][posColumna-1].contains("z") ) {
//
//        	String zombies = estadoPlanta.getTablero()[posFila][posColumna-1];
//        	Integer cantSoles = estadoPlanta.getTipoZombie(zombies);
//
//        	if (cantSoles < estadoPlanta.getEnergia()) {
//        		estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
//        		estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()-1);
//        		estadoPlanta.setTableroEnPosicion(posFila, posColumna-1, PlantaPerception.EMPTY_PERCEPTION);
//        		estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
//        		estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
//			}
//        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
//            return estadoPlanta;
//        }else if (  posColumna < 8 && estadoPlanta.getTablero()[posFila][posColumna+1].contains("z") ) {
//
//        	String zombies = estadoPlanta.getTablero()[posFila][posColumna+1];
//        	Integer cantSoles = estadoPlanta.getTipoZombie(zombies);
//
//        	if (cantSoles < estadoPlanta.getEnergia()) {
//        		estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
//        		estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()+1);
//        		estadoPlanta.setTableroEnPosicion(posFila, posColumna+1, PlantaPerception.EMPTY_PERCEPTION);
//        		estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
//        		estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
//			}
//        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
//            return estadoPlanta;
//
//        }
//        return null;
    }


    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
        PlantaState estadoPlanta = (PlantaState) ast;

        int posFila = estadoAmbiente.getPosicionPlantaFila();
        int posColumna = estadoAmbiente.getPosicionPlantaColumna();

        /* La planta puede pelear si hay un zombie al lado y si tiene mas soles de los que necesita para pelear*/
       if ( estadoPlanta.getEnergia()<1){
		   return null;
	   }

		String posArriba= null;
		String posAbajo= null;
		String posDerecha=null;
		String posIzquierda=null;

		if (posFila!=0){
			posArriba=estadoPlanta.getTablero()[posFila-1][posColumna];
		}
		if (posFila!=4){
			posAbajo =estadoPlanta.getTablero()[posFila+1][posColumna];
		}
		if (posColumna!=0){
			posIzquierda=estadoPlanta.getTablero()[posFila][posColumna-1];
		}
		if (posColumna!=8){
			posDerecha=estadoPlanta.getTablero()[posFila][posColumna+1];
		}

		if (posAbajo!= null && posAbajo.contains("z")){
			Integer cantSoles = estadoPlanta.getTipoZombie(posAbajo);

			if (cantSoles < estadoPlanta.getEnergia()) {
				estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
				estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()-1);
				estadoPlanta.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
				estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
				estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);

				estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() - cantSoles);
        		estadoAmbiente.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
        		estadoAmbiente.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
        		estadoAmbiente.setZombiesEnTablero(estadoAmbiente.getZombiesEnTablero()-1);
        		estadoAmbiente.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
			}
			estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
			return estadoAmbiente;
		} else if (posArriba!= null && posArriba.contains("z")) {
			Integer cantSoles = estadoPlanta.getTipoZombie(posArriba);

			if (cantSoles < estadoPlanta.getEnergia()) {
				estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
				estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()-1);
				estadoPlanta.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
				estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
				estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);


				estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() - cantSoles);
				estadoAmbiente.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
				estadoAmbiente.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
				estadoAmbiente.setZombiesEnTablero(estadoAmbiente.getZombiesEnTablero()-1);
				estadoAmbiente.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
			}
			estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
			return estadoAmbiente;
		} else if (posIzquierda!= null && posIzquierda.contains("z")) {
			Integer cantSoles = estadoPlanta.getTipoZombie(posIzquierda);

			if (cantSoles < estadoPlanta.getEnergia()) {
				estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
				estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()-1);
				estadoPlanta.setTableroEnPosicion(posFila, posColumna-1, PlantaPerception.EMPTY_PERCEPTION);
				estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
				estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);

				estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() - cantSoles);
				estadoAmbiente.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
				estadoAmbiente.setTableroEnPosicion(posFila, posColumna-1, PlantaPerception.EMPTY_PERCEPTION);
				estadoAmbiente.setZombiesEnTablero(estadoAmbiente.getZombiesEnTablero()-1);
				estadoAmbiente.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
			}
			estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
			return estadoAmbiente;
		} else if (posDerecha!= null && posDerecha.contains("z")) {
			Integer cantSoles = estadoPlanta.getTipoZombie(posDerecha);

			if (cantSoles < estadoPlanta.getEnergia()) {
				estadoPlanta.setEnergia(estadoPlanta.getEnergia() - cantSoles);
				estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies()-1);
				estadoPlanta.setTableroEnPosicion(posFila, posColumna+1, PlantaPerception.EMPTY_PERCEPTION);
				estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
				estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);

				estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() - cantSoles);
				estadoAmbiente.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
				estadoAmbiente.setTableroEnPosicion(posFila, posColumna+1, PlantaPerception.EMPTY_PERCEPTION);
				estadoAmbiente.setZombiesEnTablero(estadoAmbiente.getZombiesEnTablero()-1);
				estadoAmbiente.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
			}
			estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
			return estadoAmbiente;

		}

//        if ( posFila > 0 && estadoAmbiente.getTablero()[posFila-1][posColumna].contains("z")) {
//
//        	String zombies = estadoAmbiente.getTablero()[posFila-1][posColumna];
//        	Integer cantSoles = estadoAmbiente.getTipoZombie(zombies);
//
//        	if (cantSoles < estadoAmbiente.getEnergiaPlanta()) {
//        		estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() - cantSoles);
//        		estadoAmbiente.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
//        		estadoAmbiente.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
//        		estadoAmbiente.setZombiesEnTablero(estadoAmbiente.getZombiesEnTablero()-1);
//        		estadoAmbiente.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
//        		estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
//        		estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
//        		estadoPlanta.setEnergia(estadoAmbiente.getEnergiaPlanta() - cantSoles);
//        		estadoPlanta.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
//        		estadoPlanta.setTableroEnPosicion(posFila-1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
//
//			}
//        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
//            return estadoAmbiente;
//        }else if ( posFila < 4 && estadoAmbiente.getTablero()[posFila+1][posColumna].contains("z") ) {
//
//        	String zombies = estadoAmbiente.getTablero()[posFila+1][posColumna];
//        	Integer cantSoles = estadoAmbiente.getTipoZombie(zombies);
//
//        	if (cantSoles < estadoAmbiente.getEnergiaPlanta()) {
//        		estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() - cantSoles);
//        		estadoAmbiente.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
//        		estadoAmbiente.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
//        		estadoAmbiente.setZombiesEnTablero(estadoAmbiente.getZombiesEnTablero()-1);
//        		estadoAmbiente.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
//        		estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
//        		estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
//        		estadoPlanta.setEnergia(estadoAmbiente.getEnergiaPlanta() - cantSoles);
//        		estadoPlanta.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
//        		estadoPlanta.setTableroEnPosicion(posFila+1, posColumna, PlantaPerception.EMPTY_PERCEPTION);
//
//			}
//        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
//            return estadoAmbiente;
//        }else if (  posColumna > 0 && estadoAmbiente.getTablero()[posFila][posColumna-1].contains("z") ) {
//
//        	String zombies = estadoAmbiente.getTablero()[posFila][posColumna-1];
//        	Integer cantSoles = estadoAmbiente.getTipoZombie(zombies);
//
//        	if (cantSoles < estadoAmbiente.getEnergiaPlanta()) {
//        		estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() - cantSoles);
//        		estadoAmbiente.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
//        		estadoAmbiente.setTableroEnPosicion(posFila+1, posColumna-1, PlantaPerception.EMPTY_PERCEPTION);
//        		estadoAmbiente.setZombiesEnTablero(estadoAmbiente.getZombiesEnTablero()-1);
//        		estadoAmbiente.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
//        		estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
//        		estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
//        		estadoPlanta.setEnergia(estadoAmbiente.getEnergiaPlanta() - cantSoles);
//        		estadoPlanta.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
//        		estadoPlanta.setTableroEnPosicion(posFila+1, posColumna-1, PlantaPerception.EMPTY_PERCEPTION);
//
//			}
//        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
//            return estadoAmbiente;
//        }else if (  posColumna < 8 && estadoAmbiente.getTablero()[posFila][posColumna+1].contains("z") ) {
//
//        	String zombies = estadoAmbiente.getTablero()[posFila][posColumna+1];
//        	Integer cantSoles = estadoAmbiente.getTipoZombie(zombies);
//
//        	if (cantSoles < estadoAmbiente.getEnergiaPlanta()) {
//        		estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() - cantSoles);
//        		estadoAmbiente.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
//        		estadoAmbiente.setTableroEnPosicion(posFila, posColumna+1, PlantaPerception.EMPTY_PERCEPTION);
//        		estadoAmbiente.setZombiesEnTablero(estadoAmbiente.getZombiesEnTablero()-1);
//        		estadoAmbiente.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
//        		estadoPlanta.setZombiesQueVeo(estadoPlanta.getZombiesQueVeo()-1);
//        		estadoPlanta.setZombiesEnTablero(estadoPlanta.getZombiesEnTablero()-1);
//        		estadoPlanta.setEnergia(estadoAmbiente.getEnergiaPlanta() - cantSoles);
//        		estadoPlanta.setTotalZombies(estadoAmbiente.getTotalZombies()-1);
//        		estadoPlanta.setTableroEnPosicion(posFila, posColumna+1, PlantaPerception.EMPTY_PERCEPTION);
//
//			}
//        	estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
//            return estadoAmbiente;
//        }
        
       
        return null;
    }

    @Override
    public Double getCost() {
        return 1.0;
    }

    @Override
    public String toString() {
        return "Pelear";
    }
}
