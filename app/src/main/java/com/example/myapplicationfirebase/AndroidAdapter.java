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
import java.util.Map;
import java.util.zip.Inflater;
//création du recyclerview néccesaire pour afficher notre tableau de donner "reservation"
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


        //recuperation des champ ou je meterais mon tableau de donnée
        public MyViewHolder(View v){
            super(v);
            nom = v.findViewById(R.id.lnom);
            date = v.findViewById(R.id.ldate);
            heure = v.findViewById(R.id.lheure);
            nbr = v.findViewById(R.id.lnbr_pers);
            imageValid = v.findViewById(R.id.imageView);


       }

    }
    //constructeur
    public AndroidAdapter(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }
    //inflateur : on inflate/lie notre viewholder a notre row_list_res.xml qui créera un row par ligne dans la table reservation
    // et ansi créé notre tableau
    @Override
    public AndroidAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_res,parent, false);
        return new MyViewHolder(rowView);
    }
    //on insert les données de la table resrevation dans les champ de row_lit_res.xml
    @Override
    public void onBindViewHolder(MyViewHolder holder,int position){
        holder.nom.setText(reservations.get(position).getNom());
        holder.date.setText(reservations.get(position).getDate_reservation());
        holder.heure.setText(reservations.get(position).getHeure_reservation());
        holder.nbr.setText(reservations.get(position).getPersonnes());
        String comfirmation = reservations.get(position).getComfirmation();
       // Log.d("couleur", comfirmation);

       Log.d("coul", comfirmation);
        if (comfirmation == "non-comfirmée"){
            holder.itemView.setBackgroundColor(8);
          Log.d("couleur3", "onBindViewHolder: ");
        }

        //au click
        holder.imageValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("reservation");
                String myRef3 = database.getReference("reservation").getRef().getKey();
                Log.d("test", myRef3);
                //addChild.. method permettant d'utiliser les fonction suivante
                myRef.addChildEventListener(new ChildEventListener() {
                     // onChildAdded se declanche au click, me permet de recuperer la clef/id neccesaire a un update
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Log.d("test", "onChildAdded: "+snapshot.getKey());
                        String maClef = snapshot.getKey();

                        //dialogplus permet de crer un fenetre plus dynamique !!besoin de renseigner les dependance dans build.gradle!!
                        final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imageValid.getContext())
                                .setContentHolder(new ViewHolder(R.layout.vue_reservation))
                                .setExpanded(true,1200)
                                .create();

                        //recuperation des champ dans vue_reservation.xml
                        View myViev = dialogPlus.getHolderView();
                        TextView nom = myViev.findViewById(R.id.textView);
                        TextView date_res = myViev.findViewById(R.id.textView3);
                        TextView heure_res = myViev.findViewById(R.id.textView4);
                        TextView nbr_pers = myViev.findViewById(R.id.textView5);
                        TextView tel = myViev.findViewById(R.id.textView6);
                        TextView comment = myViev.findViewById(R.id.textView7);
                        TextView date_envoie = myViev.findViewById(R.id.textView8);
                        TextView confirmat = myViev.findViewById(R.id.textView9);
                        Button button = myViev.findViewById(R.id.button3);

                        //on recupère donnée de la ligne du taleau sur lequel on a clické et on insert dans les champ précédement recuperé
                        nom.setText(reservations.get(holder.getAdapterPosition()).getNom());
                        date_res.setText(reservations.get(holder.getAdapterPosition()).getDate_reservation());
                        heure_res.setText(reservations.get(holder.getAdapterPosition()).getHeure_reservation());
                        nbr_pers.setText(reservations.get(holder.getAdapterPosition()).getPersonnes());
                        tel.setText(reservations.get(holder.getAdapterPosition()).getTelephone());
                        comment.setText(reservations.get(holder.getAdapterPosition()).getCommentaire());
                        date_envoie.setText(reservations.get(holder.getAdapterPosition()).getDate_envoie());
                        confirmat.setText(reservations.get(holder.getAdapterPosition()).getComfirmation());
                        dialogPlus.show();

                        //écouteur d'évenement au click sur l'update button
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                //hashmap necesaire a transmetre a updatechildren()
                                HashMap<String,Object> map = new HashMap<>();
                              //  map.put("commentaire", reservations.get(holder.getAdapterPosition()).getCommentaire());
                                map.put("comfirmation", "comfirmée");
                           //     map.put("date_envoie", reservations.get(holder.getAdapterPosition()).getDate_envoie());
                           //     map.put("date_reservation", reservations.get(holder.getAdapterPosition()).getDate_reservation());
                          //      map.put("heure_reservation", reservations.get(holder.getAdapterPosition()).getHeure_reservation());
                            //    map.put("nom", reservations.get(holder.getAdapterPosition()).getNom());
                              //  map.put("heure_reservation", reservations.get(holder.getAdapterPosition()).getHeure_reservation());
                                //map.put("telephone", reservations.get(holder.getAdapterPosition()).getTelephone());

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef2 = database.getReference("reservation").child(maClef);
                                myRef2.updateChildren(map);
                                notifyDataSetChanged();
                                dialogPlus.dismiss();


                            }
                        });
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Log.d("test", "onChildChanged: ");
                        notifyDataSetChanged();

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
