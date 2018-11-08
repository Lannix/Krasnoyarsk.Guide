package com.example.lannix.krskguide.activity.map;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.lannix.krskguide.R;
import com.example.lannix.krskguide.database.sight_db.Sight;
import com.example.lannix.krskguide.fragment.map.InfoOfObjectsFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;

import java.util.ArrayList;
import java.util.List;

import static com.example.lannix.krskguide.activity.main.MainActivity.DB_SIGHTS;


public class MainMap extends FragmentActivity implements OnMapReadyCallback, MainMapInterface {
    private List<LatLng> places = new ArrayList<>();
    private GoogleMap mMap;
    private LinearLayout linearLayout;
    private static final String LOG_TAG = MainMap.class.getSimpleName();
    Bundle bundle = new Bundle();
    private InfoOfObjectsFragment infoFragment = new InfoOfObjectsFragment();
    MainMapInterface mainMapInterface;
    Marker markeR;
    boolean mFlag;
    boolean isNoMarker;
    public static final String TAG = "map_tag";
    private float zoom = 14;
    private ArrayList<GroundOverlay> arrayObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        linearLayout = findViewById(R.id.constraintLayoutMainMap);
        linearLayout.setVisibility(View.INVISIBLE);
        isNoMarker = true;
        mFlag=false;

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e(LOG_TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(LOG_TAG, "Can't find style. Error: ", e);
        }

        markeR = googleMap.addMarker(new MarkerOptions().position(new LatLng(0,0)));
        markeR.setVisible(true);


        //Слушатель клика по достопримечательностям
        GoogleMap.OnGroundOverlayClickListener mClickListener = new GoogleMap.OnGroundOverlayClickListener() {
            @Override
            public void onGroundOverlayClick(GroundOverlay groundOverlay) {
                linearLayout.setVisibility(View.VISIBLE);
                markeR.setVisible(false);
                mFlag=false;
                String tg = String.valueOf(groundOverlay.getTag());
                bundle.putString(TAG, tg);
                infoFragment.setArguments(bundle);

                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(groundOverlay.getPosition());
                googleMap.animateCamera(cameraUpdate);
                fragmentMapInfoCreate();
            }
        };
        googleMap.setOnGroundOverlayClickListener(mClickListener);


        //Слушатель Клика по карте
        GoogleMap.OnMapClickListener mapClickListener = new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
                googleMap.animateCamera(cameraUpdate);

                markeR.setVisible(true);
                mFlag=true;

                fragmentMapInfoDelete();
                markeR.setPosition(latLng);
            }
        };
        googleMap.setOnMapClickListener(mapClickListener);


        //шлушатель клика по маркеру
        GoogleMap.OnMarkerClickListener onMarkerClickListener = new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if ((marker != markeR) && (isNoMarker)) {
                    linearLayout.setVisibility(View.VISIBLE);
                    markeR.setVisible(false);
                    mFlag=false;
                    String tg = String.valueOf(marker.getTag());
                    bundle.putString(TAG, tg);
                    infoFragment.setArguments(bundle);

                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(marker.getPosition());
                    googleMap.animateCamera(cameraUpdate);
                    fragmentMapInfoCreate();

                    //if (googleMap.getCameraPosition().zoom<)
                }
                return true;
            }
        };
        googleMap.setOnMarkerClickListener(onMarkerClickListener);


        final ArrayList<Sight> dB = DB_SIGHTS.selectAll();



        GoogleMap.OnCameraMoveListener onCameraMoveListener = new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {



                if (googleMap.getCameraPosition().zoom >= 17.1) {
                    if (!isNoMarker) {
                        googleMap.clear();
                        drowMarker(dB, googleMap);
                        isNoMarker = !isNoMarker;
                    }
                } else if (googleMap.getCameraPosition().zoom < 17.1) if (isNoMarker) {
                    googleMap.clear();
                    drowNoMarker(dB, googleMap);
                    isNoMarker = !isNoMarker;
                }
            }


        };

        googleMap.setOnCameraMoveListener(onCameraMoveListener);


        arrayObjects = new ArrayList<>();

        LatLngBounds boxOfCamera = new LatLngBounds(
                new LatLng(55.899683, 92.733175), new LatLng(56.155407, 93.069893));

        googleMap.setLatLngBoundsForCameraTarget(boxOfCamera);
        LatLng testLat = new LatLng(56.008035, 92.869761);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(testLat));


        for (int i = 0; i < dB.size(); i++) {
            Marker nMarker = googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(dB.get(i).getMap_image_id())).position(new LatLng(dB.get(i).getCoordinates_latitude(), dB.get(i).getCoordinates_longitude()))
                    .position(new LatLng(dB.get(i).getCoordinates_latitude(), dB.get(i).getCoordinates_longitude())));
            nMarker.setTag(dB.get(i).getId());
                /*GroundOverlay groundOverlay=googleMap.addGroundOverlay(new GroundOverlayOptions()
                    .image()
                    .position(new LatLng(dB.get(i).getCoordinates_latitude(),dB.get(i).getCoordinates_longitude()), 1400f/zoom, 1400f/zoom)
                    .clickable(true));
            groundOverlay.setTag(dB.get(i).getId());*/

        }


        //googleMap.addGroundOverlay(newarkMap);

        //перетаскивает камиру над меткой


    }




    @Override
    public void fragmentMapInfoCreate() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();


        android.support.v4.app.Fragment fragment = fm.findFragmentById(R.id.constraintLayoutMainMap);


        infoFragment.setArguments(bundle);


        fm.beginTransaction()
                .replace(R.id.constraintLayoutMainMap, infoFragment)
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void fragmentMapInfoDelete() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();


        android.support.v4.app.Fragment fragment = fm.findFragmentById(R.id.constraintLayoutMainMap);


        infoFragment.setArguments(bundle);


        fm.beginTransaction()
                .remove(infoFragment)
                .commit();

    }

    void drowNoMarker(ArrayList<Sight> dB, GoogleMap googleMap) {


        for (int i = 0; i < dB.size(); i++) {
            GroundOverlay groundOverlay = googleMap.addGroundOverlay(new GroundOverlayOptions()
                    .image(BitmapDescriptorFactory.fromResource(dB.get(i).getMap_image_id()))
                    .position(new LatLng(dB.get(i).getCoordinates_latitude(), dB.get(i).getCoordinates_longitude()), 45f, 45f)
                    .clickable(true));
            groundOverlay.setTag(dB.get(i).getId());
        }
        markeR = googleMap.addMarker(new MarkerOptions().position(googleMap.getCameraPosition().target));
        if (mFlag) markeR.setVisible(true);else markeR.setVisible(false);
    }

    void drowMarker(ArrayList<Sight> dB, GoogleMap googleMap) {


        for (int i = 0; i < dB.size(); i++) {
            Marker nMarker = googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(dB.get(i).getMap_image_id())).position(new LatLng(dB.get(i).getCoordinates_latitude(), dB.get(i).getCoordinates_longitude()))
                    .position(new LatLng(dB.get(i).getCoordinates_latitude(), dB.get(i).getCoordinates_longitude())));
            nMarker.setTag(dB.get(i).getId());

        }
        markeR = googleMap.addMarker(new MarkerOptions().position(googleMap.getCameraPosition().target));
        if (mFlag) markeR.setVisible(true);else markeR.setVisible(false);

    }
}
