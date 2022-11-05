/*
    Archivo: VentanaInformacion.java
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
    CLASE: VentanaInformacion
    INTENCIÓN: Esta ventana mostrará los beneficios del juego Memorable.
    Una vez leído el contenido de esta ventana, el usuario podrá volver a la Ventana Inicial para empezar una partida. 
    RELACIONES:
    - Es una Ventana. 
*/

package co.edu.univalle.vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaInformacion extends Ventana {
    // Atributos:
    private JLabel labelTitulo = new JLabel("M E M O R A B L E");
    private JLabel labelSubtitulo = new JLabel("¿Para qué sirve?");
    private JLabel labelBeneficios = new JLabel("Beneficios de este juego:");    
    private JLabel labelLinea1;
    private JLabel labelLinea2;
    private JPanel panelCabecera = new JPanel();
    private JPanel panelPrincipal = new JPanel();
    private JButton buttonVolver = new JButton("Volver");
    
    // Constructor:
    public VentanaInformacion(){
        
        // Listeners:
        buttonVolver.addActionListener(this);
		
        // Panel superior:
        northPanel.setPreferredSize(new Dimension(850, 90));
        northPanel.setBackground(new Color(0, 165, 181));
        panelCabecera.setLayout(new GridLayout(2, 1));
        panelCabecera.setBackground(new Color(0, 0, 0, 0));
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 34));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelSubtitulo.setFont(new Font("Arial", Font.BOLD, 16));
        labelSubtitulo.setHorizontalAlignment(JLabel.CENTER);
        panelCabecera.add(labelTitulo);
        panelCabecera.add(labelSubtitulo);
        northPanel.add(panelCabecera);

        // Panel principal:
        panelPrincipal.setPreferredSize(new Dimension(500, 250));
        panelPrincipal.setLayout(new GridBagLayout());

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.weightx = 1.0;
        restricciones.weighty = 1.0;
        restricciones.fill = GridBagConstraints.BOTH;
        restricciones.insets = new Insets(15, 10, 15, 10);

        labelBeneficios.setFont(new Font("Arial", Font.BOLD, 20));
        labelBeneficios.setHorizontalAlignment(JLabel.LEFT);
        restricciones.gridx = 0;
        restricciones.gridy = 0;
        restricciones.gridwidth = 3; 
        restricciones.gridheight = 1;
        panelPrincipal.add(labelBeneficios, restricciones);

        labelLinea1 = new JLabel("<html><b>M e m o r a b l e</b> es un juego que permite entrenar la capacidad de codificación y de recuerdo de la información visual y espacial.</html");
        labelLinea1.setFont(new Font("Arial", Font.PLAIN, 14));
        restricciones.gridx = 0;
        restricciones.gridy = 1;
        restricciones.gridwidth = 3; 
        restricciones.gridheight = 1;
        panelPrincipal.add(labelLinea1, restricciones);

        labelLinea2 = new JLabel("<html>Este tipo de habilidades es relevante en la práctica de algunos deportes, el reconocimiento de personas conocidas o para recordar dónde guardamos nuestras cosas.</html");
        labelLinea2.setFont(new Font("Arial", Font.PLAIN, 14));
        restricciones.gridx = 0;
        restricciones.gridy = 2;
        restricciones.gridwidth = 3; 
        restricciones.gridheight = 1;
        panelPrincipal.add(labelLinea2, restricciones);

        restricciones.gridx = 0;
        restricciones.gridy = 3;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelPrincipal.add(new JPanel(), restricciones);

        restricciones.gridx = 1;
        restricciones.gridy = 3;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelPrincipal.add(buttonVolver, restricciones);

        restricciones.gridx = 2;
        restricciones.gridy = 3;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelPrincipal.add(new JPanel(), restricciones);

        centerPanel.add(panelPrincipal);

        // Mostrar Pantalla Inicial.
        setVisible(true);
    }   

    @Override
    public void actionPerformed(ActionEvent evento){
        if (evento.getSource() == buttonVolver){
            dispose();
            VentanaInicio ventana = new VentanaInicio(); 
        }
    } 
}
