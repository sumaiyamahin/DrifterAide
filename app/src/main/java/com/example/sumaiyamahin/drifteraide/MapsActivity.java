package com.example.sumaiyamahin.drifteraide;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<double[]> locations = new ArrayList<>();
    private List<double[]> food = new ArrayList<>();
    private List<double[]> shelter = new ArrayList<>();
    private LatLng loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        double[] l1 = {40.747217, -73.990274};
        double[] l2 = {40.739600, -74.002170};
        double[] l3 = {40.785667, -73.950084};
        locations.add(l1);
        locations.add(l2);
        locations.add(l3);
        double[] f1 = {40.744917, -73.987731};
        double[] f2 = {40.746965, -73.984802};
        food.add(f1);
        food.add(f2);
        double[] s1 = {40.746071, -73.984341};
        double[] s2 = {40.746770, -73.993106};
        shelter.add(s1);
        shelter.add(s2);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        loc = new LatLng(locations.get(0)[0], locations.get(0)[1]);
        mMap.addMarker(new MarkerOptions().position(loc).title("NYC-Link"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 15.0f));
        if(MainActivity.necessity == MainActivity.FOOD){
            loc = new LatLng(food.get(0)[0], food.get(0)[1]);
            mMap.addMarker(new MarkerOptions().position(loc).title("Isreal Food Bank"));
            loc = new LatLng(food.get(1)[0], food.get(1)[1]);
            mMap.addMarker(new MarkerOptions().position(loc).title("City Harvest"));
            //new RetrieveFeedTask().execute();
        }
        if(MainActivity.necessity == MainActivity.SHELTER){
            loc = new LatLng(shelter.get(0)[0], shelter.get(0)[1]);
            mMap.addMarker(new MarkerOptions().position(loc).title("Christian Herald Association"));
            loc = new LatLng(shelter.get(1)[0], shelter.get(1)[1]);
            mMap.addMarker(new MarkerOptions().position(loc).title("Partnership for the Homeless"));
        }
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            String json = null;
            String output = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?key=AIzaSyAWWU-xQZJ3pC3gkoJ2nMkCYZ63-xJ7zC8ec&input=food%20pantries&inputtype=textquery&locationbias=circle:50@"+loc;
            try {
                URL url = new URL(output);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setInstanceFollowRedirects(false);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("charset", "utf-8");
                connection.connect();
                InputStream inStream = connection.getInputStream();
                ByteArrayOutputStream into = new ByteArrayOutputStream();
                byte[] buf = new byte[4096];
                for (int n; 0 < (n = inStream.read(buf));) {
                    into.write(buf, 0, n);
                }
                into.close();
                json = new String(into.toByteArray(), "UTF-8");
                JSONObject obj = new JSONObject(json);
                System.out.println(obj);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}