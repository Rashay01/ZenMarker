package com.example.test1;

public class theLecturer {
    private String lecturernumber;
    private String fName;
    private String Lname;

    public theLecturer(String sID, String theName,String theLname){
        lecturernumber = sID;
        fName = theName;
        Lname = theLname;
    }

    public String getLecturernumber(){
        return lecturernumber;
    }

    public String getfName(){
        return fName;
    }

    public String getLname(){
        return Lname;
    }
}
