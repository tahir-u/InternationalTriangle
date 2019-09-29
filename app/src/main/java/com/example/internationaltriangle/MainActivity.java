package com.example.internationaltriangle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonClick;
    private EditText sideOne, sideTwo, sideThree;
    private TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonClick = findViewById(R.id.generateButton);
        buttonClick.setOnClickListener(MainActivity.this);

    }

    public void onClick(View v) {
        if (v == this.buttonClick) {
            System.out.println("Generate");
        }
    }
}
