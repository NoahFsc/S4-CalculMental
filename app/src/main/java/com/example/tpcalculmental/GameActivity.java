package com.example.tpcalculmental;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textView_calcul;
    private TextInputEditText input_resultat;
    private Button bouton_envoyer;
    private float currentResult;
    private int score = 0;
    private int nombreVie = 0;
    private String difficulty;

    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView_calcul = findViewById(R.id.textView_calcul);
        input_resultat = findViewById(R.id.input_resultat);
        bouton_envoyer = findViewById(R.id.bouton_envoyer);

        difficulty = getIntent().getStringExtra("difficulty");

        if(difficulty.equals("Facile")) {
            nombreVie = 10;
        } else if (difficulty.equals("Moyen")) {
            nombreVie = 5;
        } else {
            nombreVie = 1;
        }

        bouton_envoyer.setOnClickListener(view->{
            String donneeInserer = input_resultat.getText().toString();

            if (donneeInserer.isEmpty()) {
                Toast.makeText(this, getResources().getString(R.string.entrer_valeur), Toast.LENGTH_SHORT).show();
                return;
            }

            float resultat = Float.parseFloat(donneeInserer);

            if (resultat == currentResult) {
                score++;
                if (menu != null) {
                    MenuItem textscore = menu.findItem(R.id.text_score);
                    textscore.setTitle(getResources().getString(R.string.text_score) + ": " + score);
                }
                Toast.makeText(this, getResources().getString(R.string.correct), Toast.LENGTH_SHORT).show();
                input_resultat.setText("");

                Object[] calculationAndResult = generateRandomCalculation();
                textView_calcul.setText((String) calculationAndResult[0]);
                currentResult = (float) calculationAndResult[1];


            }else{
                if (menu != null) {
                    MenuItem nombrevie = menu.findItem(R.id.nombre_vie);
                    nombrevie.setTitle(getResources().getString(R.string.text_vie) + ": " + --nombreVie);

                }
                Toast.makeText(this, getResources().getString(R.string.incorrect), Toast.LENGTH_SHORT).show();
                input_resultat.setText("");

                Object[] calculationAndResult = generateRandomCalculation();
                textView_calcul.setText((String) calculationAndResult[0]);
                currentResult = (float) calculationAndResult[1];


            }

            if (nombreVie == 1) {
                Toast.makeText(this, getResources().getString(R.string.danger), Toast.LENGTH_SHORT).show();
            }

            if (nombreVie == 0) {
                Intent intent = new Intent(this, EnregistrementActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("difficulty", difficulty);

                startActivity(intent);
            }

        });

        Object[] calculationAndResult = generateRandomCalculation();
        textView_calcul.setText((String) calculationAndResult[0]);
        currentResult = (float) calculationAndResult[1];
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_game, menu);
        MenuItem textscore = menu.findItem(R.id.text_score);
        MenuItem nombrevie = menu.findItem(R.id.nombre_vie);

        difficulty = getIntent().getStringExtra("difficulty");
        if(difficulty.equals("Facile")) {
            nombreVie = 10;
        } else if (difficulty.equals("Moyen")) {
            nombreVie = 5;
        } else {
            nombreVie = 1;
        }

        textscore.setTitle(getResources().getString(R.string.text_score) + ": " + score);
        nombrevie.setTitle(getResources().getString(R.string.text_vie) + ": " + nombreVie);

        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    private Object[] generateRandomCalculation() {
        Random random = new Random();
        int number1 = random.nextInt(100);
        int number2 = random.nextInt(100);
        String operator;
        float result;
        int operatorChoice;
        if (difficulty.equals("Facile") || difficulty.equals("Moyen")) {
            operatorChoice = random.nextInt(3); // Choix entre 0, 1 et 2
        } else {
            operatorChoice = random.nextInt(4); // Choix entre 0, 1, 2 et 3
        }
        switch (operatorChoice) {
            case 0:
                operator = "+";
                result = number1 + number2;
                break;
            case 1:
                operator = "-";
                result = number1 - number2;
                break;
            case 2:
                operator = "*";
                result = number1 * number2;
                break;
            default:
                operator = "/";
                result = (float) number1 / number2;
                break;
        }
        return new Object[]{number1 + " " + operator + " " + number2, result};
    }


}