/*
    Archivo: VentanaJuego.java
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
    CLASE: VentanaJuego
    INTENCIÓN: Esta ventana muestra el juego: las palabra a divinar y las letras que hayan
    sido usadas, también brinda información de las rondas que quedan, y las que ha jugado
    dando una idea de los intentos restantes, todo esto con base en la información suministrada
    en VentanaTematicas. La información recolectada del estado de juego se asocia a un jugador.
    RELACIONES:
    -Es una Ventana.
 */

package co.edu.univalle.vista;

import co.edu.univalle.logica.Juego;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaJuego extends Ventana {
    // Atributos:
    private Juego juego;
    private JPanel panelRejillaGeneral = new JPanel();
    private JPanel panelGraficaAhorcado = new JPanel();
    private JPanel panelInteraccionJugador = new JPanel();
    private JPanel panelRejillaEstadisticas = new JPanel();
    private JPanel panelRejillaAbecedario = new JPanel();
    private JPanel panelBoton = new JPanel();
    private JLabel labelPalabrasTotales = new JLabel();
    private JLabel labelPalabrasRestantes = new JLabel();
    private JLabel labelPalabrasEjecutadas = new JLabel();
    private JLabel labelSprites = new JLabel();
    private ImageIcon ahorcadoSprites;
    private JLabel labelLineasPalabra = new JLabel();
    private JButton[] buttonAbecedario = new JButton[27];
    private JButton buttonSalir = new JButton("Salir");
    private String[] lineasLetrasValidas;
    private int totalLetrasEnPalabraAAdivinar;
    private int spriteContador = 1;
    private int totalLetrasAdivinadas = 0;
    private int intentosEjecutados = 0;
    private int intentosCorrectos = 0;
    private int intentosFallados = 0;

    
    // Constructor:
    public VentanaJuego(Juego juego){
        this.juego = juego;
        
        juego.nuevaPalabra();
        System.out.println(juego.getPalabraAAdivinar());
                
        // Listeners:
        buttonSalir.addActionListener(this);
		
        // Configuración de páneles propios.
        panelRejillaGeneral.setPreferredSize(new Dimension(776, 385));
        panelRejillaEstadisticas.setLayout(new GridLayout(3, 1));
        panelRejillaGeneral.setLayout(new GridLayout(1, 2));
        panelRejillaAbecedario.setLayout(new GridLayout(3, 9, 3, 3));
        panelInteraccionJugador.setLayout(new GridLayout(4, 1, 0, 20));

        // Creación del abecedario con ASCII.
        int ubicacionLetra = 0;
        for(int asciiLetra = 97; asciiLetra <= 122; asciiLetra++) {
            char letra = (char) asciiLetra;
            buttonAbecedario[ubicacionLetra] = new JButton(String.valueOf(letra));
            panelRejillaAbecedario.add(buttonAbecedario[ubicacionLetra]);
            buttonAbecedario[ubicacionLetra].addActionListener(this);
            buttonAbecedario[ubicacionLetra].setMargin(new Insets(0, 0, 0, 0));
            if(asciiLetra == 110) { // Manipulación de letra «ñ»
                buttonAbecedario[ubicacionLetra + 1] = new JButton("ñ");
                panelRejillaAbecedario.add(buttonAbecedario[ubicacionLetra + 1]);
                buttonAbecedario[ubicacionLetra + 1].addActionListener(this);
                buttonAbecedario[ubicacionLetra + 1].setMargin(new Insets(0, 0, 0, 0));
                ubicacionLetra++;
            }
            ubicacionLetra++;
        }
        
        // Cargar componentes dinámicos
        totalLetrasEnPalabraAAdivinar = juego.getPalabraAAdivinar().length();
        lineasLetrasValidas = new String[totalLetrasEnPalabraAAdivinar];
        cargarLineas(juego.getPalabraAAdivinar(), (char)64);
        cargarSprites();

        // Añadidos de ventana inicial. 
        labelPalabrasTotales.setText("Número de palabras: " + juego.getNumeroDePalabrasAAdivinar());
        labelPalabrasEjecutadas.setText("Palabras ejecutadas: " + juego.getPalabrasJugadas());
        labelPalabrasRestantes.setText("Palabras restantes: " + (juego.getNumeroDePalabrasAAdivinar() - juego.getPalabrasJugadas()));
        labelLineasPalabra.setFont(new Font("Arial", Font.BOLD, 16));
        labelLineasPalabra.setHorizontalAlignment(JLabel.CENTER);
        panelBoton.add(buttonSalir);
        panelRejillaEstadisticas.add(labelPalabrasTotales);
        panelRejillaEstadisticas.add(labelPalabrasEjecutadas);
        panelRejillaEstadisticas.add(labelPalabrasRestantes);
        panelInteraccionJugador.add(panelRejillaEstadisticas);
        panelInteraccionJugador.add(labelLineasPalabra);
        panelInteraccionJugador.add(panelRejillaAbecedario);
        panelInteraccionJugador.add(panelBoton);
        panelRejillaGeneral.add(panelInteraccionJugador);
        panelRejillaGeneral.add(panelGraficaAhorcado);
        centerPanel.add(panelRejillaGeneral);        

        // Mostrar Pantalla Inicial.
        setVisible(true);
    }

    // Métodos
    public void cargarLineas(String palabraAAdivinar, char letraEscogida){
        if (letraEscogida == 64){
            for (int contadorLetra = 0; contadorLetra < palabraAAdivinar.length(); contadorLetra++){
                if(contadorLetra == palabraAAdivinar.length() - 1)
                    lineasLetrasValidas[contadorLetra] = "___";
                else
                    lineasLetrasValidas[contadorLetra] = "___ ";
            }
        }
        else {
            String[] listaLetrasValidas = palabraAAdivinar.toLowerCase().split("");
            for(int buscadorLetraEscogida = 0; buscadorLetraEscogida < palabraAAdivinar.length(); buscadorLetraEscogida++) {
                if(letraEscogida == listaLetrasValidas[buscadorLetraEscogida].charAt(0)) {
                    if(buscadorLetraEscogida == palabraAAdivinar.length() - 1)
                        lineasLetrasValidas[buscadorLetraEscogida] = "  "+ letraEscogida + "  ";
                    else
                        lineasLetrasValidas[buscadorLetraEscogida] = "  "+ letraEscogida + "   ";
                }
            }
        }
        String lineas = String.join("", lineasLetrasValidas);
        labelLineasPalabra.setText(lineas);
    }
    
    public void cargarSprites()
    {
        try {
            ahorcadoSprites = new ImageIcon(getClass().getResource("AhorcadoSprites/"+ spriteContador +".png"));
            labelSprites.setIcon(ahorcadoSprites);
            panelGraficaAhorcado.add(labelSprites);
            spriteContador++;
        } catch (Exception e) {
            System.out.println("La imagen no pudo ser encontrada.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento){
        if (evento.getSource() == buttonSalir){
            dispose();
            VentanaInicio ventanaInicio = new VentanaInicio();
            
        } else if (evento.getSource() != null){
            intentosEjecutados++;
            for (int busquedaBoton = 0; busquedaBoton < 27; busquedaBoton++) {
                if (evento.getSource() == buttonAbecedario[busquedaBoton]){
                    String letraEscogida = buttonAbecedario[busquedaBoton].getText();
                    if(juego.verificacionDeLetra(letraEscogida)){
                        intentosCorrectos++;
                        totalLetrasAdivinadas += juego.contarOcurrencias(letraEscogida);
                        buttonAbecedario[busquedaBoton].setBackground(Color.GREEN);
                        buttonAbecedario[busquedaBoton].setEnabled(false);
                        cargarLineas(juego.getPalabraAAdivinar(), letraEscogida.charAt(0));
                    } else {
                        intentosFallados++;
                        buttonAbecedario[busquedaBoton].setBackground(Color.RED);
                        buttonAbecedario[busquedaBoton].setEnabled(false);
                        cargarSprites();
                    }
                }
            }
            verificarPerdedorOGanador();
        }
    }
    
    public void verificarPerdedorOGanador(){
        if (intentosFallados >= 9)
        {
            JOptionPane.showMessageDialog(null, "Has sobrepasado el número de intentos permitidos ¡Perdiste!", "Perdiste", JOptionPane.ERROR_MESSAGE);
            juego.palabraJugada();
            juego.palabraNoAdivinada();
            siguientePalabra();
            
        } else if (totalLetrasAdivinadas == totalLetrasEnPalabraAAdivinar){
            JOptionPane.showMessageDialog(null, "Has adivinado la palabra. ¡Felicitaciones!", "Ganaste", JOptionPane.PLAIN_MESSAGE);
            juego.palabraJugada();
            juego.palabraAdivinada();
            siguientePalabra();
        }
    }
    
    public void siguientePalabra(){
        if (juego.getPalabrasJugadas() == juego.getNumeroDePalabrasAAdivinar()){
            juego.rondaEjecutada();
            mostrarEstadisticasRonda();
            jugarOtraRonda();

        } else {
            continuarJugando();            
        }
    }
    
    public void mostrarEstadisticasRonda(){
        int palabrasAdivinadas = juego.getPalabrasAdivinadas();
        int palabrasNoAdivinadas = juego.getPalabrasNoAdivinadas();
        int palabrasDeEstaRonda = juego.getNumeroDePalabrasAAdivinar();
        
        double porcentajePalabrasAdivinadas = (double) palabrasAdivinadas / palabrasDeEstaRonda * 100;
        porcentajePalabrasAdivinadas = Math.round(porcentajePalabrasAdivinadas*100.0)/100.0; 
        
        double porcentajePalabrasNoAdivinadas = (double) palabrasNoAdivinadas / palabrasDeEstaRonda * 100;
        porcentajePalabrasNoAdivinadas = Math.round(porcentajePalabrasNoAdivinadas * 100.0)/100.0;
        
        JOptionPane.showMessageDialog(null, "Palabras adivinadas: " + palabrasAdivinadas + " (" + porcentajePalabrasAdivinadas + "%)"
                + "\nPalabras no adivinadas: " + palabrasNoAdivinadas + " (" + porcentajePalabrasNoAdivinadas + "%)" ,"Estadísticas de la ronda", JOptionPane.PLAIN_MESSAGE);
    }
    
    public void jugarOtraRonda(){
        int continuar = JOptionPane.showConfirmDialog(null, "¿Deseas iniciar una nueva ronda?", "Nueva ronda", JOptionPane.YES_NO_OPTION);
        if (continuar == JOptionPane.YES_OPTION){
            dispose();
            juego.nuevaRonda();
            VentanaTematicas nuevaVentanaTematicas = new VentanaTematicas(juego);
        } else if (continuar == JOptionPane.NO_OPTION){
            VentanaEstadisticas nuevaVentanaEstadisticas = new VentanaEstadisticas(juego);
            dispose();
        } else {
            VentanaEstadisticas nuevaVentanaEstadisticas = new VentanaEstadisticas(juego);
            dispose();
        } 
    }
    
    public void continuarJugando(){
        int continuar = JOptionPane.showConfirmDialog(null, "¿Deseas seguir jugando?", "Continuar", JOptionPane.YES_NO_OPTION);
        if (continuar == JOptionPane.YES_OPTION){
            dispose();
            VentanaJuego nuevaVentanaJuego = new VentanaJuego(juego);
        } else if (continuar == JOptionPane.NO_OPTION){
            juego.rondaEjecutada();
            dispose();
            VentanaEstadisticas nuevaVentanaEstadisticas = new VentanaEstadisticas(juego);
        } else {
            dispose();
        }
    }
}