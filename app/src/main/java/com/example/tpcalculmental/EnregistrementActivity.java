package com.example.tpcalculmental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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
    private int score; // Assurez-vous que le score est mis à jour correctement dans votre activité
    private JoueurDao joueurDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement);

        input_nom = findViewById(R.id.input_nom);
        bouton_envoyer_donnees = findViewById(R.id.bouton_envoyer_donnees);

        score = getIntent().getIntExtra("score", 0);

        joueurDao = new JoueurDao(new JoueurBaseHelper(this,"db",1));

        bouton_envoyer_donnees.setOnClickListener(view -> {
            String nom = input_nom.getText().toString();

            Joueur joueur = new Joueur();
            joueur.setNom(nom);
            joueur.setScore(score);

            joueurDao.create(joueur);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}