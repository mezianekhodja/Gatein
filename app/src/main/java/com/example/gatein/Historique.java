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

public class Historique extends AppCompatActivity {;
    private Button button;
    private TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
    String username = "";
    int counter=0;
    private static final String KEY_USER = "user", KEY_DATE="date", KEY_MESSAGE="message";
    private static final String TAG = "Historique";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
        button = findViewById(R.id.buttonafficheHist);
        textView1 = findViewById(R.id.textViewresHist1);
        textView2 = findViewById(R.id.textViewresHist2);
        textView3 = findViewById(R.id.textViewresHist3);
        textView4 = findViewById(R.id.textViewresHist4);
        textView5 = findViewById(R.id.textViewresHist5);
        textView6 = findViewById(R.id.textViewresHist6);
        textView7 = findViewById(R.id.textViewresHist7);
        textView8 = findViewById(R.id.textViewresHist8);
        textView9 = findViewById(R.id.textViewresHist9);
        textView10 = findViewById(R.id.textViewresHist10);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNote();
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserProfile userProfile = snapshot.getValue(UserProfile.class);
                username=userProfile.getUserName();
                counter=userProfile.getUserCounter()-1;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Historique.this, error.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void loadNote(){
        db.collection(username).document(String.valueOf(counter)).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
        @Override
        public void onSuccess(DocumentSnapshot documentSnapshot) {
            if (documentSnapshot.exists()){
                String message = documentSnapshot.get(KEY_MESSAGE).toString();
                String date = documentSnapshot.get(KEY_DATE).toString();

                textView1.setText("Résultat 1 : Date : "+date + ", user : "+username+", message  : "+ message);
            }
            else{
                Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        }
    })
            .addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
            Log.d(TAG, e.toString());
        }
    });
        db.collection(username).document(String.valueOf(counter-1)).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String message = documentSnapshot.get(KEY_MESSAGE).toString();
                            String date = documentSnapshot.get(KEY_DATE).toString();

                            textView2.setText("Résultat 2 : Date : "+date + ", user : "+username+", message  : "+ message);
                        }
                        else{
                            Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
        db.collection(username).document(String.valueOf(counter-2)).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String message = documentSnapshot.get(KEY_MESSAGE).toString();
                            String date = documentSnapshot.get(KEY_DATE).toString();

                            textView3.setText("Résultat 3 : Date : "+date + ", user : "+username+", message  : "+ message);
                        }
                        else{
                            Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
        db.collection(username).document(String.valueOf(counter-3)).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String message = documentSnapshot.get(KEY_MESSAGE).toString();
                            String date = documentSnapshot.get(KEY_DATE).toString();

                            textView4.setText("Résultat 4 : Date : "+date + ", user : "+username+", message  : "+ message);
                        }
                        else{
                            Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
        db.collection(username).document(String.valueOf(counter-4)).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String message = documentSnapshot.get(KEY_MESSAGE).toString();
                            String date = documentSnapshot.get(KEY_DATE).toString();

                            textView5.setText("Résultat 5 : Date : "+date + ", user : "+username+", message  : "+ message);
                        }
                        else{
                            Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
        db.collection(username).document(String.valueOf(counter-5)).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String message = documentSnapshot.get(KEY_MESSAGE).toString();
                            String date = documentSnapshot.get(KEY_DATE).toString();

                            textView6.setText("Résultat 6 : Date : "+date + ", user : "+username+", message  : "+ message);
                        }
                        else{
                            Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
        db.collection(username).document(String.valueOf(counter-6)).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String message = documentSnapshot.get(KEY_MESSAGE).toString();
                            String date = documentSnapshot.get(KEY_DATE).toString();

                            textView7.setText("Résultat 7 : Date : "+date + ", user : "+username+", message  : "+ message);
                        }
                        else{
                            Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
        db.collection(username).document(String.valueOf(counter-7)).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String message = documentSnapshot.get(KEY_MESSAGE).toString();
                            String date = documentSnapshot.get(KEY_DATE).toString();

                            textView8.setText("Résultat 8 : Date : "+date + ", user : "+username+", message  : "+ message);
                        }
                        else{
                            Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
        db.collection(username).document(String.valueOf(counter-8)).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String message = documentSnapshot.get(KEY_MESSAGE).toString();
                            String date = documentSnapshot.get(KEY_DATE).toString();

                            textView9.setText("Résultat 9 : Date : "+date + ", user : "+username+", message  : "+ message);
                        }
                        else{
                            Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
        db.collection(username).document(String.valueOf(counter-9)).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String message = documentSnapshot.get(KEY_MESSAGE).toString();
                            String date = documentSnapshot.get(KEY_DATE).toString();

                            textView10.setText("Résultat 10 : Date : "+date + ", user : "+username+", message  : "+ message);
                        }
                        else{
                            Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Historique.this, "Fail", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }
}