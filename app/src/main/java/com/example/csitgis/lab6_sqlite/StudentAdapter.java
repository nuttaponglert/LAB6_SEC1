package com.example.csitgis.lab6_sqlite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public abstract class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    private Context context;
    private List<student> studentList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView idTextView;
        public TextView nameTextView;

        public MyViewHolder(View view) {
            super(view);
            idTextView = view.findViewById(R.id.text_view_id);
            nameTextView = view.findViewById(R.id.text_view_name);
        }
    }

    public StudentAdapter(Context context, List<student> notesList){
        this.context = context;
        this.studentList = notesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.student_list_row, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder MyViewHolder, int position, @NonNull List<Object> payloads) {
        student student = studentList.get(position);

        MyViewHolder.idTextView.setText(student.getId());
        MyViewHolder.nameTextView.setText(student.getName());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
