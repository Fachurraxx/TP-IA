package frsf.ia.grupo1;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class MainClass {
    
    public static void main(String[] args) throws PrologConnectorException {
        
        
        Ambiente ambiente = new Ambiente();
        Planta agentePlanta = new Planta();
        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(ambiente, agentePlanta);
        
        simulator.start();
    }
}
