package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class StudentHome extends AppCompatActivity{

    String[] startingList = {};
    List<String> arrAssignment = new ArrayList<String>();
    Spinner spinner;
    ArrayAdapter ad;
    static String Assignmentname;
    static String Assignmentid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        getSupportActionBar().setTitle("ZenMarker-Student");
        TextView stuhwelcome = (TextView) findViewById(R.id.textView3);
        stuhwelcome.setText("Welcome " + Studentsmethods.reciece_fullname());
        TextView stuId = (TextView) findViewById(R.id.textView2);
        stuId.setText("Student Number: " + Studentsmethods.receive_studentnumber());


        spinner = findViewById(R.id.spinStuProject);
        arrAssignment = new ArrayList<String>(Arrays.asList(startingList));
        ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrAssignment);
        spinner.setAdapter(ad);


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2344401/assignment.php")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResponseBody responseBody = response.body();
                String body = responseBody.string();
                StudentHome.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            JSONArray arrJson = new JSONArray(body);
                            for (int i = 0; i < arrJson.length();i++){
                                JSONObject objJson = arrJson.optJSONObject(i);
                                String assId = objJson.getString("ASS_ID");
                                String assName = objJson.getString("ASS_NAME");
                                arrAssignment.add(assId+ ":" + assName);
                                ad.notifyDataSetChanged();
                            }

                        } catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                });

            }
        });





    }

    public void stuHProject(View view) {
        String a = spinner.getSelectedItem().toString();
        String [] spl = a.split(":");
        Assignmentname = spl[1];
        Assignmentid = spl[0];
        AppMethods.showErrorMessage( " selected: " + spinner.getSelectedItem().toString(),getApplicationContext());
        AppMethods.changepage(StudentHome.this,StuShowGroups.class);
    }

    public void stuLogout(View view) {
        AppMethods.changepage(StudentHome.this,home.class);
        finish();

    }
}