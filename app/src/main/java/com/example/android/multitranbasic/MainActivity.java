package com.example.android.multitranbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private static final String MULTITRAN_URL = "https://www.multitran.com/m.exe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSpinners();

        Button translateButton = findViewById(R.id.translate_button);
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputTextView = findViewById(R.id.input_text_view);
                String inputText = inputTextView.getText().toString();

                WebView myWebView = findViewById(R.id.web_view);
                myWebView.loadUrl(MULTITRAN_URL);
            }
        });

    }

    private void setupSpinners () {
        Spinner inputLanguageSpinner =  findViewById(R.id.input_language_spinner);
        Spinner outputLanguageSpinner =  findViewById(R.id.output_language_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        inputLanguageSpinner.setAdapter(adapter);
        outputLanguageSpinner.setAdapter(adapter);

        inputLanguageSpinner.setSelection(1);

    }




}
