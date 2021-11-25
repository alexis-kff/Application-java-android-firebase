package com.example.myapplicationfirebase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Reservation_frag extends Fragment {

    EditText enom,ephone,eday_res,ecomment,enbrpers;
    ArrayAdapter<String>aa;
    Spinner ehour;
    Button boutton ;
    String[] heure = {"12h","12h30","13h","13h30","14h"};

    public Reservation_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_reservation_, container, false);
        enom = v.findViewById(R.id.ename);
        ephone = v.findViewById(R.id.ephone);
        eday_res = v.findViewById(R.id.edayres);
        ehour = v.findViewById(R.id.spinner);
        enbrpers = v.findViewById(R.id.enbrpers);
        ecomment = v.findViewById(R.id.ecoment);
        boutton = v.findViewById(R.id.boutton);

        //arrayadapter qui contient les heure possible de reservation
        aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,heure);
        ehour.setAdapter(aa);

        //insertion firebase au click
        boutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instancier la table reservation dans firebase
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("reservation");
                //recupération des info du formulaire de reservation
                String name = enom.getText().toString();
                String day_res = eday_res.getText().toString();
                String phone = ephone.getText().toString();
                String hour_res = ehour.getSelectedItem().toString();
                String nbr_pers = enbrpers.getText().toString();
                String coment = ecomment.getText().toString();
                String comfirm = "non-comfirmée";
                //date du click donc de la prise de reservation ainsi que le format de la date
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String date_res = format.format(date);
                //insertion
                Reservation reservation = new Reservation(name,day_res,hour_res,phone,nbr_pers,coment,date_res,comfirm);
                myRef.push().setValue(reservation);
                //toat de comfirmation
                Toast toast = Toast.makeText(getContext(),"Reservation transmise",Toast.LENGTH_LONG);
                toast.show();

            }
        });

        return v;
    }
}