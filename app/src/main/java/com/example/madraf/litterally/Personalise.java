package com.example.madraf.litterally;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Personalise extends AppCompatActivity {

    Button btngreen, btnblue, btnred, btnsave;
    View holderbg, dynamicbg;

    String getThemeku;
    String themeku = "";

    private ProgressDialog loadingBar;

    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;

    String SHARED_PREFS = "codeTheme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalise);

        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);



        // get the id
        btnsave = findViewById(R.id.btnsave);

        // give an event to next activity
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser()!=null){
                    FirebaseUser user = mAuth.getCurrentUser();
                    loadUserinfo(user);}
                else{
                    Intent a = new Intent(Personalise.this,LoginActivity.class);
                }
            }
        });

        btngreen = findViewById(R.id.btngreen);
        btnblue = findViewById(R.id.btnblue);
        btnred = findViewById(R.id.btnred);

        holderbg = findViewById(R.id.holderbg);
        dynamicbg = findViewById(R.id.dynamicbg);

        // set the first-time background
        holderbg.setBackgroundResource(R.drawable.bggreen);
        holderbg.setScaleY(3);
        holderbg.setScaleX(3);

            // set the scale of button clicked
            btngreen.setScaleY(1.5f);
            btngreen.setScaleX(1.5f);

            // save to local storage (default)
            /*String themeku = "";
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(themeku, "green");
            editor.apply();*/

        // set function of changing theme
        btnblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // scale animation
                btnblue.animate().translationY(20).scaleX(1.5f).scaleY(1.5f).setDuration(350).start();

                // default the scale buttons
                btngreen.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
                btnred.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();

                // change the background
                dynamicbg.setBackgroundResource(R.drawable.bgblue);
                dynamicbg.animate().scaleX(3).scaleY(3).setDuration(800).start();

                // change color of button
                btnsave.setTextColor(Color.parseColor("#3498db"));

                // save to local storage
                String themeku = "";
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(themeku, "blue");
                editor.apply();


                // timer for change the holderbg
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        holderbg.setScaleX(3);
                        holderbg.setScaleY(3);
                        holderbg.setBackgroundResource(R.drawable.bgblue);
                        dynamicbg.setScaleX(0);
                        dynamicbg.setScaleY(0);
                    }
                }, 850);

            }
        });

        // set function of changing theme
        btngreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // scale animation
                btngreen.animate().scaleX(1.5f).scaleY(1.5f).setDuration(350).start();

                // default the scale buttons
                btnblue.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
                btnred.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();

                // change the background
                dynamicbg.setBackgroundResource(R.drawable.bggreen);
                dynamicbg.animate().scaleX(3).scaleY(3).setDuration(800).start();

                // change color of button
                btnsave.setTextColor(Color.parseColor("#1bac9c"));

                // save to local storage
                String themeku = "";
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(themeku, "green");
                editor.apply();

                // timer for change the holderbg
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        holderbg.setScaleX(3);
                        holderbg.setScaleY(3);
                        holderbg.setBackgroundResource(R.drawable.bggreen);
                        dynamicbg.setScaleX(0);
                        dynamicbg.setScaleY(0);
                    }
                }, 850);

            }
        });


        // set function of changing theme
        btnred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // scale animation
                btnred.animate().translationY(20).scaleX(1.5f).scaleY(1.5f).setDuration(350).start();

                // default the scale buttons
                btngreen.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
                btnblue.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();

                // change the background
                dynamicbg.setBackgroundResource(R.drawable.bgred);
                dynamicbg.animate().scaleX(3).scaleY(3).setDuration(800).start();

                // change color of button
                btnsave.setTextColor(Color.parseColor("#FF8D7E"));

                // save to local storage
                String themeku = "";
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(themeku, "red");
                editor.apply();

                // timer for change the holderbg
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        holderbg.setScaleX(3);
                        holderbg.setScaleY(3);
                        holderbg.setBackgroundResource(R.drawable.bgred);
                        dynamicbg.setScaleX(0);
                        dynamicbg.setScaleY(0);
                    }
                }, 850);

            }
        });
    }


    private void loadUserinfo(FirebaseUser user) {
        final FirebaseUser user_on = mAuth.getCurrentUser();
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            String email = user.getEmail().toString();
            email = email.substring(0, email.indexOf("@"));

            loadingBar.setTitle("Saving Theme..");
            loadingBar.setMessage("Please wait, while we apply the theme..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            themeku = sharedPreferences.getString(themeku, "");
            ValidateUser(email, themeku);
        }


    }

    private void ValidateUser(final String email, final String themeku) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("UserTheme").child(email).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("email", email);
                    userdataMap.put("themeku", themeku);

                    RootRef.child("UserTheme").updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Personalise.this, "Your theme has been saved", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                saveTheme();
                            }else{
                                loadingBar.dismiss();
                                Toast.makeText(Personalise.this, "Network Error ", Toast.LENGTH_SHORT).show();
                                Intent a = new Intent(Personalise.this,LoginActivity.class);
                            }

                        }
                    });

                }else if((dataSnapshot.child("UserTheme").child(themeku).exists())){
                    Intent intent = new Intent(Personalise.this,MainMenu.class);
                    startActivity(intent);
                }
                else{
                    loadingBar.dismiss();
                    Toast.makeText(Personalise.this, "Theme failed..", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void saveTheme() {


        Intent a = new Intent(Personalise.this, MainMenu.class);
        startActivity(a);
        finish();
    }
}
