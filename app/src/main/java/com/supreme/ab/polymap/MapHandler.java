package com.supreme.ab.polymap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.GeoPolyline;
import com.here.sdk.core.errors.InstantiationErrorException;
import com.here.sdk.mapviewlite.MapPolyline;
import com.here.sdk.mapviewlite.MapPolylineStyle;
import com.here.sdk.mapviewlite.MapStyle;
import com.here.sdk.mapviewlite.MapViewLite;
import com.here.sdk.mapviewlite.PixelFormat;
import com.here.sdk.routing.CalculateRouteCallback;
import com.here.sdk.routing.Route;
import com.here.sdk.routing.RoutingEngine;
import com.here.sdk.routing.RoutingError;
import com.here.sdk.routing.Waypoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapHandler {
     String TAG="";
    Context context;
    MapViewLite mapView;
    RoutingEngine routingEngine;
    GeoCoordinates startGeoCoordinates, destinationGeoCoordinates;
    Route routee;
    private final List<MapPolyline> mapPolylines = new ArrayList<>();
    public MapHandler(Context context, MapViewLite mapView){
        this.context=context;
        this.mapView=mapView;
    }

    public Route Route(GeoCoordinates detG) {
        startGeoCoordinates= new GeoCoordinates(11.59775, 37.39564);
        this.destinationGeoCoordinates= detG;
//                new GeoCoordinates(11.59775, 37.39564);
        try {
            routingEngine = new RoutingEngine();
        } catch (InstantiationErrorException e) {
            new RuntimeException("Initialization of RoutingEngine failed: " + e.error.name());
        }
        Waypoint startWaypoint = new Waypoint(startGeoCoordinates);
        Waypoint destinationWaypoint = new Waypoint(destinationGeoCoordinates);

        List<Waypoint> waypoints =
                new ArrayList<>(Arrays.asList(startWaypoint, destinationWaypoint));

        routingEngine.calculateRoute(
                waypoints,
                new RoutingEngine.CarOptions(),
                (routingError, routes) -> {
                    if (routingError == null) {
                        Route route = routes.get(0);
                        this.routee = route;
                        showRouteDetails(route);
//                        showRouteOnMap(route);
                    } else {
                        showDialog("Error while calculating a route:", routingError.toString());
                    }
                });
            return routee;

    }

    private void showRouteDetails(Route route) {
        int sec = route.getTravelTimeInSeconds();
        int meteres= route.getLengthInMeters();
        int actualSec=sec%60;
        int min= sec/60;
        int hr= min/60;
        showDialog("Route Details", "Distance : "+meteres+" Meters"+"\n " +
                "Estimated time travel : "+hr+" Hours, "+min+" Minutes, "+actualSec +" Seconds", route );
    }

    public void showDialog(String title, String message, Route route){
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(R.drawable.ic__place_foreground);
        builder.setPositiveButton("Directions to here", (dialog, which) -> {
                showRouteOnMap(route);
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            builder.setCancelable(true);
            }
        });
        builder.show();

    }
    public void showRouteOnMap(Route route){
        GeoPolyline routeGeoPolyline;
        try {
            routeGeoPolyline = new GeoPolyline(route.getShape());
        } catch (InstantiationErrorException e) {
            e.printStackTrace();
            return;
        }
        MapPolylineStyle mapPolylineStyle = new MapPolylineStyle();
        mapPolylineStyle.setColor(0x00908AA0, PixelFormat.RGBA_8888);
        mapPolylineStyle.setWidth(10);
        MapPolyline routeMapPolyline = new MapPolyline(routeGeoPolyline, mapPolylineStyle);
        mapView.getMapScene().addMapPolyline(routeMapPolyline);
        mapPolylines.add(routeMapPolyline);


//        addCircleMapMarker(startGeoCoordinates, R.drawable.green_dot);
//        addCircleMapMarker(destinationGeoCoordinates, R.drawable.green_dot);

    }
    public static void showDialog(String title, String message,Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(R.drawable.ic__place_foreground);
        builder.show();
        builder.setPositiveButton("show On Map", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                context.startActivity(new Intent(, MainActivity.class));
            }
        });
    }
    public  void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(R.drawable.ic__place_foreground);
        builder.show();
    }
    public GeoCoordinates moveTo(float Lat, float Long){
        double d1= Lat;
        double d2= Long;
       return (new GeoCoordinates(d1, d2));
    }
    public void GetMyLocation(){
        LocationManager locationManager;
    }
    public void clearMap() {
        clearRoute();
    }

    private void clearRoute() {
        for (MapPolyline mapPolyline : mapPolylines) {
            mapView.getMapScene().removeMapPolyline(mapPolyline);
        }
        mapPolylines.clear();
    }


}
