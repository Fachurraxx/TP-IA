package utn.frsf.ia.grupo1;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.examples.search.pacman.PacmanAgent;
import frsf.cidisi.faia.examples.search.pacman.PacmanEnvironment;
import frsf.cidisi.faia.examples.search.pacman.PacmanEnvironmentState;

public class PlantaPerception extends Perception {
//matrizPosiciones se representa como una matriz de 5x9. Los zombies se representarán como z1, z2, z3, z4, z5 siendo el subíndice la cantidad de soles que necesita la planta para matar a ese zombie.
//	Los girasoles se representarán con la cantidad de soles que estén presentes en su celda (0 a 99) donde 0 se refiere a un girasol sin ningún sol producido. La celda donde se encuentra la planta se representa como p. 
//	La letra x la utilizaremos para representar una celda la cual no tenemos información y la letra e para representar una celda vacía. 

    public static String Z1_PERCEPTION = "z1";
    public static String Z2_PERCEPTION = "z2";
    public static String Z3_PERCEPTION = "z3";
    public static String Z4_PERCEPTION = "z4";
    public static String Z5_PERCEPTION = "z5";
    public static String GIRASOLES_PERCEPTION = "0";
    public static String UNKNOWN_PERCEPTION = "x";
    public static String EMPTY_PERCEPTION = "e";
    

    private int leftSensor;
    private int topSensor;
    private int rightSensor;
    private int bottomSensor;
    private int energy;

    public PlantaPerception() {
        energy = 50;
    }

    public PlantaPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method is used to setup the perception.
     */
    @Override
    public void initPerception(Agent agent, Environment environment) {
        PacmanAgent pacmanAgent = (PacmanAgent) agent;
        PacmanEnvironment pacmanEnvironment = (PacmanEnvironment) environment;
        PacmanEnvironmentState environmentState =
                pacmanEnvironment.getEnvironmentState();

        int row = environmentState.getAgentPosition()[0];
        int col = environmentState.getAgentPosition()[1];

        this.setTopSensor(pacmanEnvironment.getTopCell(row, col));
        this.setLeftSensor(pacmanEnvironment.getLeftCell(row, col));
        this.setRightSensor(pacmanEnvironment.getRightCell(row, col));
        this.setBottomSensor(pacmanEnvironment.getBottomCell(row, col));
    }

    // The following methods are Pacman-specific:

    public int getLeftSensor() {
        return leftSensor;
    }

    public void setLeftSensor(int leftSensor) {
        this.leftSensor = leftSensor;
    }

    public int getTopSensor() {
        return topSensor;
    }

    public void setTopSensor(int topSensor) {
        this.topSensor = topSensor;
    }

    public int getRightSensor() {
        return rightSensor;
    }

    public void setRightSensor(int rightSensor) {
        this.rightSensor = rightSensor;
    }

    public int getBottomSensor() {
        return bottomSensor;
    }

    public void setBottomSensor(int bottomSensor) {
        this.bottomSensor = bottomSensor;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        str.append("Energy: " + this.energy);
        str.append("; ");
        str.append("Left Sensor: " + this.leftSensor);
        str.append("; ");
        str.append("Up Sensor: " + this.topSensor);
        str.append("; ");
        str.append("Right Sensor: " + this.rightSensor);
        str.append("; ");
        str.append("Down Sensor: " + this.bottomSensor);

        return str.toString();
    }
}
