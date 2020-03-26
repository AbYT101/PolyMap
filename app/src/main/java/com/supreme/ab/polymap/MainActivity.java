package com.supreme.ab.polymap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapviewlite.MapOverlay;
import com.here.sdk.mapviewlite.MapPolyline;
import com.here.sdk.mapviewlite.MapStyle;
import com.here.sdk.mapviewlite.MapViewLite;
import com.here.sdk.routing.Route;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MapViewLite mapView;
    protected String TAG="";
    private static final float DEFAULT_ZOOM_LEVEL = 14;
    private boolean isNormal=true;
    MapHandler mapHandler;
    LinearLayout linearLayout[];
    private final List<MapPolyline> mapPolylines = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a MapViewLite instance from the layout.
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
//        loadMapScene(new GeoCoordinates(11.59364, 37.39077));
        mapHandler= new MapHandler(this, mapView);
        loadMapSceneNormal();
    }
    private void loadMapSceneSatellite() {
        // Load a scene from the SDK to render the map with a map style.
        mapView.getMapScene().loadScene(MapStyle.SATELLITE, errorCode -> {
            if (errorCode == null) {
                mapView.getCamera().setTarget(new GeoCoordinates(11.59364, 37.39077));
                mapView.getCamera().setZoomLevel(14);
            } else {
                Log.d(TAG, "onLoadScene failed: " + errorCode.toString());
            }
            loadmapOverlays();
        });

    }
    private void loadMapSceneNormal() {
        // Load a scene from the SDK to render the map with a map style.
        mapView.getMapScene().loadScene(MapStyle.NORMAL_DAY, errorCode -> {
            if (errorCode == null) {
                mapView.getCamera().setTarget(new GeoCoordinates(11.59364, 37.39077));
                mapView.getCamera().setZoomLevel(14);
            } else {
                Log.d(TAG, "onLoadScene failed: " + errorCode.toString());
            }
        loadmapOverlays();
        });

    }

    public void loadmapOverlays(){
        TextView textView = new TextView(getApplicationContext());
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        textView.setText("Jorka Event");
        TextView textView1 = new TextView(getApplicationContext());
        textView1.setTextColor(Color.parseColor("#FFFFFF"));
        textView1.setText("Poly Campus, Bahirdar University");
        TextView textView2 = new TextView(getApplicationContext());
        textView2.setTextColor(Color.parseColor("#FFFFFF"));
        textView2.setText("GDG-Bahir dar, Firebase Jam");
        TextView textView3 = new TextView(getApplicationContext());
        textView3.setTextColor(Color.parseColor("#FFFFFF"));
        textView3.setText("GDG-Bahir dar, Flutter Basics");


        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
        linearLayout.setBackgroundResource(R.color.gray);
        linearLayout.setPadding(10, 10, 10, 10);
        linearLayout.addView(textView);

        LinearLayout linearLayout1 = new LinearLayout(getApplicationContext());
        linearLayout1.setBackgroundResource(R.color.gray);
        linearLayout1.setPadding(10, 10, 10, 10);
        linearLayout1.addView(textView1);

        LinearLayout linearLayout2 = new LinearLayout(getApplicationContext());
        linearLayout2.setBackgroundResource(R.color.gray);
        linearLayout2.setPadding(10, 10, 10, 10);
        linearLayout2.addView(textView2);
        LinearLayout linearLayout3 = new LinearLayout(getApplicationContext());
        linearLayout3.setBackgroundResource(R.color.gray);
        linearLayout3.setPadding(10, 10, 10, 10);
        linearLayout3.addView(textView3);
        GeoCoordinates geoCoordinates = new GeoCoordinates(11.59364,37.38077);
        GeoCoordinates geoCoordinates1= new GeoCoordinates(11.59775,37.39564);
        GeoCoordinates geoCoordinates2= new GeoCoordinates(11.57432,37.39792);
        GeoCoordinates geoCoordinates3 = new GeoCoordinates(11.598258, 37.381705);

        MapOverlay<LinearLayout> mapOverlay = new MapOverlay<>(linearLayout, geoCoordinates);
        MapOverlay<LinearLayout> mapOverlay1 = new MapOverlay<>(linearLayout1, geoCoordinates1);
        MapOverlay<LinearLayout> mapOverlay2 = new MapOverlay<>(linearLayout2, geoCoordinates2);
        MapOverlay<LinearLayout> mapOverlay3 = new MapOverlay<>(linearLayout3, geoCoordinates3);

        mapView.addMapOverlay(mapOverlay);
        mapView.addMapOverlay(mapOverlay1);
        mapView.addMapOverlay(mapOverlay2);
        mapView.addMapOverlay(mapOverlay3);

        textView.setOnClickListener((View v) -> {
            Toast.makeText(getApplicationContext(), "Bahir dar", Toast.LENGTH_SHORT).show();
            mapHandler= new MapHandler(this, mapView);
            Route route= mapHandler.Route(new GeoCoordinates(11.59364,37.38077));
//                showRouteOnMap(route);
        });
        textView1.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Poly Campus", Toast.LENGTH_SHORT).show();
            mapHandler= new MapHandler(this, mapView);
            Route route= mapHandler.Route(new GeoCoordinates(11.59775,37.39564));
        });
        textView2.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Peda Campus", Toast.LENGTH_SHORT).show();
            mapHandler= new MapHandler(this, mapView);
            Route route= mapHandler.Route(new GeoCoordinates(11.57432,37.39792));
        });
        textView3.setOnClickListener(v ->{
            Toast.makeText(getApplicationContext(), "American Cornor", Toast.LENGTH_SHORT).show();
            mapHandler= new MapHandler(this, mapView);
            Route route= mapHandler.Route(new GeoCoordinates(11.598258, 37.381705));
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clearmap:
                mapHandler.clearMap();
                break;
            case R.id.My_Location:
//                mapView.getMapScene().loadScene(MapStyle.NORMAL_DAY, );
//                checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, 112);
                break;
            case R.id.ChangeStyle:
                if (isNormal==true){
                    loadMapSceneSatellite();
                    isNormal=false;
                }
                else if(isNormal==false){
                    loadMapSceneNormal();
                    isNormal=true;
            }
                break;
            case R.id.exitApp:
                System.exit(1);
                break;
            default:
                //Do Nothing
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    }

