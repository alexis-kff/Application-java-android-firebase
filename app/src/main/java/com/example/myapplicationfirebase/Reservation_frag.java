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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("reservation");


        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_reservation_, container, false);
        enom = v.findViewById(R.id.ename);
        ephone = v.findViewById(R.id.ephone);
        eday_res = v.findViewById(R.id.edayres);
        ehour = v.findViewById(R.id.spinner);
        enbrpers = v.findViewById(R.id.enbrpers);
        ecomment = v.findViewById(R.id.ecoment);
        boutton = v.findViewById(R.id.boutton);


        aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,heure);
        ehour.setAdapter(aa);


        boutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("reservation");


                String name = enom.getText().toString();
                String day_res = eday_res.getText().toString();
                String phone = ephone.getText().toString();
                String hour_res = ehour.getSelectedItem().toString();
                String nbr_pers = enbrpers.getText().toString();
                String coment = ecomment.getText().toString();
                String comfirm = "non-comfirmée";

                DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String date_res = format.format(date);


                Reservation reservation = new Reservation(name,day_res,phone,hour_res,nbr_pers,coment,date_res,comfirm);
                myRef.push().setValue(reservation);

                Log.d("result",reservation.toString());

                Toast toast = Toast.makeText(getContext(),"Resevation transmise",Toast.LENGTH_LONG);
                toast.show();

            }
        });

        return v;
    }
}