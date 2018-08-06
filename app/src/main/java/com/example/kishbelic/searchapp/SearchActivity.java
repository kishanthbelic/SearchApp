package com.example.kishbelic.searchapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private EditText search_txt;
    private Button search_btn;

    private ListView listView;

    private ArrayList<String> userList;



    FirebaseDatabase FireDB;
    DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_txt = (EditText)findViewById(R.id.search_txt);
        search_btn = (Button)findViewById(R.id.search_btn);

        listView = (ListView)findViewById(R.id.listView);
        userList = new ArrayList<String>();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_2,userList);

        listView.setAdapter(arrayAdapter);

        userList.clear();

        FireDB = FirebaseDatabase.getInstance();
        usersRef = FireDB.getReference("Users");




    }



    public void searchClick(View view) {

        String queryText = search_txt.getText().toString();
        Query query = usersRef.orderByChild("Name")
                .startAt(queryText)
                .endAt(queryText+"\uf8ff");

    query.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


        userList.clear();
        for (DataSnapshot child:dataSnapshot.getChildren()){

            String str = "Name :" +child.child("Name").getValue() + " , Age :" +child.child("age").getValue();

            userList.add(str);

            Log.i("logman query values",child.getValue()+"");

            Log.i("logman query values","Name : "+child.child("Name").getValue()+"");


        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        Log.i("logman Error","error man");

    }
});



    }


    public void firebaseSearch(){



    }



}



