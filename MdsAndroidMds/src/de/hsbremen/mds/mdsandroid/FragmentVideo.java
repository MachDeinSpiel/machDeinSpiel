package de.hsbremen.mds.mdsandroid;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
@SuppressLint("ValidFragment")
public class FragmentVideo extends Fragment {

	String url;
	
	public FragmentVideo() {
		// Required empty public constructor
		this.url="http://bdmobi.in/videos/load/Hindi%203GP%20Music%20Videos/Ram%20Leela%20-%20Laal%20Ishq.3gp";
	}
	
	public FragmentVideo(String url) {
		this.url=url;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
	
		View view = setVideo(inflater.inflate(R.layout.fragment_video, container,
				false), url);
		
		Button btn = (Button) view.findViewById(R.id.btnReturnVideo);
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity activity = (MainActivity) getActivity();
				activity.initiater.buttonClicked("proceedWalk");
			}
		});
		
		Button btn2 = (Button) view.findViewById(R.id.btnCloseAppVideo);
		
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity activity = (MainActivity) getActivity();
				activity.initiater.buttonClicked("endGame");
			}
		});
		
		return view;
	}
	
	public View setVideo(View view, String url){
		
		final VideoView videoView = (VideoView) view.findViewById(R.id.placeholderVideo);
		videoView.setVideoURI(Uri.parse(url));
		videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				videoView.start();
			}
		});

		return view;
	}
	

}
