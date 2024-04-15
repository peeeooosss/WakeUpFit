package com.example.wakeupfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wakeupfit.Goals.selectprogram;
import com.example.wakeupfit.user.UserProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateBMI extends AppCompatActivity {

    private EditText editTextHeight, editTextWeight;
    private Button buttonUpdate;
    private TextView textViewFeedback, txt;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bmi);

        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        textViewFeedback = findViewById(R.id.textViewFeedback);
        txt = findViewById(R.id.textView12);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateBMI.this, selectprogram.class);
                startActivity(intent);
                finish();
            }
        });
        img= findViewById(R.id.imageView7);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateBMI.this, UserProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateHeightWeight();
            }
        });

        // Retrieve current height and weight from database
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Double height = dataSnapshot.child("height").getValue(Double.class);
                    Double weight = dataSnapshot.child("weight").getValue(Double.class);
                    if (height != null && weight != null) {
                        editTextHeight.setText(String.valueOf(height));
                        editTextWeight.setText(String.valueOf(weight));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(updateBMI.this, "Error retrieving data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateHeightWeight() {
        String heightStr = editTextHeight.getText().toString().trim();
        String weightStr = editTextWeight.getText().toString().trim();

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please enter height and weight", Toast.LENGTH_SHORT).show();
            return;
        }

        double height = Double.parseDouble(heightStr);
        double weight = Double.parseDouble(weightStr);
        double bmi = calculateBMI(height, weight);

        // Update database with new height, weight, and BMI
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        userRef.child("height").setValue(height);
        userRef.child("weight").setValue(weight);

        userRef.child("bmi").setValue(bmi);

        // Provide feedback based on progress
        if (bmi < 18.5) {
            textViewFeedback.setText("Underweight. Please consider gaining weight.");
        } else if (bmi >= 18.5 && bmi < 25) {
            textViewFeedback.setText("Healthy weight. Keep up the good work!");
        } else {
            textViewFeedback.setText("Overweight. Please consider losing weight.");
        }
    }

    private double calculateBMI(double height, double weight) {
        return weight / ((height)/100 * (height)/100);
    }
}
