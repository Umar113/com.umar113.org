package com.example.android.health.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.health.R;

/**
 * Created by Android on 24-02-2017.
 */
public class EmergencyActivity extends AppCompatActivity implements View.OnClickListener{
    Button number_button,number_button1,number_button2,number_button3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        number_button = (Button) findViewById(R.id.number_button);
        number_button1 = (Button) findViewById(R.id.number_button1);
        number_button2 = (Button) findViewById(R.id.number_button2);
        number_button3 = (Button) findViewById(R.id.number_button3);

        number_button.setOnClickListener(this);
        number_button1.setOnClickListener(this);
        number_button2.setOnClickListener(this);
        number_button3.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        switch (view.getId()){
            case R.id.number_button:
                phoneIntent.setData(Uri.parse("tel:+91-937-377-0145"));
                startActivity(phoneIntent);
                break;
            case R.id.number_button1:
                phoneIntent.setData(Uri.parse("tel:+91-989-042-7551"));
                startActivity(phoneIntent);
                break;
            case R.id.number_button2:
                phoneIntent.setData(Uri.parse("tel:+91-712-2250017"));
                startActivity(phoneIntent);
                break;
            case R.id.number_button3:
                phoneIntent.setData(Uri.parse("tel:+91-982-264-1876"));
                startActivity(phoneIntent);
                break;
        }

    }
}
