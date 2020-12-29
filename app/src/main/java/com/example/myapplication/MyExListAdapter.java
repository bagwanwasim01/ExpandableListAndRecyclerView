package com.example.myapplication;

import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import java.util.ArrayList;
import java.util.HashMap;

//BaseExpandableListAdapter abstract class should be extended
// which provides data and views from some data to an expandable list view
public class MyExListAdapter extends BaseExpandableListAdapter {
    //private String listGroup;

    //As we have instantiated two parameterized constructor of this class in MainActivity class
    // create constructor with below members.
    private ArrayList<String> listGroup;
    private HashMap<String,ArrayList<String>> listChild;


    public MyExListAdapter(ArrayList<String> listGroup,HashMap<String,ArrayList<String>> listChild) {
        this.listGroup = listGroup;
        this.listChild = listChild;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return listGroup.size();      //returns count of parent group items
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listChild.get(listGroup.get(groupPosition)).size();  // //returns count of child group items from each parent

    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition); //returns parent object at position
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //return listChild.get(listGroup).get(childPosition);
        return listChild.get(listGroup.get(groupPosition)).get(childPosition); //returns child object at childPos from it's parentPos
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //this will add actual parent data with any widgets we have used in parent layout file i.e. (level_1)
       convertView = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.level_1,parent,false);
        convertView.setPadding(0,0,0,20);
       //ImageView imageView = convertView.findViewById(R.id.imgChecked);

       //Declare , initialize and bind TexView with convert view which is responsible for drawing and event handling
       TextView textView = convertView.findViewById(R.id.heading);
       //gets and sets text to TextView at each parent position
       String sGroup = String.valueOf(getGroup(groupPosition));
       textView.setText(sGroup);
       //some styling applied to TextView
       textView.setTypeface(null,Typeface.BOLD);
       textView.setTextColor(Color.GRAY);
       return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //this will add actual child data with any widgets we have used in parent layout file
        //Here in this case we have used Recycler View in child layout file i.e. (level_2)
        convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.level_2, parent, false);
        //Declare , initialize and bind RecyclerView with convert view
        RecyclerView recyclerView = convertView.findViewById(R.id.recyclerView1);
        RecyclerView.Adapter adapter;
        //gets data at each child position from it's parent
        //String descH = String.valueOf(getChild(groupPosition,childPosition));

        recyclerView.setHasFixedSize(false);  //can change it's size based on adapter contents

       ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(true);

        //below very mandatory to set Layout for recycler view
        //without using this recycler view will not appear
        recyclerView.setLayoutManager(new LinearLayoutManager(parent.getContext()));

        //Create and initialize list of class ListItem
//        ArrayList<ListItem> itemArrayList = new ArrayList<>();
//        ListItem listItem = new ListItem();
//        String elearn = null;
//        elearn = parent.getResources().getString(R.string.Heading);
//        ArrayList<String> headings = new ArrayList<>();
//        headings.addAll(Arrays.asList(parent.getResources().getStringArray(R.array.HeadDesc1)));
//        listItem.setHead(String.valueOf(elearn));
//        listItem.setDesc(String.valueOf(getChild(groupPosition,childPosition)));
//        ArrayList<String> timetaken = new ArrayList<>();
//        timetaken.addAll(Arrays.asList(parent.getResources().getStringArray(R.array.timeTaken)));
//        for(int i = 0 ; i <= timetaken.size() - 1; i++){
//            timetaken.get(i);
//            listItem.setTimeTaken(String.valueOf(timetaken));
//        }
//        itemArrayList.add(listItem);

        ArrayList<ListItem> listItems;
        listItems = new ArrayList<>();
        ListItem listItem = new ListItem(
                parent.getResources().getString(R.string.Heading),
                String.valueOf(getChild(groupPosition,childPosition)));
        listItems.add(listItem);

        //instantiated new Adapter class(MyRecylerViewAdapter) for Recycler View with two parameterized constructor
        //which holds values for ListItem class and context
        adapter = new MyRecyclerViewAdapter(listItems, parent.getContext());
        //sets adapter which has provided data to Recycler View
        recyclerView.setAdapter(adapter);
        //for smooth scrolling of recycler view,set NestedScrollingEnabled to false
        recyclerView.setNestedScrollingEnabled(false);
        return convertView;
    }

//        TextView textView = convertView.findViewById(R.id.textViewDesc);
//        String sChild = String.valueOf(getChild(groupPosition,childPosition));
//        textView.setText(sChild);

//        convertView = LayoutInflater.from(parent.getContext())
//                            .inflate(android.R.layout.simple_selectable_list_item,parent,false);

//        textView.setOnClickListener(v -> Toast.makeText(parent.getContext(),sChild,Toast.LENGTH_SHORT).show())

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}


