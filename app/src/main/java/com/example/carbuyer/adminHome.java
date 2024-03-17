package com.example.carbuyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class adminHome extends AppCompatActivity {
    private WebView webView;  // for displaying web contents
    private EditText txtURL;
    Button regbt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        webView = (WebView) findViewById(R.id.webView);
        txtURL = (EditText) findViewById(R.id.txtURL);
         regbt= (Button) findViewById(R.id.regbtn);
         regbt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(), regactivityCB.class);
                 startActivity(intent);
             }
         });

    }

    public void btnGoHandler(View view) {
        // show the web page of the URL of the EditText
        webView.loadUrl(txtURL.getText().toString());
    }


}