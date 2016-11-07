package com.example.leandro.contactapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Leandro on 07/11/2016.
 */
public class verContactosActivity extends Activity{

    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ver_contactos);

        listView = (ListView) findViewById(R.id.listView1);

        HandlerDatabase handlerDatabase = new HandlerDatabase(this);

        arrayList = handlerDatabase.getListOfNames();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String o = (String) listView.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(),infoContactoActivity.class);

                intent.putExtra("ContactoSeleccionado",o);

                startActivity(intent);

                finish();

            }
        });

    }

    }
