package com.firebase_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {
    Button btn_Dis;
    Button btn_Add;
    EditText edtfn, edtln;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btn_Dis = findViewById(R.id.btn_dis);
        btn_Add = findViewById(R.id.btn_add);
        edtfn = findViewById(R.id.edt_fn);
        edtln = findViewById(R.id.edt_ln);

        firebaseDatabase = FirebaseDatabase.getInstance("https://fir-app-64e4f-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference("people");

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddActivity.this, "Added", Toast.LENGTH_SHORT).show();
                String strfn = edtfn.getText().toString();
                String strln = edtln.getText().toString();
                String id = databaseReference.push().getKey();

                PeopleModel peopleModel = new PeopleModel();
                peopleModel.setId(id);
                peopleModel.setFn(strfn);
                peopleModel.setLn(strln);

                databaseReference.child(id).setValue(peopleModel);
                Intent i = new Intent(AddActivity.this,DisplayActivity.class);

                startActivity(i);
                finish();

            }

        });
        btn_Dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddActivity.this,DisplayActivity.class);

                startActivity(i);
                finish();
            }
        });


    }
}