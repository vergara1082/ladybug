package com.example.fourtofourfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fourtofourfinal.Utils.ActivityNavigation;

public class UserAutenticado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_autenticado);
        ActivityNavigation.getInstance(getApplicationContext()).takeToRandomTask();
    }
}
