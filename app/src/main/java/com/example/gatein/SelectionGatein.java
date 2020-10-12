package com.example.gatein;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class SelectionGatein extends AppCompatActivity {
    private Button Ouvrir;
    private Button Historique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_gatein);
        Ouvrir=(Button)findViewById(R.id.buttonOuvrir);
        Historique=(Button)findViewById(R.id.buttonHistorique);
    }
}