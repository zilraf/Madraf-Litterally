package com.example.madraf.litterally;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RequestPickup extends AppCompatActivity {

    Button requestbutton;
    String item;
    EditText alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_pickup);

        requestbutton = findViewById(R.id.requestbutton);
        alamat = findViewById(R.id.alamat);

        requestbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        Spinner spinner = findViewById(R.id.spinner1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.buildingtypes, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose Building Type")){

                    Toast.makeText(parent.getContext(), "Please select Building Type", Toast.LENGTH_SHORT).show();

                }else {
                    item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Selected: "+ item, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // TODO Auto-generated

            }
        });
    }

    private void sendEmail() {
        String recipient = "litterally.3r@gmail.com,rafidmuqsith@gmail.com";
        String subject = "Request Pickup";

        String[] recipients = recipient.split(",");

        String inputalamat = alamat.getText().toString();

        String message = ("Address  : " + inputalamat + "\nType     : "+item);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}
