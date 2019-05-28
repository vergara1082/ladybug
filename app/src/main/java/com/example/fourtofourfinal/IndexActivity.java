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
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class IndexActivity extends AppCompatActivity {

    private TextView singUp;
    private FirebaseAuth mAuth;
    private TextInputEditText adress;
    private TextInputEditText password;
    private TextInputLayout textInputLayout2;
    private TextInputLayout textInputLayout4;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "ladyBugActivity";
    private CallbackManager mCallbackManager;



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

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken("553320109586-kpo6l1sehkqh2s9018na3kflnl4c1mmr.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });//

        mAuth = FirebaseAuth.getInstance();
        this.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });



    }


    // [START auth_with_facebook]
    private void handleFacebookAccessToken(AccessToken token) {


        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(null, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }


    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                            startActivity(intent);
                        } else {

                            Snackbar.make(null, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();

                        }


                    }
                });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }else{
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

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

            case R.id.button:{

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
