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
    -Conoce un objeto de la clase VentanaJuego.
    -Conoce un objeto de la clase VentanaEstadisticas.
    -Contiene un objeto de la clase Sonido.
*/

package co.edu.univalle.logica;

import co.edu.univalle.vista.VentanaJuego;
import java.awt.Color;
import java.util.*;

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
    private int tipoDeCondicion; 
    private String[] simbolos = {"♠","♣", "♥", "♦"};
    // Negro, naranja-palido, vinotinto, lima.
    private Color[] colores = {new Color(0, 0, 0), new Color(0, 0, 255), new Color(153, 0, 76), new Color(255, 255, 51)};
    private int contadorSimbolosCondicion = 0;
    private int verificarSimbolosCondicion = 0;
    private int rondaAsociada = 0;
    private int numeroAciertos = 0;
    private Sonido controladorSonido;
    private Timer timer = new Timer();
    private int tiempoDeEsperaAciertoFallo = 2000; // 2 segundos

    
    // Contructor:
    public Juego(int rondaAsociada, VentanaJuego ventanaAsociada) {
        this.ventanaAsociada = ventanaAsociada;
        palabraAAdivinar = simbolos[random.nextInt(4)];
        colorAAdividar = getRandomColor();
        this.rondaAsociada = rondaAsociada;
        this.nombreDelJugador = nombreDelJugador;
        this.controladorSonido = new Sonido(this.ventanaAsociada);
    }
    
    //Métodos:
    public Color getRandomColor() {
        return colores[random.nextInt(4)];
    }

    public void nuevaRonda() {
        tipoDeCondicion = random.nextInt(3); //0: Símbolo, 1: Color, 2: Color y Símbolo.
        palabraAAdivinar = simbolos[random.nextInt(4)];
        colorAAdividar = colores[random.nextInt(4)];
        if(tipoDeCondicion == 0){
            colorAAdividar = colores[0];
        }
        rondaAsociada += 1;
        contadorSimbolosCondicion = 0;
    }

    public void aumentarContadorCondicion(){
        contadorSimbolosCondicion++;
    }

    public int getTipoDeCondicion(){
        return tipoDeCondicion;
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
        // Att. Juan Camilo: siento que el presente algoritmo puede ser depurado.
        if(tipoDeCondicion == 0){
            if(simbolo1 == palabraAAdivinar){
                verificarSimbolosCondicion++;
                numeroAciertos++;
                if(verificarSimbolosCondicion == contadorSimbolosCondicion) {
                    sumarPuntos();
                    verificarSimbolosCondicion = 0;
                    return true;
                }
                return false;
            } else {
                restarVida();
                verificarSimbolosCondicion = 0;
                return false;
            }
        } else if(tipoDeCondicion == 1){
            if(color == colorAAdividar){
                verificarSimbolosCondicion++;
                numeroAciertos++;
                if(verificarSimbolosCondicion == contadorSimbolosCondicion) {
                    sumarPuntos();
                    verificarSimbolosCondicion = 0;
                    return true;
                }
                
                return false;
            } else {
                restarVida();
                verificarSimbolosCondicion = 0;
                return false;
            }
        }else if(tipoDeCondicion == 2){
            if(simbolo1 == palabraAAdivinar && color == colorAAdividar){
                verificarSimbolosCondicion++;
                numeroAciertos++;
                if(verificarSimbolosCondicion == contadorSimbolosCondicion) {
                    sumarPuntos();
                    verificarSimbolosCondicion = 0;
                    return true;
                }
                return false;
            } else {
                restarVida();
                verificarSimbolosCondicion = 0;
                return false;
            }
        }
        return false;
    }

    public String getVidas(){
        return vidas.toString();
    }

    public String getPuntos(){
        stringPuntuacion = "Puntuación: " + puntuacion;
        return stringPuntuacion.toString();
    }

    public int getNumeroAciertos(){
        return numeroAciertos;
    }

    public int getPuntuacion(){
        return puntuacion;
    }

    private void sumarPuntos(){
        puntuacion += 100;
        ventanaAsociada.actualizarPuntos();
        controladorSonido.reproducirSonido("./src/co/edu/univalle/vista/sonidos/acierto.wav");
        ventanaAsociada.desactivarListeners();

        TimerTask task0 = new TimerTask() {
            @Override
            public void run(){
                controladorSonido.pararSonido();
                ventanaAsociada.actualizarCasillas();
                ventanaAsociada.actualizarColores();
            }
        };
        timer.schedule(task0, tiempoDeEsperaAciertoFallo);
    }
    
    private void restarVida(){
        if(vidas.length() > 0) {
            vidas.deleteCharAt(vidas.length()-1);
            ventanaAsociada.actualizarVidas();
            rondaAsociada -= 1; // Evita que aumente la dificultad al perder vidas.
            controladorSonido.reproducirSonido("./src/co/edu/univalle/vista/sonidos/fallo.wav");
            ventanaAsociada.desactivarListeners();

            TimerTask task0 = new TimerTask() {
                @Override
                public void run(){
                    controladorSonido.pararSonido();

                    if (vidas.length() == 0){
                        gameOver();
                        return;
                    }

                    ventanaAsociada.actualizarCasillas();
                    ventanaAsociada.actualizarColores();
                }
            };
            timer.schedule(task0, tiempoDeEsperaAciertoFallo);
        }
    }

    public void gameOver(){
        ventanaAsociada.gameOver();
    }
 }