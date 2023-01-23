package com.example.test1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class LecturerMethods {
    private static theLecturer objLect;

    public static void Create_Lecturer(String sNum, String fname, String LName){
        objLect = new theLecturer(sNum,fname,LName);
    }

    public static String receive_Lectnumber(){
        return objLect.getLecturernumber();
    }

    public static String reciece_fullname(){
        return objLect.getfName() +" " +  objLect.getLname();
    }

    public static void thelectLogin(String sID, String sPass, Activity activity, Intent intent){
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("ID",sID)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2344401/lecturepassword.php")
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
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(thebody.equals("[]")){
                            AppMethods.showErrorMessage("User does not exist \n Sign up/"
                                    + " Enter new Username",activity.getApplicationContext());
                        }else {
                            try {
                                JSONArray arrJson = new JSONArray(thebody);
                                JSONObject objJson = arrJson.getJSONObject(0);
                                String actPass = objJson.getString("LECT_PASSWORD");
                                if (sPass.contentEquals(actPass)){
                                    Create_Lecturer(sID,objJson.getString("LECT_FNAME"),
                                            objJson.getString("LECT_LNAME"));
                                    activity.startActivity(intent);
                                }else{
                                    AppMethods.showErrorMessage("Incorrect password"
                                            + " entered", activity.getApplicationContext());
                                }

                            } catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }
    public static void insertAssignment (String aname,String adesc, String acourse, String lid,
                                    Activity activity,Context context) {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("aname", aname)
                .add("adesc", adesc)
                .add("acourse", acourse)
                .add("lid", lid)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2344401/insertassignment.php")
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
                String ans = responseBody.string();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ans.contentEquals("record added")) {
                            AppMethods.showErrorMessage("Created an assignment", context);
                        } else {
                            AppMethods.showErrorMessage("Unable to create assignment", context);
                        }

                    }
                });
            }
        });
    }
    public static void UpdateMark (String mark,String aid,
                                         Activity activity) {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("mark", mark)
                .add("aid", aid)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2344401/updatemarkgroup.php")
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
                String ans = responseBody.string();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ans.contentEquals("record updated")) {
                            AppMethods.showErrorMessage("Successfully updated"
                                    , activity.getApplicationContext());
                        } else {
                            AppMethods.showErrorMessage("Unable to update"
                                    , activity.getApplicationContext());
                        }

                    }
                });
            }
        });
    }

    public static void LectRegisterId(String sFname,String sLname,String sEmail,String sPass,
                                      Activity activity){
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("fname",sFname)
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
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            JSONArray arrJson = new JSONArray(thebody);
                            JSONObject objJson = arrJson.getJSONObject(0);
                            String theID = objJson.getString("MAXLECT_ID");
                            TextView t = (TextView) activity.findViewById(R.id.textViewLectReCredentials);
                            t.setText("");
                            t.setText("Lecturer information: \nLecturer number : " + theID + "\n"
                                    + "Use this Lecturer number to log in \n" + "Proceed to GO LOGIN");
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


    }

    public static void LecturerRegister (String sFname,String sLname,String sEmail,String sPass,
                                        Activity activity){
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("fname",sFname)
                .add("lname",sLname)
                .add("email",sEmail)
                .add("pass",sPass)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2344401/insertlecturer.php")
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
                String ans = responseBody.string();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ans.contentEquals("record added")){
                            AppMethods.showErrorMessage("Successfully registered"
                                    ,activity.getApplicationContext());
                        }else {
                            AppMethods.showErrorMessage("Unable to register"
                                    ,activity.getApplicationContext());
                        }

                    }
                });
            }
        });


    }

}

