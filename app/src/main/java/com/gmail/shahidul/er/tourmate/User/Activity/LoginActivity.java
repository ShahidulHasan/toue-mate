package com.gmail.shahidul.er.tourmate.User.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.shahidul.er.tourmate.Home.Activity.HomeActivity;
import com.gmail.shahidul.er.tourmate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText emailET, passwordET;
    Button submitBtn;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = (EditText) findViewById(R.id.email);
        passwordET = (EditText) findViewById(R.id.password);
        submitBtn= (Button) findViewById(R.id.buttonSignIN);

        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser = auth.getCurrentUser();

            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onPause() {
        auth.removeAuthStateListener(authStateListener);
        super.onPause();
    }

    public void submit(View view) {
        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();
if(!email.isEmpty() && !password.isEmpty()) {
    auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "" + "authentication failed!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        String email = firebaseUser.getEmail();

                        SharedPreferences saveUserData = getSharedPreferences("UserInfo", MODE_PRIVATE);

                        SharedPreferences.Editor editor = saveUserData.edit();
                        editor.putString("email", email);
                        editor.apply();
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                    }
                }
            });
} else {
    Toast.makeText(this, "email and password required", Toast.LENGTH_SHORT).show();
}

        }

    public void signUp(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

}
