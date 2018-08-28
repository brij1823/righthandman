package com.righty.sanketpatel.righthandman;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Sanket Patel on 11-06-2018.
 */

public class AlmondAdapter extends RecyclerView.Adapter<AlmondAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, msg, time;
        public LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.ocontact);
            msg = (TextView) view.findViewById(R.id.omessage);
            time = (TextView) view.findViewById(R.id.otime);
            linearLayout=(LinearLayout)view.findViewById(R.id.parent);
        }



    }


    public AlmondAdapter(List<Movie> moviesList,Context context) {
        this.moviesList = moviesList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.onepiece, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        LinearLayout linearLayout;
        Movie movie = moviesList.get(position);
        holder.name.setText(movie.getName());
        holder.msg.setText(movie.getmsg());
        holder.time.setText(movie.getTime()+"\n\n\n");
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show();
            }
        });
        


        
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
