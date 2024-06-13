package com.example.tpcalculmental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DifficultyActivity extends AppCompatActivity {

    private Button bouton_facile;
    private Button bouton_moyen;
    private Button bouton_mortsubit;
    private String difficulty = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_difficulty);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bouton_facile = findViewById(R.id.bouton_facile);

        bouton_facile.setOnClickListener(view -> {
            Intent intent = new Intent(this, GameActivity.class);
            difficulty = "Facile";
            intent.putExtra("difficulty", difficulty);
            startActivity(intent);
        });

        bouton_moyen = findViewById(R.id.bouton_moyen);

        bouton_moyen.setOnClickListener(view -> {
            Intent intent = new Intent(this, GameActivity.class);
            difficulty = "Moyen";
            intent.putExtra("difficulty", difficulty);
            startActivity(intent);
        });

        bouton_mortsubit = findViewById(R.id.bouton_mortsubit);

        bouton_mortsubit.setOnClickListener(view -> {
            Intent intent = new Intent(this, GameActivity.class);
            difficulty = "Difficile";
            intent.putExtra("difficulty", difficulty);
            startActivity(intent);
        });
    }
}