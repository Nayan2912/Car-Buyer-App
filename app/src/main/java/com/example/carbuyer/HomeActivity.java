package com.example.carbuyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {
    Button button, button1;
    private WebView webView;  // for displaying web contents
    private EditText txtURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button = findViewById(R.id.btnGo);
        button1=findViewById(R.id.button2);
        webView = (WebView) findViewById(R.id.webView);
        txtURL = (EditText) findViewById(R.id.txtURL);

        String pass=getIntent().getStringExtra("pass").toString();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), formsActivity.class);
                intent.putExtra("pass",pass);
                startActivity(intent);
            }
        });
    }
    public void btnGoHandler(View view) {
        // show the web page of the URL of the EditText
        webView.loadUrl(txtURL.getText().toString());
    }

}