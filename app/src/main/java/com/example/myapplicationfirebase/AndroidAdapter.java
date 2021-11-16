package com.example.myapplicationfirebase;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
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



        public MyViewHolder(View v){
            super(v);
            nom = v.findViewById(R.id.lnom);
            date = v.findViewById(R.id.ldate);
            heure = v.findViewById(R.id.lheure);
            nbr = v.findViewById(R.id.lnbr_pers);
            imageValid = v.findViewById(R.id.imageView);


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
    //les elements visible de la liste des reservation coté admin
    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position){
        holder.nom.setText(reservations.get(position).getNom());
        holder.date.setText(reservations.get(position).getDate_reservation());
        holder.heure.setText(reservations.get(position).getHeure_reservation());
        holder.nbr.setText(reservations.get(position).getPersonnes());
        String comfirmation = reservations.get(position).getConfirmation();
       // Log.d("couleur", comfirmation);

        Log.d("coul", comfirmation);
        if (comfirmation == "non-comfirmée"){
            holder.itemView.setBackgroundColor(8);
          Log.d("couleur3", "onBindViewHolder: ");
        }

        //vue de tout les éléments d'une reservation avec dialogplus
        holder.imageValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    HashMap hashMap = ;
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("reservation");
                myRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Log.d("click", "onChildAdded: "+snapshot.getKey());
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imageValid.getContext())
                        .setContentHolder(new ViewHolder(R.layout.vue_reservation))
                        .setExpanded(true,1100)
                        .create();

                View myViev = dialogPlus.getHolderView();
                TextView nom = myViev.findViewById(R.id.textView);
                TextView date_res = myViev.findViewById(R.id.textView3);
                TextView heure_res = myViev.findViewById(R.id.textView4);
                TextView nbr_pers = myViev.findViewById(R.id.textView5);
                TextView tel = myViev.findViewById(R.id.textView6);
                TextView comment = myViev.findViewById(R.id.textView7);
                TextView date_envoie = myViev.findViewById(R.id.textView8);
                TextView confirmtion = myViev.findViewById(R.id.textView9);
                Button button = myViev.findViewById(R.id.button3);

                nom.setText(reservations.get(holder.getAdapterPosition()).getNom());
                date_res.setText(reservations.get(holder.getAdapterPosition()).getDate_reservation());
                heure_res.setText(reservations.get(holder.getAdapterPosition()).getHeure_reservation());
                nbr_pers.setText(reservations.get(holder.getAdapterPosition()).getPersonnes());
                tel.setText(reservations.get(holder.getAdapterPosition()).getTelephone());
                comment.setText(reservations.get(holder.getAdapterPosition()).getCommentaire());
                date_envoie.setText(reservations.get(holder.getAdapterPosition()).getDate_envoie());
                confirmtion.setText(reservations.get(holder.getAdapterPosition()).getConfirmation());
                dialogPlus.show();

             //  reservations.get(position).setConfirmation("confirmé");
             //  myRef.push();
               // myRef.push().setValue()
               // myRef.child().setValue("confirmé");
               // Reservation reservation =  reservations.get(holder.getAdapterPosition());
           //     Log.d("click", "onClick: "+key);

            }
        });
    }
    @Override
    public int getItemCount(){
        return reservations.size();
    }
    public void updateConfirmation(String confirmation){
         confirmation = "confirmé";
         return;
    }
   // public updateConfirm(String id, String confirm){

  //  }


}
