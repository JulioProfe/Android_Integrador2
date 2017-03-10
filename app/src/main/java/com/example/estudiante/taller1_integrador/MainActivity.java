package com.example.estudiante.taller1_integrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.net.InetAddress;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer{

    EditText nombre;
    EditText pass;
    Button inicio;
    Button registro;
    String ipServidor;
    String getNombre;
    String getContraseña;
    Usuario usu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre= (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.contra);
        inicio= (Button) findViewById(R.id.BotonIS);
        registro= (Button) findViewById(R.id.BotonR);

        Comunicacion.getInstance("").addObserver(this);

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
