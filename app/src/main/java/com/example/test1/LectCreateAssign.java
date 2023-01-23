package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class LectCreateAssign extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lect_create_assign);
        TextView stuId = (TextView) findViewById(R.id.textView17);
        getSupportActionBar().setTitle("ZenMarker-Lecturer");
        stuId.setText("Lecturer Number: " + LecturerMethods.receive_Lectnumber());

    }
    public void Cassignment(View view)
    {
        closeKeyBoard();
        EditText edtaname = (EditText) findViewById(R.id.editTextnamea);
        EditText edtadesc = (EditText) findViewById(R.id.editTextdesca);
        EditText edtacourse = (EditText) findViewById(R.id.editTextcoursea);
        String aname = edtaname.getText().toString();
        String adesc = edtadesc.getText().toString();
        String acourse = edtacourse.getText().toString();
        Context context1 = getApplicationContext();
        LecturerMethods.insertAssignment(aname,adesc, acourse, LecturerMethods.receive_Lectnumber(),
                LectCreateAssign.this,context1);
        edtaname.setText("");
        edtacourse.setText("");
        edtadesc.setText("");
    }

    public void lectAssignBack(View view) {
        AppMethods.changepage(LectCreateAssign.this,LecturerHome.class);
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