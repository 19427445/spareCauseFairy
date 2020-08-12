package com.example.causefairy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register_Business extends AppCompatActivity {
    TextView tvlog;
    Button btnSub, btnIndividual, bntBusiness;
    EditText etBusNam, etAbn, etBusEmail, etPassword, etConPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__business);
        tvlog = findViewById(R.id.tvLog);
        btnSub = findViewById(R.id.btnSub);
        etBusNam = findViewById(R.id.etBusNam);
        etAbn = findViewById(R.id.etAbn);
        etBusEmail = findViewById(R.id.etBusEmail);
        etPassword = findViewById(R.id.etPassword);
        etConPassword = findViewById(R.id.etConPassword);
        btnIndividual = findViewById(R.id.btnIndividual);
        bntBusiness = findViewById(R.id.btnBusiness);

        btnIndividual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bus = new Intent(Register_Business.this, SignUp.class);
                startActivity(bus);
            }
        });

        tvlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(Register_Business.this, MainActivity.class);
                startActivity(login);
            }
        });

        bntBusiness.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
              Toast.makeText(Register_Business.this, "You are already on Business Registration Page", Toast.LENGTH_LONG).show();
          }
       });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busName, busEmail, abn, pass, ConPass;
                 busName = etBusNam.getText().toString();
                 abn = etAbn.getText().toString();
                 busEmail = etBusEmail.getText().toString();
                 pass = etPassword.getText().toString();
                 ConPass = etConPassword.getText().toString();

                if(busName.equals(""))
                {
                    Toast.makeText(Register_Business.this, "Business Name is required", Toast.LENGTH_SHORT).show();
                }
                else if(abn.equals(""))
                {
                    Toast.makeText(Register_Business.this, "Abn is required", Toast.LENGTH_SHORT).show();
                }
                else if(busEmail.equals(""))
                {
                    Toast.makeText(Register_Business.this, "Email is required", Toast.LENGTH_SHORT).show();
                }
                else if (pass.equals(""))
                {
                    Toast.makeText(Register_Business.this, "Password is required", Toast.LENGTH_SHORT).show();
                }
                else if(ConPass.equals(""))
                {
                    Toast.makeText(Register_Business.this, "Confirm Password is required", Toast.LENGTH_SHORT).show();
                }
                else if(!ConPass.equals(pass))
                {
                    Toast.makeText(Register_Business.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent w = new Intent(Register_Business.this, HomePage.class);
                    startActivity(w);
                }
            }
        });

    }
}