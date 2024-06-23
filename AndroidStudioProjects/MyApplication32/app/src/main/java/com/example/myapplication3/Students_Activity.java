package com.example.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;

public class Students_Activity extends AppCompatActivity{
    ListView ls;
    String nom, classe;
    HashMap<String,String> map;
    Params p =new Params();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        ls = findViewById(R.id.lst);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nom = extras.getString("nom");
            classe = extras.getString("classe");
            map = new HashMap<String, String>();
            map.put("nom", nom);
            map.put("classe", classe);
            p.values.add(map);}


        SimpleAdapter adapter = new SimpleAdapter(Students_Activity.this, p.values, R.layout.item,
                new String[]{"nom", "classe"},
                new int[]{R.id.nom, R.id.classe});


        ls.setAdapter(adapter);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Intent i= new Intent(getApplicationContext(),DetailActivity.class);
                i.putExtra("position",position);
                startActivity(i);

            }
        });


    }
}
