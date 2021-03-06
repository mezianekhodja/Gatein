package com.example.gatein;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private TextView info, registration;
    private Button login,passwordForgot;
    private int counter = 5;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editTextusername);
        password = (EditText) findViewById(R.id.editTextpassword);
        info = (TextView)findViewById(R.id.info);
        registration= (TextView)findViewById(R.id.textViewregistration) ;
        login = (Button)findViewById(R.id.buttonlog);
        passwordForgot = (Button)findViewById(R.id.forgotPassword) ;
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null){
            finish();
            openSelection();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(name.getText().toString(),password.getText().toString());
            }
        });
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openRegPage();
            }
        });
        passwordForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPF();
            }
        });
    }

    private void validate(String username, String password){
        progressDialog.setMessage("Essai de connexion");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    checkEmailVerification();
                }
                else{
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    info.setText("Number of attemps remaining = "+counter);
                    progressDialog.dismiss();
                }
                if(counter==0){
                    login.setEnabled(false);
                }
            }
        });
    }
    private void openRegPage(){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
        MainActivity.this.finish();
    }
    private void openPF(){
        Intent intent = new Intent(this, PasswordActivity.class);
        startActivity(intent);
        MainActivity.this.finish();
    }
    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        if (emailflag){
            openSelection();
        }
        else{
            Toast.makeText(this, "verify your email",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
    private void openSelection(){
        Intent intent = new Intent(this, SelectionGatein.class);
        startActivity(intent);
        MainActivity.this.finish();
    }
}