package de.hsbremen.mds.mdsandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
@SuppressLint("ValidFragment")
public class FragmentText extends Fragment {

	String text;
	
	public FragmentText() {
		// Required empty public constructor
		this.text = "Sie haben noch keine Sehenswürdikeit erreicht";
	}
	
	public FragmentText(String txtString) {
		this.text = txtString;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = setText(inflater.inflate(R.layout.fragment_text, container,
				false), this.text);
		
		Button btn = (Button) view.findViewById(R.id.btnReturnText);
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity activity = (MainActivity) getActivity();
				Button returnBtn = (Button) activity.findViewById(R.id.btnReturnText);
				returnBtn.setVisibility(Button.GONE);
				activity.initiater.buttonClicked("proceedWalk");
			}
		});
		
		Button btn2 = (Button) view.findViewById(R.id.btnCloseAppText);
		
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity activity = (MainActivity) getActivity();
				activity.initiater.buttonClicked("endGame");
			}
		});
		
		Button btn3 = (Button) view.findViewById(R.id.btnShowVideo);
		
		btn3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity activity = (MainActivity) getActivity();
				
				Button returnBtn = (Button) activity.findViewById(R.id.btnReturnText);
				returnBtn.setVisibility(Button.INVISIBLE);
				Button showVidBtn = (Button) activity.findViewById(R.id.btnShowVideo);
				showVidBtn.setVisibility(Button.INVISIBLE);		
				
				activity.initiater.buttonClicked("showVideo");
			}
		});
		
		return view;
	}
	
	public View setText(View view, String text){
		TextView txtView = (TextView) view.findViewById(R.id.placeholderText);
		txtView.setText(text);
		return view;
	}
	
}