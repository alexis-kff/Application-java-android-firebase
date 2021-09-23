package com.example.myapplicationfirebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;


public class Base extends AppCompatActivity {
    Reservation_frag reservation_frag = new Reservation_frag();
    Contact contact = new Contact();
    LaCarte laCarte = new LaCarte();
    Maps maps = new Maps();
    LeMenuDuJour leMenuDuJour = new LeMenuDuJour();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //j'ouvre l'appli et j'ai le frag reservation
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment,reservation_frag)
                .commit();

        // ma reservation finis je suis rediriger sur le frag conetenant le meunu
        String i = getIntent().getAction();
        Log.d("result", "onceate");
        if(i == "createRes"){
            Log.d("result", "dans le if");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment,laCarte)
                    .commit();


        }
    }


    public void openreservation(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment,reservation_frag)
                .commit();
    }

    public void openMenuDuJour(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment,leMenuDuJour)
                .commit();
    }

    public void openMenu(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment,laCarte)
                .commit();

    }

    public void openContact(View view) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment,contact)
                .commit();
    }

    public void openMap(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment,maps)
                .commit();
    }
}