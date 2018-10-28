package com.example.mrrs.schoolhelper;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.mrrs.schoolhelper.model.LocationMap;
import com.example.mrrs.schoolhelper.service.APIService;
import com.example.mrrs.schoolhelper.service.Dataservice;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private MapView mapView;
    private GoogleMap gmap;
    private LocationManager locationManager;
    private Criteria criteria;
    private Location location;
    private String provider;
    private LocationListener locationListener;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    public static final int LOCATION_PERMISSION = 99;
    private FloatingActionMenu materialDesignFAM;
    private FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;
    CheckPermission checkPermission;
    private ArrayList<LocationMap> locationMapArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Disable Portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_service);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.service_toolbar);
        mToolbar.setTitle(getString(R.string.service));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceActivity.super.onBackPressed();
            }
        });
        GetLatLongLocation();
        // Create object to run check permission function
        checkPermission = new CheckPermission();
//        Check location permission
        if (checkPermission.checkPermission(ServiceActivity.this)) {
            // Permission granted
        } else {
            // Permission not granted
            checkPermission.requestPermission(ServiceActivity.this, LOCATION_PERMISSION);
        }
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
        // Assign float button
        handleFloatButton();
    }

    private void handleFloatButton() {
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.float_button_main);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.service_floatBtn_item_home);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.service_floatBtn_item_food);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.service_floatBtn_item_work);
        // Catch event
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SearchMarker("Home");
                //TODO something when floating action menu first item clicked
                materialDesignFAM.close(true);
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SearchMarker("Food");
                //TODO something when floating action menu first item clicked
                materialDesignFAM.close(true);
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SearchMarker("Work");
                //TODO something when floating action menu first item clicked
                materialDesignFAM.close(true);
            }
        });
    }
    //Search Display Marker
    private void SearchMarker (String select_stay){
        for(int i = 0; i < locationMapArrayList.size(); i++){
            Double latmap = Double.valueOf(locationMapArrayList.get(i).getLatmap());
            Double longmap = Double.valueOf(locationMapArrayList.get(i).getLongmap());
            String stay = locationMapArrayList.get(i).getStay();
            if(stay.equals(select_stay)){
                gmap.clear();
                CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(latmap, longmap));
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
                MarkerOptions mp = new MarkerOptions();
                mp.position(new LatLng(latmap, longmap));
                mp.title(select_stay);
                mp.snippet("Lat: "+latmap +" Long: "+longmap);
                mp.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                gmap.addMarker(mp);
                gmap.moveCamera(center);
                gmap.animateCamera(zoom);
            }
        }
    }
    //  CHECK FOR LOCATION PERMISSION
    public static boolean checkPermission(Activity activity) {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    //REQUEST FOR PERMISSSION
    public static void requestPermission(Activity activity, final int code) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(activity, "GPS permission allows us to access location data. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, code);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }
        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        gmap.setIndoorEnabled(true);
        UiSettings uiSettings = gmap.getUiSettings();
        uiSettings.setAllGesturesEnabled(true);
        //        Check location permission
        if (checkPermission.checkPermission(ServiceActivity.this)) {
            // Permission granted
        } else {
            // Permission not granted
            checkPermission.requestPermission(ServiceActivity.this, LOCATION_PERMISSION);
        }
        // Set button get current location
        gmap.setMyLocationEnabled(true);
        // get current location
        // Getting LocationManager object from System Service LOCATION_SERVICE
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // Creating a criteria object to retrieve provider
        criteria = new Criteria();
        // Getting the name of the best provider
        provider = locationManager.getBestProvider(criteria, true);
        // Getting Current LocationMap
        location = locationManager.getLastKnownLocation(provider);
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // redraw the marker when get location update.
//                drawMarker(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        //Get current
        gmap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
//                gmap.clear();
                MarkerOptions mp = new MarkerOptions();
                mp.position(new LatLng(location.getLatitude(), location.getLongitude()));
                mp.title("My Position");
                gmap.addMarker(mp);
                gmap.moveCamera(center);
                gmap.animateCamera(zoom);
        }
        });
        locationManager.requestLocationUpdates(provider, 20000, 0, locationListener);
    }

//    private void drawMarker(Location location) {
//        // Remove any existing markers on the map
////        gmap.clear();
//        LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());
//        gmap.addMarker(new MarkerOptions()
//                .position(currentPosition)
//                .snippet("Lat:" + location.getLatitude() + "Lng:" + location.getLongitude())
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//                .title("ME"));
//        gmap.setOnMarkerClickListener(
//                new GoogleMap.OnMarkerClickListener() {
//                    boolean doNotMoveCameraToCenterMarker = true;
//
//                    public boolean onMarkerClick(Marker marker) {
//                        //Do whatever you need to do here ....
//                        return doNotMoveCameraToCenterMarker;
//                    }
//                });
//    }

    private void setMarker(Float mLat, Float mLong, String type) {
        LatLng place = new LatLng(mLat, mLong);
        gmap.addMarker(new MarkerOptions()
                .position(place)
                .snippet("Lat:" + mLat + "Lng:" + mLong)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title(type));

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void GetLatLongLocation() {
        Dataservice dataservice = APIService.getService();
        Call<List<LocationMap>> callback = dataservice.GetDataLocation();
        callback.enqueue(new Callback<List<LocationMap>>() {
            @Override
            public void onResponse(Call<List<LocationMap>> call, Response<List<LocationMap>> response) {
                locationMapArrayList = (ArrayList<LocationMap>) response.body();
            }

            @Override
            public void onFailure(Call<List<LocationMap>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(ServiceActivity.this, "this is an actual network failure" +"\n"+":( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                    Intent numbersIntent = new Intent(ServiceActivity.this, HomeActivity.class);
                    startActivity(numbersIntent);
                }
                else {
                    Toast.makeText(ServiceActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });

    }
}
