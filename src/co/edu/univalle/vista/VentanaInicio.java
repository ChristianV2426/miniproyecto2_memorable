/*
    Archivo: VentanaInicio.java
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
    CLASE: VentanaInicio
    INTENCIÓN: Pantalla de inicio donde se registrará el nombre del usuario
    que desea jugar. El usuario podrá seleccionar uno de tres botones: Jugar,
    Instrucciones, Para qué sirve. Si no hay un nombre de usuario registrado
    entonces no podrá acceder a «Jugar».
    RELACIONES:
    -Es una Ventana.
 */

package co.edu.univalle.vista;

import co.edu.univalle.logica.Juego;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaInicio extends Ventana {
    // Atributos:
    private JPanel panelCabecera = new JPanel();
    private JLabel labelTitulo = new JLabel("Fundamentos de Programación Orientada a Eventos");
    private JLabel labelSubtitulo = new JLabel("Escuela de Ingeniería de Sistemas y Computación");
    private JPanel panelPrincipal = new JPanel();
    private JLabel labelNombreJuego = new JLabel("M E M O R A B L E");
    private JLabel labelNombreUniversidad = new JLabel("Universidad del Valle");
    private JLabel labelNombreJugador = new JLabel("Ingrese su nombre:");
    private JTextField fieldNombreJugador = new JTextField();
    private JButton buttonJugar = new JButton("Jugar");
    private JButton buttonInstrucciones = new JButton("Instrucciones");
    private JButton buttonParaQueSirve = new JButton("¿Para qué sirve?");
		
    // Constructor:
    public VentanaInicio() {
        // Listeners:
        buttonJugar.addActionListener(this);
        buttonInstrucciones.addActionListener(this);
        buttonParaQueSirve.addActionListener(this);

        // Panel superior:
        northPanel.setPreferredSize(new Dimension(850, 70));
        northPanel.setLayout(new GridLayout(1, 2));
        panelCabecera.setLayout(new GridLayout(4, 1));
        panelCabecera.setBackground(new Color(0, 165, 181));
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 12));
        labelTitulo.setHorizontalAlignment(JLabel.LEFT);
        labelSubtitulo.setFont(new Font("Arial", Font.BOLD, 12));
        labelSubtitulo.setHorizontalAlignment(JLabel.LEFT);
        panelCabecera.add(new JLabel());
        panelCabecera.add(labelTitulo);
        panelCabecera.add(labelSubtitulo);
        northPanel.add(panelCabecera);

        // Panel principal:
        panelPrincipal.setPreferredSize(new Dimension(550, 300));
        panelPrincipal.setLayout(new GridBagLayout());

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.weightx = 1.0;
        restricciones.weighty = 1.0;
        restricciones.fill = GridBagConstraints.BOTH;
        restricciones.insets = new Insets(10, 10, 10, 10);

        labelNombreJuego.setFont(new Font("Arial", Font.BOLD, 55));
        labelNombreJuego.setHorizontalAlignment(JLabel.CENTER);
        restricciones.gridx = 0;
        restricciones.gridy = 0;
        restricciones.gridwidth = 3; 
        restricciones.gridheight = 1;
        panelPrincipal.add(labelNombreJuego, restricciones);

        labelNombreUniversidad.setFont(new Font("Arial", Font.BOLD, 30));
        labelNombreUniversidad.setHorizontalAlignment(JLabel.CENTER);
        restricciones.gridx = 0;
        restricciones.gridy = 1;
        restricciones.gridwidth = 3; 
        restricciones.gridheight = 1;
        panelPrincipal.add(labelNombreUniversidad, restricciones);

        labelNombreJugador.setFont(new Font("Arial", Font.PLAIN, 18));
        labelNombreJugador.setHorizontalAlignment(JLabel.CENTER);
        restricciones.gridx = 1;
        restricciones.gridy = 2;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelPrincipal.add(labelNombreJugador, restricciones);

        fieldNombreJugador.setHorizontalAlignment(JTextField.CENTER);
        fieldNombreJugador.setFont(new Font("Arial", Font.PLAIN, 14));
        restricciones.gridx = 1;
        restricciones.gridy = 3;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelPrincipal.add(fieldNombreJugador, restricciones);

        restricciones.gridx = 0;
        restricciones.gridy = 4;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelPrincipal.add(buttonInstrucciones, restricciones);

        restricciones.gridx = 1;
        restricciones.gridy = 4;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelPrincipal.add(buttonJugar, restricciones);

        restricciones.gridx = 2;
        restricciones.gridy = 4;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelPrincipal.add(buttonParaQueSirve, restricciones);

        centerPanel.add(panelPrincipal);

        // Mostrar Pantalla Inicial.
        setVisible(true);
    }

    // Métodos
    @Override
    public void actionPerformed(ActionEvent evento){
        if (evento.getSource() == buttonJugar){
            iniciarJuego();
        } else if (evento.getSource() == buttonInstrucciones){
            dispose();
             VentanaInstrucciones ventanaInstrucciones = new VentanaInstrucciones();
            
        } else if (evento.getSource() == buttonParaQueSirve){
            dispose();
            VentanaInformacion ventanaInformacion = new VentanaInformacion();
        }
    }
    
    public void iniciarJuego(){
        String nombreJugador = fieldNombreJugador.getText();
        
        if(nombreJugador.trim().isEmpty() || nombreJugador.trim().length() == 0){
            JOptionPane.showMessageDialog(null,"Por favor ingrese su nombre", "Advertencia", JOptionPane.ERROR_MESSAGE);
            fieldNombreJugador.requestFocusInWindow();
            
        } else if (!nombreJugador.matches("[a-zA-Z ]+")){
            JOptionPane.showMessageDialog(null,"Por favor ingrese solamente letras", "Advertencia", JOptionPane.ERROR_MESSAGE);
            fieldNombreJugador.setText("");
            fieldNombreJugador.requestFocusInWindow();
            
        } else {
            // Juego juego = new Juego(nombreJugador);
            // dispose();
            // VentanaJuego ventanaJuego = new VentanaJuego();
        }
    }
}