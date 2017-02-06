package com.example.sizebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * The type Success activity.
 * This activity is launched whenever a new record has been successfully saved or
 * a record has been successfully editted
 */
public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Bundle data = getIntent().getExtras();
        if(data!= null){
            final TextView tv = (TextView) findViewById(R.id.successid);
            tv.setText("Your changes have been saved successfully!");
        }

    }
}
