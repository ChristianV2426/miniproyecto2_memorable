/*
    Archivo: VentanaJuego.java
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
    CLASE: VentanaJuego
    INTENCIÓN: Esta ventana muestra el juego: las palabra a divinar y las letras que hayan
    sido usadas, también brinda información de las rondas que quedan, y las que ha jugado
    dando una idea de los intentos restantes, todo esto con base en la información suministrada
    en VentanaTematicas. La información recolectada del estado de juego se asocia a un jugador.
    RELACIONES:
    -Es una Ventana.
 */

package co.edu.univalle.vista;

// import co.edu.univalle.logica.Juego;
import java.awt.*;
// import java.util.*;
import java.awt.event.*;
import javax.swing.*;

    // Atributos:
    public class VentanaJuego extends Ventana {
        private JLabel labelPuntuacion = new JLabel("Puntuación:");
        private JLabel labelVidas = new JLabel("♥♥♥");
        private JLabel labelCondicionTexto = new JLabel("Busque las palabras con la siguiente condición:");
        private JLabel labelCondicionSimbolo = new JLabel("♣️");
        private JPanel panelCabecera = new JPanel();
        private JPanel panelMatriz = new JPanel();
        private JPanel panelPrincipal = new JPanel();
        private JPanel panelFinal = new JPanel();
        private JPanel cajaButton = new JPanel();
        private JButton buttonSonido = new JButton("Sonido");

        
        // Constructor:
        public VentanaJuego(){
            
            // Listeners:

            // Pintar páneles de prueba.

            
            // Configuración de páneles propios.
            northPanel.setPreferredSize(new Dimension(100, 90));
            northPanel.setBackground(new Color(79, 198, 198));
            panelCabecera.setLayout(new GridLayout(1, 3,40,40));
            panelCabecera.setBackground(new Color(0, 0, 0, 0));
            cajaButton.setBackground(new Color(0, 0, 0, 0));
            panelMatriz.setLayout(new GridLayout(4, 9, 10, 10));
            panelPrincipal.setLayout(new GridLayout(3, 1, 0, 5));
            panelPrincipal.setPreferredSize(new Dimension(370, 270));
            labelVidas.setForeground(new Color(205, 69, 69));
            labelVidas.setFont(new Font("Arial", Font.PLAIN, 42));
            labelPuntuacion.setFont(new Font("Arial", Font.PLAIN, 24));
            labelVidas.setHorizontalAlignment(JLabel.RIGHT);

            

            // Añadidos de ventana inicial. 

            for(int recuadros = 0; recuadros < 36; recuadros++) {
                JPanel recuadro = new JPanel();
                recuadro.setPreferredSize(new Dimension(50, 50));
                recuadro.setBackground(new Color(79, 198, 198));
                panelMatriz.add(recuadro);
            }


            cajaButton.add(buttonSonido);
            panelCabecera.add(cajaButton);
            panelCabecera.add(labelPuntuacion);
            panelCabecera.add(labelVidas);
            panelFinal.add(labelCondicionTexto);
            panelFinal.add(labelCondicionSimbolo);
            
            northPanel.add(panelCabecera);
            centerPanel.add(panelMatriz);
            southPanel.add(panelFinal);

            // Mostrar Pantalla Inicial.
            setVisible(true);
    }   



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}