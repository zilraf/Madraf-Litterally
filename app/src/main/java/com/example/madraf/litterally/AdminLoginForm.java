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

import com.example.madraf.litterally.Model.Admin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLoginForm extends AppCompatActivity {

    Button loginbutton;
    private EditText usernamelogin,passwordlogin;
    private ProgressDialog loadingBar;

    private String parentDbname = "Admin" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        loginbutton = findViewById(R.id.loginbutton);
        usernamelogin = findViewById(R.id.usernamelogin);
        passwordlogin = findViewById(R.id.passwordlogin);
        loadingBar = new ProgressDialog(this);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });
    }

    private void LoginUser() {
        String username = usernamelogin.getText().toString();
        String password = passwordlogin.getText().toString();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "Please enter your username", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Login Account..");
            loadingBar.setMessage("Please wait, while we are checking credentials..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccesstoAccount(username, password);
        }
    }

    private void AllowAccesstoAccount(final String username, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDbname).child(username).exists()){

                    Admin userData = dataSnapshot.child(parentDbname).child(username).getValue(Admin.class);

                    if(userData.getUsername().equals(username)){
                        if(userData.getPassword().equals(password)){
                            Toast.makeText(AdminLoginForm.this, "Logged In", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent a = new Intent(AdminLoginForm.this, AdminCategoryActivity.class);
                            startActivity(a);
                        }else {
                            loadingBar.dismiss();
                            Toast.makeText(AdminLoginForm.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else{
                    Toast.makeText(AdminLoginForm.this, "Account with this " + username + " do not exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(AdminLoginForm.this, "You are not admin", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
