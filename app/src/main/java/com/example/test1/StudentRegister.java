package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class StudentRegister extends AppCompatActivity {
    EditText edtfname;
    EditText edtlname;
    EditText edtpass;
    EditText edtCpass;
    EditText edtemail;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        edtfname = (EditText) findViewById(R.id.editTextReStuFname);
        edtlname = (EditText) findViewById(R.id.editTextReStuLname);
        edtpass = (EditText) findViewById(R.id.editTextReStuPass);
        edtCpass = (EditText) findViewById(R.id.editTextReStuCpass);
        edtemail = (EditText) findViewById(R.id.editTextReStuEmailAddress);
        textView1 = (TextView) findViewById(R.id.textViewReStuCpass);
        getSupportActionBar().setTitle("ZenMarker-Student");
    }

    public void StuRegister(View view) {
        closeKeyBoard();
        String fname = edtfname.getText().toString();
        String lname = edtlname.getText().toString();
        String spass = edtpass.getText().toString();
        String cpass = edtCpass.getText().toString();
        String semail = edtemail.getText().toString();
        Context context = getApplicationContext();
        boolean check = AppMethods.checkemail(semail, StudentRegister.this);
        if(fname.equals("")||lname.equals("")||check == false ||spass.equals("")||cpass.equals("")){
            AppMethods.showErrorMessage("Fill in all the fields properly", getApplicationContext());
        }else{
            if (cpass.contentEquals(spass)){
                Studentsmethods.StudentRegister(fname,lname,semail,spass,
                        StudentRegister.this,context);
                //Studentsmethods.StuRegisterId(fname,lname,semail,spass,
                 //       StudentRegister.this,context);

                OkHttpClient client = new OkHttpClient();
                FormBody formBody = new FormBody.Builder()
                        .add("fname",fname)
                        .add("lname",lname)
                        .add("email",semail)
                        .add("pass",spass)
                        .build();

                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2344401/studentid.php")
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
                        StudentRegister.this.runOnUiThread(new Runnable() {
                          @Override
                            public void run() {
                                try{
                                    JSONArray arrJson = new JSONArray(thebody);
                                    JSONObject objJson = arrJson.getJSONObject(0);
                                    String theID = objJson.getString("MAXSTU_ID");
                                    AppMethods.showErrorMessage(theID,context);
                                    TextView t = (TextView) findViewById(R.id.textViewReStuInfo);
                                    t.setText("");
                                    t.setText("Student information: \nStudent number: " + theID + "\n"
                                            + "Use this Student number to log in");
                                }catch(JSONException e){
                                    e.printStackTrace();
                                }
                           }
                        });
                    }
                });

                edtCpass.setText("");
                edtpass.setText("");
                edtemail.setText("");
                edtfname.setText("");
                edtlname.setText("");
                textView1.setVisibility(View.INVISIBLE);
            }else {
                edtCpass.setText("");
                edtpass.setText("");
                textView1.setVisibility(View.VISIBLE);
            }
        }
    }

    public void stuReLogin(View view) {
        edtCpass.setText("");
        edtpass.setText("");
        edtemail.setText("");
        edtfname.setText("");
        edtlname.setText("");
        textView1.setVisibility(View.INVISIBLE);
        AppMethods.changepage(StudentRegister.this,Studentlogin.class);
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