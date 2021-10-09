package fr.sam.projetrefuge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Accueil extends AppCompatActivity {

    private ImageView btn_noe,btn_profil, btn_penssionnaires, btn_enclos, btn_contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        btn_noe = (ImageView) findViewById(R.id.btn_noe_accueil);
        btn_profil = (ImageView) findViewById(R.id.btn_profil_accueil);
        btn_penssionnaires = (ImageView) findViewById(R.id.btn_penssionnaires_accueil);
        btn_enclos = (ImageView) findViewById(R.id.btn_enclos_accueil);
        btn_contact = (ImageView) findViewById(R.id.btn_contact_accueil);



        btn_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Accueil.this, Profil.class);
                startActivity(intent);
            }

        });
        btn_penssionnaires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(Accueil.this, Penssionnaires.class);
                startActivity(intent);
            }

        });
        btn_enclos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Intent intent = new Intent(Accueil.this, Enclos.class);
                startActivity(intent);*/
            }

        });
        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent intent = new Intent(Accueil.this, Contacts.class);
                startActivity(intent);*/
            }

        });
    }
}