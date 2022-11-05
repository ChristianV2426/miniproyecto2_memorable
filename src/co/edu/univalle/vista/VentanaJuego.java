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

import co.edu.univalle.logica.*;
import java.awt.*;
// import java.util.*;
import java.awt.event.*;
import javax.swing.*;

    // Atributos:
    public class VentanaJuego extends Ventana {
        private JLabel labelPuntuacion = new JLabel();
        private JLabel labelVidas = new JLabel();
        private JLabel labelCondicionTexto = new JLabel("Busque las palabras con la siguiente condición:");
        private Juego pruebaJuego = new Juego(0, this);
        private JLabel labelUsuario = new JLabel("Jugador: Juan Narváez");
        private JLabel labelCondicionSimbolo = new JLabel();
        private JPanel panelCabecera = new JPanel();
        private JPanel panelButtonSonido = new JPanel();
        private JPanel panelLabelPuntuacion = new JPanel();
        private JPanel panelLabelVidas = new JPanel();
        private JPanel panelMatriz = new JPanel();
        private JPanel panelFinal = new JPanel();
        private JButton buttonSonido = new JButton("🔊"); //🔇

        
        // Constructor:
        public VentanaJuego(){
            // Listeners:
            // Pintar páneles de prueba.
            // cajaButton.setBackground(Color.blue);
            
            // Panel superior:
            northPanel.setPreferredSize(new Dimension(850, 90));
            northPanel.setBackground(new Color(0, 165, 181));
            panelCabecera.setPreferredSize(new Dimension(550, 70));
            panelCabecera.setBackground(new Color(0, 165, 181));
            panelCabecera.setLayout(new GridBagLayout());

            GridBagConstraints restricciones = new GridBagConstraints();
            restricciones.weightx = 1.0;
            restricciones.weighty = 1.0;
            restricciones.fill = GridBagConstraints.BOTH;
            restricciones.insets = new Insets(5, 5, 5, 5);

            restricciones.gridx = 0;
            restricciones.gridy = 0;
            restricciones.gridwidth = 1; 
            restricciones.gridheight = 1;
            JPanel panelEnBlanco1 = new JPanel();
            panelEnBlanco1.setBackground(new Color(0, 165, 181));
            panelCabecera.add(panelEnBlanco1, restricciones);
        
            restricciones.gridx = 0;
            restricciones.gridy = 1;
            restricciones.gridwidth = 1; 
            restricciones.gridheight = 1;
            panelButtonSonido.setBackground(new Color(0, 165, 181));
            panelButtonSonido.add(buttonSonido);
            panelCabecera.add(panelButtonSonido, restricciones);

            restricciones.gridx = 0;
            restricciones.gridy = 2;
            restricciones.gridwidth = 1; 
            restricciones.gridheight = 1;
            JPanel panelEnBlanco2 = new JPanel();
            panelEnBlanco2.setBackground(new Color(0, 165, 181));
            panelCabecera.add(panelEnBlanco2, restricciones);
    
            restricciones.gridx = 1;
            restricciones.gridy = 1;
            restricciones.gridwidth = 1; 
            restricciones.gridheight = 1;
            panelLabelPuntuacion.setBackground(new Color(0, 165, 181));
            panelLabelPuntuacion.setMinimumSize(new Dimension(350, 0));
            panelLabelPuntuacion.setLayout(new GridLayout(1, 1));
            labelPuntuacion.setFont(new Font("Arial", Font.PLAIN, 24));
            labelPuntuacion.setHorizontalAlignment(JLabel.CENTER);
            labelPuntuacion.setText(pruebaJuego.getPuntos());
            panelLabelPuntuacion.add(labelPuntuacion);
            panelCabecera.add(panelLabelPuntuacion, restricciones);

            restricciones.gridx = 2;
            restricciones.gridy = 1;
            restricciones.gridwidth = 1; 
            restricciones.gridheight = 1;
            panelLabelVidas.setLayout(new GridLayout(1, 1));
            panelLabelVidas.setMinimumSize(new Dimension(50, 0));
            labelVidas.setFont(new Font("Arial", Font.PLAIN, 36));
            labelVidas.setHorizontalAlignment(JLabel.CENTER);
            labelVidas.setForeground(new Color(205, 69, 69));
            labelVidas.setText(pruebaJuego.getVidas());
            panelLabelVidas.add(labelVidas);
            panelCabecera.add(panelLabelVidas, restricciones);          
            
            northPanel.add(panelCabecera);

            
            // Panel central:
            centerPanel.setBackground(new Color(238, 238, 238));
            panelMatriz.setLayout(new GridLayout(4, 9, 10, 10));
            actualizarCasillas();
            centerPanel.add(panelMatriz);

            // Panel inferior:
            southPanel.setBackground(new Color(238, 238, 238));
            labelCondicionTexto.setFont(new Font("Arial", Font.PLAIN, 18));
            labelCondicionSimbolo.setText(pruebaJuego.getSimboloRonda());
            labelCondicionSimbolo.setFont(new Font("Arial", Font.PLAIN, 24));
            panelFinal.add(labelCondicionTexto);
            panelFinal.add(labelCondicionSimbolo);
            southPanel.add(panelFinal);

            // Mostrar Pantalla Inicial.
            setVisible(true);
    }   

    public void actualizarVidas(){
        labelVidas.setText(pruebaJuego.getVidas());
        
    }

    public void actualizarPuntos(){
        labelPuntuacion.setText(pruebaJuego.getPuntos());
    }

    public void actualizarCasillas(){
        panelMatriz.removeAll();
        pruebaJuego.nuevaRonda();
        labelCondicionSimbolo.setText(pruebaJuego.getSimboloRonda());
        
        for(int recuadros = 0; recuadros < 36; recuadros++) {
            Casilla recuadro = new Casilla(pruebaJuego);
            panelMatriz.add(recuadro.pintar());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}