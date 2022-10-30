/*
    Archivo: VentanaInformacion.java
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
    CLASE: VentanaInformacion
    INTENCIÓN: Esta ventana mostrará los beneficios del juego El Ahorcado.
    * Una vez leído el contenido de esta ventana, el usuario podrá volver a la Ventana Inicial para empezar una partida. 
    RELACIONES:
    - Es una Ventana. 
*/

package co.edu.univalle.vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaInformacion extends Ventana {
    // Atributos:
    private JLabel labelTitulo = new JLabel("El Ahorcado");
    private JLabel labelSubtitulo = new JLabel("Universidad del Valle");
    private JLabel labelBeneficios = new JLabel("Beneficios de este juego:");    
    private JLabel labelLinea1;
    private JLabel labelLinea2;
    private JLabel labelLinea3;
    private JLabel labelLinea4;
    private JPanel panelCabecera = new JPanel();
    private JPanel panelPrincipal = new JPanel();
    private JPanel panelFinal = new JPanel();
    private JButton buttonVolver = new JButton("Volver");
    
    // Constructor:
    public VentanaInformacion(){
        
        // Listeners:
        buttonVolver.addActionListener(this);
		
        // Configuración de páneles propios.
        northPanel.setPreferredSize(new Dimension(100, 90));
        northPanel.setBackground(new Color(79, 198, 198));
        panelCabecera.setLayout(new GridLayout(2, 1));
        panelCabecera.setBackground(new Color(0, 0, 0, 0));
        panelPrincipal.setLayout(new GridLayout(6, 1, 0, 5));
        panelPrincipal.setPreferredSize(new Dimension(500, 300));


        // Añadidos de ventana inicial. 
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 34));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelSubtitulo.setFont(new Font("Arial", Font.BOLD, 16));
        labelSubtitulo.setHorizontalAlignment(JLabel.CENTER);
        panelCabecera.add(labelTitulo);
        panelCabecera.add(labelSubtitulo);
        northPanel.add(panelCabecera);
        
        labelBeneficios.setFont(new Font("Arial", Font.BOLD, 20));
        labelLinea1 = new JLabel("<html>El ahorcado es una excelente herramienta para aprender de forma divertida y así potenciar nuestras habilidades cognitivas, lógicas y lingüísticas."
                + " Algunos de los beneficios de este juego son: </html");
        labelLinea1.setFont(new Font("Arial", Font.PLAIN, 14));
        labelLinea2 = new JLabel("<html>* Aumenta la retentiva y desarrolla la capacidad lógica del jugador. </html");
        labelLinea2.setFont(new Font("Arial", Font.PLAIN, 14));
        labelLinea3 = new JLabel("<html>* Potencia el pensamiento creativo y la capacidad de tomar decisiones en contextos cambiantes.</html");
        labelLinea3.setFont(new Font("Arial", Font.PLAIN, 14));
        labelLinea4 = new JLabel("<html>* Amplía el vocabulario y mejora la ortografía. </html");
        labelLinea4.setFont(new Font("Arial", Font.PLAIN, 14));
        panelFinal.add(buttonVolver);
        panelPrincipal.add(labelBeneficios);
        panelPrincipal.add(labelLinea1);
        panelPrincipal.add(labelLinea2);
        panelPrincipal.add(labelLinea3);
        panelPrincipal.add(labelLinea4);
        panelPrincipal.add(panelFinal);
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
