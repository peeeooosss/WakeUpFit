package com.example.wakeupfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wakeupfit.Goals.MuscleGainActivity;
import com.example.wakeupfit.Goals.WeightGainActivity;
import com.example.wakeupfit.Goals.WeightLossActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class welcomeback extends AppCompatActivity {

    TextView textView;
    Button btn;
    String goal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeback);

        // Find the "Continue" button by ID
        Button buttonContinue = findViewById(R.id.buttoncontinue);

        // Set OnClickListener for the "Continue" button
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the user's goal from the database
                retrieveGoalFromDatabase();
            }
        });
    }

    private void retrieveGoalFromDatabase() {
        // Get current user ID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Get reference to user's goal in Firebase Realtime Database
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("goals");

        // Retrieve the user's goal from the database
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Check if the goal exists
                if (dataSnapshot.exists()) {
                    // Assign the retrieved goal to the class-level variable
                    goal = dataSnapshot.getValue(String.class);
                    // Navigate to the respective goal activity
                    navigateToRespectiveGoal(goal);
                } else {
                    // Handle case where the goal does not exist
                    Toast.makeText(welcomeback.this, "Goal not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Toast.makeText(welcomeback.this, "Error retrieving goal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToRespectiveGoal(String goal) {
        Intent intent;
        switch (goal) {
            case "Muscle gain":
                intent = new Intent(welcomeback.this, MuscleGainActivity.class);
                break;
            case "Weight loss":
                intent = new Intent(welcomeback.this, WeightLossActivity.class);
                break;
            case "Weight gain":
                intent = new Intent(welcomeback.this, WeightGainActivity.class);
                break;
            default:
                // Handle default case or error
                return;
        }
        startActivity(intent);
    }
}