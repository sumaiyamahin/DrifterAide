package com.example.sumaiyamahin.drifteraide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final int FOOD = 0;
    public static final int SHELTER = 1;
    public static int necessity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button food = findViewById(R.id.food);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFood();
            }
        });

        Button shelter = findViewById(R.id.shelter);
        shelter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShelter();
            }
        });
    }

    public void openFood(){
        necessity = FOOD;
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void openShelter(){
        necessity = SHELTER;
        Intent intent2 = new Intent(this, MapsActivity.class);
        startActivity(intent2);
    }

}
