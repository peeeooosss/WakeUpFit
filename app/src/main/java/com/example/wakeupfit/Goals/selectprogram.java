package com.example.wakeupfit.Goals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wakeupfit.R;

public class selectprogram extends AppCompatActivity {

    ImageView img1, img2, img3, img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectprogram);

        img1 = findViewById(R.id.imageView10);
        img2 = findViewById(R.id.imageView11);
        img3 = findViewById(R.id.imageView12);
        img4 = findViewById(R.id.steps); // New ImageView for step counter

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectprogram.this, MuscleGainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectprogram.this, WeightLossActivity.class);
                startActivity(intent);
                finish();
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectprogram.this, WeightGainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectprogram.this, StepCounterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
