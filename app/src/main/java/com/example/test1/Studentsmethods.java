package com.example.test1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Studentsmethods {
    private static theStudent objStudent;
    private static String SelectedAssigment;

    public static void Create_student(String sNum, String fname, String LName){
        objStudent = new theStudent(sNum,fname,LName);
    }

    public static String receive_studentnumber(){
        return objStudent.getStudentNumber();
    }

    public static String reciece_fullname(){
        return objStudent.getFname() + " " +  objStudent.getlName();
    }

    public static void setSelectedAssigment(String theID){
        SelectedAssigment = theID;
    }

    public static String getSelectedAssigment() {
        return SelectedAssigment;
    }


    public static void theLogin(String sID, String sPass, Context context, Activity activity,
                                Intent intent){
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("ID",sID)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2344401/studentspass.php")
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
                            AppMethods.showErrorMessage("User does not exist \nSign up/"
                                    + " Enter new Username",context);
                        }else {
                            try {
                                JSONArray arrJson = new JSONArray(thebody);
                                JSONObject objJson = arrJson.getJSONObject(0);
                                String actPass = objJson.getString("STU_PASSWORD");
                                if (sPass.contentEquals(actPass)){
                                    Create_student(sID,objJson.getString("STU_FNAME"),
                                            objJson.getString("STU_LNAME"));
                                    activity.startActivity(intent);
                                }else{
                                    AppMethods.showErrorMessage("Incorrect password"
                                            + " entered", context);


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

    public static void StuRegisterId(String sFname,String sLname,String sEmail,String sPass,
                                     Activity activity,Context context){
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("fname",sFname)
                .add("lname",sLname)
                .add("email",sEmail)
                .add("pass",sPass)
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
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            JSONArray arrJson = new JSONArray(thebody);
                            JSONObject objJson = arrJson.getJSONObject(0);
                            String theID = objJson.getString("MAXSTU_ID");
                            AppMethods.showErrorMessage(theID,context);
                            TextView t = (TextView) activity.findViewById(R.id.textViewReStuInfo);
                            t.setText("");
                            t.setText("Student information: \n Student number: " + theID + "\n"
                                    + "Use this student number to log in");
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public static void StudentRegister (String sFname,String sLname,String sEmail,String sPass,
                                        Activity activity,Context context){
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("fname",sFname)
                .add("lname",sLname)
                .add("email",sEmail)
                .add("pass",sPass)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2344401/insertstudent.php")
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
                            AppMethods.showErrorMessage("Successfully registered",context);

                        }else {
                            AppMethods.showErrorMessage("Unable to register",context);
                        }

                    }
                });
            }
        });

    }

    public static void insertGroup (String gname,String gdesc, String gmark, String assid,
                                        Activity activity,Context context) {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("gname", gname)
                .add("gdesc", gdesc)
                .add("gmark", gmark)
                .add("assid", assid)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2344401/insertgroup.php")
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
                        AppMethods.showErrorMessage(ans, context);
                        if (ans.contentEquals("record added")) {
                            AppMethods.showErrorMessage("Successfully Created a group", context);
                        } else {
                            AppMethods.showErrorMessage("Unable to register", context);
                        }

                    }
                });
            }
        });
    }

    public static void studenttogroup (String sid,String gid,
                                    Activity activity,Context context)
    {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("sid", sid)
                .add("gid", gid)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2344401/insertstutogroup.php")
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
                String theans = responseBody.string();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AppMethods.showErrorMessage(theans,context);
                    }
                });
            }
        });

    }



}
