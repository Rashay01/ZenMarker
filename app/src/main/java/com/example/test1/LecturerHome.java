package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LecturerHome extends AppCompatActivity {

    String[] startingList = {};
    List<String> assignments = new ArrayList<String>();
    Spinner spinner;
    ArrayAdapter ad;
    static String Assignmentname;
    static String Assignmentid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_home);
        getSupportActionBar().setTitle("ZenMarker-Lecturer");
        TextView tvWelcome = (TextView) findViewById(R.id.textViewLectHwelcome);
        TextView tvNumber = (TextView) findViewById(R.id.textView1LectHNumber);
        tvWelcome.setText("Welcome Lecturer: " + LecturerMethods.reciece_fullname());
        tvNumber.setText("Lectuer number: " + LecturerMethods.receive_Lectnumber());

        spinner = findViewById(R.id.spinLectureAssignments);
        assignments = new ArrayList<String>(Arrays.asList(startingList));
        ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1,assignments);
        spinner.setAdapter(ad);


        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("LECT_ID",LecturerMethods.receive_Lectnumber())
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2344401/assignmentlect.php")
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
                String body = responseBody.string();
                LecturerHome.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            JSONArray arrJson = new JSONArray(body);
                            for (int i = 0; i < arrJson.length();i++){
                                JSONObject objJson = arrJson.optJSONObject(i);
                                String assId = objJson.getString("ASS_ID");
                                String assName = objJson.getString("ASS_NAME");
                                assignments.add(assId+ ":" + assName);
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
    public void markApage(View view) {
        if(spinner.getSelectedItemPosition() >=0){
            String a = spinner.getSelectedItem().toString();
            String [] spl = a.split(":");
            Assignmentname = spl[1];
            Assignmentid = spl[0];
            AppMethods.showErrorMessage( " selected: " + spinner.getSelectedItem().toString()
                    ,getApplicationContext());
            AppMethods.changepage(LecturerHome.this,LectMarkAssignment.class);
        }else{
            AppMethods.showErrorMessage("There are no assignments \nCreate a new assignment"
                    ,getApplicationContext());
        }

    }


    public void lectSignOut(View view) {
        AppMethods.changepage(LecturerHome.this,home.class);
        finish();
    }

    public void Cassign(View view)
    {
        AppMethods.changepage(LecturerHome.this,LectCreateAssign.class);
    }
}