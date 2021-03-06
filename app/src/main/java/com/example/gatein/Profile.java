package com.example.gatein;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private Button button;
    private ImageView profilPic;
    private TextView profileName, profileMail, profilePhone, profileHoraires,profileCounter;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        profilPic = (ImageView) findViewById(R.id.imageViewProfilePic);
        profileName = (TextView)findViewById((R.id.tvProfilename));
        profileMail = (TextView)findViewById((R.id.tvProfilemail));
        profilePhone = (TextView)findViewById((R.id.tvProfilephone));
        profileHoraires = (TextView)findViewById((R.id.tvProfilehoraires));
        profileCounter = (TextView)findViewById((R.id.tvProfilenombreDemandes));

        button = findViewById(R.id.buttonchangemdp);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          openUpdatePassword();
                                      }
                                  });

        button = findViewById(R.id.buttonUpdateProfile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateProfile();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserProfile userProfile = snapshot.getValue(UserProfile.class);
                profileName.setText("Name = "+userProfile.getUserName());
                profileMail.setText("Mail = "+userProfile.getUserEmail());
                profilePhone.setText("Phone = "+userProfile.getUserPhone());
                profileHoraires.setText("Horaires = "+userProfile.getUserHoraires());
                profileCounter.setText("Nombre de demandes = "+String.valueOf(userProfile.getUserCounter()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, error.getCode(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openActivityConnexion() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void Logout() {
        firebaseAuth.signOut();
        openActivityConnexion();
    }
    public void openUpdateProfile() {
        Intent intent = new Intent(this, UpdateProfile.class);
        startActivity(intent);
        Profile.this.finish();
    }

    public void openUpdatePassword() {
        Intent intent = new Intent(this, UpdatePassword.class);
        startActivity(intent);
        Profile.this.finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public void openProfil() {
        finish();
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
    public void openSelection() {
        finish();
        Intent intent = new Intent(this, SelectionGatein.class);
        startActivity(intent);
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