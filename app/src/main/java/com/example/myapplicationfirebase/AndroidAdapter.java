package com.example.myapplicationfirebase;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.MyViewHolder> {
    private ArrayList<Reservation> reservations;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nom;
        public TextView date;
        public TextView heure;
        public TextView nbr;
        public TextView coment;
        public TextView confirm;
        public ImageView imageValid;
        public ImageView imageDelete;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("reservation");



        public MyViewHolder(View v){
            super(v);
            nom = v.findViewById(R.id.lnom);
            date = v.findViewById(R.id.ldate);
            heure = v.findViewById(R.id.lheure);
            nbr = v.findViewById(R.id.lnbr_pers);
            coment = v.findViewById(R.id.lcoment);
            confirm = v.findViewById(R.id.lcomfirm);
            imageValid = v.findViewById(R.id.imageView);
            imageDelete = v.findViewById(R.id.imageView2);

            imageValid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   
                }
            });
            imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

       }

    }

    public AndroidAdapter(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }
    @Override
    public AndroidAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_res,parent, false);
        return new MyViewHolder(rowView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.nom.setText(reservations.get(position).getNom());
        holder.date.setText(reservations.get(position).getDate_reservation());
        holder.heure.setText(reservations.get(position).getHeure_reservation());
        holder.nbr.setText(reservations.get(position).getPersonnes());
        holder. coment.setText(reservations.get(position).getCommentaire());
        holder.confirm.setText(reservations.get(position).getConfirmation());
    }
    @Override
    public int getItemCount(){
        return reservations.size();
    }


}
