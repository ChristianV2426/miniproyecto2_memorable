/*
    Archivo: Casilla.java
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
    CLASE: Casilla
    INTENCIÓN: Esta clase se comporta como un objeto para cada casilla pintada, y manipula los atributos internos.
    RELACIONES: conoce a Juego.
*/

package co.edu.univalle.logica;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class Casilla implements MouseListener{
    static private Casilla casillas[] = new Casilla[36];
    private String[] simbolos = {"♠","♣", "♥", "♦", ""}; 
    private String simbolo = new String();
    private Color colorPintadoSimbolo;
    static private int contadorId = 0;
    private Random random = new Random();
    static private int simbolosPintados = 0;
    static private Boolean correcionPorcentaje = true;
    static private Boolean simboloCondicionImpreso = false;
    static private int probabilidadImpresion = 30;
    private JPanel recuadro = new JPanel();
    private JLabel labelSymbolo = new JLabel();
    private Color colorPintadoLabel;
    static private int controladorDificultad = 1;
    static private int numeroSimbolosAPintar = 2;
    private JLabel recuadroLabel;
    private JPanel recuadroPanel;
    private int tipoDeCondicion;
    private Color colorJpanel;
    
    /*  El tener un registro de qué ronda se está jugando
    permitirá ir aumentando la dificultad. */ 
    static private Juego pruebaJuego;
    
    public Casilla(Juego pruebaJuego){
        this.pruebaJuego = pruebaJuego; 
        
        /* Con base en el número de casillas (36), y el número de elementos que pueden ser
        * pintados (4), se considera el número 30 como una buena estimación probabilística 
        * para el correcto funcinamiento de los símbolos pintados. */
        int simboloAPintar = random.nextInt(probabilidadImpresion); // Funciona como valor probabilístico.
        
        if (contadorId < 36){
            // Se asegura de que por lo menos se pinte una vez.
            if(simbolosPintados != numeroSimbolosAPintar-1 && contadorId > 9 && correcionPorcentaje) {
                correcionPorcentaje = false;
                probabilidadImpresion = 6;
            }
            // Se asignan los símbolos a cada casilla.
            if (simboloAPintar >= 0 && simboloAPintar <= 3 && simbolosPintados < numeroSimbolosAPintar) {
                colorPintadoSimbolo = pruebaJuego.getRandomColor();
                setColorPintado(colorPintadoSimbolo);
                tipoDeCondicion = pruebaJuego.getTipoDeCondicion();
                labelSymbolo.setForeground(colorPintadoSimbolo);
                // Att. Juan Camilo: siento que el presente algoritmo puede ser depurado.
                simbolo = simbolos[simboloAPintar];
                if(tipoDeCondicion == 0){
                    if (simbolo == pruebaJuego.getSimboloRonda()) {
                        simboloCondicionImpreso = true;
                        pruebaJuego.aumentarContadorCondicion();
                    }
                } else if(tipoDeCondicion == 1){
                    if (colorPintadoSimbolo == pruebaJuego.getColorRonda()) {
                        simboloCondicionImpreso = true;
                        pruebaJuego.aumentarContadorCondicion();
                    } 
                }else if(tipoDeCondicion == 2){
                    if ((simbolo == pruebaJuego.getSimboloRonda()) && (colorPintadoSimbolo == pruebaJuego.getColorRonda())) {
                        simboloCondicionImpreso = true;
                        pruebaJuego.aumentarContadorCondicion();
                    }
                }
                if (simboloCondicionImpreso == false && simbolosPintados == numeroSimbolosAPintar-1) {
                    /* Si no se ha asignado el símbolo de condición, y ya estamos en la última casilla, entonces 
                    * asignelo a esa última casilla */
                    labelSymbolo.setForeground(pruebaJuego.getColorRonda());
                    simbolo = pruebaJuego.getSimboloRonda();
                    setColorPintado(pruebaJuego.getColorRonda());
                    pruebaJuego.aumentarContadorCondicion();
                } else if (simboloCondicionImpreso == false && simbolosPintados != numeroSimbolosAPintar-1 && contadorId == 34) {
                    /* Esta condición se ejerce a paortir de puntuaciones grandes, cuando los simbolosPintados no llegan a ser 
                     * iguales a numeroSimbolosAPintar-1 */
                    labelSymbolo.setForeground(pruebaJuego.getColorRonda());
                    simbolo = pruebaJuego.getSimboloRonda();
                    setColorPintado(pruebaJuego.getColorRonda());
                    pruebaJuego.aumentarContadorCondicion();
                }    
                simbolosPintados++;

            } else {
                simbolo = simbolos[4];
            } // ¿y si hay una probabilidad de 2/3?
    
            casillas[contadorId] = this;
            contadorId++;
        }
    }
    
    // Métodos.
    public void retirarMouseListener(){
        recuadro.removeMouseListener(this);
    }

    public JPanel pintar() {
        labelSymbolo.setText(simbolo);
        recuadro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        recuadro.setPreferredSize(new Dimension(50, 50));
        labelSymbolo.setHorizontalAlignment(JLabel.CENTER);
        labelSymbolo.setFont(new Font("Arial", Font.PLAIN, 36));
        recuadro.setLayout(new GridLayout(1, 1));
        colorJpanel = new Color(0, 165, 181);
        recuadro.setBackground(colorJpanel);
        recuadro.addMouseListener(this);
        recuadro.add(labelSymbolo);
        recuadroLabel = labelSymbolo;
        recuadroPanel = recuadro;
        return recuadro;
    }
    
    public void resetCasillas(){
        contadorId = 0;
        simbolosPintados = 0;
        correcionPorcentaje = true;
        simboloCondicionImpreso = false;
        probabilidadImpresion = 30;
    }
    
    public String getSimbolo() {
        return simbolo;
    }
    
    public Color getColor() {
        return colorPintadoLabel;
    }

    private void setColorPintado(Color color) {
        colorPintadoLabel = color;
    }
    
    private void confirmarDificultad(){
        // Cada 3 aciertos de ronda se aumentará un símbolo.
        if (controladorDificultad == 3) {
            numeroSimbolosAPintar += 1;
            controladorDificultad = 0;
        }
    }

    public JPanel getJpanel(){
        return recuadroPanel;
    }

    public Color getColorJpanel(){
        return colorJpanel;
    }

    public JLabel getJlabel(){
        return recuadroLabel;
    }

    public Casilla[] getCasillas(){
        return casillas;
    }

    public void reiniciarDificultad(){
        controladorDificultad = 1;
        numeroSimbolosAPintar = 2;
    }

    public void comprobar() {
        resetCasillas();
        pruebaJuego.getSimboloRonda();

        if(pruebaJuego.aciertoSimbolo(getSimbolo(), getColor())){
            controladorDificultad += 1;
            confirmarDificultad();
            colorJpanel = new Color(0, 204, 0);
        } else if(tipoDeCondicion == 0 && simbolo == pruebaJuego.getSimboloRonda()){
            colorJpanel = new Color(0, 204, 0);
        } else if(tipoDeCondicion == 1 && colorPintadoSimbolo == pruebaJuego.getColorRonda()){
            colorJpanel = new Color(0, 204, 0);
        }else if(tipoDeCondicion == 2 && (simbolo == pruebaJuego.getSimboloRonda()) && (colorPintadoSimbolo == pruebaJuego.getColorRonda())){
            colorJpanel = new Color(0, 204, 0);
        } else {
            colorJpanel = new Color(204, 0, 0);
        }
        recuadroLabel.setVisible(true);
        recuadro.setBackground(colorJpanel);
        recuadro.removeMouseListener(this);
        recuadro.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    // Mouse Listener
    @Override
    public void mouseClicked(MouseEvent e) {
        comprobar();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
