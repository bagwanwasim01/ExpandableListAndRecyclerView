package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//RecyclerView.Adapter abstract class should be extended
// which is responsible for providing views that represents items in data set
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    //As we have instantiated two parameterized constructor of this class in MyExListAdapter class
    // create constructor with below members.
    private List<ListItem> listItems;
    private Context context;
    //to check whether layout is expanded or not
    private  boolean expanded =  false;
    public MyRecyclerViewAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //As our Recycler view need at least and only one View Holder
        //Inside OnCreateViewHolder(),create a new ViewHolder object.
        //Here row layout is inflated,passed to the ViewHolder object and each child view
        //can be found and stored.
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Inside OnBindViewHolder(),it takes ViewHolder object i.e holder and sets the proper
        //list data for particular row on the views inside.
        //Here we have ListItem class which is holding data for Heading and Desc
        ListItem listItem = listItems.get(position);
        //sets heading for each TextView(textViewHead) inside Recycler View
        holder.head.setText(listItem.getHead());
        //sets heading for each TextView(textViewDesc) inside Recycler View
        holder.desc.setText(listItem.getDesc());
        //holder.timeTaken.setText(listItem.getTimeTaken());
        //On Clicking DropDown arrow child layout from each CardLayout has to expand
        holder.dropDown.setOnClickListener(v -> {
            //Toast.makeText(holder.itemView.getContext(),"Selected",Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(context,SecondActivity.class);
            //context.startActivity(intent);

            //check if layout is already expanded if not then,
            //perform below ->
            if(!expanded) {
                //sets image resource for ImageView as arrow up
                holder.dropDown.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                //Here child layout is inflated,passed to the parent Layout and each child layout
                //can be found and expanded on clicking ImageView.
                holder.childLayout = (LinearLayout) LayoutInflater.from(holder.itemView.getContext())
                        .inflate(R.layout.child_layout, null, true);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                holder.childLayout.setLayoutParams(params);
                //add view for child layout to parent layout
                holder.parentLayout.addView(holder.childLayout);
                //create anim folder inside res directory and add new resource xml file named slide_down
                Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
                // creates/starts animation
                holder.childLayout.startAnimation(slideDown);
                //As we are expanding the layout,we need to enable visibility of child layout
                holder.childLayout.setVisibility(View.VISIBLE);
                //Once layout is expanded,set flag expanded to true because now layout is expanded
                expanded = true;
            }
            else if(expanded){
                //On collapsing the layout,we need to disable visibility of child layout
                holder.childLayout.setVisibility(View.GONE);
                //sets image resource for ImageView as arrow down
                holder.dropDown.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                //Once layout is collapsed,set flag expanded to false because now layout is collapsed
                expanded = false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        //Declare widgets and layouts to be displayed inside recycler view
        private TextView head,desc,timeTaken;
        private ImageView dropDown;
        private LinearLayout parentLayout,childLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Initialize each widget and layout with proper id
            head = itemView.findViewById(R.id.textViewHead);
            desc = itemView.findViewById(R.id.textViewDesc);
            timeTaken = itemView.findViewById(R.id.txtTime);
            dropDown = itemView.findViewById(R.id.imageView1);
            parentLayout = itemView.findViewById(R.id.LL);
            childLayout = itemView.findViewById(R.id.childLayout);
        }
    }
}
