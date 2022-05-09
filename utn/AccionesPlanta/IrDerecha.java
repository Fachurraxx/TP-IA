package AccionesPlanta;

import frsf.cidisi.faia.examples.search.pacman.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.grupo1.EstadoAmbiente;
import frsf.ia.grupo1.PlantaPerception;
import frsf.ia.grupo1.PlantaState;

public class IrDerecha extends SearchAction {

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {

		PlantaState estadoPlanta = (PlantaState) s;

		int row = estadoPlanta.getPosicionPlantaFila();
		int col = estadoPlanta.getPosicionPlantaColumna();
		int prevCol = col;
		String posicionPlantaValor = estadoPlanta.getTableroEnPosicion(row, col);

		// Check the limits of the world
		if (col == 8) {
			col = 0;
		} else {
			col = col + 1;
		}

//		if (col != 8) {
		String tableroValor = estadoPlanta.getTableroEnPosicion(row, col);
		if (tableroValor == PlantaPerception.EMPTY_PERCEPTION || tableroValor == PlantaPerception.UNKNOWN_PERCEPTION) {

			estadoPlanta.setTableroEnPosicion(row, col, PlantaPerception.PLANTA_PERCEPTION);

			
		} else {
			if (!tableroValor.contains("z")) {// p-12
				//HAY GIRASOL
				estadoPlanta.setTableroEnPosicion(row, col,
						PlantaPerception.PLANTA_PERCEPTION.concat("-").concat(tableroValor));
			} else {
				int tipoZombie = estadoPlanta.getTipoZombie(tableroValor);
				estadoPlanta.setEnergia(estadoPlanta.getEnergia() - (2 * tipoZombie));

				if (estadoPlanta.getEnergia() > 1) {
					estadoPlanta.setTableroEnPosicion(row, col, PlantaPerception.PLANTA_PERCEPTION);
					estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies() - 1);
				} else {
					estadoPlanta.setTableroEnPosicion(row, col,
							PlantaPerception.PLANTA_PERCEPTION.concat("-").concat(tableroValor));
				}
			}
		}
		
		if (posicionPlantaValor.contains("-")) {
	        //Nos fijamos si en la celda anterior habia algo
			posicionPlantaValor = posicionPlantaValor.split("-")[1];
			estadoPlanta.setTableroEnPosicion(row, prevCol, posicionPlantaValor);
		} else {
			estadoPlanta.setTableroEnPosicion(row, prevCol, PlantaPerception.EMPTY_PERCEPTION);
		}
		
		estadoPlanta.setPosicionPlantaColumna(col);
		estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
//        }

		return estadoPlanta;

	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {

		PlantaState estadoPlanta = (PlantaState) ast;
		EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;

		int row = estadoAmbiente.getPosicionPlantaFila();
		int col = estadoAmbiente.getPosicionPlantaColumna();
		int prevCol = col;
		String posicionPlantaValor = estadoPlanta.getTableroEnPosicion(row, col);

		// Check the limits of the world
		if (col == 8) {
			col = 0;
		} else {
			col = col + 1;
		}

//		if (col != 8) {
		String tableroValor = estadoAmbiente.getTableroEnPosicion(row, col);
		if (tableroValor == PlantaPerception.EMPTY_PERCEPTION || tableroValor == PlantaPerception.UNKNOWN_PERCEPTION) {
			// seteamos estado del ambiente y de la planta
			estadoAmbiente.setTableroEnPosicion(row, col, PlantaPerception.PLANTA_PERCEPTION);
			estadoPlanta.setTableroEnPosicion(row, col, PlantaPerception.PLANTA_PERCEPTION);

			
		} else {
			if (!tableroValor.contains("z")) {// p-12
				estadoAmbiente.setTableroEnPosicion(row, col,
						PlantaPerception.PLANTA_PERCEPTION.concat("-").concat(tableroValor));
				estadoPlanta.setTableroEnPosicion(row, col,
						PlantaPerception.PLANTA_PERCEPTION.concat("-").concat(tableroValor));
			} else {
				int tipoZombie = estadoAmbiente.getTipoZombie(tableroValor);
				estadoAmbiente.setEnergiaPlanta(estadoAmbiente.getEnergiaPlanta() - (2 * tipoZombie));
				estadoPlanta.setEnergia(estadoPlanta.getEnergia() - (2 * tipoZombie));

				if (estadoAmbiente.getEnergiaPlanta() > 1) {
					estadoAmbiente.setTableroEnPosicion(row, col, PlantaPerception.PLANTA_PERCEPTION);
					estadoAmbiente.setTotalZombies(estadoAmbiente.getTotalZombies() - 1);
					estadoPlanta.setTableroEnPosicion(row, col, PlantaPerception.PLANTA_PERCEPTION);
					estadoPlanta.setTotalZombies(estadoPlanta.getTotalZombies() - 1);
				} else {
					estadoAmbiente.setTableroEnPosicion(row, col,
							PlantaPerception.PLANTA_PERCEPTION.concat("-").concat(tableroValor));
					estadoPlanta.setTableroEnPosicion(row, col,
							PlantaPerception.PLANTA_PERCEPTION.concat("-").concat(tableroValor));
				}

			}
		}
		
		if (posicionPlantaValor.contains("-")) {
			posicionPlantaValor = posicionPlantaValor.split("-")[1];
			estadoAmbiente.setTableroEnPosicion(row, prevCol, posicionPlantaValor);
			estadoPlanta.setTableroEnPosicion(row, prevCol, posicionPlantaValor);
		} else {
			estadoAmbiente.setTableroEnPosicion(row, prevCol, PlantaPerception.EMPTY_PERCEPTION);
			estadoPlanta.setTableroEnPosicion(row, prevCol, PlantaPerception.EMPTY_PERCEPTION);
		}
		
		estadoPlanta.setPosicionPlantaColumna(col);
		estadoAmbiente.setPosicionPlantaColumna(col);
		estadoPlanta.setCantidadAccionesRealizadas(this.getCost());
//		}
		return estadoAmbiente;
	}

	@Override
	public Double getCost() {
		return 4.0;
	}

	/**
	 * See comments in the Eat class.
	 */
	@Override
	public String toString() {
		return "Ir Derecha";
	}
}
