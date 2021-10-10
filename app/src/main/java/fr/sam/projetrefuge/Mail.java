package fr.sam.projetrefuge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Mail extends AppCompatActivity {

    EditText etObjet, etMessage;
    Button btnEnvoyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);


        etObjet = findViewById(R.id.et_objet);
        etMessage = findViewById(R.id.et_message);
        btnEnvoyer = findViewById(R.id.et_btn_envoyer);

        btnEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto: victorrettenbach@outlook.fr")); //mail victor
                intent.putExtra(Intent.EXTRA_SUBJECT,etObjet.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,etMessage.getText().toString());
                startActivity(intent);
            }
        });
    }
}