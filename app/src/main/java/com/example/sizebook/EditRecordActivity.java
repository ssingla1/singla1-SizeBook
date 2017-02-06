package com.example.sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

/**
 * This activity is launched when a user wants to edit an existing record
 * The user can make changes to the record and save it
 * The type Edit record activity.
 */
public class EditRecordActivity extends AppCompatActivity {


    private static final String FILENAME = "file.sav";
    private ArrayList<Person> personList;
    private Person currentPerson;
    private EditText name;
    private EditText date;
    private EditText neck;
    private EditText bust;
    private EditText chest;
    private EditText waist;
    private EditText hip;
    private EditText inseam;
    private EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        loadFromFile();
        Bundle itemdata = getIntent().getExtras();
        int itemid = itemdata.getInt("itemid");
        currentPerson = personList.get(itemid);

        neck = (EditText) findViewById(R.id.neckid2);
        bust = (EditText) findViewById(R.id.bustid2);
        chest = (EditText) findViewById(R.id.chestid2);
        waist = (EditText) findViewById(R.id.waistid2);
        hip = (EditText) findViewById(R.id.hipid2);
        inseam = (EditText) findViewById(R.id.inseamid2);
        name = (EditText) findViewById(R.id.nameid2);
        date = (EditText) findViewById(R.id.dateid2);
        comment= (EditText) findViewById(R.id.commentid2);

        Button Cancel = (Button) findViewById(R.id.cancel2);
        Button Save = (Button) findViewById(R.id.savechanges2);

        name.setText(currentPerson.getName());
        date.setText(currentPerson.getDate());
        comment.setText(currentPerson.getComment());
        neck.setText(currentPerson.getDimensions().getNeck());
        bust.setText(currentPerson.getDimensions().getBust());
        chest.setText(currentPerson.getDimensions().getChest());
        waist.setText(currentPerson.getDimensions().getWaist());
        hip.setText(currentPerson.getDimensions().getHip());
        inseam.setText(currentPerson.getDimensions().getInseam());


        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namedata  = name.getText().toString();
                if(namedata.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Name field cannot be empty!",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    String commentdata = comment.getText().toString();
                    String datedata = date.getText().toString();
                    Dimensions dimensions = new Dimensions(neck.getText().toString(),bust.getText().toString(),
                                                        chest.getText().toString(),waist.getText().toString(),
                                                        hip.getText().toString(),inseam.getText().toString());
                    currentPerson.setName(namedata);
                    currentPerson.setDate(datedata);
                    currentPerson.setComment(commentdata);
                    currentPerson.setDimensions(dimensions);
                    RewriteFile();
                    Intent i = new Intent(EditRecordActivity.this,SuccessActivity.class);
                    i.putExtra("editsaved","saved");
                    finish();
                    startActivity(i);
                }
            }
        });
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
            personList = new ArrayList<Person>();
        }
    }

    /**
     * This function rewrites the sav file with the new data
     */
    private void RewriteFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(personList, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
