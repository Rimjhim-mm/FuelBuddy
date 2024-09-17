package com.example.fuelfrenzy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class Launcher extends AppCompatActivity {

    ImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        gifImageView = findViewById(R.id.intro);

        Glide.with(this)
                .asGif()
                .load("https://nh1design.com/wp-content/uploads/2019/01/Fuel-Buddy-Thumbnail-Animation.gif")
                .into(gifImageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Launcher.this, Login.class));
                finish();
            }
        }, 5000);

    }
}
