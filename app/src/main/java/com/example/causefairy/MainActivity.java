package com.example.causefairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firestore.v1beta1.TargetOrBuilder;

public class MainActivity extends AppCompatActivity {

    //Button btnLogout;

    EditText etEmail, etPass;
    Button btnLogin;
    TextView tvReg;
    // TextView tvfrgtPass;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        //btnLogout = findViewById(R.id.btnLogout);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        tvReg = findViewById(R.id.tvReg);
        btnLogin = findViewById(R.id.btnLogin);
        //tvfrgtPass = findViewById(R.id.tvfrgtPass);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                {
                    Intent d = new Intent(MainActivity.this, SplashScreen.class);
                    startActivity(d);
                }
            }
        };
      /*  tvfrgtPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(MainActivity.this, Reset_Password.class);
                startActivity(f);
            }
        });
	*/
        btnLogin.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            String email = etEmail.getText().toString();
                                            String pass = etPass.getText().toString();

                                            if (email.isEmpty()) {
                                                etEmail.setError("Email is Required");
                                                etEmail.requestFocus();
                                            } else if (pass.isEmpty()) {
                                                etPass.setError("Please enter Password");
                                                etPass.requestFocus();
                                            }
                                            //  else if (email.isEmpty() && pass.isEmpty()) {
                                            //       Toast.makeText(MainActivity.this, "Please enter your Login details", Toast.LENGTH_SHORT).show();
                                            //  }
/*    else  {
mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
@Override
public void onComplete(@NonNull Task<AuthResult> task) {
if (!task.isSuccessful()) {
    Toast.makeText(MainActivity.this, "SignUp Was Unsuccessful But at least GIT worked!", Toast.LENGTH_SHORT).show();
} else {
    startActivity(new Intent(MainActivity.this, HomePage.class));
}
}
});
}*/
                                            else {
                                                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (!task.isSuccessful()) {
                                                            Toast.makeText(MainActivity.this, "Sign In Problem", Toast.LENGTH_LONG).show();
                                                        } else {
                                                            FirebaseUser user = mAuth.getCurrentUser();
                                                            Toast.makeText(MainActivity.this, "User Signed in", Toast.LENGTH_SHORT).show();
                                                            Intent l = new Intent(MainActivity.this, HomePage.class);
                                                            startActivity(l);

                                                        }
                                                    }
                                                });

                                                /*else if (email.equals("noumanejaz828@gmail.com") && pass.equals("1234")) {
                    Intent d = new Intent(MainActivity.this, HomePage.class);
                    startActivity(d);
                } */
                                                //Toast.makeText(MainActivity.this, "Incorrect Username or Password!", Toast.LENGTH_SHORT).show();

                                            }
                                        }
    });
                    tvReg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, SignUp.class);
                            startActivity(i);
                        }
                    });
       /* btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
               // Intent i = new Intent(MainActivity.this, MainActivity.class); //hack
                //startActivity(i);
            }
        });*/
                }
}

