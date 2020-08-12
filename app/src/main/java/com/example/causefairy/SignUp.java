package com.example.causefairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    private static final String TAG = "SignUp";

    private static final String KEY_NAME1 = "name1";
    private static final String KEY_NAME2 = "name2";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_CONPASS = "conpass";

     EditText etEmail, etName1, etName2, etPass, etConPass;

    Button btnSignUp, btnInd, btnBus;
    TextView tvLogin;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName1 = findViewById(R.id.etName1);
        etName2 = findViewById(R.id.etName2);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        etConPass = findViewById(R.id.etConPass);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvLogin = findViewById(R.id.tvLogin);
        btnInd = findViewById(R.id.btnInd);
        btnBus = findViewById(R.id.btnBus);

        btnInd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUp.this, "You are already on Individual Registration Page", Toast.LENGTH_LONG).show();
          }
        });

        btnBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bus = new Intent(SignUp.this, Register_Business.class);
                startActivity(bus);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent log = new Intent(SignUp.this, MainActivity.class);
                startActivity(log);
            }
       });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1, email, name2, pass;
                name1 = etName1.getText().toString();
                name2 = etName2.getText().toString();
                email = etEmail.getText().toString();
                pass = etPass.getText().toString();
                String conPass = etConPass.getText().toString();

                if (name1.equals("")) {
                    Toast.makeText(SignUp.this, "First Name Required", Toast.LENGTH_SHORT).show();
                } else if (name2.equals("")) {
                    Toast.makeText(SignUp.this, " Last Name Required", Toast.LENGTH_SHORT).show();
                } else if (email.equals("")) {
                    Toast.makeText(SignUp.this, "Email Required", Toast.LENGTH_SHORT).show();
                } else if (pass.equals("")) {
                    Toast.makeText(SignUp.this, "Password Required", Toast.LENGTH_SHORT).show();
                } else if (conPass.equals("")) {
                    Toast.makeText(SignUp.this, "Confirm Password Required", Toast.LENGTH_SHORT).show();
                } else if (!conPass.equals(pass)) {
                    Toast.makeText(SignUp.this, "Passwords Don't Match", Toast.LENGTH_SHORT).show();
                } else {
                    Intent l = new Intent(SignUp.this, HomePage.class);
                    startActivity(l);
                }
            }
        });
    }

    public void saveUser(View v) {
        String name1 = etName1.getText().toString();
        String name2 = etName2.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();
        String conpass = etConPass.getText().toString();

        Map<String, Object> user = new HashMap<>();
        user.put(KEY_NAME1, name1);
        user.put(KEY_NAME2, name2);
        user.put(KEY_EMAIL, email);
        user.put(KEY_PASSWORD, password);
        user.put(KEY_CONPASS, conpass);


        db.collection("SavedUserList").document("Saved User 1").set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void aVoid) {
                      Toast.makeText(SignUp.this, "User Saved", Toast.LENGTH_SHORT).show();
                  }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e){
                        Toast.makeText(SignUp.this, "Erroooor!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
              });


    }
}