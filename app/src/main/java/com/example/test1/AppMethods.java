package com.example.test1;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.widget.Toast;


public class AppMethods {
    public static void showErrorMessage(String theMessage, Context context){
        CharSequence text = theMessage;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public static void changepage(Activity activity,Class Changeto){
        Intent intent = new Intent(activity, Changeto);
        activity.startActivity(intent);
        activity.finish();
    }
    public static boolean checkemail(String sEmail,Activity a){
        boolean ans = false;
        if (sEmail.contentEquals("")){
            ans = false;
        } else{
            if (sEmail.contains("@") == true){
                int ind = sEmail.indexOf("@");
                if ( ind >0 && ind < sEmail.length()-1){
                    ans = true;
                }else{
                    ans = false;
                    showErrorMessage("Enter valid Email",a.getApplicationContext());
                }

            }else{
                ans = false;
                showErrorMessage("Enter valid Email",a.getApplicationContext());

            }
        }
        return ans;
    }

}
