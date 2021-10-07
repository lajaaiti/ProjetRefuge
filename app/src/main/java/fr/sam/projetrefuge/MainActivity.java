package fr.sam.projetrefuge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    CardView connexion, inscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connexion = (CardView) findViewById(R.id.btn_connexion_main);

        inscription = (CardView) findViewById(R.id.btn_inscription_main);

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Inscription.class);
                startActivity(intent);
            }

        });

       connexion.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), Connexion.class);
               startActivity(intent);

           }
       });

    }
}