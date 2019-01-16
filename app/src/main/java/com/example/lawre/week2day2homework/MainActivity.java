package com.example.lawre.week2day2homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    RecyclerView myRecycler;
    MySQLHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MySQLHelper(this);
        myRecycler = findViewById(R.id.rvMainRecyclerView);
        RecyclerViewAdapter rvAdapter = new RecyclerViewAdapter(db.getAllStudents());
        RecyclerView.LayoutManager layoutMan = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(layoutMan);
        myRecycler.setAdapter(rvAdapter);
    }

    public void onClick(View view)
    {
        if(view.getId()==R.id.btGoToInput)
        {
            Intent myIntent = new Intent(this, UserInput.class);
            startActivity(myIntent);
        }
    }
}
