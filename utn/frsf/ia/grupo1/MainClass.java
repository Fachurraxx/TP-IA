package frsf.ia.grupo1;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class MainClass {
    
    public static void main(String[] args) throws PrologConnectorException {
        
        
        Ambiente ambiente = new Ambiente();
        int energiaPlanta = ambiente.getEnvironmentState().getEnergiaPlanta();
        int totalZombies = ambiente.getEnvironmentState().getTotalZombies();
        int zombiesEnTablero = ambiente.getEnvironmentState().getZombiesEnTablero();
        int zombiesQueVeo = ambiente.getEnvironmentState().getZombiesQueVeo();
        
        Planta agentePlanta = new Planta(energiaPlanta, totalZombies, zombiesEnTablero, zombiesQueVeo);
        
        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(ambiente, agentePlanta);
        
        simulator.start();
    }
}
