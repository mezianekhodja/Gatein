package com.example.gatein;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Message extends AppCompatActivity {
    private EditText message;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        message=(EditText) findViewById(R.id.editText1);
        ok=(Button) findViewById(R.id.button);
    }
}