package com.chuck.student_attendance_mockup;

/**
 * Created by 040107741 on 17/03/2015.
 *
 * This should really be called roll
 */
public class Student {

    int studID;
    String name;
    String lecturer;
    String course;
    String cluster;


    public Student() {

    }

    public Student(int i, String n, String l, String u, String c){

        studID = i;
        name = n;
        lecturer = l;
        course = u;
        cluster = c;

    }

}
