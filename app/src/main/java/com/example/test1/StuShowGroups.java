package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class StuShowGroups extends AppCompatActivity {
    String[] startingList = {};
    List<String> friends = new ArrayList<String>();
    Spinner spinner;
    ArrayAdapter ad;
    static String groupid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_show_groups);
        getSupportActionBar().setTitle("ZenMarker-Student");

        TextView name1 = (TextView) findViewById(R.id.textView9);
        name1.setText("Assingment name: " + StudentHome.Assignmentname);
        TextView stuId = (TextView) findViewById(R.id.textView11);
        stuId.setText("Student Number: " + Studentsmethods.receive_studentnumber());


        spinner = findViewById(R.id.spinner);
        friends = new ArrayList<String>(Arrays.asList(startingList));
        ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1,friends);
        spinner.setAdapter(ad);


        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("assid", StudentHome.Assignmentid)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2344401/asignmentgroups.php")
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
                StuShowGroups.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            JSONArray arrJson = new JSONArray(body);
                            for (int i = 0; i < arrJson.length();i++){
                                JSONObject objJson = arrJson.optJSONObject(i);
                                String gid = objJson.getString("GROUP_ID");
                                String gname = objJson.getString("GROUP_NAME");
                                friends.add(gid+ ":" + gname);
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

    public void jGroup(View view) {
        if(spinner.getSelectedItemPosition() >= 0){
            AppMethods.showErrorMessage( " selected: " + spinner.getSelectedItem().toString(),getApplicationContext());
            String a = spinner.getSelectedItem().toString();
            String [] spl = a.split(":");
            groupid = spl[0];
            Context context = getApplicationContext();
            Studentsmethods.studenttogroup(Studentsmethods.receive_studentnumber(), groupid,
                    StuShowGroups.this,context);
        } else{
            AppMethods.showErrorMessage("No groups made \n create a new group",getApplicationContext());
        }

    }
    public void NGroup(View view)
    {
        AppMethods.changepage(StuShowGroups.this,StuCreateGroup.class);
    }

    public void stuGrBack(View view) {
        AppMethods.changepage(StuShowGroups.this,StudentHome.class);
    }
}