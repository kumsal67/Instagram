package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("basladi");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        email=findViewById(R.id.loginemail);
        password=findViewById(R.id.loginpassword);
        FirebaseUser user=auth.getCurrentUser();
        if (user!=null){
            Intent intent=new Intent(getApplicationContext(),FeedActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
            Login.super.finish();
        }

    }
    public void signin(View v){
        auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d("Succes","Created usersuccec");
                            FirebaseUser user=auth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"Succes",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(intent);
                            Login.super.finish();
                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }



    public void signup(View v){
        auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d("Succes","Created usersuccec");
                            FirebaseUser user=auth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"Succes",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(intent);
                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}