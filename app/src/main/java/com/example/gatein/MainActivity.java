package com.example.gatein;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private TextView info, registration;
    private Button login;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editTextusername);
        password = (EditText) findViewById(R.id.editTextpassword);
        info = (TextView)findViewById(R.id.info);
        registration= (TextView)findViewById(R.id.textViewregistration) ;
        login = (Button)findViewById(R.id.buttonlog);

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
    }

    private void validate(String username, String password){
        if((username.equals("Admin"))&&(password.equals("mdp"))){
            Intent intent = new Intent(this, SelectionGatein.class);
            startActivity(intent);
        }
        else{
            counter--;
            info.setText("nombre d'essais restants : "+String.valueOf(counter));
        }
        if(counter == 0){
            login.setEnabled(false);
        }
    }
    private void openRegPage(){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}