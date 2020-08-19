package com.example.android.multitranbasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout rootLayout;
    private Spinner inputLanguageSpinner;
    private Spinner outputLanguageSpinner;
    private EditText inputTextView;
    private WebView myWebView;
    private QueryUtils queryUtils = new QueryUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSpinners();

        Button translateButton = findViewById(R.id.translate_button);
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTextView = findViewById(R.id.input_text_view);
                String inputText = inputTextView.getText().toString();
                String inputLanguage = inputLanguageSpinner.getSelectedItem().toString();
                String outputLanguage = outputLanguageSpinner.getSelectedItem().toString();

                if (checkUserInput(inputText)) {
                    String url = queryUtils.buildUrl(inputText, inputLanguage, outputLanguage);
                    myWebView = findViewById(R.id.web_view);
                    myWebView.loadUrl(url);
                }

            }
        });



    }

    private void setupSpinners () {
        inputLanguageSpinner =  findViewById(R.id.input_language_spinner);
        outputLanguageSpinner =  findViewById(R.id.output_language_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        inputLanguageSpinner.setAdapter(adapter);
        outputLanguageSpinner.setAdapter(adapter);

        //setting default input language (russian)
        inputLanguageSpinner.setSelection(1);

    }

    private boolean checkUserInput (String inputText) {
     boolean userInputPresence = false;

     if (! inputText.isEmpty()) {
         userInputPresence = true;}
     else {
         rootLayout = findViewById(R.id.constraint_layout);
         Snackbar.make(rootLayout, "Введите слово, которое хотите перевести", Snackbar.LENGTH_SHORT).show();
     }

     return userInputPresence;
    }




}
