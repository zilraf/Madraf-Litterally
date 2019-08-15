package com.example.madraf.litterally;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class ProfileSettings extends AppCompatActivity {

    Button btnsave2, logout;
    /*View holderbg, dynamicbg;*/
    TextView username,displayname;
    ImageView userphoto;

    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;


    String SHARED_PREFS = "codeTheme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        mAuth = FirebaseAuth.getInstance();

        displayname = findViewById(R.id.nameuser);
        username = findViewById(R.id.uname);
        userphoto = findViewById(R.id.userphoto);

        if (mAuth.getCurrentUser() != null) {
            FirebaseUser user = mAuth.getCurrentUser();
            loadUserinfo(user);
        }

        // get the id
        btnsave2 = findViewById(R.id.btnsave2);
        logout = findViewById(R.id.logout);

        // give an event to next activity
        btnsave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ProfileSettings.this, Personalise.class);
                startActivity(a);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });


        /*holderbg = findViewById(R.id.holderbg);
        dynamicbg = findViewById(R.id.dynamicbg);*/



    }

    private void loadUserinfo(FirebaseUser user) {
        final FirebaseUser user_on = mAuth.getCurrentUser();
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            String photo = String.valueOf(user.getPhotoUrl());
            String email = user.getEmail();
            String name = user.getDisplayName();

            //Picasso.with(ProfileSettings.this).load(photo).into(userphoto);



            //Drawable d = getResources().getDrawable(android.R.drawable.ic_dialog_email);
            //ImageView image = (ImageView)findViewById(R.id.image);
            //image.setImageDrawable(d);

            Picasso.get().load(photo).into(userphoto);

            //String uri="@drawable/" + photo;
            //userphoto.setImageResource(getResources().getIdentifier(uri, null, getPackageName()));

            //userphoto.getDrawable();
            //Drawable img1 = userphoto.getDrawable();

            //Bitmap bitmap1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.img1);
            //Bitmap circularBitmap1 = RoundedImage.getRoundedCornerBitmap(bitmap1, 100);
            //userphoto.setImageBitmap(circularBitmap1);

            displayname.setText(name);
            username.setText(email);

        }


    }

    private void Logout() {
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                task -> loadUserinfo(null));

        Intent a = new Intent(ProfileSettings.this,LoginActivity.class);
        startActivity(a);
        finish();
    }
}
