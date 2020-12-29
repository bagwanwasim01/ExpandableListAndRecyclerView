package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //Defined instances of required classes ExpandableListView and ExpandableListAdapter
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    //Defined and Instantiated ArrayList for parent item (Production Eng)
    // and HashMap for it's child items i.e. Overview of Petrochemicals , etc.
    private ArrayList<String> listGroup = new ArrayList<>();
    private HashMap<String, ArrayList<String>> listChild = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = findViewById(R.id.exp_list);  //Initialized the expandable list view with it's ID
        //As we want three parent groups to be displayed,we are adding 3 const strings in ArrayList as below
        listGroup.add("Production Engineering I");
        listGroup.add("Production Engineering II");
        listGroup.add("Production Engineering III");

        for(int g = 0;g<= listGroup.size() - 1; g++){
            //Below will fetch child item data from string array which is stored in strings.xml
            // and store it in ArrayList of String
            ArrayList<String> headings = new ArrayList<>();
            headings.addAll(Arrays.asList(getResources().getStringArray(R.array.HeadDesc1)));
            //This will add each group of child items to it's parent group i.e. Production Eng
            listChild.put(listGroup.get(g),headings);
        }
        //instantiated new Adapter class(MyExListAdapter) for Expandable List View with two parameterized constructor
        //which holds values for parent group and Key-Value pair of parent and child items
        expandableListAdapter = new MyExListAdapter(listGroup,listChild);
        //sets adapter which has provided data to this Expandable List View
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setNestedScrollingEnabled(false);
        //Sets Group indicator to right of Expandable List View
        expandableListView.setIndicatorBounds(expandableListView.getRight() + 880 , expandableListView.getWidth());

//        expandableListView.setOnGroupExpandListener(groupPosition -> {
//            Toast.makeText(getApplicationContext(), getString(R.string.production_engineering), Toast.LENGTH_LONG).show();
//        });
    }
}