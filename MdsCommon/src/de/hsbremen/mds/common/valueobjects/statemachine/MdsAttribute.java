package de.hsbremen.mds.common.valueobjects.statemachine;

public class MdsAttribute {

	private Object object;
	private String type;
	
	public MdsAttribute(Object object, String type) {
		this.object = object;
		this.type = type;
	}
	
	
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
