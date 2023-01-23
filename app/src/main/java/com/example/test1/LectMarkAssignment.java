package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

public class LectMarkAssignment extends AppCompatActivity {
    String[] startingList = {};
    List<String> assignments = new ArrayList<String>();
    ArrayList<String> assignmentMarks = new ArrayList<String>();
    Spinner spinner;
    ArrayAdapter ad;
    //static String assId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lect_mark_assignment);
        getSupportActionBar().setTitle("ZenMarker-Lecturer");

        TextView Massign = (TextView) findViewById(R.id.textView23);
        Massign.setText("Assignment selected: " + LecturerHome.Assignmentid + ": " + LecturerHome.Assignmentname);

        spinner = findViewById(R.id.spinner23);
        assignments = new ArrayList<String>(Arrays.asList(startingList));
        ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, assignments);
        spinner.setAdapter(ad);


        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("assid", LecturerHome.Assignmentid)
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
                LectMarkAssignment.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONArray arrJson = new JSONArray(body);
                            for (int i = 0; i < arrJson.length(); i++) {
                                JSONObject objJson = arrJson.optJSONObject(i);
                                String assId = objJson.getString("GROUP_ID");
                                String assName = objJson.getString("GROUP_NAME");
                                assignmentMarks.add(objJson.getString("GROUP_MARK"));
                                assignments.add(assId + ":" + assName);
                                ad.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });


    }
    public void UMark(View view)
    {
        closeKeyBoard();
        if(spinner.getSelectedItemPosition()>=0){
            EditText edtMark = (EditText) findViewById(R.id.editTextNumberMark);
            String ans = spinner.getSelectedItem().toString();
            String [] spl = ans.split(":");
            String assid = spl[0];
            String mark = edtMark.getText().toString();
            int imark = Integer.parseInt(mark);
            if (imark >= 0 && imark <= 100){
                LecturerMethods.UpdateMark(mark,assid, LectMarkAssignment.this);
            } else{
                AppMethods.showErrorMessage("Enter a valid mark (0-100)",
                        LectMarkAssignment.this);
            }
            edtMark.setText("");
        } else {
            AppMethods.showErrorMessage("There are no groups made \nfor the selected assignment",getApplicationContext());
        }
    }


    public void lectMback(View view) {
        AppMethods.changepage(LectMarkAssignment.this,LecturerHome.class);
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