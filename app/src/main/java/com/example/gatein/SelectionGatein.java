package com.example.gatein;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SelectionGatein extends AppCompatActivity {
    private Button Ouvrir, Historique, Urgences;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_gatein);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},1);

        Ouvrir=(Button)findViewById(R.id.buttonOuvrir);
        Historique=(Button)findViewById(R.id.buttonHistorique);
        Urgences=(Button) findViewById(R.id.buttonUrgences);
        firebaseAuth = FirebaseAuth.getInstance();
        Ouvrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessage();
            }
        });
        Historique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistorique();
            }
        });
        Urgences.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrgences();
            }
        }));
    }
    public void openActivityConnexion() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        SelectionGatein.this.finish();
    }
    public void openUrgences() {
        new AlertDialog.Builder(this)
                .setTitle("Emergency")
                .setMessage("Etes vous sûr de vouloir réaliser cette action? Tout appui non sérieux s'expose à une ammende")
                .setIcon(R.drawable.warning)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:0299284321"));//Urgences de Rennes
                        if (ActivityCompat.checkSelfPermission(SelectionGatein.this,
                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(callIntent);
                    }})
                .setNegativeButton(android.R.string.no, null).show();
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

    //ouvrir la page Message après la page selection
    public void openMessage(){
        Intent intent = new Intent(this, OuvrirMessage.class);
        startActivity(intent);
        SelectionGatein.this.finish();
    }
    //ouvrir la page historique après la page selection
    public void openHistorique(){
        Intent intent = new Intent(this, Historique.class);
        startActivity(intent);
        SelectionGatein.this.finish();
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