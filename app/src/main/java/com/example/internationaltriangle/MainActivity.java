package com.example.internationaltriangle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
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
        this.buttonClick.setInputType(InputType.TYPE_NULL);

        // Add text handlers/listeners
        this.sideOne = findViewById(R.id.input1);
        this.sideOne.setInputType(InputType.TYPE_CLASS_NUMBER);

        this.sideTwo = findViewById(R.id.input2);
        this.sideTwo.setInputType(InputType.TYPE_CLASS_NUMBER);

        this.sideThree = findViewById(R.id.input3);
        this.sideThree.setInputType(InputType.TYPE_CLASS_NUMBER);

        // Add results handler
        this.results = findViewById(R.id.resultsTextBox);

    }

    public void onClick(View v) {
        if (v == this.buttonClick) {

            String sideOneString = this.sideOne.getText().toString();
            String sideTwoString = this.sideTwo.getText().toString();
            String sideThreeString = this.sideThree.getText().toString();

            if (isValidInput(sideOneString, sideTwoString, sideThreeString)) {
                int sideOne = Integer.parseInt(this.sideOne.getText().toString());
                int sideTwo = Integer.parseInt(this.sideTwo.getText().toString());
                int sideThree = Integer.parseInt(this.sideThree.getText().toString());
                boolean isValidTriangle = this.isValidTriangle(sideOne, sideTwo, sideThree);
                String triangleType = this.getTriangleType(sideOne, sideTwo, sideThree);

                this.results.clearComposingText();
                this.results.setText(triangleType + " triangle");
            } else {
                this.results.clearComposingText();
                this.results.setText("One or more sides are missing for the triangle type calculation.");
            }

        }
    }

//    public boolean isValidTriangle(int sideOne, int sideTwo, int sideThree) {
//        Integer[] sides = { sideOne, sideTwo, sideThree };
//        int max = Collections.max(Arrays.asList(sides));
//        int sidesTotal = 0;
//
//        for (int i = 0; i < sides.length; i += 1) {
//            if (sides[i] != max) {
//                sidesTotal += (sides[i] * sides[i]);
//            }
//        }
//
//        return (sidesTotal == (max * max));
//    }

    public String getTriangleType(int sideOne, int sideTwo, int sideThree) {
        Integer[] sides = { sideOne, sideTwo, sideThree };

        boolean isEquilateral = (sides[0] == sides[1] && sides[1] == sides[2]);
        boolean isScalene = (sides[0] != sides[1] && sides[1] != sides[2] && sides[0] != sides[2]);
//        boolean isIsosceles = (sides[0] == sides[1] && sides[0] != sides[2]) ||
//                (sides[0] == sides[2] && sides[0] != sides[1]);

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
