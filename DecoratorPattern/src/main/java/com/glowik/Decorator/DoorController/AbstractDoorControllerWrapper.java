package com.glowik.Decorator.DoorController;

public abstract class AbstractDoorControllerWrapper implements DoorControllerIF{

	private DoorControllerIF wrapee;
	
	AbstractDoorControllerWrapper (DoorControllerIF wrapee){
		this.wrapee = wrapee;
	}
	
	public void requestOpen(String key){
		wrapee.requestOpen(key);
	}
	
	public void close(){
		wrapee.close();
	}
}
