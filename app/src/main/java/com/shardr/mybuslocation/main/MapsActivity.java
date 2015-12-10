package com.shardr.mybuslocation.main;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.Parse;
import com.parse.ParseObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    final int MY_PERMISSIONS_REQUEST_GPS = 0;
    private Location currentBestLocation = null;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "f3NkG0t5fb1AoYGAZeKGNYNpt7QlMv1lWa5Rd205", "gKop5sLZUnUZvKCuhTyEkn7i0aPH5rNx5QQrRbZx");

        ParseObject tempObject = new ParseObject("BusRoutes");

        tempObject.put("Name", "TestName");
        tempObject.saveInBackground();


        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private boolean CheckPermissionAndEnabled() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_GPS);
        }

        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

//        try {
//            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        } catch(Exception ex) {}

        if(!gps_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Please enable location settings to view Bus Location");
            dialog.setPositiveButton(this.getResources().getString(R.string.open_location_settings), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton(this.getString(R.string.Cancel), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            dialog.show();
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_GPS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {

//                    this.finish();
//                    System.exit(0);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        CheckPermissionAndEnabled();

        try {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            Location locationNet = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            long GPSLocationTime = 0;
            if (null != locationGPS) { GPSLocationTime = locationGPS.getTime(); }

            long NetLocationTime = 0;

//            if (null != locationNet) {
//                NetLocationTime = locationNet.getTime();
//            }

//            if ( 0 < GPSLocationTime - NetLocationTime ) {
//                currentBestLocation =  locationGPS;
//            }
//            else {
////                currentBestLocation = locationNet;
//                currentBestLocation = locationGPS;
//            }
            currentBestLocation = locationGPS;

            // Add a marker in Sydney and move the camera
            LatLng myLocation = new LatLng(currentBestLocation.getLatitude(), currentBestLocation.getLongitude());
//            LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(myLocation).title("Me"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 13.0f));
        } catch (SecurityException e){
            e.printStackTrace();
        }
    }
}
