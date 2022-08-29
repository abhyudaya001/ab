package com.example.abtube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

import static com.example.abtube.R.id.googleLogin;

public class Login extends AppCompatActivity {
private MaterialEditText email,password;
private ProgressBar progressBar;
private TextView forgetPassword;
private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        getSupportActionBar().hide();
        Button registerBtn = findViewById(R.id.registerr);
        Button loginBtn = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        firebaseAuth = firebaseAuth.getInstance();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,RegisterActivity.class));
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tex_email =email.getText().toString();
                String tex_password =password.getText().toString();
                if(TextUtils.isEmpty(tex_email)||TextUtils.isEmpty(tex_password)){
                    Toast.makeText(Login.this,"All Field Required",Toast.LENGTH_SHORT).show();
                }
                else{
                    login(tex_email,tex_password);
                }

            }
        });
    }

    private void login(String tex_email, String tex_password) {
        progressBar.setVisibility(View.GONE);
        firebaseAuth.signInWithEmailAndPassword(tex_email,tex_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
             if(task.isSuccessful())
             {
               Intent intent =new Intent(Login.this,MainActivity.class);
               intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);
               finish();
             }
             else{
                 Toast.makeText(Login.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
             }
            }
        });

    }
}