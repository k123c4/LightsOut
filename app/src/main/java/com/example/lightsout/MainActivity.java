package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int GRID_SIZE = 3;
    View.OnClickListener buttonlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button current = (Button) v;

            for (int i = 0; i < grid.getChildCount(); i++) {
                Button gridButton = (Button) grid.getChildAt(i);
                if (gridButton == current) {
                    int row = i / GRID_SIZE;
                    int col = i % GRID_SIZE;

                    if (cellState[row][col] == true) {
                        cellState[row][col] = false;
                    } else {
                        cellState[row][col] = true;

                    }
                }
            }
            recolor();
        }
    };
    private GridLayout grid;
    private boolean cellState [][];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cellState = new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}};

        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.light_grid);



        Button randomizeButton = findViewById(R.id.RandBT);
        randomizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomize();
                recolor();
            }
        });


        for (int i = 0; i < grid.getChildCount(); i++) {
            Button currButton = (Button) grid.getChildAt(i);
            currButton.setOnClickListener(buttonlistener);
        }
        recolor();
    }

    public void recolor(){
        for (int i = 0; i < grid.getChildCount(); i++) {
            Button gridButton = (Button) grid.getChildAt(i);

            // Find the button's row and col
            int row = i / GRID_SIZE;
            int col = i % GRID_SIZE;

            if (cellState[row][col] == true) {
                gridButton.setBackgroundColor(getColor(R.color.dark_moss));
            } else {
                gridButton.setBackgroundColor(getColor(R.color.light_green));
            }
        }
    }

    public void randomize(){
        Random random = new Random();
        for(int i =0; i< GRID_SIZE; i++){
            for(int j =0; j< GRID_SIZE; j++){
                cellState[i][j] = random.nextBoolean();
            }
        }
    }
}


