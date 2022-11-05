/*
    Archivo: Juego.java
    Fundamentos de Programación Orientada a Eventos - 750014C Grupo 01
    Proyecto 1 - El ahorcado

    Autores: 
    Juan Camilo Narvaez Tascon - juan.narvaez.tascon@correounivalle.edu.co - 2140112-3743
    Christian David Vargas Gutiérrez - vargas.christian@correounivalle.edu.co - 2179172-3743

    Profesor:
    Ing. M.Sc. Luis Yovany Romo Portilla

    Licencia: GNU-GPL
*/

/**
    CLASE: Juego
    INTENCIÓN: Esta clase se encargará de implementar la lógica del juego ahorcado.
    RELACIONES:
    -Conoce un jugador.
*/

package co.edu.univalle.logica;
// import java.util.Map.Entry;

import co.edu.univalle.vista.VentanaJuego;

import java.util.*;

public class Juego {
    //Atributos:
    private VentanaJuego ventanaAsociada;
    private StringBuilder vidas = new StringBuilder("♥♥♥");
    private int puntuacion = 0;
    private String stringPuntuacion;
    private String palabraAAdivinar;
    private Random random = new Random();
    private String nombreDelJugador;
    private String[] simbolos = {"♠","♣", "♥", "♦"}; 

    private int rondaAsociada = 0;
    
    //Métodos:
    public Juego(int rondaAsociada, VentanaJuego ventanaAsociada) {
        this.ventanaAsociada = ventanaAsociada;
        palabraAAdivinar = simbolos[random.nextInt(4)];
        this.rondaAsociada = rondaAsociada;
        this.nombreDelJugador = nombreDelJugador;
    }

    public void nuevaRonda() {
        palabraAAdivinar = simbolos[random.nextInt(4)];
        rondaAsociada += 1;
    }

    public int getRondaAsociada(){
        return rondaAsociada;
    }

    public String getSimboloRonda() {
        return palabraAAdivinar;
    }

    public Boolean aciertoSimbolo(String simbolo1){
        if(simbolo1 == palabraAAdivinar){
            sumarPuntos();
            return true;
        } else {
            restarVida();
            return false;
        }
    }

    public String getVidas(){
        return vidas.toString();
    }

    public String getPuntos(){
        stringPuntuacion = "Puntuación: " + puntuacion;
        return stringPuntuacion.toString();
    }

    private void sumarPuntos(){
        puntuacion += 100;
        ventanaAsociada.actualizarPuntos();
        ventanaAsociada.actualizarCasillas();
    }
    
    private void restarVida(){
        if(vidas.length() > 0) {
            vidas.deleteCharAt(vidas.length()-1);
            ventanaAsociada.actualizarVidas();
            ventanaAsociada.actualizarCasillas();
        } else {
            System.out.println("No hay más vidas");// Texto de depuración. !!!!!!!!!!
        }
    }
 }