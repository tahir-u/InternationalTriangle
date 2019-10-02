package com.example.internationaltriangle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonClick;
    private TextView sideOne;
    private TextView sideTwo;
    private TextView sideThree;
    private TextView results;

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

                    String triangleType = this.getTriangleTypes(sideOne, sideTwo, sideThree);

                    this.results.clearComposingText();
                    this.results.setText(triangleType + " triangle");
                } catch (Exception e) {
                    this.results.clearComposingText();
                    this.results.setText("One or more triangle sides were invalid.");
                }

            } else {
                this.results.clearComposingText();
                this.results.setText("One or more sides are missing for the triangle type calculation.");
            }

        }
    }

    public String getTriangleTypes(double sideOne, double sideTwo, double sideThree) {
        Double[] sides = { sideOne, sideTwo, sideThree };

        // Check for negative values
        if (sideOne <= 0 || sideTwo <= 0 || sideThree <= 0) {
            return "One or more triangle sides were invalid.";
        }

        boolean isEquilateral = Double.compare(sides[0], sides[1]) == 0 &&
                Double.compare(sides[1], sides[2]) == 0;
        boolean isScalene = Double.compare(sides[0], sides[1]) != 0 &&
                Double.compare(sides[1], sides[2]) != 0 &&
                Double.compare(sides[0], sides[2]) != 0;

        if (isEquilateral) {
            return "Equilateral";
        } else if (isScalene) {
            return "Scalene";
        } else {
            return "Isosceles";
        }
    }

    public boolean isValidInput(String sideOne, String sideTwo, String sideThree) {
        return (sideOne.length() > 0) && (sideTwo.length() > 0) && (sideThree.length() > 0);
    }
}
