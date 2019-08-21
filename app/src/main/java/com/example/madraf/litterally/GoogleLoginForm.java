package com.example.madraf.litterally;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madraf.litterally.Model.UserInfo;
import com.example.madraf.litterally.Prevalent.Prevalent;
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

import io.paperdb.Paper;

public class GoogleLoginForm extends AppCompatActivity {

    Button continuebutton;
    private EditText usernamelogingoogle,numberlogingoogle;
    private ProgressDialog loadingBar;
    //String phone,username;
    String parentDbname = "UserInfo";
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login_form);

        continuebutton = findViewById(R.id.continue_btn);
        usernamelogingoogle = findViewById(R.id.usernamelogingoogle);
        numberlogingoogle = findViewById(R.id.numberlogingoogle);
        loadingBar = new ProgressDialog(this);

        Paper.init(this);

        mAuth = FirebaseAuth.getInstance();

        continuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });

        String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserUsernameKey = Paper.book().read(Prevalent.UserUsernameKey);

        if (mAuth.getCurrentUser() != null)
        {
              AllowAccess(UserPhoneKey, UserUsernameKey);

                loadingBar.setTitle("Already Logged in");
                loadingBar.setMessage("Please wait.....");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

        }
    }

    public void AllowAccess(String phone, String username) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                    UserInfo usersData = dataSnapshot.child("UserInfo").getValue(UserInfo.class);

                    if (usersData.getUsername().equals(username))
                    {
                        if (usersData.getPhone().equals(phone))
                        {
                            Toast.makeText(GoogleLoginForm.this, "Please wait...", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(GoogleLoginForm.this, Personalise.class);
                            Prevalent.currentOnlineUser = usersData;
                            startActivity(intent);
                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(GoogleLoginForm.this, "Error...", Toast.LENGTH_SHORT).show();
                        }
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void LoginUser() {
        String username = usernamelogingoogle.getText().toString();
        String phone = numberlogingoogle.getText().toString();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "Please enter your username", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please enter your number", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Login Account..");
            loadingBar.setMessage("Please wait, while we are checking credentials..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            UpdateUserInfo(username, phone);
        }
    }

    private void UpdateUserInfo(final String username, final String phone)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!(dataSnapshot.child("UserInfo").child(username).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("username", username);

                    RootRef.child("UserInfo").child(username).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(GoogleLoginForm.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(GoogleLoginForm.this, Personalise.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(GoogleLoginForm.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(GoogleLoginForm.this, "This " + phone + " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(GoogleLoginForm.this, "Please try again using another phone number.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(GoogleLoginForm.this, Personalise.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
