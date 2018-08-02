package com.example.kishbelic.searchapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchActivity extends AppCompatActivity {

    EditText search_txt;
    Button search_btn;


    FirebaseDatabase FireDB;
    DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_txt = (EditText)findViewById(R.id.search_txt);
        search_btn = (Button)findViewById(R.id.search_btn);

        FireDB = FirebaseDatabase.getInstance();
        usersRef = FireDB.getReference("Users");



    }



    public void searchClick(View view) {

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child:dataSnapshot.getChildren()){
                    String name = child.child("Name").getValue().toString();

                    if (search_txt.getText().equals(name) ||(search_txt.getText().toString() == name)){

                        Log.i("logman values","correct man");
                    }


                    Log.i("logman values",child.getValue()+"");
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.i("logman Error", databaseError.toException()+"");
            }
        });


    }


}


