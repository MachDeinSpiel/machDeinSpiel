package de.hsbremen.mds.mdsandroid;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class FragmentMap extends Fragment {
	
    Location location;
    LocationManager manager;
    FragmentActivity fragAct;
    View mapView;
    double longitude;
    double latitude;

	public FragmentMap() {
		// Required empty public constructor
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		
		mapView = inflater.inflate(R.layout.fragment_map, container,false);
		
		Button btn = (Button) mapView.findViewById(R.id.btnStart);
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity activity = (MainActivity) getActivity();
				activity.gpsInit();
			}
		});
		
		Button btn2 = (Button) mapView.findViewById(R.id.btnCloseAppMap);
		
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity activity = (MainActivity) getActivity();
				activity.initiater.buttonClicked("endGame");
			}
		});
	
		return mapView;
	}
	
	public View getMapView(){
		return this.mapView;
	}
}
