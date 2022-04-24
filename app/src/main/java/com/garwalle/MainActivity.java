package com.garwalle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvCouleur;
    private Button btnHelloWorldIntent;
    private Button btnOtherApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCouleur = findViewById(R.id.tvMessage);
        btnHelloWorldIntent = findViewById(R.id.btnHelloWorldIntent);
        btnOtherApp = findViewById(R.id.btnOtherApp);

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

    }
}