package com.example.wakeupfit.Goals;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wakeupfit.DietAndExercises.DietPlanWeightGainActivity;
import com.example.wakeupfit.DietAndExercises.ExercisesWeightGainActivity;
import com.example.wakeupfit.DietAndExercises.ExercisesMuscleActivity;
import com.example.wakeupfit.R;
import com.example.wakeupfit.user.UserProfileActivity;

public class WeightGainActivity extends AppCompatActivity {

    TextView txt2, txt;
    ImageView img;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weight_gain);

        img = findViewById(R.id.imageView8);
        txt2 = findViewById(R.id.textView14);
        Button dietPlanButton = findViewById(R.id.dietPlanButton);
        Button exercisesButton = findViewById(R.id.exercisesButton);
        txt = findViewById(R.id.weightGainDescriptionTextView);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightGainActivity.this, ExercisesMuscleActivity.class);
                startActivity(intent);

            }
        });

        // Set onClickListeners for buttons
        dietPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to DietPlanActivity
                Intent intent = new Intent(WeightGainActivity.this, DietPlanWeightGainActivity.class);
                startActivity(intent);
            }
        });

        exercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ExercisesActivity
                Intent intent = new Intent(WeightGainActivity.this, ExercisesWeightGainActivity.class);
                startActivity(intent);
            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightGainActivity.this, selectprogram.class);
                startActivity(intent);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WeightGainActivity.this, UserProfileActivity.class);
                startActivity(intent);

                finish();

            }
         });
    }
}