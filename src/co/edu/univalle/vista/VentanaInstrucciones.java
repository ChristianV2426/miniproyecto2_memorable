/*
    Archivo: VentanaInstrucciones.java
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
    CLASE: VentanaInstrucciones
    INTENCIÓN: Esta ventana mostrará las instrucciones del juego. Una vez leídas las instrucciones,
    el usuario podrá volver a la Ventana Inicial para empezar una partida. 
    RELACIONES:
    - Es una Ventana. 
*/

package co.edu.univalle.vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaInstrucciones extends Ventana {
    // Atributos:
    private JLabel labelTitulo = new JLabel("El Ahorcado");
    private JLabel labelSubtitulo = new JLabel("Universidad del Valle");
    private JLabel labelInstrucciones = new JLabel("Instrucciones:");    
    private JLabel labelPrimeraInstruccion;
    private JLabel labelSegundaInstruccion;
    private JPanel panelCabecera = new JPanel();
    private JPanel panelPrincipal = new JPanel();
    private JPanel panelFinal = new JPanel();
    private JButton buttonVolver = new JButton("Volver");
    
    // Constructor:
    public VentanaInstrucciones(){
        
        // Listeners:
        buttonVolver.addActionListener(this);
		
        // Configuración de páneles propios.
        northPanel.setPreferredSize(new Dimension(100, 90));
        northPanel.setBackground(new Color(79, 198, 198));
        panelCabecera.setLayout(new GridLayout(2, 1));
        panelCabecera.setBackground(new Color(0, 0, 0, 0));
        panelPrincipal.setLayout(new GridLayout(5, 1, 0, 5));
        panelPrincipal.setPreferredSize(new Dimension(370, 270));

        // Añadidos de ventana inicial. 
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 34));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelSubtitulo.setFont(new Font("Arial", Font.BOLD, 16));
        labelSubtitulo.setHorizontalAlignment(JLabel.CENTER);
        panelCabecera.add(labelTitulo);
        panelCabecera.add(labelSubtitulo);
        northPanel.add(panelCabecera);
        
        labelInstrucciones.setFont(new Font("Arial", Font.BOLD, 20));
        labelPrimeraInstruccion = new JLabel("<html>* Haz clic con el mouse sobre las letras del abecedario para adivinar la palabra escondida.</html>");
        labelPrimeraInstruccion.setFont(new Font("Arial", Font.PLAIN, 14));
        labelSegundaInstruccion = new JLabel("<html>* A medida que vas adivinando la palabra, las letras cambiarán de color: el verde indica que la letra sí hace parte de la palabra y el rojo indica lo contrario.</html>");
        labelSegundaInstruccion.setFont(new Font("Arial", Font.PLAIN, 14));
        panelFinal.add(buttonVolver);
        panelPrincipal.add(labelInstrucciones);
        panelPrincipal.add(labelPrimeraInstruccion);
        panelPrincipal.add(labelSegundaInstruccion);
        panelPrincipal.add(new JPanel());
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