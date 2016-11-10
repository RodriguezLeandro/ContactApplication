package com.example.leandro.contactapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Leandro on 07/11/2016.
 */
public class messagesActivity extends Activity implements View.OnClickListener {

    View button1;
    EditText editText1;
    boolean booleano = false;
    String email = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mandar_mensaje);

        Bundle bundle = new Bundle();

        bundle = getIntent().getExtras();

        if (bundle != null){
            booleano = true;
            email = (String)bundle.get("emailAux");
        }
        else{
            booleano = false;
        }

        button1 = findViewById(R.id.button1);
        editText1 = (EditText)findViewById(R.id.editText1);

        button1.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == button1.getId()){
            if (booleano) {
                System.out.print("El nombre que tenemos es : "+email);
                Intent mensajenormal = new Intent();
                mensajenormal.setAction(Intent.ACTION_SEND);
                mensajenormal.putExtra(Intent.EXTRA_EMAIL,email);
                mensajenormal.putExtra(android.content.Intent.EXTRA_TEXT, editText1.getText().toString());
                mensajenormal.setType("text/plain");
                startActivity(mensajenormal);
                finish();
            }
            else{
                Intent mensajewhatsapp = new Intent();
                mensajewhatsapp.setAction(Intent.ACTION_SEND);
                mensajewhatsapp.putExtra(Intent.EXTRA_TEXT, editText1.getText().toString());
                mensajewhatsapp.setType("text/plain");
                mensajewhatsapp.setPackage("com.whatsapp");
                startActivity(mensajewhatsapp);
                finish();
            }
        }
    }
}
