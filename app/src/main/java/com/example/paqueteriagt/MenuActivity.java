package com.example.paqueteriagt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MenuActivity extends AppCompatActivity {

    CardView paquete, admin, ofertas, condicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo);

        //castear las variables
        paquete = findViewById(R.id.card1);
        admin = findViewById(R.id.card2);
        ofertas = findViewById(R.id.card3);
        condicion = findViewById(R.id.card4);

        //llamando al paquete
        getPaquete();
        getOferta();
        getAdmin();
        getCondicion();

    }


    private void getPaquete() {

        paquete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, PaqueteActivity.class);
                startActivity(i);
            }
        });
    }

    private void getCondicion() {

        condicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, CondicionActivity.class);
                startActivity(i);
            }
        });

    }


    private void getAdmin() {

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, AdminActivity.class);
                startActivity(i);
            }
        });
    }

    private void getOferta() {

        ofertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this,OfertasActivity.class);
                startActivity(i);
            }
        });
    }
}