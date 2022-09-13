package com.firebase_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
ListView listView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
    listView = findViewById(R.id.list_view);
        firebaseDatabase = FirebaseDatabase.getInstance("https://fir-app-64e4f-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference("people");
        ArrayList<PeopleModel> peopleModelArrayList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    PeopleModel peopleModel = dataSnapshot1.getValue(PeopleModel.class);
                    peopleModelArrayList.add(peopleModel);
                }

                MyBaseAdapter myBaseAdapter = new MyBaseAdapter(DisplayActivity.this,peopleModelArrayList);
                listView.setAdapter(myBaseAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}