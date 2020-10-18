package com.example.gatein;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SelectionGatein extends AppCompatActivity {
    private Button Ouvrir;
    private Button Historique;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_gatein);
        Ouvrir=(Button)findViewById(R.id.buttonOuvrir);
        Historique=(Button)findViewById(R.id.buttonHistorique);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void openActivityConnexion() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        SelectionGatein.this.finish();
    }
    private void Logout() {
        firebaseAuth.signOut();
        openActivityConnexion();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public void openProfil() {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
        SelectionGatein.this.finish();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
            }
            case R.id.profileMenu:{
                openProfil();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}