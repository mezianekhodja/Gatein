package com.example.gatein;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class OuverturePorte extends AppCompatActivity {
    private Button button;
    private TextView textView;
    String username = "Undefined";
    private static final String KEY_USER = "user", KEY_DATE="date", KEY_MESSAGE="message";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    String currentTime = Calendar.getInstance().getTime().toString();
    private static final String TAG = "OuverturePorte";
    String horairesUser = "acces non autorisé";
    String reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ouverture_porte);

        button = findViewById(R.id.buttonLOADGate);
        textView = findViewById(R.id.textViewresOP);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserProfile userProfile = snapshot.getValue(UserProfile.class);
                username=userProfile.getUserName();
                horairesUser=userProfile.getUserHoraires();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OuverturePorte.this, error.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    //si on souhaite charger
    public void loadNote(){
        db.collection(username).document("1").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String message = documentSnapshot.get(KEY_MESSAGE).toString();
                            String date = documentSnapshot.get(KEY_DATE).toString();

                            textView.setText("Date : "+date + "user : "+username+"message  : "+ message);
                        }
                        else{
                            Toast.makeText(OuverturePorte.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(OuverturePorte.this, "Fail", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }
    public void saveNote() {
        reason = getIntent().getExtras().getString("raison");
        Map<String, Object> note = new HashMap<>();
        note.put(KEY_USER,username);
        note.put(KEY_DATE,currentTime);
        note.put(KEY_MESSAGE,reason);

            db.collection(username).document("1").set(note)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            textView.setText("Demande transmise : date : "+currentTime + ", user : "+username+", message  : "+ reason);
                            Toast.makeText(OuverturePorte.this, "Sucess", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(OuverturePorte.this, "Fail", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, e.toString());
                        }
                    });
        }
}