package com.example.powerhome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splash), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        View paimonGroup = findViewById(R.id.splash_paimonGroup);
        ImageView paimonImage = findViewById(R.id.splash_paimon_ic);
        TextView titleText = findViewById(R.id.splash_title);
        ImageView topDecor = findViewById(R.id.splash_top_decoration);
        ImageView bottomWave = findViewById(R.id.splash_bottomWave);

        Animation tilt = AnimationUtils.loadAnimation(this, R.anim.bounce_rotate);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.magic_pulse);
        paimonGroup.startAnimation(pulse);
        paimonImage.startAnimation(tilt);
        topDecor.startAnimation(tilt);

        fadeIn(paimonImage, 300);
        fadeIn(titleText, 500);
        fadeIn(topDecor, 400);
        fadeIn(bottomWave, 600);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 5000);
    }

    private void fadeIn(View view, long delay) {
        view.setAlpha(0f);
        view.animate()
                .alpha(1f)
                .setDuration(800)
                .setStartDelay(delay)
                .start();
    }
}
