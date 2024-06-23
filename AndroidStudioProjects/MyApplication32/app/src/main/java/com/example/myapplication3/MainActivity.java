package com.example.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    EditText nom,classe;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nom=findViewById(R.id.nom);
        classe=findViewById(R.id.classe);
        add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener()
            {
            @Override
            public void onClick(View v) {
                // Création d'un Intent pour naviguer vers StudentsActivity
                Intent i = new Intent(getApplicationContext(), StudentsActivity.class);
                // Passage de données à StudentsActivity à l'aide de putExtra()
                i.putExtra("nom", nom.getText().toString());
                i.putExtra("classe", classe.getText().toString());
                // Démarrage de StudentsActivity en utilisant l'Intent configuré
                startActivity(i);
            }

            });
        }

    public static class StudentsActivity extends AppCompatActivity {
        ListView ls;
        String nom, classe;
        HashMap<String,String> map;
        Params p =new Params();


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_students);




            // Récupération de la ListView depuis le layout activity_students.xml
            ls = findViewById(R.id.lst);

            // Récupération des données passées depuis l'activité précédent
            Bundle extras = getIntent().getExtras();
            if (extras != null) {

                // Extraction du nom et de la classe depuis les extras
                nom = extras.getString("nom");
                classe = extras.getString("classe");

                // Création d'une nouvelle HashMap pour stocker le nom et la classe
                map = new HashMap<String, String>();
                map.put("nom", nom);
                map.put("classe", classe);

                // Ajout de la HashMap à la liste de valeurs dans Params
                p.values.add(map);}


            // Création d'un SimpleAdapter pour lier les données à la ListView
            SimpleAdapter adapter = new SimpleAdapter(StudentsActivity.this, p.values, R.layout.item,
                    new String[]{"nom", "classe"},
                    new int[]{R.id.nom, R.id.classe});

            // Définition de l'adaptateur pour la ListView
            ls.setAdapter(adapter);

            // Gestion du clic sur un élément de la ListView
            ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                    // Lorsqu'un élément est cliqué, ouvrir DetailActivity pour afficher les détails de l'élément
                    Intent i= new Intent(getApplicationContext(),DetailActivity.class);
                    //Passer la position de l'élément cliqué à DetailActivity
                    i.putExtra("position",position);
                    startActivity(i);

                }
            });





        }
    }
}
