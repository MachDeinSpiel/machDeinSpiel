package de.hsbremen.mds.mdsandroid;
//package com.example.gpstest;
//
//import android.app.Activity;
//import android.content.Context;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.widget.TextView;
//import android.widget.Toast;
//
///**
//* Created by AndroidTeam on 19.12.13.
//*/
//
//public class GPSService extends Activity implements LocationListener{
//
//    Location location;
//    LocationManager locationManager;
//    int GPSStatus;
//    boolean changed = false;
//
//    public GPSService(LocationManager manager){
//
//        this.locationManager = manager;
//
//
//    }
//
//    /************* Called after each 3 sec **********/
//    @Override
//    public void onLocationChanged(Location location) {
//        this.changed = true;
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//        this.GPSStatus = 0;
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//        this.GPSStatus = 1;
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//        //TODO
//    }
//
//    ///GETTER/SETTER
//
//    public Location getLocation(){
//        return this.location;
//    }
//
//    public LocationManager getLocationManager(){
//        return this.locationManager;
//    }
//
//    public String getLatitude(){
//        return String.valueOf(this.location.getLatitude());
//    }
//
//    public String getLongitude(){
//        return String.valueOf(this.location.getLongitude());
//    }
//
//    public boolean isChanged(){
//        if(this.changed){
//            this.changed=false;
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public int getGPSStatus(){
//        return this.GPSStatus;
//    }
//
//    new Thread(new Runnable() {
//        public void run() {
//
//            GPSService gps = new GPSService(man);
//            LocationManager manager = gps.getLocationManager();
//
//            while(true){
//
//                if(gps.getGPSStatus()==1){
//                    TextView view  = (TextView) findViewById(R.id.txtGPSVal);
//                    view.setText("AN");
//                } else {
//                    TextView view  = (TextView) findViewById(R.id.txtGPSVal);
//                    if(!view.getText().equals("AUS")){
//                        view.setText("AUS");
//                    }
//                }
//
//                    /*if(gps.isChanged()){
//                        updateLocationFields(gps.getLocation());
//                    }*/
//
//                try {
//                    Thread.sleep(5000);
//                }
//                catch (Exception e) {
//
//                }
//            }
//        }
//    }).start();
//
//}
//
//
