package fr.sam.projetrefuge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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

import models.Users;
import utils.CheckFields;

public class Connexion extends AppCompatActivity {

    TextView mot_de_passe, error_mail_connexion, error_password_connexion;
    EditText mail_connexion, password_connexion;
    ImageView btn_connexion_connexion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        //appelle la méthode de falcification de certificat
        handleSSLHandshake();

        error_mail_connexion = (TextView) findViewById(R.id.error_mail_connexion);
        error_password_connexion = (TextView) findViewById(R.id.error_password_connexion);

        mail_connexion = (EditText) findViewById(R.id.mail_connexion);
        password_connexion = (EditText) findViewById(R.id.password_connexion);

        btn_connexion_connexion = (ImageView) findViewById(R.id.btn_connexion_connexion);

        mot_de_passe = findViewById(R.id.mot_de_passe_oublie_connexion);
        mot_de_passe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Connexion.this, MotDePassOublier.class);
                startActivity(intent);
            }

        });

        btn_connexion_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Users subs = new Users();
                subs.setAction("login");
                subs.setMail(mail_connexion.getText().toString());
                subs.setPassword((password_connexion.getText().toString()));
                // TODO faire ou appeler les méthodes de contrôles des champs

                String state_mail = CheckFields.mail_refuge_inscription(subs);
                error_mail_connexion.setText(state_mail);
                String state_password = CheckFields.password_refuge_connexion(subs);
                error_password_connexion.setText(state_password);
                if(state_mail.equals("") && state_password.equals("")){
                    volleyPost(subs);
                }
            }
            // méthode qui se charge de créer la string qui servira à la création du Json à envoyer au serveur backend PHP
            // elle a besoin en paramètre d'un objet, l'objet utilisateur(Users).
            public void volleyPost(Users subs) {

                // postUrl qui reçois l'adresse du serveur et le nom du script à appeler.
                String postUrl = "http://82.66.81.89/refuge.fr/refugeandro.php";// connexion à wamp à distance
                //String postUrl = "https://192.168.43.244/testing/refugeandro.php";// connexion a xampp en local



                RequestQueue requestQueue = Volley.newRequestQueue(Connexion.this);
                // créer les paires clé valeur à stocker dans l'objet json.
                // attention, j'ai créé une paire "action : "register" qui servira au script PHP pour router la demande, par exemple register->insert,
                // delete->del, maj->update par exemple.
                JSONObject postData = new JSONObject();
                try {
                    postData.put("action", subs.getAction());
                    postData.put("mail", subs.getMail());
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
                            // si "success" et égal à true, l'email et le mot de passe existe déjà en base de données
                            if (rep) {
                                //Toast.makeText(Connexion.this, "Bienvenue.", Toast.LENGTH_SHORT).show();
                                Intent login = new Intent(Connexion.this, Accueil.class);
                                startActivity(login);
                                // sinon le back m'as répondu que l'utilisateur veut utiliser un mail déjà existant, et donc un false sans insert et j'affiche un Toast.
                            } else {
                                Toast.makeText(Connexion.this, "Erreur dans les données saisis.", Toast.LENGTH_SHORT).show();
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