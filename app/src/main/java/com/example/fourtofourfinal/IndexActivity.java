package com.example.fourtofourfinal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class IndexActivity extends AppCompatActivity {

    TextView singUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        singUp = findViewById(R.id.signUp);
        singUp.setMovementMethod(LinkMovementMethod.getInstance());
    }


    public void onclick(View view){

        switch (view.getId()){
            case R.id.signUp:{
                Intent intent =  new Intent(getApplicationContext(),activity_sign_up.class);
                startActivity(intent);
            }
        }

    }
}
