package com.example.sizebook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * This a custom adapter that is used to display the list view items, in a proper
 * format, that are of type Person.
 * Created by Shivansh on 2017-02-05.
 */
public class MyAdapter extends ArrayAdapter<Person> {
    /**
     * The Person list.
     */
    ArrayList<Person> personList = new ArrayList<>();

    /**
     * Instantiates a new My adapter.
     *
     * @param context            the context
     * @param textViewResourceId the text view resource id
     * @param objects            the objects
     */
    public MyAdapter(Context context,int textViewResourceId, ArrayList<Person> objects) {
        super(context,textViewResourceId, objects);
        personList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.list_items, null);
        TextView textView = (TextView) v.findViewById(R.id.textView);

        String displaytext =  "Name:  "+ personList.get(position).getName() +
                "\nDate Added:  " + personList.get(position).getDate() +
                "\nNeck:  " + personList.get(position).getDimensions().getNeck() +
                "\nBust:  " + personList.get(position).getDimensions().getBust() +
                "\nChest:  " + personList.get(position).getDimensions().getChest() +
                "\nWaist:  " + personList.get(position).getDimensions().getWaist() +
                "\nHip:  " + personList.get(position).getDimensions().getHip() +
                "\nInseam:  " + personList.get(position).getDimensions().getInseam() +
                "\nComment:  " + personList.get(position).getComment() ;
        textView.setText(displaytext);
        return v;
    }
}
