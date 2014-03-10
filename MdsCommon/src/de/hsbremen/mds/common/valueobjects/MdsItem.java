package de.hsbremen.mds.common.valueobjects;

/**
 * @author chumbawamba!
 */

public class MdsItem {

	private String name;
	private String imagePath;
	
	public MdsItem(String name, String imagePath) {
			this.name = name;
			this.imagePath = imagePath;
		}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
