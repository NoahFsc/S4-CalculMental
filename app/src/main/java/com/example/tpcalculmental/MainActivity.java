package com.example.tpcalculmental;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boutonJouer = findViewById(R.id.bouton_jouer);
        boutonScores = findViewById(R.id.bouton_scores);
        boutonAPropos = findViewById(R.id.bouton_apropos);

        boutonJouer.setOnClickListener(view -> {
            Intent intent = new Intent(this, GameActivity.class);
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

}