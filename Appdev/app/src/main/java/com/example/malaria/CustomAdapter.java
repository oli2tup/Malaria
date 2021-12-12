package com.example.malaria;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList sample_id, sample_initials, sample_lastName, sample_date, sample_age;

    int position;


    CustomAdapter (Context context,ArrayList sample_id, ArrayList sample_initials,
                   ArrayList sample_lastName, ArrayList sample_date, ArrayList sample_age){

        this.context = context;
        this.sample_id = sample_id;
        this.sample_initials = sample_initials;
        this.sample_lastName = sample_lastName;
        this.sample_date = sample_date;
        this.sample_age = sample_age;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.sample_id_txt.setText(String.valueOf(sample_id.get(position)));
        holder.sample_initials_txt.setText(String.valueOf(sample_initials.get(position)));
        holder.sample_lastName_txt.setText(String.valueOf(sample_lastName.get(position)));
        holder.sample_date_txt.setText(String.valueOf(sample_date.get(position)));
        holder.sample_age_txt.setText(String.valueOf(sample_age.get(position)));

        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Update_Table.class);
                intent.putExtra("id", String.valueOf(sample_id.get(position)));
                intent.putExtra("initials", String.valueOf(sample_initials.get(position)));
                intent.putExtra("lastName", String.valueOf(sample_lastName.get(position)));
                intent.putExtra("date", String.valueOf(sample_date.get(position)));
                intent.putExtra("age", String.valueOf(sample_age.get(position)));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return sample_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView sample_id_txt, sample_initials_txt, sample_lastName_txt, sample_date_txt, sample_age_txt;
        LinearLayout rowLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sample_id_txt = itemView.findViewById(R.id.sample_id_txt);
            sample_initials_txt = itemView.findViewById(R.id.sample_initials_txt);
            sample_lastName_txt = itemView.findViewById(R.id.sample_lastName_txt);
            sample_date_txt = itemView.findViewById(R.id.sample_date_txt);
            sample_age_txt = itemView.findViewById(R.id.sample_age_txt);
            rowLayout = itemView.findViewById(R.id.rowLayout);
        }
    }
}
