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
    private Juego pruebaJuego = new Juego(1);

    public Casilla(){
        int rondaAsociada = pruebaJuego.getRondaAsociada();
        int simboloAPintar = random.nextInt(100);


        /* TODO: Se debe de corregir el sistema de probabilidad 
         * a la hora de pintar los simbolos, puesto que, como está,
         * siempre pintará un símbolo en la casilla 30 si no se 
         * han pintado símbolos antes, y consiguiente pintará
         * simbolos seguidos. Prueba 2.
         */ 

        if(rondaAsociada == 1) {
            if (contadorId < 36){
                if (simboloAPintar >= 0 && simboloAPintar <= 3 && contadorNivelacion < 5) {
                    simbolo = simbolos[simboloAPintar];
                    contadorNivelacion++;
                } else {
                    simbolo = simbolos[4];
                }
                contadorId++;
                
                // **** CORREGIR
                if(contadorId > 29 && contadorNivelacion <= 4) {
                    simbolo = simbolos[random.nextInt(4)];
                    System.out.println("ENTRÓ en " + contadorId + " y pintó " + simbolo);
                    contadorNivelacion++;
                }
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
