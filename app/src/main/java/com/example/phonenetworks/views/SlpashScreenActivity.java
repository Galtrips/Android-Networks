package com.example.phonenetworks.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.phonenetworks.R;

public class SlpashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slpash_screen);

        (new Handler()).postDelayed(this::getMainActivity, 3000);
    }

    private void getMainActivity() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);

        startActivity(i);
        finish();
    }
}