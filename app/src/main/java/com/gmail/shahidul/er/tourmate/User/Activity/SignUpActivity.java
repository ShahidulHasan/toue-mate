package com.gmail.shahidul.er.tourmate.User.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.shahidul.er.tourmate.R;
import com.gmail.shahidul.er.tourmate.User.Model.Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    int i=0;
    EditText passwordET, confirmPasswordET, fullNameET, emailET, phoneET;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        fullNameET = (EditText) findViewById(R.id.fullName);
        emailET = (EditText) findViewById(R.id.email);
        passwordET = (EditText) findViewById(R.id.password);
        confirmPasswordET = (EditText) findViewById(R.id.confirmPassword);
        phoneET = (EditText) findViewById(R.id.phone);

        auth=FirebaseAuth.getInstance();

        mDatabase.child("profiles").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                i++;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void save(View view) {
        final String password = passwordET.getText().toString().trim();
        final String phone = phoneET.getText().toString().trim();
        final String email = emailET.getText().toString().trim();
        final String fullName = fullNameET.getText().toString().trim();
        final String confirmPassword = confirmPasswordET.getText().toString().trim();

        if (!password.equals(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
        } else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "" + "authentication failed!!!", Toast.LENGTH_SHORT).show();
                        }else{
                            if (i == 0){ i = 1;} else { i++; }
                            Profile profile = new Profile();
                            String profileId = mDatabase.push().getKey();
                            profile.setId(i);
                            profile.setEmail(email);
                            profile.setFullName(fullName);
                            profile.setPhone(phone);

                            mDatabase.child(profileId).setValue(profile);

                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                });
        }
    }

}
