package com.example.powerhome;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
import org.json.JSONException;

public class RegisterActivity extends AppCompatActivity {

    private EditText editFirstName, editLastName, editEmail, editPassword, editPhone;

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
        editFirstName = findViewById(R.id.firstName);
        editLastName = findViewById(R.id.lastName);
        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        editPhone = findViewById(R.id.phone);
        List<EditText> editTextList = Arrays.asList(editFirstName, editLastName, editEmail, editPassword);
        Button signUpButton = findViewById(R.id.signUpButton);

        // Bouton d'inscription
        signUpButton.setOnClickListener(v -> {
            String nom = editFirstName.getText().toString().trim();
            String prenom = editLastName.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String motDePasse = editPassword.getText().toString().trim();
            String tel = editPhone.getText().toString().trim();
            if (!nom.isEmpty() && !prenom.isEmpty() && !email.isEmpty() && !motDePasse.isEmpty()) {
                registerUser(nom, prenom, email, motDePasse, tel);
            } else {
                Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
            }
        });

        Drawable firstname_icon = ContextCompat.getDrawable(this, R.drawable.ic_mini_paimon);
        Drawable lastname_icon = ContextCompat.getDrawable(this, R.drawable.emote_paimon_meme);
        Drawable email_icon = ContextCompat.getDrawable(this, R.drawable.ic_email);
        Drawable password_icon = ContextCompat.getDrawable(this, R.drawable.ic_artifact);
        List<Drawable> input_drawables = Arrays.asList(firstname_icon, lastname_icon, email_icon, password_icon);
        int sizeInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 34, getResources().getDisplayMetrics());
        for (int i = 0; i < input_drawables.size(); ++i) {
            Drawable drawable = input_drawables.get(i);
            if (drawable != null) {
                drawable.setBounds(0, 0, sizeInDp, sizeInDp);
                editTextList.get(i).setCompoundDrawables(drawable, null, null, null);
                editTextList.get(i).setCompoundDrawablePadding(30);
            }
        }

        Spinner spinner = findViewById(R.id.countryCodeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.country_codes,
                R.layout.country_code_spinner_item
        );
        adapter.setDropDownViewResource(R.layout.country_code_spinner);
        spinner.setAdapter(adapter);

    }
    private void registerUser(String nom, String prenom, String email, String motDePasse, String tel) {
        String url = SessionManager.HOST + "/www/register.php";

        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject data = new JSONObject();
        try {
            data.put("nom", nom);
            data.put("prenom", prenom);
            data.put("email", email);
            data.put("mot_de_passe", motDePasse);
            data.put("telephone", editPhone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, data,
                response -> {
                    try {
                        if (response.getBoolean("success")) {
                            Toast.makeText(getApplicationContext(), getString(R.string.register_success), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getApplicationContext(), getString(R.string.error) + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }

}
