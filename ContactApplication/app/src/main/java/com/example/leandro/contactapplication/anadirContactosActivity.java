package com.example.leandro.contactapplication;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Leandro on 07/11/2016.
 */
public class anadirContactosActivity extends Activity implements View.OnClickListener{

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    View button1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_anadir_contactos);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);

        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == (R.id.button1)){

            HandlerDatabase handlerDatabase = new HandlerDatabase(this);

            handlerDatabase.setContactData(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString());

            Toast toast = Toast.makeText(this,"Contacto guardado: "+"\n"+editText1.getText().toString()+"\n"+editText2.getText().toString()+"\n"+editText3.getText().toString()+"\nEmail: "+editText4.getText().toString(), Toast.LENGTH_LONG);
            toast.show();
            finish();

        }
    }
}
