package com.example.leandro.contactapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Leandro on 07/11/2016.
 */
public class infoContactoActivity extends Activity implements View.OnClickListener {


    Button button1, button2, button3, button4, button5;
    Bundle bundle;
    String nombre, apellido, telefono, email;
    HandlerDatabase handlerDatabase;
    EditText editText1, editText2, editText3, editText4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info_contacto);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            nombre = (String) bundle.get("ContactoSeleccionado");
        } else {
            //No deberia entrar aca creo.
            finish();
        }

        handlerDatabase = new HandlerDatabase(getApplicationContext());
        apellido = handlerDatabase.getApellido(nombre);
        telefono = handlerDatabase.getTelefono(nombre);
        email = handlerDatabase.getEmail(nombre);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);

        editText1.setText(nombre);
        editText2.setText(apellido);
        editText3.setText(telefono);
        editText4.setText(email);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        //Button1 es Llamar.
        if (view.getId() == R.id.button1) {
            try {
                Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+editText3.getText().toString()));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intentLlamada);
                finish();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //Button2 es Mandar whatsapp.
        if (view.getId() == R.id.button2){
            Intent intentWhatsApp = new Intent(getApplicationContext(),messagesActivity.class);
            startActivity(intentWhatsApp);
            finish();

        }
        //Button3 es Mandar mensaje.
        if (view.getId() == R.id.button3){
            Intent intentmessage = new Intent(getApplicationContext(),messagesActivity.class);
            intentmessage.putExtra("booleano",2);
            intentmessage.putExtra("emailAux",editText4.getText().toString());
            startActivity(intentmessage);
            finish();

        }
        //Button4 es Editar contacto.
        if (view.getId() == R.id.button4){
            handlerDatabase.setModifiedContactData(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString());
            Toast toast = Toast.makeText(getApplicationContext(),"Contacto actualizado: "+
                    "\nNombre: "+editText1.getText().toString()+"\nApellido: "+
                    editText2.getText().toString()+"\nTelefono: "+editText3.getText().toString()+"\nEmail: "+editText4.getText().toString(), Toast.LENGTH_LONG);
            toast.show();
            finish();
        }
        //Button5 es Eliminar contacto.
        if (view.getId() == R.id.button5){
            handlerDatabase.eliminarContacto(editText1.getText().toString());
            Toast toast = Toast.makeText(getApplicationContext(),"Contacto Eliminado : "+editText1.getText().toString(), Toast.LENGTH_LONG);
            toast.show();
            finish();
        }
    }
}
