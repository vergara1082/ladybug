package com.example.fourtofourfinal;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class IndexActivity extends AppCompatActivity {

    private TextView singUp;
    private FirebaseAuth mAuth;
    private TextInputEditText adress;
    private TextInputEditText password;
    private TextInputLayout textInputLayout2;
    private TextInputLayout textInputLayout4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        singUp = findViewById(R.id.signUp);
        singUp.setMovementMethod(LinkMovementMethod.getInstance());
        mAuth = FirebaseAuth.getInstance();
        adress = findViewById(R.id.textAdress);
        password =  findViewById(R.id.txtPassword);
        textInputLayout2 = findViewById(R.id.textInputLayout2);
        textInputLayout4 = findViewById(R.id.textInputLayout4);
    }


    public void onclick(View view){

        switch (view.getId()){
            case R.id.signUp:{
                Intent intent =  new Intent(getApplicationContext(),activity_sign_up.class);
                startActivity(intent);
            }

            case R.id.btnSignUP:{
                signIn(view);
            }
        }

    }

    public void signIn(final View view){
        String email =  adress.getText().toString();
        String password =  this.password.getText().toString();
        boolean isvalid = false;

        if(email.isEmpty() || email.length() < 2){
            textInputLayout2.setError(getText(R.string.email_empty));
            isvalid = true;

        }else{
            textInputLayout2.setErrorEnabled(false);
        }

        if (password.isEmpty() || password.length() < 6){
            textInputLayout4.setError(getText(R.string.password_empty));
            isvalid = true;

        }else{
            textInputLayout4.setErrorEnabled(false);
        }

        if(isvalid){
            return;
        }

        if (email.length() > 2 && password.length() > 6   ){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                                startActivity(intent);

                            } else {

                                Snackbar.make(view, R.string.msn_autentication_failed, Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                            }

                            // ...
                        }
                    });
        }


    }


}
