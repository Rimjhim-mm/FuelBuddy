package com.example.fuelfrenzy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class Launcher extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        progressBar = findViewById(R.id.loadingProgressBar);

        progressBar.setMax(100);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Launcher.this, Login.class));
                finish();
            }
        }, 5000);

        new Thread(new Runnable() {
            public void run() {
                int progress = 0;
                while (progress <= 100) {
                    final int finalProgress = progress;
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressBar.setProgress(finalProgress);
                        }
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress++;
                }
            }
        }).start();
    }
}
