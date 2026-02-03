package com.glowik.Decorator.DoorController;

import org.apache.log4j.Logger;



public class DoorControllerFrontDoor implements DoorControllerIF {
	static Logger logger = Logger.getLogger(DoorControllerFrontDoor.class);
	
	@Override
	public void requestOpen(String key) {
		logger.info("Front Door: request to open: "  + key);
		
	}

	@Override
	public void close() {
		logger.info("Front Door: close");
		
	}

	public static void main(String[] args) {
		System.out.println("Hello Door controller!");
		DoorControllerIF c = new DoorControllerFrontDoor();
        c.requestOpen("7890");
        
        c.close();
 
        DoorControllerIF c2 = new DoorControllerWrapperA(c,"font",new SurvellianceCameraA());
        c2.requestOpen("5555");
	}

}
