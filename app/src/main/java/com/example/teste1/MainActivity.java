package com.example.teste1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teste1.classe.user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {



    user us=new user();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn= findViewById(R.id.connecter);
        final TextView email=findViewById(R.id.email);
         final TextView ps =findViewById(R.id.password);

        /*DatabaseReference ref= FirebaseDatabase.getInstance().getReference("bases").child("user");
        user u =new user(2 ,"medos","medo" , "06893939", "sale", "sale hay inbiat", "med@gmail.com", "12345", "jggg");
        ref.push().setValue(u);*/

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                String e=email.getText().toString();
                String p=ps.getText().toString();
               if(TextUtils.isEmpty(e) || TextUtils.isEmpty(p) ){
                   Toast.makeText(MainActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();

               }else{
                   getData(e,p);
               }




           }
       });


    }

    private void getData(final String email, final String ps) {

      FirebaseDatabase.getInstance().getReference().child("bases").child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user user = null;
                if(snapshot.exists()) {
                    for (DataSnapshot s : snapshot.getChildren()) {
                        us = s.getValue(user.class);


                        // Toast.makeText(MainActivity.this, " true", Toast.LENGTH_SHORT).show();
                        //Log.v("Email :", us.getEmail());
                    /*Intent myInt =new Intent(MainActivity.this,profile.class);
                           myInt.putExtra("user",us);
                           startActivity(myInt);*/
                        if (us != null)
                            if (us.getEmail().equals(email) && us.getPassword().equals(ps)) {
                                user = us;
                                break;
                            }
                    }
                    if (user != null) {
                        Toast.makeText(MainActivity.this, " true", Toast.LENGTH_SHORT).show();
                        Intent myInt =new Intent(MainActivity.this,profile.class);
                        myInt.putExtra("user",us);
                        startActivity(myInt);
                    } else {
                        Toast.makeText(MainActivity.this, " email ou password incorrecte", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, " ERReur", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

   /* private  void affiche(){
        FirebaseDatabase.getInstance().getReference().child("bases").child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s:snapshot.getChildren()){
                    us=s.getValue(user.class);
                    if(us != null) {
                        Log.v("user :", us.getEmail());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
*/


}
