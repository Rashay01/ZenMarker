package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Studentlogin extends AppCompatActivity {
    private final OkHttpClient client = new OkHttpClient();
    //public theStudent objStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);
        getSupportActionBar().setTitle("ZenMarker-Student");
    }

    public void stuStart(View view) {
        Intent intent = new Intent(Studentlogin.this, home.class);
        startActivity(intent);
    }

    private void closeKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void stuRegister(View view) {
        AppMethods.changepage(Studentlogin.this,StudentRegister.class);
    }

    public void Test(View view) {
        closeKeyBoard();
        EditText edtstuNUM = (EditText) findViewById(R.id.editTextTextPersonName);
        String theID = edtstuNUM.getText().toString();
        edtstuNUM.setText("");
        EditText edtpass = (EditText) findViewById(R.id.editTextTextPassword);
        String thepass = edtpass.getText().toString();
        edtpass.setText("");
        Context context = getApplicationContext();
        Intent intent = new Intent(Studentlogin.this, StudentHome.class);
        Studentsmethods.theLogin(theID,thepass,context, Studentlogin.this,intent);
    }

}