package com.enctyptednotepad;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class HomePageActivity extends AppCompatActivity {

    private TextView eStoredText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);

        eStoredText = findViewById(R.id.tvWelcome);
    }
}