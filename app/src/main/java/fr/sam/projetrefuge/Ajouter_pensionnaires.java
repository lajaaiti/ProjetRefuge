package fr.sam.projetrefuge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;


import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import models.Pensionnaires;
//import caméra :
import android.widget.ImageView;
//fin import caméra.

public class Ajouter_pensionnaires extends AppCompatActivity {


    String image;
    EditText nom_ajou_pensionnaires, espece_ajou_pensionnaires, sexe_ajou_pensionnaires, num_enclos_ajou_pensionnaires;
    EditText age_ajou_pensionnaires, vaccin_ajou_pensionnaires, proprietaire_ajou_pensionnaires;
    EditText mail_ajou_pensionnaires, telephone_ajou_pensionnaires, date_arrive_ajou_pensionnaires, date_sortie_ajou_pensionnaires , commentaire_ajou_pensionnaires;
    Button btn_ajouter_toutou, btn_ajou_photo;
    ImageView camera;

    //module photo
    //module photo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_pensionnaires);

        handleSSLHandshake(); // pour le certificat SSL
        nom_ajou_pensionnaires = (EditText) findViewById(R.id.nom_ajou_pensionnaires);
        espece_ajou_pensionnaires = (EditText) findViewById(R.id.espece_ajou_pensionnaires);
        sexe_ajou_pensionnaires = (EditText) findViewById(R.id.sexe_ajou_pensionnaires);
        num_enclos_ajou_pensionnaires = (EditText) findViewById(R.id.num_enclo_ajou_pensionnaires);
        age_ajou_pensionnaires = (EditText) findViewById(R.id.age_ajou_pensionnaires);
        vaccin_ajou_pensionnaires = (EditText) findViewById(R.id.vaccin_ajou_pensionnaires);
        proprietaire_ajou_pensionnaires = (EditText) findViewById(R.id.prorietaire_ajou_pensionnaires);
        mail_ajou_pensionnaires = (EditText) findViewById(R.id.mail_ajou_pensionnaires);
        telephone_ajou_pensionnaires = (EditText) findViewById(R.id.telephone_ajou_pensionnaires);
        date_arrive_ajou_pensionnaires = (EditText) findViewById(R.id.date_arrive_ajou_pensionnaires);
        date_sortie_ajou_pensionnaires = (EditText) findViewById(R.id.date_sortie_ajou_pensionnaires);
        commentaire_ajou_pensionnaires = (EditText) findViewById(R.id.commentaire_ajou_pensionnaires);
        btn_ajouter_toutou = (Button) findViewById(R.id.btn_ajouter_toutou);


        //------------------------module camera----------------------
        camera = (ImageView) findViewById(R.id.camera);
        btn_ajou_photo = (Button) findViewById(R.id.btn_ajou_photo);
        if (ContextCompat.checkSelfPermission(Ajouter_pensionnaires.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Ajouter_pensionnaires.this,
                    new String[]{
                            Manifest.permission.CAMERA
            },100);
        }
        btn_ajou_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
        //------------------------module camera----------------------



        btn_ajouter_toutou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pensionnaires pens = new Pensionnaires();

                pens.setAction("add_pens");
                pens.setNom(nom_ajou_pensionnaires.getText().toString());
                pens.setEspece(espece_ajou_pensionnaires.getText().toString());
                pens.setSexe(sexe_ajou_pensionnaires.getText().toString());
                pens.setNum_enclos(num_enclos_ajou_pensionnaires.getText().toString());
                pens.setAge(age_ajou_pensionnaires.getText().toString());
                pens.setVaccin(vaccin_ajou_pensionnaires.getText().toString());
                pens.setProprietaire(proprietaire_ajou_pensionnaires.getText().toString());
                pens.setMail(mail_ajou_pensionnaires.getText().toString());
                pens.setTelephone(telephone_ajou_pensionnaires.getText().toString());
                pens.setDate_arrive(date_arrive_ajou_pensionnaires.getText().toString());
                pens.setDate_sortie(date_sortie_ajou_pensionnaires.getText().toString());
                pens.setCommentaires(commentaire_ajou_pensionnaires.getText().toString());
                //TODO toujours et encore le module image
                // ici j'essai de convertir l'image e, byte pour l'intégrer à l'objet.
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                captureImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                //pens.setImage();
                //TODO toujours et encore le module image


                volleyPost(pens);
            }


            public void volleyPost(Pensionnaires pens) {

                // postUrl qui reçois l'adresse du serveur et le nom du script à appeler.
                //String postUrl = "https://192.168.43.244/testing/refugeandro.php";// connexion a xampp en local
                String postUrl = "http://82.66.81.89/refuge.fr/refugeandro.php";// connexion a Wamp domicile laurent
                RequestQueue requestQueue = Volley.newRequestQueue(Ajouter_pensionnaires.this);
                // créer les paires clé valeur à stocker dans l'objet json.
                // attention, j'ai créé une paire "action : "register" qui servira au script PHP pour router la demande, par exemple register->insert,
                // delete->del, maj->update par exemple.
                JSONObject postData = new JSONObject();
                try {
                    postData.put("action", pens.getAction());
                    postData.put("nom", pens.getNom());
                    postData.put("espece", pens.getEspece());
                    postData.put("sexe", pens.getSexe());
                    postData.put("num_enclos", pens.getNum_enclos());
                    postData.put("age", pens.getAge());
                    postData.put("vaccin", pens.getVaccin());
                    postData.put("proprietaire", pens.getProprietaire());
                    postData.put("mail", pens.getMail());
                    postData.put("telephone", pens.getTelephone());
                    postData.put("date_arrivee", pens.getDate_arrive());
                    postData.put("date_sortie", pens.getDate_sortie());
                    postData.put("commentaires", pens.getCommentaires());
                    // test sur l'ajout de l'image en cours...
                   // postData.put("image",pens.getImage());
                    //System.out.println("------------------------------------------>"+pens.getImage());
                    // test sur l'ajout de l'image en cours...


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
                                Toast.makeText(Ajouter_pensionnaires.this, "Pensionnaires ajouté.", Toast.LENGTH_SHORT).show();

                                // sinon le back m'as répondu que l'utilisateur veut utiliser un mail déjà existant, et donc un false sans insert et j'affiche un Toast.
                            } else {
                                Toast.makeText(Ajouter_pensionnaires.this, "Pensionnaires déjà existant.", Toast.LENGTH_SHORT).show();
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
    //module image
    // TODO à finaliser l'affichage est fonctionel dans la vu mais l'objet n'est pas encore setter correctement.
    // cette méthode est lié au code de la ligne 78 à 95

    static Bitmap captureImage;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            captureImage = (Bitmap) data.getExtras().get("data");
            camera.setImageBitmap(captureImage);
        }
    }
    //module image


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