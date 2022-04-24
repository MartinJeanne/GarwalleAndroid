package com.garwalle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvCouleur;
    private Button btnHelloWorldIntent;
    private Button btnOtherApp;
    private Button btnImpliciteIntent;
    private Button btnSendSMS;
    private Button btnSendSMSviaIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCouleur = findViewById(R.id.tvMessage);
        btnHelloWorldIntent = findViewById(R.id.btnHelloWorldIntent);
        btnOtherApp = findViewById(R.id.btnOtherApp);
        btnImpliciteIntent = findViewById(R.id.btnImpliciteIntent);
        btnSendSMS = findViewById(R.id.btnSendSMS);
        btnSendSMSviaIntent = findViewById(R.id.btnSendSMSviaIntent);

        // Demande la permission d'envoyer des SMS
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},1);

        btnHelloWorldIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelloWorldActivity.class);
                startActivity(intent);
            }
        });

        btnOtherApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.garwallereceiver", "com.garwallereceiver.HelloWorldActivity2"));
                startActivity(intent);
            }
        });

        btnImpliciteIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("helloWorld.ACTION");
                startActivity(intent);
            }
        });

        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    SmsManager smgr = SmsManager.getDefault();
                    smgr.sendTextMessage("0783997737",null,"Hello world !",null,null);
                    Toast.makeText(MainActivity.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSendSMSviaIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("sms", "0783997737", null));
                    i.putExtra("sms_body", "Hello world !");
                    startActivity(Intent.createChooser(i, "Send sms via:"));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}