package com.teamviewer.collabmates;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword, editTextName, editTextIdNumber;
    private Button signUp;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usersRef = FirebaseDatabase.getInstance().getReference("Users");

        editTextEmail = findViewById(R.id.email_signup);
        editTextPassword = findViewById(R.id.password_signup);
        editTextName = findViewById(R.id.name);
        editTextIdNumber = findViewById(R.id.idNumber);

        Button haveAccountButton = findViewById(R.id.signin_button);

        haveAccountButton.setOnClickListener(view -> finish());

        signUp = findViewById(R.id.signup_button);

        signUp.setOnClickListener(view -> {
            String getName = editTextName.getText().toString().trim();
            String getEmail = editTextEmail.getText().toString().trim();
            String getPassword = editTextPassword.getText().toString().trim();
            String getIdNumber = editTextIdNumber.getText().toString().trim();

            if (TextUtils.isEmpty(getName) || TextUtils.isEmpty(getEmail) || TextUtils.isEmpty(getPassword) || TextUtils.isEmpty(getIdNumber)) {
                Toast.makeText(SignUpActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.createUserWithEmailAndPassword(getEmail, getPassword)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            if (firebaseUser != null) {
                                String userId = firebaseUser.getUid();
                                saveUserDataToFirebase(userId, getName, getIdNumber, getEmail);
                            }
                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SignUpActivity.this, "Failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private void saveUserDataToFirebase(String userId, String name, String idNumber, String email) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", name);
        hashMap.put("idnumber", idNumber);
        hashMap.put("email", email);

        usersRef.child(userId).setValue(hashMap)
                .addOnSuccessListener(aVoid -> Toast.makeText(SignUpActivity.this, "Data added successfully", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(SignUpActivity.this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
