package frsf.ia.grupo1;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class MainClass {
    
    public static void main(String[] args) throws PrologConnectorException {
        Planta agentePlanta = new Planta();
        
        Ambiente ambiente = new Ambiente();         	
	    PlantaPerception perception = new PlantaPerception();
//
//	    SearchBasedAgentSimulator simulator =
//                new SearchBasedAgentSimulator(ambiente, agentePlanta);
//        
//        simulator.start();
//        
	    while(ambiente.getEnvironmentState().energiaPlanta >1 && !ambiente.getEnvironmentState().zombieEnCasa) {
	    	perception.initPerception(agentePlanta, ambiente);
	    }
	    
	    System.out.println("El juego termino");
   
	    
		System.out.println(ambiente.getEnvironmentState().toString());
		System.out.println("-----------p----------------");
	    
//	     perception.initPerception(agentePlanta, ambiente);
//	     
//	     perception.initPerception(agentePlanta, ambiente);
//	     
//	     perception.initPerception(agentePlanta, ambiente);
//	     
//	     perception.initPerception(agentePlanta, ambiente);
//	     
//	     perception.initPerception(agentePlanta, ambiente);
//     
//	     perception.initPerception(agentePlanta, ambiente);
//	     
//	     perception.initPerception(agentePlanta, ambiente);
//	     
//	     perception.initPerception(agentePlanta, ambiente);
//	     
//	     perception.initPerception(agentePlanta, ambiente);
//	     
//	     perception.initPerception(agentePlanta, ambiente);
//	     
//	     perception.initPerception(agentePlanta, ambiente);
//	     
//	     perception.initPerception(agentePlanta, ambiente);
//	     
//	     perception.initPerception(agentePlanta, ambiente);
//	     
//	     perception.initPerception(agentePlanta, ambiente);
	     
	     
//	     System.out.print(perception.toString());
	    

    }
}
