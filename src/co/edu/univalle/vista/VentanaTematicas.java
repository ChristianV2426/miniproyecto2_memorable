/*
    Archivo: VentanaTematica.java
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
    CLASE: VentanaTematica
    INTENCIÓN: En esta venta se le pedirá al usuario que indique la temática de palbras
    que desea adivinar, también se le pregutará cuántas rondas desea hacer; esto
    establecerá las vidas del jugador para realizar estadísticas. Una vez seleccionado 
    lo indicado procederá a la pantalla de juego.
    RELACIONES:
    -Es una Ventana.
*/

package co.edu.univalle.vista;

import co.edu.univalle.logica.Juego;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class VentanaTematicas extends Ventana {
    // Atributos:
    private Juego juego;
    private JLabel labelTitulo = new JLabel("El Ahorcado");
    private JLabel labelSubtitulo = new JLabel("Universidad del Valle");
    private JLabel labelEscogerTematica = new JLabel("Seleccione una temática: ");
    private JLabel labelNumeroDePalabras = new JLabel("Número de palabras: ");
    private JComboBox boxCategorias;
    private JTextField fieldNumeroDePalabras = new JTextField();
    private JPanel panelCabecera = new JPanel();
    private JPanel panelPrincipal = new JPanel();
    private JPanel panelEscogerTematica = new JPanel();
    private JPanel panelEscogerNumeroDePalabras = new JPanel();
    private JButton buttonJugar = new JButton("Jugar");
    private JButton buttonVolver = new JButton("Volver");
    
    // Constructor:
    public VentanaTematicas(Juego juego) {
        this.juego = juego;
        
        // Listeners:
        buttonJugar.addActionListener(this);
        buttonVolver.addActionListener(this);
		
        // Configuración de páneles propios.
        northPanel.setPreferredSize(new Dimension(100, 90));
        northPanel.setBackground(new Color(79, 198, 198));
        panelCabecera.setLayout(new GridLayout(2, 1));
        panelCabecera.setBackground(new Color(0, 0, 0, 0));
        panelPrincipal.setLayout(new GridLayout(4, 1, 0, 20));
        panelPrincipal.setPreferredSize(new Dimension(200, 250));
        panelEscogerTematica.setLayout(new GridLayout(2, 1));
        panelEscogerNumeroDePalabras.setLayout(new GridLayout(2, 1));

        // Añadidos de ventana inicial. 
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 34));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelSubtitulo.setFont(new Font("Arial", Font.BOLD, 16));
        labelSubtitulo.setHorizontalAlignment(JLabel.CENTER);
        panelCabecera.add(labelTitulo);
        panelCabecera.add(labelSubtitulo);
        northPanel.add(panelCabecera);
        
        labelEscogerTematica.setFont(new Font("Arial", Font.PLAIN, 16));
        labelEscogerTematica.setHorizontalAlignment(JLabel.CENTER);
        boxCategorias = new JComboBox<String>(juego.getCategorias());
        boxCategorias.setSelectedIndex(2);
        panelEscogerTematica.add(labelEscogerTematica);
        panelEscogerTematica.add(boxCategorias);
        labelNumeroDePalabras.setFont(new Font("Arial", Font.PLAIN, 16));
        labelNumeroDePalabras.setHorizontalAlignment(JLabel.CENTER);
        fieldNumeroDePalabras.setHorizontalAlignment(JTextField.CENTER);
        fieldNumeroDePalabras.setFont(new Font("Arial", Font.PLAIN, 14));
        panelEscogerNumeroDePalabras.add(labelNumeroDePalabras);
        panelEscogerNumeroDePalabras.add(fieldNumeroDePalabras);
        panelPrincipal.add(panelEscogerTematica);
        panelPrincipal.add(panelEscogerNumeroDePalabras);
        panelPrincipal.add(buttonJugar);
        panelPrincipal.add(buttonVolver);
        centerPanel.add(panelPrincipal);
        
        // Mostrar Pantalla Inicial.
        setVisible(true);
    }   

    @Override
    public void actionPerformed(ActionEvent evento){
        if (evento.getSource() == buttonJugar){
            iniciarJuego();

        } else if (evento.getSource() == buttonVolver){
            dispose();
            VentanaInicio ventana = new VentanaInicio(); 
        }
    }
    
    public void iniciarJuego(){
        try{
            int numeroDePalabrasAAdivinar = Integer.parseInt(fieldNumeroDePalabras.getText());

            if(numeroDePalabrasAAdivinar < 1) 
                throw new NumberFormatException("Número no lógico");
            
            int indexCategoriaSeleccionada = boxCategorias.getSelectedIndex();
            juego.setIndexCategoriaSeleccionada(indexCategoriaSeleccionada);
            juego.setNumeroDePalabrasAAdivinar(numeroDePalabrasAAdivinar);
            dispose();
            VentanaJuego ventanaJuego = new VentanaJuego(juego);

        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido", "Error de ingreso de dato numérico", JOptionPane.ERROR_MESSAGE);
            fieldNumeroDePalabras.setText("");
            fieldNumeroDePalabras.requestFocus();
        }    
    }
}
