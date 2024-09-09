package hi.do_an_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Chi_tiet_video_Activity extends AppCompatActivity {
    WebView webView;
    String idVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_video);
        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        // LẤY ID VIDEO


        //==============================================================
        Intent intent = getIntent();
        String linkVideo = intent.getStringExtra("linkVideo");

        String videoId = linkVideo;
        String embedUrl = "https://www.youtube.com/embed/" + intent.getStringExtra("linkVideo");
        String html = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"" + embedUrl + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        webView.loadData(html, "text/html", "utf-8");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}