package kz.almaty.satbayevuniversity.ui.notification.portalNotification.webViewNotification;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.notification.Notification;

public class WebViewNotification extends AppCompatActivity {
    private Notification notification;
    private WebView webView;
    private Toolbar toolbar;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_notification1);
        webView = findViewById(R.id.notification_web_view);
        toolbar = findViewById(R.id.toolbarOfNotification);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        getFromIntent();

        initToolbar();

        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        webView.setWebViewClient(new WebViewClient()
          {
            @Override
              public void onPageStarted(WebView view, String url, Bitmap favicon) {
              super.onPageStarted(view, url, favicon);
              webView.setVisibility(View.GONE);
            }

            @Override
             public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.setVisibility(View.VISIBLE); }
          }
        );
            webView.loadDataWithBaseURL(null, notification.getContent(), "text/html", "UTF-8", null);
    }

    private void getFromIntent() {
        Intent intent = getIntent();
        notification= (Notification) intent.getSerializableExtra("WebViewNotification");
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.close_file_icon);
        getSupportActionBar().setTitle(notification.getTitle());
    }

}
