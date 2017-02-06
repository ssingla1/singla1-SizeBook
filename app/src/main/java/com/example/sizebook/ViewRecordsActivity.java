package com.example.sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.Locale;

/**
 * This activity is launched when a user clicks on View Records from the main Activity
 * The total number of records is shown in the top portion of the activity
 * and the rest of the page contains a list view displaying all the records that exist
 * in the database. The user can long click on any record to get Action mode options
 * such as delete and edit so that they can make changes to the records in the
 * database.
 * The type View records activity.
 */
public class ViewRecordsActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ListView recordList;
    private TextView numRecords;
    private ArrayList<Person> personList;
    private MyAdapter adapter;
    private Person selecteditem;
    private int itemposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);

        recordList = (ListView) findViewById(R.id.listview01);
        numRecords = (TextView) findViewById(R.id.numrecords);
        loadFromFile();

        numRecords.setText(String.format(Locale.getDefault(),"%d",personList.size()));

        Log.i("String person list" , personList.toString());
        adapter = new MyAdapter(this,R.layout.list_items, personList);
        recordList.setAdapter(adapter);


        /**
         * This snippet of code was taken from a small part of the video
         * https://www.youtube.com/watch?v=rNZQpaie7-o
         * on 3rd feb, 2017 and modified to fulfill the needs of my project.
         * */
        recordList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        recordList.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener(){
            @Override
            public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
                itemposition = position;
                selecteditem = personList.get(position);
          }

            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.main_menu,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                if (item.getItemId()==R.id.delete){
                    personList.remove(selecteditem);
                    Toast.makeText(getApplicationContext(), "Record Deleted",
                            Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                    numRecords.setText(String.format(Locale.getDefault(),"%d",personList.size()));
                    RewriteFile();
                    mode.finish();

                }
                else if(item.getItemId()==R.id.edit){
                    Intent i = new Intent(ViewRecordsActivity.this,EditRecordActivity.class);
                    i.putExtra("itemid",itemposition);
                    mode.finish();
                    startActivity(i);

                }

                return false;
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {

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

            Type listType = new TypeToken<ArrayList<Person>>(){}.getType();
            personList = gson.fromJson(in,listType);

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
            gson.toJson(personList,out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
