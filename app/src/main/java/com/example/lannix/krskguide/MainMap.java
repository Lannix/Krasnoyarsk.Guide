package com.example.lannix.krskguide;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;


public class MainMap extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnPoiClickListener {
    private List<LatLng> places = new ArrayList<>();
    private GoogleMap mMap;
    private static final String TAG = MainMap.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnPoiClickListener(this);
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        LatLngBounds ADELAIDE = new LatLngBounds(
                new LatLng(55.899683, 92.733175), new LatLng(56.155407, 93.069893));
// Constrain the camera target to the Adelaide bounds.
        googleMap.setLatLngBoundsForCameraTarget(ADELAIDE);
        LatLng sydney = new LatLng(56.030727, 92.914512);
        googleMap.addMarker(new MarkerOptions().position(sydney).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_gorki))).setTitle("kssf");

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
    @Override
    public void onPoiClick(PointOfInterest poi) {
        Toast.makeText(getApplicationContext(), "Clicked: " +
                        poi.name + "\nPlace ID:" + poi.placeId +
                        "\nLatitude:" + poi.latLng.latitude +
                        " Longitude:" + poi.latLng.longitude,
                Toast.LENGTH_SHORT).show();
    }

}
