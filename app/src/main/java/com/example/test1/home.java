package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("ZenMarker");
    }

    public void theStudent(View view) {
        AppMethods.changepage(home.this,Studentlogin.class);
    }

    public void mainLecture(View view) {
        AppMethods.changepage(home.this,LecturerLogin.class);
    }
}