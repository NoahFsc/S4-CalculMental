package com.example.tpcalculmental;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoresActivity extends AppCompatActivity {

    private ImageButton filterButton;

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
        joueurDao.insertFakeData(100);

        List<Joueur> joueurs = joueurDao.getAll();

        JoueurAdapter joueurAdapter = new JoueurAdapter(joueurs);
        recyclerView.setAdapter(joueurAdapter);

        ImageButton filterButton = findViewById(R.id.button_filter);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ScoresActivity.this);
                builder.setTitle(getResources().getString(R.string.choix_difficulte));

                String[] difficulties = getResources().getStringArray(R.array.difficulties);
                builder.setItems(difficulties, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedDifficulty = difficulties[which];
                        List<Joueur> filteredJoueurs = filterJoueursByDifficulty(joueurs, selectedDifficulty);
                        AlertDialog.Builder orderBuilder = new AlertDialog.Builder(ScoresActivity.this);
                        orderBuilder.setTitle(getResources().getString(R.string.choix_ordre));

                        String[] orders = getResources().getStringArray(R.array.ordres);
                        orderBuilder.setItems(orders, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String selectedOrder = orders[which];
                                List<Joueur> orderedJoueurs = orderJoueurs(filteredJoueurs, selectedOrder);
                                JoueurAdapter joueurAdapter = new JoueurAdapter(orderedJoueurs);
                                recyclerView.setAdapter(joueurAdapter);
                            }
                        });

                        orderBuilder.show();
                    }
                });

                builder.show();
            }
        });
    }

    private List<Joueur> orderJoueurs(List<Joueur> joueurs, String order) {
        List<Joueur> orderedJoueurs = new ArrayList<>(joueurs);

        if (order.equals("Croissant")) {
            return joueurs;

        } else if (order.equals("Décroissant")) {
               Collections.sort(orderedJoueurs, new Comparator<Joueur>() {
                    @Override
                    public int compare(Joueur o1, Joueur o2) {
                        return o1.getScore() - o2.getScore();
                    }
                });

        }

        return orderedJoueurs;
    }

    private List<Joueur> filterJoueursByDifficulty(List<Joueur> joueurs, String difficulty) {

        if (difficulty.equals("Tous")) {
            return joueurs;
        }

        List<Joueur> filteredJoueurs = new ArrayList<>();
        for (Joueur joueur : joueurs) {
            if (joueur.getTypeDifficulte().equals(difficulty)) {
                filteredJoueurs.add(joueur);
            }
        }
        return filteredJoueurs;
    }
}