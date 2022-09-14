package com.firebase_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent i1 = new Intent(DisplayActivity.this,UpdateActivity.class);
            i1.putExtra("KEY_Fn",peopleModelArrayList.get(i).getFn());
            i1.putExtra("KEY_Ln",peopleModelArrayList.get(i).getLn());
            i1.putExtra("KEY_Id",peopleModelArrayList.get(i).getId());
            startActivity(i1);
            finish();
        }
    });
    }
}