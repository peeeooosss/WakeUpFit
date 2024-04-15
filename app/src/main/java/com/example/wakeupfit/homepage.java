package com.example.wakeupfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wakeupfit.Goals.MuscleGainActivity;
import com.example.wakeupfit.Goals.WeightGainActivity;
import com.example.wakeupfit.Goals.WeightLossActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class homepage extends AppCompatActivity {

    public EditText editTextAge;
    Spinner spinnerGoals;
    public EditText editTextHeight;
    public EditText editTextWeight;
    Button btnSaveDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        editTextAge = findViewById(R.id.editTextAge);
        spinnerGoals = findViewById(R.id.spinnerGoals);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        btnSaveDetails = findViewById(R.id.btnSaveDetails);

        // Set up the goals spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.goals, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGoals.setAdapter(adapter);

        // Handle item selection in the goals spinner
        spinnerGoals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGoal = parent.getItemAtPosition(position).toString();
                Toast.makeText(homepage.this, "Ok Noted!", Toast.LENGTH_SHORT).show();
                // Handle selected goal
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected
                Toast.makeText(homepage.this, "Please select your goal", Toast.LENGTH_SHORT).show();
            }
        });

        btnSaveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSelectedGoalPage();

            }
        });
    }


    public void navigateToSelectedGoalPage() {


        String selectedGoal = spinnerGoals.getSelectedItem().toString();
        Intent intent;

        String age1 = editTextAge.getText().toString().trim();
        String height1 = editTextHeight.getText().toString().trim();
        String weight1 = editTextWeight.getText().toString().trim();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userGoals = database.getReference("users").child(userId);

        if (!selectedGoal.isEmpty()){
            userGoals.child("goals").setValue(selectedGoal);
        }
        DatabaseReference userRef = database.getReference("users").child(userId);

        String ageString = editTextAge.getText().toString().trim();
        if (!ageString.isEmpty()) {
            int age = Integer.parseInt(ageString);
            userRef.child("age").setValue(age);
        }


        // Validate input fields
        if (age1.isEmpty() || height1.isEmpty() || weight1.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save user details to database or perform further actions
        // For now, just display a toast message
        Toast.makeText(this, "Details saved: Age=" + age1 + ", Height=" + height1 + ", Weight=" + weight1 + ", Goal=" + selectedGoal, Toast.LENGTH_SHORT).show();

        double height = Double.parseDouble(height1);
        double weight = Double.parseDouble(weight1);
        double bmi = weight / ((height)/100*(height)/100);

        String userIdBmi = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userRefBmi = database.getReference("users").child(userIdBmi);



        if (bmi >0) {

            userRefBmi.child("bmi").setValue(bmi);
        }

        // Launch activity based on selected goal
        switch (selectedGoal) {
            case "Weight loss":
                intent = new Intent(homepage.this, WeightLossActivity.class);
                startActivity(intent);

                break;
            case "Weight gain":
                intent = new Intent(homepage.this, WeightGainActivity.class);
                startActivity(intent);

                break;
            case "Muscle gain":
                intent = new Intent(homepage.this, MuscleGainActivity.class);
                startActivity(intent);

                break;
            default:
                // Handle default case (should not happen)
                break;
        }
    }
}
