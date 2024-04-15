package com.example.wakeupfit.Goals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wakeupfit.DietAndExercises.DietPlanWeightLossActivity;
import com.example.wakeupfit.DietAndExercises.ExercisesWeightLossActivity;
import com.example.wakeupfit.R;
import com.example.wakeupfit.user.UserProfileActivity;

public class WeightLossActivity extends AppCompatActivity {

    TextView txt3;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weight_loss);
        img = findViewById(R.id.imageView7);
        txt3 = findViewById(R.id.textView13);
        Button dietPlanButton = findViewById(R.id.dietPlanButton);
        Button exercisesButton = findViewById(R.id.exercisesButton);

        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightLossActivity.this, selectprogram.class);
                startActivity(intent);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightLossActivity.this, UserProfileActivity.class);
                startActivity(intent);
                finish();

            }
        });


        // Set onClickListeners for buttons
        dietPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to DietPlanActivity
                Intent intent = new Intent(WeightLossActivity.this, DietPlanWeightLossActivity.class);
                startActivity(intent);
            }
        });

        exercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ExercisesActivity
                Intent intent = new Intent(WeightLossActivity.this, ExercisesWeightLossActivity.class);
                startActivity(intent);
            }
        });

    }
}