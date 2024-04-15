package com.example.wakeupfit.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wakeupfit.R;
import com.example.wakeupfit.homepage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registration extends AppCompatActivity {

   public EditText editTextName;
    EditText editTextEmail;
    EditText editTextPassword;
    Button btnRegister;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();


                // Check if fields are not empty
                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(registration.this, "All fields are required.", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Registration success, save additional user data to Firebase Database
                                    FirebaseUser currentUser = mAuth.getCurrentUser();
                                    String userId = currentUser.getUid();
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name)
                                            .build();

                                    currentUser.updateProfile(profileUpdates)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        // Display name updated successfully
                                                    }
                                                }
                                            });


                                    // Create a User object with additional data
                                    User1 user = new User1(name, email, password);


                                    // Save user data to Firebase Realtime Database
                                    mDatabase.child("users").child(userId).setValue(user);

                                    // Show success message
                                    Toast.makeText(registration.this, "Registration successful", Toast.LENGTH_SHORT).show();

                                    // Navigate to HomePageActivity
                                    Intent intent = new Intent(registration.this, homepage.class);
                                    startActivity(intent);
                                    finish(); // Finish RegistrationActivity
                                }
                                 else {
                                    // Registration failed, display error message
                                    Toast.makeText(registration.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }


        });
    }
}
