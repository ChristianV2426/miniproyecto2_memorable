package co.edu.univalle.logica;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class Casilla implements MouseListener{
    private String[] simbolos = {"♠","♣", "♥", "♦", ""}; 
    private String simbolo = new String();
    static private int contadorId = 0;
    private Random random = new Random();
    static private int contadorNivelacion = 0;
    static Boolean correcionPorcentaje = true;
    static Boolean simboloCondicionImpreso = false;
    static int probabilidadImpresion = 30;

    /*  El tener un registro de qué ronda se está jugando
    permitirá ir aumentando la dificultad. */ 
    static private Juego pruebaJuego;

    public Casilla(Juego pruebaJuego){
        this.pruebaJuego = pruebaJuego;
        int numeroSimbolosAPintar = pruebaJuego.getRondaAsociada()+1;
        int rondaAsociada = pruebaJuego.getRondaAsociada();
        /* Con base en el número de casillas (36), y el número de elementos que pueden ser
         * pintados (4), se considera el número 30 como una buena estimación probabilística 
         * para el correcto funcinamiento de los símbolos pintados. */
        int simboloAPintar = random.nextInt(probabilidadImpresion); // Funciona como valor probabilístico.

        if (contadorId < 36){
            // Se asegura de que por lo menos se pinte una vez.
            if(contadorNivelacion != numeroSimbolosAPintar-1 && contadorId > 17 && correcionPorcentaje) {
                correcionPorcentaje = false;
                probabilidadImpresion = 6;
            }
            // Se asignan los símbolos a cada casilla.
            if (simboloAPintar >= 0 && simboloAPintar <= 3 && contadorNivelacion < numeroSimbolosAPintar) {
                simbolo = simbolos[simboloAPintar];
                if (simbolo == pruebaJuego.getSimboloRonda()) {
                    simboloCondicionImpreso = true;
                } else if (simboloCondicionImpreso == false && contadorNivelacion == numeroSimbolosAPintar-1) {
                    /* Si no se ha asignado el símbolo de condición, y ya estamos en la última casilla, entonces 
                     * asignelo a esa última casilla */
                    simbolo = pruebaJuego.getSimboloRonda();
                }
                contadorNivelacion++;
            } else {
                simbolo = simbolos[4];
            }
            // System.out.println("Símbolo asignado: " + simbolo); // Texto de depuración. !!!!!!!!!!

            contadorId++;
        }
    }

    // Métodos.
    public JPanel pintar() {
        JPanel recuadro = new JPanel();
        JLabel labelSymbolo = new JLabel(simbolo);
        recuadro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        recuadro.setPreferredSize(new Dimension(50, 50));
        labelSymbolo.setHorizontalAlignment(JLabel.CENTER);
        labelSymbolo.setFont(new Font("Arial", Font.PLAIN, 36));
        recuadro.setLayout(new GridLayout(1, 1));
        recuadro.setBackground(new Color(0, 165, 181));
        recuadro.addMouseListener(this);
        recuadro.add(labelSymbolo);
        return recuadro;
    }

    public void resetCasillas(){
        contadorId = 0;
        contadorNivelacion = 0;
        correcionPorcentaje = true;
        simboloCondicionImpreso = false;
        probabilidadImpresion = 30;
    }

    public String getSimbolo() {
        return simbolo;
    }

    // Mouse Listener
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        resetCasillas();
        pruebaJuego.getSimboloRonda();
        pruebaJuego.aciertoSimbolo(getSimbolo());
        // System.out.println("Símbolo presionado: " + getSimbolo());// Texto de depuración. !!!!!!!!!!
        // System.out.println("Símbolo condición: " + pruebaJuego.getSimboloRonda());// Texto de depuración. !!!!!!!!!!
        // System.out.println("Es el mismo: " + pruebaJuego.aciertoSimbolo(getSimbolo()));// Texto de depuración. !!!!!!!!!!
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
