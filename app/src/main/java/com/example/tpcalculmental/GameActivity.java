package com.example.tpcalculmental;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

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
    private int currentResult;

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
        Object[] calculationAndResult = generateRandomCalculation();
        textView_calcul.setText((String) calculationAndResult[0]);
        currentResult = (int) calculationAndResult[1];
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_game, menu);
        MenuItem textscore = menu.findItem(R.id.text_score);
        MenuItem nombrevie = menu.findItem(R.id.nombre_vie);
        return super.onCreateOptionsMenu(menu);
    }

    private Object[] generateRandomCalculation() {
        Random random = new Random();
        int number1 = random.nextInt(100);
        int number2 = random.nextInt(100);
        String operator;
        int result;
        switch (random.nextInt(4)) {
            case 0:
                operator = "+";
                result = number1 + number2;
                break;
            case 1:
                operator = "-";
                result = number1 - number2;
                break;
            case 2:
                operator = "/";
                result = number1 / number2;
                break;
            default:
                operator = "*";
                result = number1 * number2;
                break;
        }
        return new Object[]{number1 + " " + operator + " " + number2, result};
    }


}