package com.example.estudiante.taller1_integrador;

import java.io.Serializable;

/**
 * Created by estudiante on 10/03/17.
 */

public class Usuario implements Serializable {

    public String correo, contraseña, name;
    public boolean registrado;

    public Usuario(String correo, String contraseña, String name, boolean registrado) {
        this.contraseña = contraseña;
        this.correo = correo;
        this.name = name;
        this.registrado = registrado;
    }

}
