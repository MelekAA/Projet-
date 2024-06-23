package com.example.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {
EditText nom, classe;
Button modifier, supprimer;
int position ; // Position de l'élément sélectionné dans la liste
Params p =new Params(); //Instance de la classe Params qui contient les données des étudiants
HashMap<String,String> m; // HashMap pour stocker les valeurs des détails de l'étudiant sélectionné

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); // Lie le layout XML activity_detail à cette activité

        // Initialisation des vues (champs de texte et boutons)
        nom=findViewById(R.id.nom);
        classe=findViewById(R.id.classe);
        modifier=findViewById(R.id.modifier);
        supprimer=findViewById(R.id.supprimer);


        // Récupération des données passées à l'activité via une Intent
        Bundle extras =getIntent().getExtras();
        if(extras!=null){
            // Extraction de la position de l'étudiant sélectionné
            position=extras.getInt("position");
        }

        // Récupération des détails de l'étudiant sélectionné à partir de Params
        m= p.values.get(position);
        nom.setText(m.get("nom"));
        classe.setText(m.get("classe"));
        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Mise à jour des valeurs dans la HashMap avec les nouvelles données saisies
                m.put("nom",nom.getText().toString());
                m.put("classe",classe.getText().toString());

                // Création d'une Intent pour démarrer StudentsActivity
                Intent i=new Intent(getApplicationContext(), MainActivity.StudentsActivity.class);

                //Démarrage de l'activité StudentsActivity
                startActivity(i);
                finish();


            }
        });
// Configuration du bouton Supprimer
        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Suppression de l'élément sélectionné de Params
                p.values.remove(position);
                // Création d'une Intent pour démarrer StudentsActivity
                Intent i=new Intent(getApplicationContext(), MainActivity.StudentsActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}