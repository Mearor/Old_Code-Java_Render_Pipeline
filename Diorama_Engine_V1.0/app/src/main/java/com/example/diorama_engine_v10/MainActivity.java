package com.example.diorama_engine_v10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button Initialise_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialise_Screen_Components();


        Initialise_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),Engine_Activity.class);
                startActivity(intent);
            }
        });


    }

    public void Initialise_Screen_Components(){

        Initialise_Button =  (Button)findViewById(R.id.Initialise_Button);
    }
}