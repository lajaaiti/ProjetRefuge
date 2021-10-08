package fr.sam.projetrefuge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Profil extends AppCompatActivity {

    Button modifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        modifier = (Button) findViewById(R.id.btn_modifier_profil);

                modifier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Profil.this, Profil_bis.class);
                        startActivity(intent);
                    }
                });
    }
}