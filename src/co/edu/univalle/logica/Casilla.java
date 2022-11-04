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

    /*  El tener un registro de qué ronda se está jugando
    permitirá ir aumentando la dificultad. */ 
    static private Juego pruebaJuego;

    public Casilla(Juego pruebaJuego){
        this.pruebaJuego = pruebaJuego;
        Boolean correcionPorcentaje = true;
        int numeroSimbolosAPintar = 8;
        int rondaAsociada = pruebaJuego.getRondaAsociada();
        /* Con base en el número de casillas (36), y el número de elementos que pueden ser
         * pintados (4), se considera el número 30 como una buena estimación probabilística 
         * para el correcto funcinamiento de los símbolos pintados. */
        int simboloAPintar = random.nextInt(30); // Funciona como valor probabilístico.


        /* TODO: Se debe tener en consideración que el elemento que sale en condición
         * debe aparecer en los simbolos desplegados. */
        if(rondaAsociada == 1) {
            if (contadorId < 36){
                // Se asegura de que por lo menos se pinte una vez.
                if(contadorNivelacion != numeroSimbolosAPintar-1 && contadorId > 30 && correcionPorcentaje) {
                    correcionPorcentaje = false;
                    simboloAPintar = random.nextInt(6); // Porcentaje de alerta.
                    System.out.println("ENTRÓ en " + contadorId + " y pintó " + simbolo);
                }

                if (simboloAPintar >= 0 && simboloAPintar <= 3 && contadorNivelacion < numeroSimbolosAPintar) {
                    simbolo = simbolos[simboloAPintar];
                    contadorNivelacion++;
                } else {
                    simbolo = simbolos[4];
                }
    
                contadorId++;
            }
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
        recuadro.setBackground(new Color(79, 198, 198));
        recuadro.addMouseListener(this);
        recuadro.add(labelSymbolo);
        
        return recuadro;
    }

    public String getSimbolo() {
        return simbolo;
    }

    // Mouse Listener
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        // this.pintar().setBackground(Color.RED);
        System.out.println(getSimbolo());
        System.out.println(pruebaJuego.getSimboloRonda());
        System.out.println(pruebaJuego.aciertoSimbolo(getSimbolo()));
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
