/*
    Archivo: VentanaInicio.java
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
    private JLabel labelTitulo = new JLabel("El Ahorcado");
    private JLabel labelSubtitulo = new JLabel("Universidad del Valle");
    private JLabel labelNombre = new JLabel("Ingrese su nombre:");
    private JPanel panelInputNombre = new JPanel();
    private JPanel panelFormularioIncio = new JPanel();
    private JTextField fieldNombre = new JTextField();
    private JButton buttonJugar = new JButton("Jugar");
    private JButton buttonInstrucciones = new JButton("Instrucciones");
    private JButton buttonParaQueSirve = new JButton("¿Para qué sirve?");
		
    // Constructor:
    public VentanaInicio() {
        // Listeners:
        buttonJugar.addActionListener(this);
        buttonInstrucciones.addActionListener(this);
        buttonParaQueSirve.addActionListener(this);

        // Configuración de páneles propios.
        northPanel.setPreferredSize(new Dimension(100, 90));
        northPanel.setBackground(new Color(79, 198, 198));
        panelCabecera.setLayout(new GridLayout(2, 1));
        panelCabecera.setBackground(new Color(0, 0, 0, 0));
        panelInputNombre.setLayout(new GridLayout(2, 1));
        panelFormularioIncio.setLayout(new GridLayout(4, 1, 0, 20));
        panelFormularioIncio.setPreferredSize(new Dimension(180, 250));

        // Añadidos de ventana inicial. 
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 34));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelSubtitulo.setFont(new Font("Arial", Font.BOLD, 16));
        labelSubtitulo.setHorizontalAlignment(JLabel.CENTER);
        labelNombre.setFont(new Font("Arial", Font.PLAIN, 18));
        panelCabecera.add(labelTitulo);
        panelCabecera.add(labelSubtitulo);
        northPanel.add(panelCabecera);
        fieldNombre.setHorizontalAlignment(JTextField.CENTER);
        fieldNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        panelInputNombre.add(labelNombre);
        panelInputNombre.add(fieldNombre);
        panelFormularioIncio.add(panelInputNombre);
        panelFormularioIncio.add(buttonJugar);
        panelFormularioIncio.add(buttonInstrucciones);
        panelFormularioIncio.add(buttonParaQueSirve);
        centerPanel.add(panelFormularioIncio);

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
        String nombreJugador = fieldNombre.getText();
        
        if(nombreJugador.trim().isEmpty() || nombreJugador.trim().length() == 0){
            JOptionPane.showMessageDialog(null,"Por favor ingrese su nombre", "Advertencia", JOptionPane.ERROR_MESSAGE);
            fieldNombre.requestFocusInWindow();
            
        } else if (!nombreJugador.matches("[a-zA-Z ]+")){
            JOptionPane.showMessageDialog(null,"Por favor ingrese solamente letras", "Advertencia", JOptionPane.ERROR_MESSAGE);
            fieldNombre.setText("");
            fieldNombre.requestFocusInWindow();
            
        } else {
            Juego juego = new Juego(nombreJugador);
            dispose();
            VentanaTematicas ventanaTematicas = new VentanaTematicas(juego);
        }
    }
}