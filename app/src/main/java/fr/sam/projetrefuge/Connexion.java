package fr.sam.projetrefuge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Connexion extends AppCompatActivity {

    TextView MDP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        MDP =  findViewById(R.id.mot_de_passe_oublier);

        MDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Connexion.this, MotDePassOublier.class);
                startActivity(intent);
            }

        });
    }
}