package com.example.teste1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teste1.classe.user;

public class profile extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView txt=findViewById(R.id.mail);
       user u= (user) getIntent().getSerializableExtra("user");

        assert u != null;
        txt.setText(u.getEmail()+" and "+u.getPassword());
    }
}
