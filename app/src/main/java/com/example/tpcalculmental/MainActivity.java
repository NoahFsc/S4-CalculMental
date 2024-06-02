package com.example.tpcalculmental;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button boutonJouer;
    private Button boutonScores;
    private Button boutonAPropos;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.bgmusic);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        boutonJouer = findViewById(R.id.bouton_jouer);
        boutonScores = findViewById(R.id.bouton_scores);
        boutonAPropos = findViewById(R.id.bouton_apropos);

        boutonJouer.setOnClickListener(view -> {
            Intent intent = new Intent(this, DifficultyActivity.class);
            startActivity(intent);
        });

        boutonScores.setOnClickListener(view -> {
            Intent intent = new Intent(this, ScoresActivity.class);
            startActivity(intent);
        });

        boutonAPropos.setOnClickListener(view -> {
            Intent intent = new Intent(this, AProposActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}