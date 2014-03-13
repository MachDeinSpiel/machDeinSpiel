package de.hsbremen.mds.mdsandroid;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
@SuppressLint("ValidFragment")
public class FragmentImage extends Fragment {

	String imagePath = "";
	
	public FragmentImage() {
	}
	
	public FragmentImage(String imgPath) {
		this.imagePath = imgPath;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = setImage(inflater.inflate(R.layout.fragment_image, container, false) , this.imagePath);
		
		Button btn = (Button) view.findViewById(R.id.btnReturnImage);
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity activity = (MainActivity) getActivity();
				activity.initiater.buttonClicked("proceedWalk");
			}
		});
		
		Button btn2 = (Button) view.findViewById(R.id.btnCloseAppImage);
		
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity activity = (MainActivity) getActivity();
				activity.initiater.buttonClicked("endGame");
			}
		});
		
        return view;
	}
	
	public View setImage(View view, String url){

        ImageView imageView = (ImageView) view.findViewById(R.id.placeholderImage);
        imageView.setImageResource(R.drawable.bremenroland);
		
		return view;
	}
	
}
