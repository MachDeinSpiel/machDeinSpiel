/**
 * 
 */
package de.hsbremen.mds.common.valueobjects;

/**
 * @author AndroidDeppen
 * Klasse fŸr MDS-Objekte
 */

//TODO: brauchen wir die Klasse überhaupt?

public class MdsText {

	String name;
	String url;
	String text;
	
	public MdsText(String name, String url, String text){
		super();
		this.name = name;
		this.url = url;
		this.text = text;
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
	
}
