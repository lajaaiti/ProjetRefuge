package fr.sam.projetrefuge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Camera extends AppCompatActivity {

    WebView webcam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        webcam = (WebView) findViewById(R.id.webcam);

        webcam.setWebViewClient(new WebViewClient());
        webcam.loadUrl("https://www.youtube.com/watch?v=rjLPm4hhJag");

        WebSettings webSettings = webcam.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
    @Override
    public void onBackPressed() {
        if (webcam.canGoBack()) {
            webcam.goBack();
        } else {
            super.onBackPressed();
        }
    }

}