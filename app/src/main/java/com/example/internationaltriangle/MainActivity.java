package com.example.internationaltriangle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

// Java Specific
import java.util.Arrays;
import java.util.Collections;

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

        // Add text handlers/listeners
        this.sideOne = findViewById(R.id.input1);
        this.sideTwo = findViewById(R.id.input2);
        this.sideThree = findViewById(R.id.input3);

        // Add results handler
        this.results = findViewById(R.id.resultsTextBox);

    }

    public void onClick(View v) {
        if (v == this.buttonClick) {

            int sideOne = Integer.parseInt(this.sideOne.getText().toString());
            int sideTwo = Integer.parseInt(this.sideTwo.getText().toString());
            int sideThree = Integer.parseInt(this.sideThree.getText().toString());
            boolean isValidTriangle = this.isValidTriangle(sideOne, sideTwo, sideThree);

            String isValid = isValidTriangle ?
                    "Valid Triangle" :
                    "Invalid Triangle";
            String triangleType = this.getTriangleType(sideOne, sideTwo, sideThree);

            if (isValidTriangle) {
                this.results.clearComposingText();
                this.results.setText(isValid + " (" + triangleType + ")");
            } else {
                this.results.clearComposingText();
                this.results.setText(isValid);
            }

        }
    }

    public boolean isValidTriangle(int sideOne, int sideTwo, int sideThree) {
        Integer[] sides = { sideOne, sideTwo, sideThree };
        int max = Collections.max(Arrays.asList(sides));
        int sidesTotal = 0;

        for (int i = 0; i < sides.length; i += 1) {
            if (sides[i] != max) {
                sidesTotal += (sides[i] * sides[i]);
            }
        }

        return (sidesTotal == (max * max));
    }

    public String getTriangleType(int sideOne, int sideTwo, int sideThree) {
        Integer[] sides = { sideOne, sideTwo, sideThree };

        boolean isEquilateral = (sides[0] == sides[1] && sides[1] == sides[2]);
        boolean isScalene = (sides[0] != sides[1] && sides[1] != sides[2] && sides[0] != sides[2]);
        boolean isIsosceles = (sides[0] == sides[1] && sides[0] != sides[2]) ||
                (sides[0] == sides[2] && sides[0] != sides[1]);

        if (isEquilateral) {
            return "Equilateral";
        } else if (isScalene) {
            return "Scalene";
        } else {
            return "Isosceles";
        }
    }
}
