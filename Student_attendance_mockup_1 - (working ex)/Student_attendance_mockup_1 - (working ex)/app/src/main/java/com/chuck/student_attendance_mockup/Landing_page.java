package com.chuck.student_attendance_mockup;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Landing_page extends ActionBarActivity {


    //playing with sets ... avoids duplicates
    //in the final product the sql will make this unnecessary

    //note student objects should really be called roll objects

    Set<Integer> studIDset = new TreeSet<Integer>();
    Set<String> studNameSet = new TreeSet<String>();
    Set<String> lectSet = new TreeSet<String>();
    Set<String> courseSet = new TreeSet<String>();
    Set<String> clusterSet = new TreeSet<String>();
/*
//the sets weren't filling the arrays, which broke the avd but not the samsung fsr

    Integer[] studIDarray = new Integer[9];
    String[] studNameArray = new String[9];
    String[] courseArray = new String[9];
    String[] lectArray = new String[9];
    String[] clusterArray = new String[9];
*/

    Integer[] studIDarray = new Integer[5];
    String[] studNameArray = new String[6];
    String[] courseArray = new String[3];
    String[] lectArray = new String[3];
    String[] clusterArray = new String[3];

    String[] anArray = new String[] {"Dummy list item","Dummy list item","Dummy list item","Dummy list item","Dummy list item","Dummy list item","Dummy list item","Dummy list item","Dummy list item","Dummy list item"};

    Student[] studArray = new Student[9];  //mental note java array sizes are literal not last index

    boolean validID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        //create some stuff to get the bits working with
        studArray[0] = new Student(1, "Jimmy", "Nic", "Programming", "oo");
        studArray[1] = new Student(1, "Jimmy", "Nic", "Programming", "pm");
        studArray[2] = new Student(2, "Thu", "Nic", "Programming", "oo");
        studArray[3] = new Student(2, "Thu", "Nic", "Programming", "pm");
        studArray[4] = new Student(3, "Barney", "Nic", "Programming", "oo");
        studArray[5] = new Student(3, "Barney", "Nic", "Programming", "pm");
        studArray[6] = new Student(4, "Brandon", "Guido", "Design", "pm");
        studArray[7] = new Student(4, "Louis", "Guido", "Design", "pm");
        studArray[8] = new Student(0, "", "", "", "");   //hopefully a temp measure to prevent spinners preloading values
        //int counter = 0;

        //use object array to fill sets

        for (Student s : studArray)
        {
            studIDset.add(s.studID);
            studNameSet.add(s.name);
            lectSet.add(s.lecturer);
            courseSet.add(s.course);
            clusterSet.add(s.cluster);
        }

        //use sets to fill arrays

        studIDset.toArray(studIDarray);
        studNameSet.toArray(studNameArray);
        lectSet.toArray(lectArray);
        courseSet.toArray(courseArray);
        clusterSet.toArray(clusterArray);

        //use arrays to fill spinners

        //spinner fragment from android dev
        Spinner spLect = (Spinner) findViewById(R.id.spLect);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> spLectAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lectArray);
        // Specify the layout to use when the list of choices appears
        spLectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spLect.setAdapter(spLectAdapter);

        Spinner spCourse = (Spinner) findViewById(R.id.spCourse);
        ArrayAdapter<String> spCourseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, courseArray);
        spCourseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCourse.setAdapter(spCourseAdapter);

        Spinner spCluster = (Spinner) findViewById(R.id.spCluster);
        ArrayAdapter<String> spClusterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, clusterArray);
        spClusterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCluster.setAdapter(spClusterAdapter);



        //try to setup click listeners for spinners
        spLect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //updateList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //fiddle with listview
        ListView listView = (ListView) findViewById(R.id.listView);

        /**/
        ArrayAdapter<String> lvNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, anArray);
        listView.setAdapter(lvNameAdapter);


        //fiddle with list item click handlers
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "You pressed list item "+ position+"!", Toast.LENGTH_SHORT).show();
            }
        });


        //setup CHECK button
        Button btnCheck = (Button) findViewById(R.id.btnCheckID);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText) findViewById(R.id.editTextStudNo);
                validID = false;

                if (!input.getText().toString().isEmpty()) {
                    for (Student s : studArray){
                        if (s.studID == Integer.parseInt(input.getText().toString())) {
                            validID = true;
                        }
                    }

                    if (validID) {
                        input.setTextColor(Color.GREEN);
                        Toast.makeText(getApplicationContext(), "Student number valid!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        input.setTextColor(Color.RED);
                        Toast.makeText(getApplicationContext(), "Student number not found!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), "Input a student ID!", Toast.LENGTH_SHORT).show();
            }
        });

        //setup DISPLAY button ... [WIP]
        Button btnDisplay = (Button) findViewById(R.id.btnDisplay);
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent displayIntent = new Intent(v.getContext(), Output_Screen.class);
                ListView listView = (ListView) findViewById(R.id.listView);
                //displayIntent.putExtra("dataz",listView.getSelectedItem().toString());
                displayIntent.putExtra("dataz","I AM DATAZ!");
                startActivity(displayIntent);

            }
        });
       /* */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateList(){

        //it seems to run this on load fsr

        //i think i'm going about this the hard way
        //most or all of this will be replaced with calls to the soap

        String lect = "";
        String course = "";
        String cluster = "";

        boolean lectChk = false;
        boolean courseChk = false;
        boolean clusterChk = false;

        Spinner spLect = (Spinner) findViewById(R.id.spLect);
        Spinner spCourse = (Spinner) findViewById(R.id.spCourse);
        Spinner spCluster = (Spinner) findViewById(R.id.spCluster);

        Set<Student> studSet = new TreeSet<Student>();
        List<String> nameList = new ArrayList<String>();


/*
        //this code was supposed to populate the list with items from the student/roll based on the spinners... but wasn't working
        //

        if (spLect.getSelectedItem() != "") {
            lect = spLect.getSelectedItem().toString();
            lectChk = true;
        }
        if (spCourse.getSelectedItem() != "") {
            course = spCourse.getSelectedItem().toString();
            courseChk = true;
        }
        if (spCluster.getSelectedItem() != "") {
            cluster = spCluster.getSelectedItem().toString();
            clusterChk = true;
        }

        // ... there must be an easier way
        //add students who match one of the search criteria
        for (Student s : studArray) {
            if (lectChk == true && s.lecturer == lect){
                studSet.add(s);
            }
            if (courseChk == true && s.course == course){
                studSet.add(s);
            }
            if (clusterChk == true && s.cluster == cluster){
                studSet.add(s);
            }

        }

        //remove students who don't match one of the search criteria   ... hopefully
        for (Student s : studSet) {
            if (lectChk == true && s.lecturer != lect){
                studSet.remove(s);
            }
            if (courseChk == true && s.course != course){
                studSet.remove(s);
            }
            if (clusterChk == true && s.cluster != cluster){
                studSet.remove(s);
            }

        }


        for (Student s : studSet) {
            nameList.add(s.name);
        }
        */

        nameList.add("BLANKY");
        nameList.add("BLANKY TWO");



        //finagle grid/listview adapter

        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> lvNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);
        listView.setAdapter(lvNameAdapter);
    }

}
