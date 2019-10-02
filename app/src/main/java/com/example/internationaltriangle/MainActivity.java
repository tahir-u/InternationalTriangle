package com.example.internationaltriangle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonClick;
    private TextView sideOne;
    private TextView sideTwo;
    private TextView sideThree;
    private TextView results;
    private HashMap<String, String> englishPhrases;
    private HashMap<String, String> spanishPhrases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add button handler/listener
        this.buttonClick = findViewById(R.id.generateButton);
        this.buttonClick.setOnClickListener(MainActivity.this);
        this.buttonClick.setInputType(InputType.TYPE_NULL);

        // Add text handlers/listeners
        this.sideOne = findViewById(R.id.input1);

        this.sideTwo = findViewById(R.id.input2);

        this.sideThree = findViewById(R.id.input3);

        // Add results handler
        this.results = findViewById(R.id.resultsTextBox);

        // Phrases for output
        this.englishPhrases = this.generateEnglishPhrases();
        this.spanishPhrases = this.generateSpanishPhrases();

    }

    private HashMap<String, String> generateEnglishPhrases() {
        HashMap<String, String> result = new HashMap();

        result.put("equilateral", "Equilateral Triangle.");
        result.put("isosceles", "Isosceles Triangle.");
        result.put("scalene", "Scalene Triangle.");

        result.put("goodbye", "Goodbye!");

        result.put("invalid sides", "One or more triangle sides were invalid.");
        result.put("invalid triangle", "Invalid triangle (the inputs do not form a triangle).");
        result.put("missing sides", "One or more sides are missing for the triangle type calculation.");

        return result;
    }

    private HashMap<String, String> generateSpanishPhrases() {
        HashMap<String, String> result = new HashMap();

        result.put("equilateral", "Triángulo equilátero.");
        result.put("isosceles", "Triángulo isósceles.");
        result.put("scalene", "Triángulo escaleno.");

        result.put("goodbye", "Adiós!");

        result.put("invalid sides", "Uno o más lados del triángulo no eran válidos.");
        result.put("invalid triangle", "\n" +
                "Triángulo no válido (las entradas no forman un triángulo).");
        result.put("missing sides", "Faltan uno o más lados para el cálculo del tipo de triángulo.");

        return result;
    }

    public void onClick(View v) {
        if (v == this.buttonClick) {

            String sideOneString = this.sideOne.getText().toString();
            String sideTwoString = this.sideTwo.getText().toString();
            String sideThreeString = this.sideThree.getText().toString();

            if (isValidInput(sideOneString, sideTwoString, sideThreeString)) {

                try {
                    double sideOne = Double.parseDouble(this.sideOne.getText().toString());
                    double sideTwo = Double.parseDouble(this.sideTwo.getText().toString());
                    double sideThree = Double.parseDouble(this.sideThree.getText().toString());

                    if (Double.compare(sideOne, 0) == 0 || Double.compare(sideTwo, 0) == 0 || Double.compare(sideThree, 0) == 0) {
                        this.results.clearComposingText();
                        this.results.setText("Goodbye!");
                    } else {
                        String triangleType = this.getTriangleTypes(sideOne, sideTwo, sideThree);

                        this.results.clearComposingText();
                        this.results.setText(triangleType);
                    }
                } catch (Exception e) {
                    this.results.clearComposingText();
                    this.results.setText("One or more triangle sides were invalid.");
                }

            } else {

                if (sideOneString.contains("0") || sideTwoString.contains("0") || sideThreeString.contains("0")) {
                    this.results.clearComposingText();
                    this.results.setText("Goodbye!");
                } else {
                    this.results.clearComposingText();
                    this.results.setText("One or more sides are missing for the triangle type calculation.");
                }
            }

        }
    }

    public String getTriangleTypes(double sideOne, double sideTwo, double sideThree) {
        Double[] sides = { sideOne, sideTwo, sideThree };

        // Check for negative values
        if (sideOne <= 0 || sideTwo <= 0 || sideThree <= 0) {
            return "One or more triangle sides were invalid.";
        } else if ((sideOne + sideTwo < sideThree) || (sideOne + sideThree < sideOne) || (sideTwo + sideThree < sideOne)) {
            return "Invalid triangle (the inputs do not form a triangle).";
        }

        boolean isEquilateral = Double.compare(sides[0], sides[1]) == 0 &&
                Double.compare(sides[1], sides[2]) == 0;
        boolean isScalene = Double.compare(sides[0], sides[1]) != 0 &&
                Double.compare(sides[1], sides[2]) != 0 &&
                Double.compare(sides[0], sides[2]) != 0;

        if (isEquilateral) {
            return "Equilateral triangle";
        } else if (isScalene) {
            return "Scalene triangle";
        } else {
            return "Isosceles triangle";
        }
    }

    public boolean isValidInput(String sideOne, String sideTwo, String sideThree) {
        return (sideOne.length() > 0) && (sideTwo.length() > 0) && (sideThree.length() > 0);
    }
}
