/*
    Archivo: VentanaInstrucciones.java
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
    private JLabel labelTitulo = new JLabel("M E M O R A B L E");
    private JLabel labelSubtitulo = new JLabel("Instrucciones");
    private JLabel labelInstruccion = new JLabel();
    private JLabel labelImagen = new JLabel();
    private JPanel panelCabecera = new JPanel();
    private JPanel panelPrincipal = new JPanel();
    private JPanel panelButtonVolver = new JPanel();
    private JPanel panelButtonAnterior = new JPanel();
    private JPanel panelButtonSiguiente = new JPanel();
    private JPanel panelImagen = new JPanel();
    private JPanel panelLabelInstruccion = new JPanel();
    private JButton buttonVolver = new JButton("Volver");
    private JButton buttonAnterior = new JButton("Anterior");
    private JButton buttonSiguiente = new JButton("Siguiente");
    private int numeroDeInstruccion;
    private ImageIcon imagenInstruccion;
    
    // Constructor:
    public VentanaInstrucciones(){
        
        // Listeners:
        buttonVolver.addActionListener(this);
        buttonAnterior.addActionListener(this);
        buttonSiguiente.addActionListener(this);

        // Panel superior:
        northPanel.setPreferredSize(new Dimension(850, 70));
        northPanel.setBackground(new Color(0, 165, 181));
        panelCabecera.setLayout(new GridLayout(2, 1));
        panelCabecera.setBackground(new Color(0, 0, 0, 0));
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelSubtitulo.setFont(new Font("Arial", Font.BOLD, 16));
        labelSubtitulo.setHorizontalAlignment(JLabel.CENTER);
        panelCabecera.add(labelTitulo);
        panelCabecera.add(labelSubtitulo);
        northPanel.add(panelCabecera);

        // Panel principal:
        panelPrincipal.setPreferredSize(new Dimension(600, 350));
        panelPrincipal.setLayout(new GridBagLayout());

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.weightx = 1.0;
        restricciones.weighty = 1.0;
        restricciones.fill = GridBagConstraints.BOTH;
        restricciones.insets = new Insets(10, 10, 10, 10);

        restricciones.gridx = 2;
        restricciones.gridy = 0;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelButtonVolver.add(buttonVolver);
        panelPrincipal.add(panelButtonVolver, restricciones);

        restricciones.gridx = 0;
        restricciones.gridy = 1;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelPrincipal.add(new JPanel(), restricciones);

        restricciones.gridx = 2;
        restricciones.gridy = 2;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelButtonSiguiente.add(buttonSiguiente);
        panelPrincipal.add(panelButtonSiguiente, restricciones);

        restricciones.gridx = 0;
        restricciones.gridy = 2;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelButtonAnterior.add(buttonAnterior);
        panelPrincipal.add(panelButtonAnterior, restricciones);

        restricciones.gridx = 0;
        restricciones.gridy = 3;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelPrincipal.add(new JPanel(), restricciones);

        restricciones.gridx = 1;
        restricciones.gridy = 0;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 4;
        panelImagen.setMaximumSize(new Dimension(350, 240));
        panelPrincipal.add(panelImagen, restricciones);

        restricciones.gridx = 0;
        restricciones.gridy = 4;
        restricciones.gridwidth = 3; 
        restricciones.gridheight = 1;
        labelInstruccion.setFont(new Font("Arial", Font.PLAIN, 14));
        panelLabelInstruccion.setMinimumSize(new Dimension(600, 70));;
        panelLabelInstruccion.setLayout(new GridLayout(1, 1));
        panelLabelInstruccion.add(labelInstruccion);
        panelPrincipal.add(panelLabelInstruccion, restricciones);

        centerPanel.add(panelPrincipal);

        // Cargar componentes dinámicos
        numeroDeInstruccion = 1;
        cargarComponentesDinamicos(numeroDeInstruccion);

        // Mostrar Pantalla Inicial.
        setVisible(true);
    }

    public void cargarComponentesDinamicos(int numeroDeInstruccion){
        // Cambiar la imagen que ejemplifica la instrucción
        try{
            imagenInstruccion = new ImageIcon(getClass().getResource("imagenes/instruccion" + numeroDeInstruccion + ".png"));
            } catch (Exception exception){
                System.out.println("No se pudo cargar la imagen");
            }
            labelImagen.setIcon(imagenInstruccion);;
            panelImagen.removeAll();
            panelImagen.add(labelImagen);
        
        // Cambiar el texto de la instrucción
        switch (numeroDeInstruccion){
            case 1:
                labelInstruccion.setText("<html>En <b>M e m o r a b l e</b> aparecerán una serie de figuras de distintos colores sobre un mozaico de fichas."
                + " Al cabo de unos segundos las figuras desaparecerán y tendrás que responder a una pregunta.</html");
                buttonAnterior.setEnabled(false);
                break;
            
            case 2: 
                labelInstruccion.setText("<html>La pregunta aparecerá en la parte inferior de la pantalla, y para responderla debes dar clic sobre la ficha que cumpla la condición preguntada.</html");
                buttonAnterior.setEnabled(true);
                break;
            
            case 3: 
                labelInstruccion.setText("<html><b>Debes de estar atento de lo que te preguntan</b> porque es posible que tengas que pulsar más de una ficha...</html");
                break;

            case 4: 
                labelInstruccion.setText("<html>En el ejemplo en pantalla hay que encontrar todas las figuras de color vinotinto,"
                + " por lo que hay que dar clic sobre las 2 fichas que contienen figuras de color vinotinto.</html");
                buttonSiguiente.setEnabled(true);
                break;
            
            case 5:
                labelInstruccion.setText("<html>Es importante que memorices la forma, el color y la posición de cada ficha."
                + " Haz tu mejor esfuerzo y logra algo <b>M e m o r a b l e</b>.</html");
                buttonSiguiente.setEnabled(false);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento){
        if (evento.getSource() == buttonVolver){
            dispose();
            VentanaInicio ventana = new VentanaInicio(); 

        } else if (evento.getSource() == buttonSiguiente){
            numeroDeInstruccion++;
            cargarComponentesDinamicos(numeroDeInstruccion);

        } else if (evento.getSource() == buttonAnterior){
            numeroDeInstruccion--;
            cargarComponentesDinamicos(numeroDeInstruccion);
        }
    }
}