package com.glowik.Decorator.DoorController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SurvellianceCameraA implements SurvellianceMonitorIF{
	static Logger logger = LoggerFactory.getLogger( SurvellianceCameraA .class);
	@Override
	public void display(String camera) {
		logger.info("Camera A capturing image: " +camera);
		
	}

}
