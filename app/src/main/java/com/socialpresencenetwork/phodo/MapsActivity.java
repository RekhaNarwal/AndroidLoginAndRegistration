package com.socialpresencenetwork.phodo;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    AppLocationService appLocationService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();
        appLocationService = new AppLocationService(
                MapsActivity.this);
        Location nwLocation = appLocationService
                .getLocation(LocationManager.NETWORK_PROVIDER);

        if (nwLocation != null) {
            double latitude = nwLocation.getLatitude();
            double longitude = nwLocation.getLongitude();
            ShowMarkerOnMap(latitude,longitude);
        }

//        // Acquire a reference to the system Location Manager
//        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//
//// Define a listener that responds to location updates
//        LocationListener locationListener = new LocationListener() {
//            public void onLocationChanged(Location location) {
//                // Called when a new location is found by the network location provider.
//                //makeUseOfNewLocation(location);
//                Log.d("LOCATION", String.valueOf(location.getLatitude()));
//            }
//            public void onStatusChanged(String provider, int status, Bundle extras) {}
//
//            public void onProviderEnabled(String provider) {}
//
//            public void onProviderDisabled(String provider) {}
//        };
//
//// Register the listener with the Location Manager to receive location updates
//        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
//        if (locationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER))
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
//        else
//            Log.d("LOCATION", "No network providers");
//
//
//        if (locationManager.getAllProviders().contains(LocationManager.GPS_PROVIDER))
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//        else
//            Log.d("LOCATION", "No gps provider");
//
//        if (locationManager.getAllProviders().contains(LocationManager.PASSIVE_PROVIDER))
//            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, locationListener);
//        else
//            Log.d("LOCATION", "No passive provider");
//        Log.d("LOCATION", "Providers = " + String.valueOf(locationManager.getAllProviders().size()));
//
//        setUpMapIfNeeded();
//
//        try {
//            getLocation();
//        } catch (Exception e){}
    }

    private void ShowMarkerOnMap(double latitude, double longitude) {
        MarkerOptions marker= new MarkerOptions().position(new LatLng(latitude,longitude))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.post_pin_c))
                .flat(true)
                .title("I'm here!");
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 12));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.addMarker(marker);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        setUpMapIfNeeded();
    }

//    private void getLocation() {
//        // Get the location manager
//        LocationManager locationManager = (LocationManager)
//                getSystemService(LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        String bestProvider = locationManager.getBestProvider(criteria, false);
//        Location location = locationManager.getLastKnownLocation(bestProvider);
//        // Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
//        List<Address> addresses = null;
//
//        LatLng mapLoc = new LatLng(location.getLatitude(), location.getLongitude());
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mapLoc, (float) 11.0));
//
//    }
//
//    /**
//     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
//     * installed) and the map has not already been instantiated.. This will ensure that we only ever
//     * call {@link #setUpMap()} once when {@link #mMap} is not null.
//     * <p/>
//     * If it isn't installed {@link SupportMapFragment} (and
//     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
//     * install/update the Google Play services APK on their device.
//     * <p/>
//     * A user can return to this FragmentActivity after following the prompt and correctly
//     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
//     * have been completely destroyed during this process (it is likely that it would only be
//     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
//     * method in {@link #onResume()} to guarantee that it will be called.
//     */
//    private void setUpMapIfNeeded() {
//        // Do a null check to confirm that we have not already instantiated the map.
//        if (mMap == null) {
//            // Try to obtain the map from the SupportMapFragment.
//            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
//                    .getMap();
//            // Check if we were successful in obtaining the map.
//            if (mMap != null) {
//                setUpMap();
//            }
//        }
//    }
//
//    /**
//     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
//     * just add a marker near Africa.
//     * <p/>
//     * This should only be called once and when we are sure that {@link #mMap} is not null.
//     */
//    private void showCurrentLocation(Location location){
//
//        mMap.clear();
//
//        LatLng currentPosition = new LatLng(location.getLatitude(),location.getLongitude());
//
//        mMap.addMarker(new MarkerOptions()
//                .position(currentPosition)
//                .snippet("Lat: " + location.getLatitude() + ", Lng: " + location.getLongitude())
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.post_pin_c))
//                .flat(true)
//                .title("I'm here!"));
//
//        // Zoom in, animating the camera.
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 18));
//    }
//    private void setUpMap() {
//
//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//
//        Criteria criteria = new Criteria();
//        criteria.setAccuracy(Criteria.ACCURACY_FINE);
//
//        String provider = locationManager.getBestProvider(criteria, true);
//
//        LocationListener locationListener = new LocationListener() {
//
//            @Override
//            public void onLocationChanged(Location location) {
//                showCurrentLocation(location);
//            }
//
//            @Override
//            public void onStatusChanged(String s, int i, Bundle bundle) {
//            }
//
//            @Override
//            public void onProviderEnabled(String s) {
//            }
//
//            @Override
//            public void onProviderDisabled(String s) {
//            }
//        };
//
//        locationManager.requestLocationUpdates(provider, 2000, 0, locationListener);
//
//        // Getting initial Location
//        Location location = locationManager.getLastKnownLocation(provider);
//        // Show the initial location
//        if (location != null) {
//            showCurrentLocation(location);
//        }
//    }

    /******* profile Activity *********/
    public void getLinearProfilePage (View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
    }
    /******* Post Activity *********/
    public void getLinearPostPage (View view) {
        Intent intent = new Intent(getApplicationContext(), PostActivity.class);
        startActivity(intent);
    }
    /******* Place Activity *********/
    public void getLinearPlacePage (View view) {
        Intent intent = new Intent(getApplicationContext(), PlaceActivity.class);
        startActivity(intent);
    }
    /******* Friends Activity *********/
    public void getLinearFriendsPage (View view) {
        Intent intent = new Intent(getApplicationContext(), TabsFragmentActivity.class);
        startActivity(intent);
    }
    /******* Settings Activity *********/
    public void getLinearSettingsPage (View view) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }
}
