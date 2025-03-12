package com.example.powerhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText firstName, lastName, email, password, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView titleTextView = findViewById(R.id.headerTitle);
        titleTextView.setText(R.string.title_activity_register);

        // Initialisation des vues
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);

        Button signUpButton = findViewById(R.id.signUpButton);
        ImageView backButton = findViewById(R.id.backButton);

        // Bouton d'inscription
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    Toast.makeText(RegisterActivity.this, "Inscription réussie !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateInputs() {
        String firstNameInput = firstName.getText().toString().trim();
        String lastNameInput = lastName.getText().toString().trim();
        String emailInput = email.getText().toString().trim();
        String passwordInput = password.getText().toString().trim();
        String phoneInput = phone.getText().toString().trim();

        // Regex pour le prénom et nom (2 à 25 lettres alphabétiques uniquement)
        Pattern namePattern = Pattern.compile("^[A-Za-zÀ-ÿ]{2,25}$");

        // Regex pour l'email (format standard)
        Pattern emailPattern = Pattern.compile("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$");

        // Regex pour le mot de passe (min 8 caractères, 1 lettre, 1 chiffre, 1 symbole)
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

        // Vérification des champs
        if (!namePattern.matcher(firstNameInput).matches()) {
            firstName.setError(getString(R.string.error_firstname));
            return false;
        }

        if (!namePattern.matcher(lastNameInput).matches()) {
            lastName.setError(getString(R.string.error_lastname));
            return false;
        }

        if (!emailPattern.matcher(emailInput).matches()) {
            email.setError(getString(R.string.error_email));
            return false;
        }

        if (!passwordPattern.matcher(passwordInput).matches()) {
            password.setError(getString(R.string.error_password));
            return false;
        }

        if (phoneInput.length() < 8 || !phoneInput.matches("\\d+")) {
            phone.setError(getString(R.string.error_phone));
            return false;
        }

        return true;
    }

}
