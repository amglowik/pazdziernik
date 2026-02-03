package com.glowik.Decorator.DoorController;

import org.apache.log4j.Logger;


public class SurvellianceCameraA implements SurvellianceMonitorIF{
	static Logger logger = Logger.getLogger( SurvellianceCameraA .class);
	@Override
	public void display(String camera) {
		logger.info("Camera A capturing image: " +camera);
		
	}

}
