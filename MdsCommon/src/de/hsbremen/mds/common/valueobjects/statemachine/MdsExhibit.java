package de.hsbremen.mds.common.valueobjects.statemachine;

/**
 * @author JG, NH, JW, SE, AB, RS, OT
 */

public class MdsExhibit {

	private String name;
	private String url;
	private String text;
	private boolean movable;
	private double longitude;
	private double latitude;

	public MdsExhibit(String name, String url, String text, double longitude,
			double latitude, boolean movable) {
		this.setName(name);
		this.setText(text);
		this.setUrl(url);
		this.setLongitude(longitude);
		this.setLatitude(latitude);
		this.movable = movable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
