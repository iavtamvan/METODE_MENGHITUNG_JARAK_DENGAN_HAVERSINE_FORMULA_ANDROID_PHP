package com.dedykuncoro.haversine;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.dedykuncoro.haversine.adapter.CustomListAdapter;
import com.dedykuncoro.haversine.app.AppController;
import com.dedykuncoro.haversine.module.Jarak;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuncoro on 03/29/2016.
 */
public class MainActivity extends AppCompatActivity implements LocationListener,
        SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout swipe;
    ListView list;
    CustomListAdapter adapter;
    List<Jarak> itemList = new ArrayList<>();
    Double latitude, longitude;
    Criteria criteria;
    Location location;
    LocationManager locationManager;
    String provider;

    private static final String TAG = MainActivity.class.getSimpleName();

    /* 10.0.2.2 adalah IP Address localhost EMULATOR ANDROID STUDIO,
    Ganti IP Address tersebut dengan IP Laptop Apabila di RUN di HP. HP dan Laptop harus 1 jaringan */
    private static final String url = "http://192.168.43.57/local/haversine/haversine.php?lat=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // menyamakan variabel pada layout dan java
        list = (ListView) findViewById(R.id.list);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);

        // mengisi data dari adapter ke listview
        adapter = new CustomListAdapter(this, itemList);
        list.setAdapter(adapter);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();

        provider = locationManager.getBestProvider(criteria, false);

        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           lokasi();
                       }
                   }
        );

    }

    @Override
    public void onRefresh() {
        lokasi();
    }

    // fungsi ngecek lokasi GPS device pengguna
    private void lokasi() {
        location = locationManager.getLastKnownLocation(provider);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // permintaan update lokasi device dalam waktu per detik
        locationManager.requestLocationUpdates(provider, 1000, 1, this);

        if (location != null) {
            onLocationChanged(location);
        } else {
           /* latitude longitude Alun-alun Demak sebagai default jika tidak ditemukan lokasi dari device pengguna */
            callListVolley(-6.894796, 110.638413);
        }
    }

    // untuk menampilkan lokasi wisata terdekat dari device pengguna
    private void callListVolley(double lat, double lng) {
        itemList.clear();
        adapter.notifyDataSetChanged();

        swipe.setRefreshing(true);

        JsonArrayRequest jArr = new JsonArrayRequest(url + lat + "&lng=" + lng,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, response.toString());

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Jarak j = new Jarak();
                                j.setNama(obj.getString("nama"));
                                j.setGambar(obj.getString("gambar"));

                                double jarak = Double.parseDouble(obj.getString("jarak"));

                                j.setJarak("" + round(jarak, 2));

                                itemList.add(j);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // memberitahu adapter jika ada perubahan data
                        adapter.notifyDataSetChanged();

                        swipe.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                swipe.setRefreshing(false);
            }
        });

        // menambah permintaan ke queue
        AppController.getInstance().addToRequestQueue(jArr);
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }

    // untuk menyederhanakan angka dibelakang koma jarak
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    // untuk menentukan lokasi gps dari device pengguna
    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        // untuk melihat latitude longitude posisi device pengguna pada logcat ditemukan atau tidak
        Log.e(TAG, "User location latitude:" + latitude + ", longitude:" + longitude);

        callListVolley(latitude, longitude);
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
}
