package fr.sam.projetrefuge;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Penssionnaires extends AppCompatActivity {

    ImageView ajouter, liste_toutous, camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penssionnaires);

        ajouter = (ImageView) findViewById(R.id.btn_ajou_penssionnaire);
        liste_toutous = (ImageView) findViewById(R.id.btn_liste_pensionnaires);
        camera = (ImageView) findViewById(R.id.btn_cam_pensionnaires);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent =new Intent(Penssionnaires.this, Ajouter_pensionnaires.class);
                startActivity(intent);*/
            }
        });
        liste_toutous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent =new Intent(Penssionnaires.this, Liste_pensionnaires.class);
                startActivity(intent);*/
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Camera.class);
                startActivity(intent);
            }
        });

    }
}