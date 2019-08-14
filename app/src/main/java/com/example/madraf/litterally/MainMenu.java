package com.example.madraf.litterally;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MainMenu extends AppCompatActivity{

    ImageButton profile;

    String getThemeku,email,randomValue;
    String themeku = "";
    String SHARED_PREFS = "codeTheme";

    View bgview;
    ImageView icontheme,bayangan;
    FirebaseAuth mAuth;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {



        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_pickup:
                    startActivity(new Intent(MainMenu.this, Setor.class));
                    return true;
                case R.id.navigation_pet:
                    startActivity(new Intent(MainMenu.this, Recycle.class));
                    return true;
                case R.id.navigation_profile:
                    startActivity(new Intent(MainMenu.this, ProfileSettings.class));
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mAuth = FirebaseAuth.getInstance();

        profile = findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationv = AnimationUtils.loadAnimation(MainMenu.this,R.anim.rotate);
                profile.startAnimation(animationv);
                PopupMenu popupMenu = new PopupMenu(MainMenu.this,profile);
                popupMenu.getMenuInflater().inflate(R.menu.pet_menu, popupMenu.getMenu());

                popupMenu.show();
            }
        });

        icontheme = findViewById(R.id.icontheme);
        bayangan = findViewById(R.id.bayangan);
        bgview = findViewById(R.id.bgview);

        if(mAuth.getCurrentUser()!= null){
            FirebaseUser user = mAuth.getCurrentUser();
            email = user.getEmail().toString();
            email = email.substring(0, email.indexOf("@"));

            //AllowAccesstoAccount();
            getThemeku = ("green");
            changeOurTheme();
        }else{
            changeOurTheme();
        }

        Animation animation = AnimationUtils.loadAnimation(MainMenu.this,R.anim.idle);
        icontheme.startAnimation(animation);

        icontheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                balarunimation();
                balarunToast();
            }
        });

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }

    public void balarunimation(){

        Animation animation = AnimationUtils.loadAnimation(MainMenu.this,R.anim.idle);
        Animation animation1 = AnimationUtils.loadAnimation(MainMenu.this,R.anim.wiggle);
        Animation animation2 = AnimationUtils.loadAnimation(MainMenu.this,R.anim.zoomin);

        Random random = new Random();
        int i1 = random.nextInt(4 - 1) + 1;
        randomValue = String.valueOf((int) (i1));


        AnimationSet as = new AnimationSet(false);

        switch (randomValue) {
            case "1":
                icontheme.setImageResource(R.drawable.balag_blush);
                break;
            case "2":
                icontheme.setImageResource(R.drawable.balag_angry);
                break;
            case "3":
                icontheme.setImageResource(R.drawable.balag_sleep);
                icontheme.startAnimation(animation2);
                break;
        }

        as.addAnimation(animation1);
        as.addAnimation(animation);
        icontheme.startAnimation(as);

        /*icontheme.startAnimation(animation1);
        if(animation1.hasEnded()){
            icontheme.clearAnimation();
            icontheme.startAnimation(animation);
        }*/
        //icontheme.startAnimation(animation);
        //bayangan.startAnimation(animation);

    }

    public void balarunToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_balarunta, (ViewGroup) findViewById(R.id.toast_root));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 150);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }


    private void AllowAccesstoAccount() {
        DatabaseReference themeRef;
        themeRef = FirebaseDatabase.getInstance().getReference();


        themeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                themeku = String.valueOf(dataSnapshot.child("themeku").getValue());


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void changeOurTheme(){

        //getThemeku = (""+themeku);

        if(getThemeku.equals("blue")) {
            icontheme.setImageResource(R.drawable.icmob);
            bgview.setBackgroundResource(R.drawable.bgblue);
            bgview.setScaleY(3);
            bgview.setScaleX(3);

        }
        else if(getThemeku.equals("green")) {
            icontheme.setImageResource(R.drawable.icmog);
            bgview.setBackgroundResource(R.drawable.bggreen);
            bgview.setScaleY(3);
            bgview.setScaleX(3);
        }
        else if(getThemeku.equals("red")) {
            icontheme.setImageResource(R.drawable.icmor);
            bgview.setBackgroundResource(R.drawable.bgred);
            bgview.setScaleY(3);
            bgview.setScaleX(3);
        }

    }

}
