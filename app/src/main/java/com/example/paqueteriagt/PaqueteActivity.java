package com.example.paqueteriagt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muddzdev.styleabletoast.StyleableToast;
import com.skydoves.elasticviews.ElasticCheckButton;

public class PaqueteActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ElasticCheckButton consultarLatLon, btnGuardar;
    EditText edtLat, edtLong, edtcodigo, edtbillete, edttelefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paquete);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo);

        databaseReference = FirebaseDatabase.getInstance().getReference();


        consultarLatLon = findViewById(R.id.botton);
        btnGuardar = findViewById(R.id.btnEnviar);
        edtLat = findViewById(R.id.txtLat);
        edtLong = findViewById(R.id.txtPedido);
        edtcodigo = findViewById(R.id.txtNo);
        edtbillete = findViewById(R.id.txtbillete);
        edttelefono = findViewById(R.id.txtTel);

        getLocalizacion();
        getCargarLocalizacion();
        guardarDatos();
    }

    private void guardarDatos() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitud = edtLat.getText().toString().trim();
                String longitud = edtLong.getText().toString().trim();
                String codigo = edtcodigo.getText().toString().trim();
                String billete = edtbillete.getText().toString().trim();
                String telefono = edttelefono.getText().toString().trim();

                if (TextUtils.isEmpty(latitud)) {

                    StyleableToast.makeText(PaqueteActivity.this, "Por favor pulsar el boton generar ubucacion", Toast.LENGTH_LONG, R.style.ColoredBackground).show();
                } else if (TextUtils.isEmpty(longitud)) {
                    StyleableToast.makeText(PaqueteActivity.this, "Por favor pulsar el boton generar ubucacion", Toast.LENGTH_LONG, R.style.ColoredBackground).show();

                } else if (TextUtils.isEmpty(codigo)) {
                    StyleableToast.makeText(PaqueteActivity.this, "Por favor ingresar un codigo", Toast.LENGTH_LONG, R.style.ColoredBackground).show();
                } else if (TextUtils.isEmpty(billete)) {
                    StyleableToast.makeText(PaqueteActivity.this, "Por favor ingresar un billete ", Toast.LENGTH_LONG, R.style.ColoredBackground).show();

                } else if (TextUtils.isEmpty(telefono)) {
                    StyleableToast.makeText(PaqueteActivity.this, "Por favor ingresar un telefono", Toast.LENGTH_LONG, R.style.ColoredBackground).show();

                } else {
                    Destinos destinos = new Destinos(Double.valueOf(latitud), Double.valueOf(longitud), codigo, billete, telefono);

                    databaseReference.child("destinos").child(codigo).setValue(destinos);
                    StyleableToast.makeText(PaqueteActivity.this, "Datos enviados correctamente", Toast.LENGTH_LONG, R.style.ColoredBackground).show();

                    Intent i = new Intent(PaqueteActivity.this, MenuActivity.class);
                    startActivity(i);
                    finish();

                }


            }
        });

    }


    private void getCargarLocalizacion() {

        consultarLatLon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) PaqueteActivity.this.getSystemService(Context.LOCATION_SERVICE);

                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {

                        edtLat.setText("" + location.getLatitude());
                        edtLong.setText("" + location.getLongitude());

                    }
                };
                int permiso = ContextCompat.checkSelfPermission(PaqueteActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                StyleableToast.makeText(PaqueteActivity.this, "Ubicacion generada con exito!!", Toast.LENGTH_LONG, R.style.ColoredBackground).show();
            }
        });

    }


    private void getLocalizacion() {

        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (permiso == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }

        }

    }
}