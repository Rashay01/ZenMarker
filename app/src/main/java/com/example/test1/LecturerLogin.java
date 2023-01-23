package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class LecturerLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_login);
        getSupportActionBar().setTitle("ZenMarker-Lecturer");
    }

    public void lectLogin(View view) {
        closeKeyBoard();
        EditText edtId = (EditText) findViewById(R.id.editTextLectLgnumber);
        EditText edtPass = (EditText) findViewById(R.id.editTextLectLgPass);
        String sId = edtId.getText().toString();
        String sPass = edtPass.getText().toString();
        edtId.setText("");
        edtPass.setText("");
        Intent intent = new Intent(LecturerLogin.this, LecturerHome.class);
        LecturerMethods.thelectLogin(sId,sPass,LecturerLogin.this,intent);
    }

    public void lectLgBack(View view) {
        AppMethods.changepage(LecturerLogin.this,home.class);
    }

    private void closeKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void lectSignup(View view) {
        AppMethods.changepage(LecturerLogin.this,LecturerRegister.class);
    }
}