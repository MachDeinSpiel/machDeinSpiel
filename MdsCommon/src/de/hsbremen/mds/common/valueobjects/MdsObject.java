
package de.hsbremen.mds.common.valueobjects;

/**
 * @author AndroidDeppen
 * Klasse für MDS-Objekte
 */
public class MdsObject {

	String name;
	String url;
	String text;
	String kind;
	
	public MdsObject(String name, String url, String text, String kind){
		super();
		this.name = name;
		this.url = url;
		this.text = text;
		this.kind = kind;
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
