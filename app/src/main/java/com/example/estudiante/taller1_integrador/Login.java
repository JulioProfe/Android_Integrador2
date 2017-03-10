package com.example.estudiante.taller1_integrador;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by estudiante on 10/03/17.
 */

public class Login extends AppCompatActivity implements Observer {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
