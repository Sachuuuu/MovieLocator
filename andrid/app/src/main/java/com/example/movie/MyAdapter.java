package com.example.movie;

/*
* MyAdapter.java
*
* This class is implemented to load the data to recycler view
*
* */
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) { //Constructor

        //set values
        this.listItems = listItems;
        this.context = context;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder( MyAdapter.ViewHolder holder, int position) {

        //get the value and set them into appropriate text views
        final ListItem listItem = listItems.get(position);
        holder.textViewHead.setText(listItem.getHead());
        holder.textViewDesc.setText(listItem.getCinemas());

        //load the image into imageview
        Picasso.with(context).load(listItem.getImg_url()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listItems.size(); //get count
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //declare variables
        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageView;


        public ViewHolder( View itemView) {
            super(itemView);
            textViewHead =(TextView)itemView.findViewById(R.id.textViewHead);
            textViewDesc =(TextView)itemView.findViewById(R.id.textViewDesc);
            imageView = (ImageView)itemView.findViewById(R.id.imageView1);
        }
    }
}
