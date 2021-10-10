package fr.sam.projetrefuge;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Contacts extends AppCompatActivity {

    ImageView telephone, mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        telephone = (ImageView) findViewById(R.id.btn_telephone);
        mail = (ImageView) findViewById(R.id.btn_mail);

         mail.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Contacts.this, Mail.class);
            startActivity(intent);

                 }
             });

        telephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0033769799063")); //num lolo
                startActivity(intent);
            }
        });
    }
}