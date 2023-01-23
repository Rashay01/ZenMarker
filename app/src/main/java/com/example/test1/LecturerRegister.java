package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

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

public class LecturerRegister extends AppCompatActivity {
    EditText edtfName;
    EditText edtLname;
    EditText edtEmail;
    EditText edtPass;
    EditText edtCpass;
    TextView tvCpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_register);
        getSupportActionBar().setTitle("ZenMarker-Lecturer");
        edtfName = (EditText) findViewById(R.id.editTextLectReFname);
        edtLname = (EditText) findViewById(R.id.editTextLectReLname);
        edtEmail = (EditText) findViewById(R.id.editTextLectReEmail);
        edtPass = (EditText) findViewById(R.id.editTextLectRePass);
        edtCpass = (EditText) findViewById(R.id.editTextLectReCpass);
        tvCpass = (TextView) findViewById(R.id.textViewLectReCpass);
    }

    public void lectReLogin(View view) {
        edtfName.setText("");
        edtLname.setText("");
        edtEmail.setText("");
        edtPass.setText("");
        edtCpass.setText("");
        tvCpass.setVisibility(View.INVISIBLE);
        AppMethods.changepage(LecturerRegister.this,LecturerLogin.class);
    }

    public void LectRegister(View view) {
        closeKeyBoard();
        String sName = edtfName.getText().toString();
        String sLname = edtLname.getText().toString();
        String sEmail = edtEmail.getText().toString();
        String sPass = edtPass.getText().toString();
        String sCpass = edtCpass.getText().toString();
        Boolean check = AppMethods.checkemail(sEmail,LecturerRegister.this);
        if(sName.equals("")||sLname.equals("")||check==false||sPass.equals("")||sCpass.equals("")){
            AppMethods.showErrorMessage("Fill in all the fields", getApplicationContext());
        }else{
            if(sCpass.equals(sPass)){
                LecturerMethods.LecturerRegister(sName,sLname,sEmail,sPass,LecturerRegister.this);
                //LecturerMethods.LectRegisterId(sName,sLname,sEmail,sPass,LecturerRegister.this);

                OkHttpClient client = new OkHttpClient();
                FormBody formBody = new FormBody.Builder()
                        .add("fname",sName)
                        .add("lname",sLname)
                        .add("email",sEmail)
                        .add("pass",sPass)
                        .build();

                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2344401/lecturerid.php")
                        .post(formBody)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        ResponseBody responseBody = response.body();
                        String thebody = responseBody.string();
                        LecturerRegister.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    JSONArray arrJson = new JSONArray(thebody);
                                    JSONObject objJson = arrJson.getJSONObject(0);
                                    String theID = objJson.getString("MAXLECT_ID");
                                    TextView t = (TextView) findViewById(R.id.textViewLectReCredentials);
                                    t.setText("");
                                    t.setText("Lecturer information: \nLecturer number : " + theID + "\n"
                                            + "Use this Lecturer Number to log in \n" + "Proceed to GO LOGIN");
                                }catch(JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });

                edtfName.setText("");
                edtLname.setText("");
                edtEmail.setText("");
                edtPass.setText("");
                edtCpass.setText("");
                tvCpass.setVisibility(View.INVISIBLE);
            }else{
                edtPass.setText("");
                edtCpass.setText("");
                tvCpass.setVisibility(View.VISIBLE);
            }
        }
    }

    private void closeKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}