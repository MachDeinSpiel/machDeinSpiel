package de.hsbremen.mds.interpreter;

import java.util.HashMap;
import java.util.List;

import javax.xml.stream.Location;

import de.hsbremen.mds.common.valueobjects.statemachine.MdsAttribute;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsEvent;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsExhibit;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsItem;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsPlayer;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsTrigger;

/**
 * @author JG, NH, JW
 */

public class EventParser {
	
	//Location
	double longitude;
	double latitude;
	//Exhibits
	List<MdsExhibit> exhibits;
	//Items
	List<MdsItem> items;
	//Player
	MdsPlayer player;
	
	//Konstanten für Events
	public static final String TYPE_GAME = "gameEvent";
	public static final String TYPE_UI = "uiEvent";
	
	//Konstanten für HashMap-Keys
	public static final String PARAM_QUANTIFIER = "quantifier";
	public static final String PARAM_TARGET = "target";
	public static final String PARAM_WHO = "who";
	public static final String PARAM_RADIUS = "radius";
	public static final String PARAM_Text= "text";
	
	//Kontanten für Groups
	public static final String GROUP_EXHIBIT = "exhibits";
	public static final String GROUP_ITEM = "item";
	
	//Konstanten für Namen
	public static final String NEARBY = "nearby";

	public EventParser(MdsPlayer player, List<MdsExhibit> exhibits, List<MdsItem> items) {
		this.exhibits = exhibits;
		this.player = player;
		this.items = items;
	}
	
	public MdsExhibit getOneExhibitInRadius(double radius) {
		
		MdsExhibit closestE = null;
		float smallesDif = (float) radius;
		
		//set first Exhibits as closest
		//closestE = exhibits.get(0);
		//set its difference as smallest
		//float result[] = new float[3];
		//Location.distanceBetween(this.latitude, this.longitude, closestE.getLatitude(), closestE.getLongitude(), result);
		//smallesDif = result[0];
		//smallesDif = Math.sqrt(Math.pow(closestE.getLatitude() - this.latitude, 2) + Math.pow(closestE.getLongitude() - this.longitude, 2));
		
		//go through all other Exhibits
		Location myLoc = new Location("myPos");
		myLoc.setLatitude(latitude);
		myLoc.setLongitude(longitude);
		Log.d("Interpreter", "myLoc:"+myLoc.toString());
		for(MdsExhibit ex : exhibits) {
			//double dif = Math.sqrt(Math.pow(ex.getLatitude() - this.latitude, 2) 
			//			 + Math.pow(ex.getLongitude() - this.longitude, 2));
			Location exLoc = new Location("exhibitPos");
			exLoc.setLatitude(ex.getLatitude());
			exLoc.setLongitude(ex.getLongitude());
			Log.d("Interpreter", "exLoc:"+exLoc.toString());
			float dif = exLoc.distanceTo(myLoc);
			Log.d("Interpreter", "prüfe :["+ex.getName()+"("+ex.getLatitude()+"|"+ex.getLongitude()+")] distance:["+dif+"]");
			// if the current dif is smaller then the smallestDif set it as smallestDif
			Log.d("Interpreter", "closesE:["+(closestE == null ? "null" : closestE.getName())+"] distance:["+smallesDif+"]");
			if(dif < smallesDif) {
				smallesDif = dif;
				closestE = ex;
			}
		}
		return closestE;
	}
	
	public void setGameData(double longitude, double latitude){
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	/**
	 * Prüft, ob ein Event erfüllt ist. Wenn das der Fall ist, wird diesem ggf. ein Trigger-Objekt hinzugefügt und
	 * es dann zurück gegeben, andernfalls wird null zurückgegeben
	 * @param event MdsEvent, das geprüft werden soll
	 * @return MdsEvent (ggf. mit gesetztem Attribute triggerObject) wenn erfüllt, sonst null
	 */
	public boolean checkEvent(MdsEvent event) {
		Log.d("Interpreter", "checking Events...");
		if(event.getType().equals(TYPE_GAME) && event.getName().equals(NEARBY)) {
			Log.d("Interpreter", "GameEvent, NearyBy");
			HashMap<String, String> eventHash = event.getParams();
			
			// for our first Version only the radius and the target of the "nearby" event are important
			// get target
			String target = eventHash.get("target");
			// get radius
			String radiusString = eventHash.get("radius");
			double radius = Double.parseDouble(radiusString);
			// FIXME: Funktioniert auf lange Sicht nicht, nur in unserer ersten Version
			if (target.equals(GROUP_EXHIBIT)) {
				MdsExhibit ex = getOneExhibitInRadius(radius);
				if (ex != null) {
					Log.d("Interpreter", "Nächste Exhibit:"+ex.getName());
					MdsTrigger trigger = new MdsTrigger();
					trigger.setAttribute(new MdsAttribute(ex, "MdsExhibit"), "object");
					event.setTrigger(trigger);
					return true;
				} else
					Log.d("Interpreter", "Nächste Exhibit: keins(null)");
					return false;
			} else
				return false;
		} else 
			return false;
	}
	
	private int quantifier(MdsEvent event){
		//TODO: In dieser App noch nicht relevant
		return 0;
	}
	
	private MdsPlayer who(MdsEvent event){
		
		//TODO: In dieser App noch nicht relevant
		return null;
	}
}
