package com.example.test1;

public class theStudent {
    private String StudentNumber;
    private String fname;
    private String lName;

    public theStudent(String sNumber, String theFname, String theLname){
        StudentNumber = sNumber;
        fname = theFname;
        lName = theLname;
    }
     public String getStudentNumber(){
        return StudentNumber;
     }

     public String getFname(){
        return fname;
     }

     public String getlName(){
        return lName;
     }
}
