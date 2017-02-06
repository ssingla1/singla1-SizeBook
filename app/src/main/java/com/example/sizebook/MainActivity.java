package com.example.sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The type Main activity.
 * This activity is the first activity that shows up when the app is launched
 * and gives the user the option to View records or make a new record
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ViewRecords = (Button)findViewById(R.id.viewrecords);
        Button NewRecord = (Button) findViewById(R.id.newrecord);

        NewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NewRecordActivity.class));
            }
        });
        ViewRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ViewRecordsActivity.class));
            }
        });
    }
}
