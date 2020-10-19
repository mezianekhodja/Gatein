package com.example.gatein;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class Message extends AppCompatActivity {
    private EditText message;
    private Button ok;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        message=(EditText) findViewById(R.id.editTextmessage);
        ok=(Button) findViewById(R.id.button_message);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void openActivityConnexion() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Message.this.finish();
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
        Message.this.finish();
    }
    public void openSelection() {
        Intent intent = new Intent(this, SelectionGatein.class);
        startActivity(intent);
        Message.this.finish();
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
            case R.id.HomeMenu:{
                openSelection();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}