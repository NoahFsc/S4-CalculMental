package com.example.tpcalculmental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tpcalculmental.database.DataBaseHelper;
import com.example.tpcalculmental.database.JoueurBaseHelper;
import com.example.tpcalculmental.database.JoueurDao;
import com.example.tpcalculmental.entities.Joueur;
import com.google.android.material.textfield.TextInputEditText;

public class EnregistrementActivity extends AppCompatActivity {

    private TextInputEditText input_nom;
    private Button bouton_envoyer_donnees;
    private int score;
    private String difficulty = "";
    private JoueurDao joueurDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement);

        input_nom = findViewById(R.id.input_nom);
        bouton_envoyer_donnees = findViewById(R.id.bouton_envoyer_donnees);

        score = getIntent().getIntExtra("score", 0);

        difficulty = getIntent().getStringExtra("difficulty");

        joueurDao = new JoueurDao(new JoueurBaseHelper(this,"db",1));

        bouton_envoyer_donnees.setOnClickListener(view -> {

            String nom = input_nom.getText().toString();

            if (nom.isEmpty()) {
                Toast.makeText(this, getResources().getString(R.string.entrer_nom), Toast.LENGTH_SHORT).show();
                return;
            }

            if (nom.length() > 10) {
                Toast.makeText(this, getResources().getString(R.string.erreur_pseudo), Toast.LENGTH_SHORT).show();
                return;
            }

            Joueur joueur = new Joueur(nom, score, difficulty);

            joueurDao.create(joueur);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}