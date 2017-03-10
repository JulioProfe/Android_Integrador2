package com.example.estudiante.taller1_integrador;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by estudiante on 10/03/17.
 */

public class Comunicacion extends Observable implements Runnable {

    DatagramSocket ds;
    private InetAddress ip;
    private final int Puerto = 5000;
    private ArrayList<Usuario> mensajes;
    private static Comunicacion ref;
    String servidor;

    private Comunicacion(String ipS) {
        this.mensajes = new ArrayList<Usuario>();

        this.servidor = ipS;
        try {
            ip = InetAddress.getByName(servidor);
            ds = new DatagramSocket(Puerto);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Comunicacion getInstance(String ipS){
        if (ref == null){
            ref = new Comunicacion(ipS);
            Thread hilo = new Thread(ref);
            hilo.start();
        }
        return ref;
    }

    @Override
    public void run() {
        while (true){
            recibir();
        }
    }

    private byte[] serializar(Object m){

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(m);
            oos.flush();
            oos.close();
            return baos.toByteArray();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }


    private Object deserializar(byte[] data) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object aux =  ois.readObject();
            return aux;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void recibir(){

        try {
            byte[] buffer = new byte[1024];
            DatagramPacket dPacket = new DatagramPacket(buffer, buffer.length);
            ds.receive(dPacket);

            Object recibido = deserializar(dPacket.getData());
            setChanged();
            notifyObservers(recibido);
            clearChanged();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviar(Object ob){

        try {
            byte[] data = serializar(ob);
            DatagramPacket packet = new DatagramPacket(data, data.length, ip, Puerto);
            ds.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
