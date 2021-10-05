package fr.sam.projetrefuge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import models.Users;
import utils.CheckFields;

public class Inscription extends AppCompatActivity {


    //déclaration des 'EditText' qui vont servir de passerelle pour le bindage.

    private EditText refuge_inscription, rna_inscription, mail_inscription, password_inscription,telephone_inscription, confrimez_inscription;
    private ImageView btn_inscription;
    private TextView error_refuge_inscription, error_nra_inscription, error_mail_inscription, error_password_inscription, error_telephone_inscription, error_confirm_inscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        //appelle la méthode de falcification de certificat

        handleSSLHandshake();


        // Bindage des champs dans la vue xml avec les 'EditText' déclarés précedement.
        refuge_inscription = (EditText) findViewById(R.id.refuge_inscription);
        rna_inscription = (EditText) findViewById(R.id.rna_inscription);
        mail_inscription = (EditText) findViewById(R.id.mail_inscription);
        password_inscription = (EditText) findViewById(R.id.password_inscription);
        telephone_inscription = (EditText) findViewById(R.id.telephone_inscription);
        confrimez_inscription = (EditText) findViewById(R.id.confirmez_inscription);


        btn_inscription = (ImageView) findViewById(R.id.btn_inscription);

        error_refuge_inscription = (TextView) findViewById(R.id.error_refuge_inscription);
        error_nra_inscription = (TextView) findViewById(R.id.error_rna_inscription);
        error_mail_inscription = (TextView) findViewById(R.id.error_mail_inscription);
        error_password_inscription = (TextView) findViewById(R.id.error_password_inscription);
        error_telephone_inscription = (TextView) findViewById(R.id.error_telephone_inscription);
        error_confirm_inscription = (TextView) findViewById(R.id.error_confirm_inscription);

        // placement d'un écpiteur d'événements sur l'imageView

        btn_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lorsque le bouton est cliqué, création d'un objet.

                Users subs = new Users();

                // affectation des valeurs aux attributs de l'objet.
                // l'attribut action est nécessaire lors de l'appel dans le backend. Il me permet de faire du routage.
                subs.setAction("register");
                subs.setId_refuge(refuge_inscription.getText().toString());
                subs.setNra_code(rna_inscription.getText().toString());
                subs.setId_refuge(refuge_inscription.getText().toString());
                subs.setMail(mail_inscription.getText().toString());
                subs.setPassword(password_inscription.getText().toString());
                subs.setTelephone(telephone_inscription.getText().toString());
                subs.setConfirm(confrimez_inscription.getText().toString());

                // Appel aux différentes  méthode pour contrôler la validaités des champs saisis
                String state_refuge = CheckFields.nom_refuge_inscription(subs);
                error_refuge_inscription.setText(state_refuge);
                String state_rna = CheckFields.rna_refuge_inscription(subs);
                error_nra_inscription.setText(state_rna);
                String state_mail = CheckFields.mail_refuge_inscription(subs);
                error_mail_inscription.setText(state_mail);
                String state_password = CheckFields.password_refuge_inscription(subs);
                error_password_inscription.setText(state_password);
                String state_telephone = CheckFields.telephone_refuge_inscription(subs);
                error_telephone_inscription.setText(state_telephone);
                String state_confirm = CheckFields.confirm_refuge_inscription(subs);
                error_confirm_inscription.setText(state_confirm);



                if(state_refuge.equals("") && state_rna.equals("") && state_mail.equals("") && state_password.equals("") && state_telephone.equals("") && state_confirm.equals("")){
                    volleyPost(subs);
                }
            }


            // méthode qui se charge de créer la string qui servira à la création du Json à envoyer au serveur backend PHP
            // elle a besoin en paramètre d'un objet, l'objet utilisateur(Users).
            public void volleyPost(Users subs) {

                // postUrl qui reçois l'adresse du serveur et le nom du script à appeler.
                //String postUrl = "https://192.168.43.244/testing/write.php";// connexion a xampp en local
                String postUrl = "https://192.168.43.244/testing/refugeandro.php";// connexion a xampp en local
                RequestQueue requestQueue = Volley.newRequestQueue(Inscription.this);
                // créer les paires clé valeur à stocker dans l'objet json.
                // attention, j'ai créé une paire "action : "register" qui servira au script PHP pour router la demande, par exemple register->insert,
                // delete->del, maj->update par exemple.
                JSONObject postData = new JSONObject();
                try {
                    postData.put("action", subs.getAction());
                    postData.put("id_refuge", subs.getId_refuge());
                    postData.put("nra_code", subs.getNra_code());
                    postData.put("mail", subs.getMail());
                    postData.put("telephone", subs.getTelephone());
                    postData.put("password", subs.getPassword());


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //test pour afficher le retour
                        System.out.println(response);
                        // try catch qui me permet de récuperer la réponse du back et de la stocker dans une variable que je vais passer sous condition
                        try {
                            boolean rep = response.getBoolean("success");
                            // si "success" et égal à true, l'email n'existe pas en Base de données, j'ai déjà inscrit l'utilisateur dans la base, je le redirige vers login
                            if (rep) {
                                Intent login = new Intent(Inscription.this, Connexion.class);
                                startActivity(login);
                                // sinon le back m'as répondu que l'utilisateur veut utiliser un mail déjà existant, et donc un false sans insert et j'affiche un Toast.
                            } else {
                                Toast.makeText(Inscription.this, "Adresse mail déjà utilisée.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                requestQueue.add(jsonObjectRequest);
                // System.out.println("ici -------------------->"+ jsonObjectRequest);
            }

        });
    }
    //Methode à ne pas utiliser hors dev local "simule la présence d'un certificat de confiance 'cert' SSL"
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
}