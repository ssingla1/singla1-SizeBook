package com.example.sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * The type New record activity.
 * This activity is used for making a new record
 * Allows the user to enter their details and stores this data
 * to a File = "file.sav" in Json
 */
public class NewRecordActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private EditText name;
    private EditText date;
    private EditText neck;
    private EditText bust;
    private EditText chest;
    private EditText waist;
    private EditText hip;
    private EditText inseam;
    private EditText comment;
    private ArrayList<Person> personList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);

        Button SaveRecord = (Button) findViewById(R.id.savebutton);

        neck = (EditText) findViewById(R.id.neckid);
        bust = (EditText) findViewById(R.id.bustid);
        chest = (EditText) findViewById(R.id.chestid);
        waist = (EditText) findViewById(R.id.waistid);
        hip = (EditText) findViewById(R.id.hipid);
        inseam = (EditText) findViewById(R.id.inseamid);
        name = (EditText) findViewById(R.id.nameid);
        date = (EditText) findViewById(R.id.dateid);
        comment= (EditText) findViewById(R.id.commentid);

        SaveRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namedata  = name.getText().toString();
                if(namedata.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Name field cannot be empty!",
                                    Toast.LENGTH_SHORT).show();
                }
                else {
                    loadFromFile();
                    String datedata = date.getText().toString();
                    Dimensions dimensionsdata = getDimensions();
                    String commentdata = comment.getText().toString();

                    try {
                        Person person = new Person(namedata, datedata, dimensionsdata, commentdata);
                        personList.add(person);
                        saveInFile();
                    } catch (Exception e) {
                        personList = new ArrayList<Person>();
                    }

                    startActivity(new Intent(NewRecordActivity.this, SuccessActivity.class));
                    finish();
                }
            }
        });
    }

    /**
     * This function gets the text from edit text elements and converts it into a
     * Dimensions object to store in a Person object
     *
     * @return Dimensions
     */
    private Dimensions getDimensions() {


        Dimensions newdimensions = new Dimensions(neck.getText().toString(),bust.getText().toString(),
                                                chest.getText().toString(),waist.getText().toString(),
                                              hip.getText().toString(),inseam.getText().toString());


        return newdimensions;
    }

    /**
     * This function was taken from lab3 Exercise of lonelyTwitter on 31st January,2017
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(personList,out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
    /**
     * This function was taken from lab3 Exercise of lonelyTwitter on 31st January,2017
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Person>>() {
            }.getType();
            personList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            personList = new ArrayList<Person>();
        }
    }
}



