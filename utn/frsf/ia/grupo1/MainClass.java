package frsf.ia.grupo1;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class MainClass {
    
    public static void main(String[] args) throws PrologConnectorException {
        Planta agentePlanta = new Planta();
        
        Ambiente ambiente = new Ambiente();
            	
	     PlantaPerception perception = new PlantaPerception();
	      
	     perception.initPerception(agentePlanta, ambiente);
	     perception.toString();
      
        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(ambiente, agentePlanta);
        
        simulator.start();
    }
}
