package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class StuCreateGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_create_group);
        getSupportActionBar().setTitle("ZenMarker-Student");


    }
    public void CreGroup(View view)
    {
        closeKeyBoard();
        EditText edtgname = (EditText) findViewById(R.id.editTextN);
        EditText edtgdesc = (EditText) findViewById(R.id.editTextD);
        String gname = edtgname.getText().toString();
        String gdesc = edtgdesc.getText().toString();
        Context context1 = getApplicationContext();
        Studentsmethods.insertGroup(gname,gdesc, "-1" , StudentHome.Assignmentid,
                StuCreateGroup.this,context1);
        edtgdesc.setText("");
        edtgname.setText("");

    }


    public void stuCreBack(View view) {
        AppMethods.changepage(StuCreateGroup.this,StuShowGroups.class);
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