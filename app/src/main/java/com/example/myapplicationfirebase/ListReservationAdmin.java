package com.example.myapplicationfirebase;


import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListReservationAdmin extends AppCompatActivity {


    private ArrayList<Reservation> reservations = new ArrayList<>();
    private RecyclerView.Adapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reservation_admin);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("reservation");


        RecyclerView liste = findViewById(R.id.list);
        liste.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        liste.setLayoutManager(layoutManager);

       AndroidAdapter myAdapter = new AndroidAdapter(reservations);
       liste.setAdapter(myAdapter);


     //   Log.d("resul", "adapter"+ aa.toString());


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Reservation reservation = ds.getValue(Reservation.class);
                    Log.d("result", "Value is: " + reservation.getNom()+" "+reservation.getDate_reservation()+" "+reservation.getConfirmation());
                    reservations.add(reservation);
                    //Log.d("result", "reservations"+ reservations.toString());
                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("result", "erreur n'arrive a lire les valeur!!", error.toException());
            }

        });


    }


}