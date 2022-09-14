package com.firebase_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity {
    EditText edtFn,edtLn;
    Button btnUpdate,btnDelete;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edtFn = findViewById(R.id.edt_fn);
        edtLn = findViewById(R.id.edt_ln);

        btnUpdate = findViewById(R.id.btn_up);
        btnDelete = findViewById(R.id.btn_dis);
        firebaseDatabase = FirebaseDatabase.getInstance("https://fir-app-64e4f-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference("people");
        Intent i = getIntent();
        String strfn = i.getStringExtra("KEY_Fn");
        String strln = i.getStringExtra("KEY_Ln");

        final String strId = i.getStringExtra("KEY_Id");

        edtFn.setText(strfn);
        edtLn.setText(strln);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strfn = edtFn.getText().toString();
                String strln = edtLn.getText().toString();

                PeopleModel peopleModel = new PeopleModel();
                peopleModel.setId(strId);
                peopleModel.setFn(strfn);
                peopleModel.setLn(strln);

                databaseReference.child(strId).setValue(peopleModel);

                Intent I = new Intent(UpdateActivity.this,DisplayActivity.class);
                startActivity(I);
                finish();

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(strId).removeValue();
                Intent I = new Intent(UpdateActivity.this,DisplayActivity.class);
                startActivity(I);
                finish();
            }
        });

    }
}