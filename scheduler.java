package com.righty.sanketpatel.righthandman;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class scheduler extends AppCompatActivity {
    TextView t;
    Database database;
    private List<Movie> movieList = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        Button b=(Button)findViewById(R.id.create);
        database=new Database(this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(scheduler.this,create.class);
                startActivity(i);
                finish();
            }
        });
        //t=(TextView)findViewById(R.id.listen);
        Cursor res=database.getalldata();
        StringBuffer buffer=new StringBuffer();
        while(res.getCount()==0){
         //   Toast.makeText(scheduler.this,"Error",Toast.LENGTH_LONG).show();
            return;
        }



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        Context context=this;
        RecyclerView.Adapter mAdapter = new AlmondAdapter(movieList,context);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        while (res.moveToNext()){
           String s1,s2,s3;
           s1=res.getString(0);
           s2=res.getString(1);
           s3=res.getString(2);
           Movie movie = new Movie(s1,s2,s3);
           movieList.add(movie);

        }
        mAdapter.notifyDataSetChanged();



    }
}
