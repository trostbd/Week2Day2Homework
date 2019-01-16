package com.example.lawre.week2day2homework;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    ArrayList<Student> StudentsList;

    public RecyclerViewAdapter(ArrayList<Student> StudentsList) {
        this.StudentsList = StudentsList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student,viewGroup,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position)
    {
        Student newStudent = StudentsList.get(position);

        if(newStudent != null)
        {
            viewHolder.tvSsn.setText("SSN: "+newStudent.getSsn());
            viewHolder.tvName.setText("Name: "+newStudent.getName());
            viewHolder.tvMajor.setText("Major: "+newStudent.getMajor());
            viewHolder.tvMinor.setText("Minor: "+newStudent.getMinor());
            viewHolder.tvGpa.setText("GPA: "+newStudent.getGpa());
            viewHolder.tvDob.setText("DOB: "+newStudent.getDob());
            viewHolder.tvCity.setText("Home City: "+newStudent.getHomeCity());
            viewHolder.tvState.setText("Home State: "+newStudent.getHomeState());
        }
    }

    @Override
    public int getItemCount() {
        return StudentsList != null ? StudentsList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvSsn, tvName, tvMajor,tvMinor,tvGpa,tvDob,tvCity,tvState;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSsn = itemView.findViewById(R.id.tvSsn);
            tvName = itemView.findViewById(R.id.tvName);
            tvMajor = itemView.findViewById(R.id.tvMajor);
            tvMinor = itemView.findViewById(R.id.tvMinor);
            tvGpa = itemView.findViewById(R.id.tvGpa);
            tvDob = itemView.findViewById(R.id.tvDob);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvState = itemView.findViewById(R.id.tvState);
        }
    }
}