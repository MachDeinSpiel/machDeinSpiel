package de.hsbremen.mds.common.valueobjects.statemachine;

import java.util.HashMap;

/**
 * @author JG, NH, JW, SE, AB, RS, OT
 */

public class MdsEvent {
		
	private String type;
	private String name;
	private HashMap<String, String> params;
	
	//Trigger-Object. Bleibt zunächst Null, wird beim Eintreten eines Events gesetzt
	private MdsTrigger trigger;
	
	public MdsEvent(String type, String name, HashMap<String, String> params){
    	this.type = type;
    	this.name = name;
    	this.params = params;	
    }
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<String, String> getParams() {
		return params;
	}
	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
	public MdsTrigger getTrigger() {
		return trigger;
	}
	public void setTrigger(MdsTrigger trigger) {
		this.trigger = trigger;
	}
	
}
