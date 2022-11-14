/*
    Archivo: VentanaEstadisticas.java
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
    CLASE: VentanaEstadisticas
    INTENCIÓN: Esta ventana muestra las estadísticas finales del juego; 
    al final de cada ronda el jugador verá su puntaje final, número de aciertos y tiempo total jugado. 
    También podrá iniciar una nueva partida si así lo desea. 

    RELACIONES:
    -Es una Ventana.
    -Conoce un juego
 */

package co.edu.univalle.vista;

import co.edu.univalle.logica.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

public class VentanaEstadisticas extends Ventana {
    // Atributos:
    private Juego juego;
    private VentanaJuego ventanaAsociada;
    private JPanel panelCabecera = new JPanel();
    private JPanel panelPrincipal = new JPanel();
    private JPanel panelButtonVolver = new JPanel();
    private JLabel labelTitulo = new JLabel("M E M O R A B L E");
    private JLabel labelSubtitulo = new JLabel("Estadísticas del juego");
    private JButton buttonVolver = new JButton("Volver a jugar");
    private JLabel labelEstadisticas = new JLabel("¡Se acabaron tus vidas! Fin del juego...");
    private JLabel labelAciertos = new JLabel("Cantidad de aciertos: ");
    private JLabel labelPuntaje = new JLabel("Puntaje total: ");
    private JLabel labelTiempo = new JLabel("Tiempo jugado: ");
    private Sonido controlSonido;
    private long tiempoTotal;
    
    // Constructor:
    public VentanaEstadisticas(Juego juego, VentanaJuego ventanaAsociada){
        this.juego = juego;
        this.ventanaAsociada = ventanaAsociada;
        tiempoTotal = ventanaAsociada.getTiempoFinal();
        controlSonido = new Sonido(ventanaAsociada);

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

        // Panel central:
        panelPrincipal.setPreferredSize(new Dimension(450, 300));
        panelPrincipal.setLayout(new GridBagLayout());

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.weightx = 1.0;
        restricciones.weighty = 1.0;
        restricciones.fill = GridBagConstraints.BOTH;
        restricciones.insets = new Insets(10, 15, 5, 10);

        restricciones.gridx = 0;
        restricciones.gridy = 0;
        restricciones.gridwidth = 3; 
        restricciones.gridheight = 1;
        labelEstadisticas.setFont(new Font("Arial", Font.BOLD, 20));
        labelEstadisticas.setHorizontalAlignment(JLabel.CENTER);
        panelPrincipal.add(labelEstadisticas, restricciones);

        restricciones.gridx = 0;
        restricciones.gridy = 1;
        restricciones.gridwidth = 3; 
        restricciones.gridheight = 1;
        labelAciertos.setFont(new Font("Arial", Font.PLAIN, 16));
        labelAciertos.setHorizontalAlignment(JLabel.CENTER);
        panelPrincipal.add(labelAciertos, restricciones);

        restricciones.gridx = 0;
        restricciones.gridy = 2;
        restricciones.gridwidth = 3; 
        restricciones.gridheight = 1;
        labelPuntaje.setFont(new Font("Arial", Font.PLAIN, 16));
        labelPuntaje.setHorizontalAlignment(JLabel.CENTER);
        panelPrincipal.add(labelPuntaje, restricciones);

        restricciones.gridx = 0;
        restricciones.gridy = 3;
        restricciones.gridwidth = 3; 
        restricciones.gridheight = 1;
        labelTiempo.setFont(new Font("Arial", Font.PLAIN, 16));
        labelTiempo.setHorizontalAlignment(JLabel.CENTER);
        panelPrincipal.add(labelTiempo, restricciones);

        restricciones.gridx = 0;
        restricciones.gridy = 4;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelPrincipal.add(new JPanel(), restricciones);

        restricciones.gridx = 1;
        restricciones.gridy = 4;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelButtonVolver.add(buttonVolver);
        panelPrincipal.add(panelButtonVolver, restricciones);

        restricciones.gridx = 2;
        restricciones.gridy = 4;
        restricciones.gridwidth = 1; 
        restricciones.gridheight = 1;
        panelPrincipal.add(new JPanel(), restricciones);

        centerPanel.add(panelPrincipal);

        // Cargar estadísticas:
        cargarEstadisticas();

        // Mostrar Pantalla Inicial.
        setVisible(true);
        controlSonido.reproducirSonido("./src/co/edu/univalle/vista/sonidos/partidaFinalizada.wav");
    }   

    public void cargarEstadisticas(){
        try{
            labelAciertos.setText(labelAciertos.getText() + juego.getNumeroAciertos() + " aciertos.");
        } catch (Exception exception){
            System.out.println("No se pudo cargar la estadística del número de aciertos");
        }

        try{
            labelPuntaje.setText(labelPuntaje.getText() + juego.getPuntuacion() + " puntos.");
        } catch (Exception exception){
            System.out.println("No se pudo cargar la estadística del puntaje");
        }

        labelTiempo.setText(labelTiempo.getText() + calcularTiempo());
    }

    public String calcularTiempo(){
        String texto = "";
        double doubleTiempoTotal = Math.round((double)tiempoTotal/1000);

        if(doubleTiempoTotal < 60.0){
            texto = (int)doubleTiempoTotal + " segundos.";

        } else if(doubleTiempoTotal >= 60.0 && doubleTiempoTotal < 3600.0){ 
            texto = (int)doubleTiempoTotal/60 + " minuto(s) y " + (int)doubleTiempoTotal%60 + " segundos.";
        }
        return texto;
    }

    @Override
    public void actionPerformed(ActionEvent evento){
        if (evento.getSource() == buttonVolver){
            dispose();
            VentanaInicio ventana = new VentanaInicio();
        }
    }
}