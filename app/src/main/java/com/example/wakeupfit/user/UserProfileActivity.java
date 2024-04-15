package com.example.wakeupfit.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wakeupfit.Goals.MuscleGainActivity;
import com.example.wakeupfit.Goals.WeightGainActivity;
import com.example.wakeupfit.Goals.WeightLossActivity;
import com.example.wakeupfit.Goals.selectprogram;
import com.example.wakeupfit.R;
import com.example.wakeupfit.updateBMI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;


public class UserProfileActivity extends AppCompatActivity {

    TextView textViewName ,txtprogram;
    TextView textViewAge, textViewBMI;
    Button button;
    ImageView img;
    String goal;
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        textViewName = findViewById(R.id.textViewName);
        textViewAge = findViewById(R.id.textViewAge);
        textViewBMI = findViewById(R.id.textViewBMI);
        button = findViewById(R.id.button4);
        txtprogram = findViewById(R.id.textView11);
        txtprogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, selectprogram.class);
                startActivity(intent);
                finish();
            }
        });
        img = findViewById(R.id.imageView6);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveGoalFromDatabase();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, updateBMI.class);
                startActivity(intent);
                finish();
            }
        });
        FirebaseUser Currentuser = FirebaseAuth.getInstance().getCurrentUser();
        if (Currentuser != null) {
            String displayName = Currentuser.getDisplayName();
            if (displayName != null && !displayName.isEmpty()) {
                textViewName.setText("Name " + displayName );
            } else {
                textViewName.setText("Welcome!");
            }
        }


        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        // Get reference to user's age in Firebase Realtime Database
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

        // Add ValueEventListener to retrieve user's age
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get the age value from the database
                if (dataSnapshot.exists()) {
                    Integer age = dataSnapshot.child("age").getValue(Integer.class);
                    Double bmi = dataSnapshot.child("bmi").getValue(Double.class);

                    DecimalFormat dci = new DecimalFormat("#.#");
                    String formatedbmi = dci.format(bmi);
                    // Update TextView with user's age
                    textViewAge.setText("Age: " + age);
                    textViewBMI.setText("BMI: "+ formatedbmi);
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
                    Toast.makeText(UserProfileActivity.this, "Can't Back", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Toast.makeText(UserProfileActivity.this, "Error!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToRespectiveGoal(String goal) {
        Intent intent;
        switch (goal) {
            case "Muscle gain":
                intent = new Intent(UserProfileActivity.this, MuscleGainActivity.class);
                break;
            case "Weight loss":
                intent = new Intent(UserProfileActivity.this, WeightLossActivity.class);
                break;
            case "Weight gain":
                intent = new Intent(UserProfileActivity.this, WeightGainActivity.class);
                break;
            default:
                // Handle default case or error
                return;
        }
        startActivity(intent);
    }

}

//homepage --> bmi add // save in database and call it // make a option to change the database when the weight and height will change!