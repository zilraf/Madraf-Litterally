package com.example.madraf.litterally;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class Setor extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    Dialog myDialog;

    TextView littempat, litalamat;

    String getThemeku;
    String themeku = "";
    String SHARED_PREFS = "codeTheme";

    Button pickup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setor);

        //jenisrumah.setText("Google is your friend.", TextView.BufferType.EDITABLE);

        /*List<String> categories = new ArrayList<>();
        categories.add(0,"Choose Building Type");
        categories.add("House");
        categories.add("Kos");
        categories.add("Kantor");
        categories.add("Sekolah");
        categories.add("Hotel/Apartment");
        categories.add("Industri");
        categories.add("Lain");*/
        //setContentView(R.layout.popup_pickup);


        //ArrayAdapter<String> dataAdapter;
        pickup = findViewById(R.id.pickup);

        pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Setor.this,RequestPickup.class);
                startActivity(a);
            }
        });







        myDialog = new Dialog(this);

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

    public void ShowPopup(View v) {
        TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.popup_pickup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("M");
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
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
