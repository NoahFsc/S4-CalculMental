package com.example.tpcalculmental;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpcalculmental.database.JoueurBaseHelper;
import com.example.tpcalculmental.database.JoueurDao;
import com.example.tpcalculmental.entities.Joueur;

import java.util.List;

public class ScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scores);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView_joueur);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        JoueurDao joueurDao = new JoueurDao(new JoueurBaseHelper(this,"db",1));
        joueurDao.insertFakeData(300);

        List<Joueur> joueurs = joueurDao.getAll();

        JoueurAdapter joueurAdapter = new JoueurAdapter(joueurs);
        recyclerView.setAdapter(joueurAdapter);
    }
}