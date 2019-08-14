package com.example.madraf.litterally;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class Setor extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    TextView littempat, litalamat;

    String getThemeku;
    String themeku = "";
    String SHARED_PREFS = "codeTheme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setor);

        litalamat = findViewById(R.id.litalamat);
        littempat = findViewById(R.id.littempat);

        changeOurTheme();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapfrag);
        mapFragment.getMapAsync(this);
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
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style));

        // Add a marker in Sydney and move the camera
        LatLng markas = new LatLng(-6.204412, 106.780354);
        mMap.addMarker(new MarkerOptions().position(markas).title("Lokasi Markas"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markas, 12F));
    }

    public void changeOurTheme() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        getThemeku = sharedPreferences.getString(themeku, "");

        if (getThemeku.equals("blue")) {
            littempat.setTextColor(Color.parseColor("#3498db"));
            litalamat.setTextColor(Color.parseColor("#3498db"));
        } else if (getThemeku.equals("green")) {
            littempat.setTextColor(Color.parseColor("#3498db"));
            litalamat.setTextColor(Color.parseColor("#3498db"));
        } else if (getThemeku.equals("red")) {
            littempat.setTextColor(Color.parseColor("#3498db"));
            litalamat.setTextColor(Color.parseColor("#3498db"));
        }

    }

}
