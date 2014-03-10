package de.hsbremen.mds.common.valueobjects.statemachine;

/**
 * @author JG, NH, JW, SE, AB, RS, OT
 */

public class MdsItem {

	private String name;
	private double longitude;
	private double latitude;
	
	
	//Konstruktor mit Uebergabe parameter
	public MdsItem(String name, double longitude, double latitude) {
			this.name = name;
			this.longitude = longitude;
			this.latitude =latitude;
		}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
