package com.glowik.Decorator.DoorController;

public class DoorControllerWrapperA extends AbstractDoorControllerWrapper{
	private String camera;
	private SurvellianceMonitorIF monitor;
	
	public DoorControllerWrapperA(DoorControllerIF wrapee,
			String camera,
			SurvellianceMonitorIF monitor) {
		super(wrapee);
		this.camera = camera;
		this.monitor = monitor;
	}

	public void requestOpen(String key){
		monitor.display(camera);
		super.requestOpen(key);
	}
		
}
