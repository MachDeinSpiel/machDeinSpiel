package de.hsbremen.mds.mdsandroid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import de.hsbremen.mds.common.listener.AndroidInitiater;
import de.hsbremen.mds.common.listener.AndroidListener;
import de.hsbremen.mds.common.valueobjects.MdsImage;
import de.hsbremen.mds.common.valueobjects.MdsMap;
import de.hsbremen.mds.common.valueobjects.MdsText;
import de.hsbremen.mds.common.valueobjects.MdsVideo;

public class MainActivity extends FragmentActivity implements TabListener,
		LocationListener, GuiInterface {

	ActionBar actionBar;
	ViewPager viewPager;
	Location location;
	AndroidInitiater initiater;
	LocationManager manager;
	double longitude;
	double latitude;
	MdsFragmentAdapter mfa = null;
	ActionBar.Tab tabMap = null;
	boolean initComplete = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mfa = new MdsFragmentAdapter(getSupportFragmentManager());

		// Initiater für die Listener registrierung
		initiater = new AndroidInitiater();

		File jsonDatei = jsonEinlesen();

		// Interpreter Instanziert und sich selbst mitgegeben.
		
		viewPager = (ViewPager) findViewById(R.id.pager);

		viewPager.setAdapter(mfa);

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int pos) {
				actionBar.setSelectedNavigationItem(pos);
			}

		});

		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mfa.addFragment("FragmentBackpack");
		mfa.addFragment("FragmentMap");
		mfa.addFragment("FragmentText");
//		mfa.addFragment("FragmentImage");
		mfa.addFragment("FragmentVideo");


		addTab("Backpack");
		addTab("Map");
		addTab("Text");
//		addTab("Image");
		addTab("Video");
		
		// TODO: Hier muss noch ein Möglichkeit gefunden werden den Interpreter oder ein Interface zu erstellen
//		Interpreter interpreter = new Interpreter(jsonDatei, this);
		
		initComplete = true;
		
	}
	
	
	

	public void redrawFragments(int number) {
		mfa.removeFragment(number);
		mfa.notifyDataSetChanged();
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	public void setTabMap() {
		actionBar.selectTab(tabMap);
	}

	public void gpsInit() {

		manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, // 1
																			// sec
				10, this);

		// showText("Hier werden ihre derzeitigen\n Koordinaten angezeigt.");

		boolean isNetworkEnabled = manager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);

		if (isNetworkEnabled) {
			showProviderEnable();
		} else {
			showProviderDisable();
		}

		updateLocationFields();

		System.out.println("GPS wurde initialisiert");
	}

	public void showProviderDisable() {
		TextView view = (TextView) findViewById(R.id.txtGPSVal);
		view.setText("AUS");
		view.setBackgroundColor(Color.RED);
		updateLocationFields();
	}

	public void showProviderEnable() {
		TextView view = (TextView) findViewById(R.id.txtGPSVal);
		view.setText("AN");
		view.setBackgroundColor(Color.GREEN);
		updateLocationFields();
		initiater.locationChanged(location);
	}

	public void updateLocationFields() {

		String latitude;
		String longitude;

		location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (location != null) {

			latitude = String.valueOf(location.getLatitude());
			longitude = String.valueOf(location.getLongitude());

		} else {

			latitude = "Kein Empfang!";
			longitude = "Kein Empfang!";

		}

		TextView latVal = (TextView) findViewById(R.id.txtLatVal);
		TextView longVal = (TextView) findViewById(R.id.txtLongVal);

		latVal.setText(latitude);
		longVal.setText(longitude);

		latVal.invalidate();
		longVal.invalidate();
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		updateLocationFields();
		initiater.locationChanged(arg0);
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		showProviderDisable();
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		showProviderEnable();
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setAndroidListener(AndroidListener listener,
			double positionsIntervall) {
		initiater.setListener(listener, positionsIntervall);
	}

	@Override
	public void nextFragment(MdsImage mds) {
		// TODO Auto-generated method stub
		// mfa.addFragment("FragmentImage");
		// addTab("Image");
		viewPager.setCurrentItem(2);
		Button btn = (Button) findViewById(R.id.btnReturnImage);
		btn.setVisibility(1);
	}

	@Override
	public void nextFragment(MdsVideo mds) {
		// TODO Auto-generated method stub
		// mfa.addFragment("FragmentVideo");
		// addTab("Video");
		viewPager.setCurrentItem(3);
		Button btn = (Button) findViewById(R.id.btnReturnVideo);
		btn.setVisibility(1);
	}

	@Override
	public void nextFragment(MdsText mds) {
		// TODO Auto-generated method stub
		// mfa.addFragment("FragmentText");
		// addTab("Text");
		viewPager.setCurrentItem(1);
		TextView view = (TextView) findViewById(R.id.placeholderText);
		view.setText(mds.getText());
		Button btn = (Button) findViewById(R.id.btnReturnText);
		btn.setVisibility(1);
		Button btn2 = (Button) findViewById(R.id.btnShowVideo);
		btn2.setVisibility(1);
	}

	@Override
	public void nextFragment(MdsMap mds) {
		// TODO Auto-generated method stub

		viewPager.setCurrentItem(0);
		
		if(initComplete){
			TextView view = (TextView) findViewById(R.id.placeholderText);
			view.setText("Sie haben noch keine Sehenswürdigkeit erreicht");
		}
	}

	public static InputStream getInputStreamFromUrl(String url) {
		  InputStream content = null;
		  try {
		    HttpClient httpclient = new DefaultHttpClient();
		    HttpResponse response = httpclient.execute(new HttpGet(url));
		    content = response.getEntity().getContent();
		  } catch (Exception e) {
		    Log.e("[GET REQUEST]", "Network exception", e);
		  }
		    return content;
		}

	private File jsonEinlesen() {

		ThreadPolicy tp = ThreadPolicy.LAX;
		StrictMode.setThreadPolicy(tp);
		
		//InputStream is = getInputStreamFromUrl("http://195.37.176.178:1388/MDSS-0.1/api/appinfo/2.xml");
		InputStream is = getInputStreamFromUrl("http://195.37.176.178:1388/MDSS-0.1/api/appinfo/3");
		
		// TemporŠre Datei anlegen
				File json = null;
				try {
					json = File.createTempFile("TourismApp", ".json");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

//				Zum Testen bitte drin lassen!!
//				// Assetmanager um auf den Assetordner zuzugreifen(Json ist da drin)
//
//				    AssetManager am = getAssets();
//
//				      // Inputstream zum einlesen der Json
//				      try {
//						is = am.open("test.json");
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}

				try {
					// Inputstream zum einlesen der Json
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					
					// Json wird zeilenweise eingelesn uns in das File json geschrieben
					FileWriter writer = new FileWriter(json ,true);
					
					String t = "";
					
					while((t = br.readLine()) != null){
						System.out.println(t);
						writer.write(t);
					}
					
					writer.flush();
					writer.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}       

				// †berprŸfung, ob es geklappt hat
				if(json.exists()){
					System.out.println("Geklappt");
					System.out.println(json.length());
				}else{
					System.out.println("Nicht geklappt");
				}
				
				return json;
		  
	}

	public void addTab(String name) {
		ActionBar.Tab tabImage = actionBar.newTab();
		tabImage.setText(name);
		tabImage.setTabListener(this);
		actionBar.addTab(tabImage);
	}

	public void removeTab(int site) {
		actionBar.removeTabAt(site);
	}

}
