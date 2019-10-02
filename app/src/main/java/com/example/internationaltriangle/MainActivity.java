package com.example.internationaltriangle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Switch;
import android.widget.CompoundButton;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonClick;
    private Switch languageSwitch;
    private TextView sideOne;
    private TextView sideTwo;
    private TextView sideThree;
    private TextView results;
    private TextView labelOne;
    private TextView labelTwo;
    private TextView labelThree;
    private HashMap<String, String> englishPhrases;
    private HashMap<String, String> spanishPhrases;
    private HashMap<String, String> currentPhrases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add button handler/listener
        this.buttonClick = findViewById(R.id.generateButton);
        this.buttonClick.setOnClickListener(MainActivity.this);

        // Add switch handler/listener
        this.languageSwitch = findViewById(R.id.languageSwitch);

        // Add text handlers/listeners
        this.sideOne = findViewById(R.id.input1);

        this.sideTwo = findViewById(R.id.input2);

        this.sideThree = findViewById(R.id.input3);

        // Add label handlers
        this.labelOne = findViewById(R.id.labelSide1);
        this.labelTwo = findViewById(R.id.labelSide2);
        this.labelThree = findViewById(R.id.labelSide3);

        // Add results handler
        this.results = findViewById(R.id.resultsTextBox);

        // Phrases for output
        this.englishPhrases = this.generateEnglishPhrases();
        this.spanishPhrases = this.generateSpanishPhrases();

        this.currentPhrases = this.generateEnglishPhrases(); // English by default

        this.languageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String label = b ? "Español" : "English";
                compoundButton.setText(label);
            }
        });
    }

    private HashMap<String, String> generateEnglishPhrases() {
        HashMap<String, String> result = new HashMap();

        result.put("side 1", "Side 1:");
        result.put("side 2", "Side 2:");
        result.put("side 3", "Side 3:");
        result.put("generate", "Generate");

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

        result.put("side 1", "Lado 1:");
        result.put("side 2", "Lado 2:");
        result.put("side 3", "Lado 3:");
        result.put("generate", "Generar");

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

    private void setCurrentPhrases(String language) {
        switch (language) {
            case "Español":
                this.currentPhrases = this.generateSpanishPhrases();
                break;
            default:
                this.currentPhrases = this.generateEnglishPhrases();
                break;
        }
    }

    private void setLabels() {
        this.labelOne.setText(this.currentPhrases.get("side 1"));
        this.labelTwo.setText(this.currentPhrases.get("side 2"));
        this.labelThree.setText(this.currentPhrases.get("side 3"));
        this.buttonClick.setText(this.currentPhrases.get("generate"));
    }

    public void onClick(View v) {
        if (v == this.buttonClick) {
            String language = this.languageSwitch.getText().toString();
            this.setCurrentPhrases(language);
            this.setLabels();

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
                        this.results.setText(this.currentPhrases.get("goodbye"));
                    } else {
                        String triangleType = this.getTriangleTypes(sideOne, sideTwo, sideThree);

                        this.results.clearComposingText();
                        this.results.setText(this.currentPhrases.get(triangleType));
                    }
                } catch (Exception e) {
                    this.results.clearComposingText();
                    this.results.setText(this.currentPhrases.get("invalid sides"));
                }

            } else {

                if (sideOneString.contains("0") || sideTwoString.contains("0") || sideThreeString.contains("0")) {
                    this.results.clearComposingText();
                    this.results.setText(this.currentPhrases.get("goodbye"));
                } else {
                    this.results.clearComposingText();
                    this.results.setText(this.currentPhrases.get("missing sides"));
                }
            }

        }
    }

    public String getTriangleTypes(double sideOne, double sideTwo, double sideThree) {
        Double[] sides = { sideOne, sideTwo, sideThree };

        // Check for negative values
        if (sideOne <= 0 || sideTwo <= 0 || sideThree <= 0) {
            return "invalid sides";
        } else if ((sideOne + sideTwo < sideThree) || (sideOne + sideThree < sideOne) || (sideTwo + sideThree < sideOne)) {
            return "missing sides";
        }

        boolean isEquilateral = Double.compare(sides[0], sides[1]) == 0 &&
                Double.compare(sides[1], sides[2]) == 0;
        boolean isScalene = Double.compare(sides[0], sides[1]) != 0 &&
                Double.compare(sides[1], sides[2]) != 0 &&
                Double.compare(sides[0], sides[2]) != 0;

        if (isEquilateral) {
            return "equilateral";
        } else if (isScalene) {
            return "scalene";
        } else {
            return "isosceles";
        }
    }

    public boolean isValidInput(String sideOne, String sideTwo, String sideThree) {
        return (sideOne.length() > 0) && (sideTwo.length() > 0) && (sideThree.length() > 0);
    }
}
