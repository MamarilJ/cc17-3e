package com.teamviewer.collabmates;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Button haveAccountButton = findViewById(R.id.signin_button);

        haveAccountButton.setOnClickListener(view -> {
            // Go back to MainActivity when the "Have Account" button is clicked
            finish(); // This finishes the current activity (SignUpActivity) and goes back to the previous one (if any)
        });


    }
}

