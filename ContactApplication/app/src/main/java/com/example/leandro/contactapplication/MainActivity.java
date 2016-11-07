package com.example.leandro.contactapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    View button1;
    View button2;
    View button3;
    HandlerDatabase handlerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button1){
            Intent i = new Intent(this,verContactosActivity.class);
            startActivity(i);
        }
        if (view.getId() == R.id.button2){
            Intent j = new Intent(this,anadirContactosActivity.class);
            startActivity(j);
        }
        if (view.getId() == R.id.button3){
            handlerDatabase = new HandlerDatabase(getApplicationContext());
            handlerDatabase.deleteAllFromDatabase();
            Toast toast = Toast.makeText(getApplicationContext(),"Base de datos completamente borrada.", Toast.LENGTH_LONG);
            toast.show();
        }

    }
}
