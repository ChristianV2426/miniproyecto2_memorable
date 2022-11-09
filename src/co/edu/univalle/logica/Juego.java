/*
    Archivo: Juego.java
    Fundamentos de Programación Orientada a Eventos - 750014C Grupo 01
    Proyecto 2 - Memorable

    Autores: 
    Juan Camilo Narvaez Tascon - juan.narvaez.tascon@correounivalle.edu.co - 2140112-3743
    Christian David Vargas Gutiérrez - vargas.christian@correounivalle.edu.co - 2179172-3743

    Profesor:
    Ing. M.Sc. Luis Yovany Romo Portilla

    Licencia: GNU-GPL
*/

/**
    CLASE: Juego
    INTENCIÓN: Esta clase se encargará de implementar la lógica del juego Memorable.
    RELACIONES:
*/

package co.edu.univalle.logica;
// import java.util.Map.Entry;

import co.edu.univalle.vista.VentanaJuego;

import java.awt.Color;
import java.util.*;

import javax.swing.text.AttributeSet.ColorAttribute;
import javax.swing.text.StyleConstants.ColorConstants;

public class Juego {
    //Atributos:
    private VentanaJuego ventanaAsociada;
    private StringBuilder vidas = new StringBuilder("♥♥♥");
    private int puntuacion = 0;
    private String stringPuntuacion;
    private String palabraAAdivinar;
    private Color colorAAdividar;
    private Random random = new Random();
    private String nombreDelJugador;
    private String[] simbolos = {"♠","♣", "♥", "♦"};
    // Negro, morado, verde, azul.
    private Color[] colores = {new Color(0,0,0), new Color(148,41,255), new Color(29,217,9), new Color(57,62,219)};
    private int contadorSimbolosCondicion = 0;
    private int verificarSimbolosCondicion = 0;
    private int rondaAsociada = 0;
    
    //Métodos:
    public Juego(int rondaAsociada, VentanaJuego ventanaAsociada) {
        this.ventanaAsociada = ventanaAsociada;
        palabraAAdivinar = simbolos[random.nextInt(4)];
        colorAAdividar = getRandomColor();
        this.rondaAsociada = rondaAsociada;
        this.nombreDelJugador = nombreDelJugador;
    }

    public Color getRandomColor() {
        return colores[random.nextInt(4)];
    }

    public void nuevaRonda() {
        palabraAAdivinar = simbolos[random.nextInt(4)];
        colorAAdividar = colores[random.nextInt(4)];
        rondaAsociada += 1;
        contadorSimbolosCondicion = 0;
    }

    public void aumentarContadorCondicion(){
        contadorSimbolosCondicion++;
        
    }

    public int getRondaAsociada(){
        return rondaAsociada;
    }

    public String getSimboloRonda() {
        return palabraAAdivinar;
    }

    public Color getColorRonda() {
        return colorAAdividar;
    }

    public Boolean aciertoSimbolo(String simbolo1, Color color){
        if(simbolo1 == palabraAAdivinar && color == colorAAdividar){
            verificarSimbolosCondicion++;
            System.out.println("contadorSimbolosCondicion: " + contadorSimbolosCondicion);
            if(verificarSimbolosCondicion == contadorSimbolosCondicion) {
                sumarPuntos();
                verificarSimbolosCondicion = 0;
            }
            return true;
        } else {
            restarVida();
            verificarSimbolosCondicion = 0;
            return true;
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
        ventanaAsociada.actualizarColores();
    }
    
    private void restarVida(){
        if(vidas.length() > 0) {
            vidas.deleteCharAt(vidas.length()-1);
            ventanaAsociada.actualizarVidas();
            rondaAsociada -= 1; // Evita que aumente la dificultad al perder vidas.
            ventanaAsociada.actualizarCasillas();
            ventanaAsociada.actualizarColores();
        } else {
            System.out.println("No hay más vidas");// Texto de depuración. !!!!!!!!!!
        }
    }
 }