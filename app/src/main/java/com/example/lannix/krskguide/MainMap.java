package com.example.lannix.krskguide;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
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
        GoogleMap.OnGroundOverlayClickListener mClickListener = new GoogleMap.OnGroundOverlayClickListener(){
            @Override
            public void onGroundOverlayClick(GroundOverlay groundOverlay) {
                startActivity(new Intent(getBaseContext(), DescriptionActivity.class));
            }
        };
        googleMap.setOnGroundOverlayClickListener(mClickListener);
        LatLngBounds boxOfCamera = new LatLngBounds(
                new LatLng(55.899683, 92.733175), new LatLng(56.155407, 93.069893));

        googleMap.setLatLngBoundsForCameraTarget(boxOfCamera);
        LatLng testLat = new LatLng(55.969994, 92.786536);

       GroundOverlayOptions newarkMap = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.mipmap.ic_gorki_round))
                .position(testLat, 360f, 350f)
                .clickable(true);
        GroundOverlay mSydney=googleMap.addGroundOverlay(newarkMap);
        mSydney.setTag("aaaaaaaaaa");
        mSydney.getTag();

        googleMap.addGroundOverlay(newarkMap);

//перетаскивает камиру над меткой
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(testLat));

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
