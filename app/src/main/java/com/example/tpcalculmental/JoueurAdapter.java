package com.example.tpcalculmental;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpcalculmental.entities.Joueur;

import java.util.List;

public class JoueurAdapter extends RecyclerView.Adapter<JoueurAdapter.JoueurViewHolder> {
    private List<Joueur> joueurs;

    public JoueurAdapter(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    @NonNull
    @Override
    public JoueurViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joueur_item, parent, false);
        return new JoueurViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JoueurViewHolder holder, int position) {
        Joueur joueur = joueurs.get(position);
        holder.nom.setText(joueur.getNom());
        holder.score.setText(String.valueOf(joueur.getScore()));
        holder.difficulte.setText(joueur.getTypeDifficulte());
    }

    @Override
    public int getItemCount() {
        return joueurs.size();
    }

    public static class JoueurViewHolder extends RecyclerView.ViewHolder {
        public TextView nom;
        public TextView score;
        public TextView difficulte;

        public JoueurViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.nomTextView);
            score = itemView.findViewById(R.id.scoreTextView);
            difficulte = itemView.findViewById(R.id.difficulteTextView);
        }
    }
}
