package com.example.sumaiyamahin.drifteraide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button high_priority = findViewById(R.id.highPriority);
        high_priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHighPriority();
            }
        });

        Button low_priority = findViewById(R.id.lowPriority);
        low_priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLowPriority();
            }
        });
    }

    public void openHighPriority(){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void openLowPriority(){
        Intent intent2 = new Intent(this, Main3Activity.class);
        startActivity(intent2);
    }

}
