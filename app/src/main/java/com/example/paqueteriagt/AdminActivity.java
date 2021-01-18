package com.example.paqueteriagt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.muddzdev.styleabletoast.StyleableToast;
import com.skydoves.elasticviews.ElasticCheckButton;

import dmax.dialog.SpotsDialog;

public class AdminActivity extends AppCompatActivity {

    ElasticCheckButton recuperar,btnenviar;
    TextInputEditText gmail,password;
    private FirebaseAuth mAuth;

    AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo);


        recuperar = findViewById(R.id.recuperar);
        btnenviar = findViewById(R.id.btnEnviar);

        gmail = findViewById(R.id.gmail);
        password = findViewById(R.id.pass);

        alerta = new SpotsDialog.Builder().setContext(AdminActivity.this).setMessage("Por Favor espere...").build();


        getRecuperar();
        loginAdmin();






    }

    private void limpiar() {
        gmail.setText("");
        password.setText("");

    }

    private void loginAdmin() {

        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userE = gmail.getText().toString().trim();
                String passE = password.getText().toString().trim();

                if(TextUtils.isEmpty(userE)){
                    StyleableToast.makeText(AdminActivity.this, "Ingrese un usuario!", Toast.LENGTH_LONG, R.style.ColoredBackground).show();
                }else if(TextUtils.isEmpty(passE)){
                    StyleableToast.makeText(AdminActivity.this, "Ingrese una Contraseña", Toast.LENGTH_LONG, R.style.ColoredBackground).show();
                }else{
                    alerta.show();
                    mAuth.signInWithEmailAndPassword(userE,passE).addOnCompleteListener(AdminActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                StyleableToast.makeText(AdminActivity.this, "Credenciales Incorrectas", Toast.LENGTH_LONG, R.style.ColoredBackground).show();
                            }else{
                                limpiar();
                                Intent i = new Intent(AdminActivity.this,MenuActivity.class);
                                startActivity(i);
                                alerta.dismiss();
                            }

                        }
                    });
                }
            }
        });
    }

    private void getRecuperar() {
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this ,RecuperarActivity.class);
                startActivity(i);
            }
        });
    }
}