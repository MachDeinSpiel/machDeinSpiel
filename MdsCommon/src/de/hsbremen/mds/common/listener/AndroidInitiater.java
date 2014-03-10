package de.hsbremen.mds.common.listener;

import android.location.Location;
import android.util.Log;

public class AndroidInitiater {

	double positionIntervall;
	Location oldLocation;
    AndroidListener listener;
    
    public AndroidInitiater(){
    	positionIntervall = 0;
    	oldLocation = null;
    	listener = null;
    }
    
    public void setListener(AndroidListener toAdd, double intervall) {
        this.listener = toAdd;
        this.positionIntervall = intervall;
        oldLocation = null;
        Log.d("Android", "Listener hinzugefügt");
    }

    public void locationChanged(Location loc) {
    	 Log.d("Android", "LocationChanged");
    	if(listener != null) {
	    	//if(oldLocation == null || loc.distanceTo(oldLocation)>= positionIntervall) {
	            Log.d("Android", "Neue Position");
	    		// Callback mit aktueller Location
	    		listener.onPositionChanged(loc.getLongitude(), loc.getLatitude());
	    		oldLocation = loc;
	    	//}
    	}
    }

    public void buttonClicked(String name) {
    	if(listener != null) {
	        // Callback mit Event Informationen
	        listener.onButtonClick(name);
	    }
    }
    
    
}
