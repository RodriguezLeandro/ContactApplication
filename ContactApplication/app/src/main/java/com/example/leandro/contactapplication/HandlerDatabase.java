package com.example.leandro.contactapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Leandro on 07/11/2016.
 */
public class HandlerDatabase extends SQLiteOpenHelper {

    public HandlerDatabase(Context context) {
        super(context, "ContactDatabase", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query,query2,query3,nombre, apellido, telefono;

        query = "CREATE TABLE IF NOT EXISTS Contactos(Nombre VARCHAR, Apellido VARCHAR, Telefono INT);";

        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int veranterior, int vernueva) {

    }

    public void setContactData(String nombre, String apellido, String numeroTelefono){
        ContentValues values = new ContentValues();

        values.put("Nombre",nombre);
        values.put("Apellido",apellido);
        values.put("Telefono",numeroTelefono);

        this.getWritableDatabase().insert("Contactos",null,values);
    }

    public String getContactData(int position){
        String resultado;
        String columnas[] = {"Nombre","Apellido","Telefono"};

        Cursor cursor = this.getReadableDatabase().query("Contactos",columnas,null,null,null,null,null);

        int indexNombre, indexApellido, indexTelefono;

        indexNombre = cursor.getColumnIndex("Nombre");
        indexApellido = cursor.getColumnIndex("Apellido");
        indexTelefono = cursor.getColumnIndex("Telefono");

        cursor.moveToPosition(position);

        resultado = "Nombre : "+cursor.getString(indexNombre)+"\nApellido: "+cursor.getString(indexApellido)+"\nTelefono: "+cursor.getString(indexTelefono);

        return resultado;
    }

    public ArrayList<String> getListOfNames(){

        ArrayList<String> arrayList = new ArrayList<String>();

        String columnas[] = {"Nombre"};

        Cursor cursor = this.getReadableDatabase().query("Contactos",columnas,null,null,null,null,null);

        int indexNombre = cursor.getColumnIndex("Nombre");
        int i = 0;

        for(cursor.moveToFirst(); !cursor.isAfterLast();cursor.moveToNext()){
            arrayList.add(i,cursor.getString(indexNombre));
        }

        return arrayList;
    }
    public String getApellido(String nombre){
        String apellidoAux = "";
        String columnas[] = {"Nombre","Apellido"};
        Cursor cursor = this.getReadableDatabase().query("Contactos",columnas,null,null,null,null,null);
        int indexNombre = cursor.getColumnIndex("Nombre");
        int indexApellido = cursor.getColumnIndex("Apellido");

        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            if (cursor.getString(indexNombre).equals(nombre)){
                apellidoAux = cursor.getString(indexApellido);
            }
        }

        return apellidoAux;
    }
    public String getTelefono(String nombre){
        String apellidoAux = "";
        String columnas[] = {"Nombre","Telefono"};
        Cursor cursor = this.getReadableDatabase().query("Contactos",columnas,null,null,null,null,null);
        int indexNombre = cursor.getColumnIndex("Nombre");
        int indexTelefono = cursor.getColumnIndex("Telefono");

        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            if (cursor.getString(indexNombre).equals(nombre)){
                apellidoAux = cursor.getString(indexTelefono);
            }
        }

        return apellidoAux;
    }
    public void setModifiedContactData(String nombre, String apellido, String telefono){

        String query;
        query = "UPDATE Contactos SET Nombre = '"+nombre+"', Apellido = '"+apellido+"', Telefono = '"+telefono+"' " +
                "WHERE (NOMBRE = '"+nombre+"' OR APELLIDO = '"+apellido+"' ) ";
        this.getWritableDatabase().execSQL(query);
    }
    public void eliminarContacto(String nombre){

        String query;
        query = "DELETE FROM Contactos WHERE NOMBRE = '"+nombre+"' ";
        this.getWritableDatabase().execSQL(query);
    }
    public void deleteAllFromDatabase(){
        String query;
        query = "DELETE FROM Contactos";
        this.getWritableDatabase().execSQL(query);
    }


}
