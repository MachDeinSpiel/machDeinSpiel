package de.hsbremen.mds.common.valueobjects.statemachine;

/**
 * @author JG, NH, JW, SE, AB, RS, OT
 */

public class MdsPlayer {

	private String name;
	private double longitude;
	private double latitude;
	
	public MdsPlayer(String name, double longitude, double latitude){
		this.setName(name);
		this.setLongtitude(longitude);
		this.setLatitude(latitude);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLongtitude() {
		return longitude;
	}
	public void setLongtitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
