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
    INTENCIÓN: Esta ventana muestra el juego: en la parte superior aparece el nombre del jugador,
    los puntos que ha logrado y las vidas que le quedan. En la parte inferior aparece la figura/forma/color que el jugador
    debe identificar para ganar puntos. En la parte central se encuentra el mozaico de casillas donde están las figuras
    que el juego genera aleatoriamente. 

    RELACIONES:
    -Es una Ventana.
    -Contiene un objeto de la clase Juego.
    -Contiene un arreglo de objetos de la clase Casilla. 
    -Contiene un objeto de la clase Sonido. 
 */

package co.edu.univalle.vista;

import co.edu.univalle.logica.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.*;

public class VentanaJuego extends Ventana implements KeyListener {
        // Atributos:
        private JLabel labelPuntuacion = new JLabel();
        private JLabel labelVidas = new JLabel();
        private JLabel labelCondicionTexto = new JLabel();
        private Juego pruebaJuego = new Juego(0, this);
        private Sonido controladorSonido = new Sonido(this);
        private JLabel labelUsuario = new JLabel();
        private JLabel labelCondicionSimbolo = new JLabel();
        private JPanel panelCabecera = new JPanel();
        private JPanel panelButtonSonido = new JPanel();
        private JPanel panelLabelPuntuacion = new JPanel();
        private JPanel panelLabelVidas = new JPanel();
        private JPanel panelMatriz = new JPanel();
        private JPanel panelFinal = new JPanel();
        private JButton buttonSonido = new JButton("🔊"); //🔇
        private Casilla casillas[] = new Casilla[36];
        static private int posicionTecla = 0;
        private Timer timer = new Timer();
        private int tiempoDeEsperaNuevaRonda = 5000; // 5 segundos. 
        private int tipoDeCondicion;
        private long tiempoInicial;
        private long tiempoFinal;
        
        // Constructor:
        public VentanaJuego(){
            tiempoInicial = System.currentTimeMillis();

            // Listeners:
            buttonSonido.addActionListener(this);
            buttonSonido.putClientProperty("on", true);
            
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
            buttonSonido.setFocusable(false);
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
            panelLabelPuntuacion.setMinimumSize(new Dimension(350, 50));
            panelLabelPuntuacion.setLayout(new GridLayout(2, 1));
            labelPuntuacion.setFont(new Font("Arial", Font.PLAIN, 24));
            labelPuntuacion.setHorizontalAlignment(JLabel.CENTER);
            labelPuntuacion.setText(pruebaJuego.getPuntos());
            panelLabelPuntuacion.add(labelPuntuacion);
            labelUsuario.setHorizontalAlignment(JLabel.CENTER);
            panelLabelPuntuacion.add(labelUsuario);
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
            labelCondicionSimbolo.setText("");
            labelCondicionSimbolo.setFont(new Font("Arial", Font.PLAIN, 24));
            panelFinal.add(labelCondicionTexto);
            panelFinal.add(labelCondicionSimbolo);
            southPanel.add(panelFinal);

            // Mostrar Pantalla Inicial.
            setVisible(true);
        }   
        
    // Métodos.
    public void actualizarVidas(){
        labelVidas.setText(pruebaJuego.getVidas());
    }
    
    public void actualizarPuntos(){
        labelPuntuacion.setText(pruebaJuego.getPuntos());
    }
    
    public void setNombreUsuario(String nombreUsuario){
        labelUsuario.setText("Jugador: " + nombreUsuario);
    }

    public long getTiempoFinal(){
        return tiempoFinal;
    }

    public void actualizarColores(){
        labelCondicionSimbolo.setForeground(pruebaJuego.getColorRonda());
    }

    public void desactivarListeners(){
        for (int contadorCasillas = 0; contadorCasillas < 36; contadorCasillas++){
            casillas[contadorCasillas].getJpanel().removeMouseListener(casillas[contadorCasillas]);
        }
        this.removeKeyListener(this);
    }

    public void activarListeners(){
        for (int contadorCasillas = 0; contadorCasillas < 36; contadorCasillas++){
            casillas[contadorCasillas].getJpanel().addMouseListener(casillas[contadorCasillas]);
        }
        this.addKeyListener(this);
    }
    
    public void actualizarCasillas(){
        labelCondicionTexto.setText("Observe las figuras detenidamente...");
        panelMatriz.removeAll();
        pruebaJuego.nuevaRonda();
        labelCondicionSimbolo.setText(pruebaJuego.getSimboloRonda());

        for(int recuadros = 0; recuadros < 36; recuadros++) {
            Casilla recuadro = new Casilla(pruebaJuego);
            panelMatriz.add(recuadro.pintar());
            casillas = recuadro.getCasillas();
        }
        casillas[posicionTecla].getJpanel().setBackground(new Color(85, 227, 237));
        labelCondicionSimbolo.setText("");
        tipoDeCondicion = pruebaJuego.getTipoDeCondicion();
        controladorSonido.reproducirSonido("./src/co/edu/univalle/vista/sonidos/timer.wav");

        desactivarListeners();

        // Tareas temporales:
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                activarListeners();
                for(int recuadros = 0; recuadros < 36; recuadros++) {
                    casillas[recuadros].getJlabel().setVisible(false); // Se recomienda comentar esta línea para depurar.
                }
                labelCondicionSimbolo.setText(pruebaJuego.getSimboloRonda());
                labelCondicionSimbolo.setForeground(pruebaJuego.getColorRonda());
                if(tipoDeCondicion == 0){
                    labelCondicionTexto.setText("Seleccione la(s) figura(s) que cumpla(n) con este símbolo:");
                } else if(tipoDeCondicion == 1){
                    labelCondicionTexto.setText("Seleccione la(s) figura(s) que cumpla(n) con este color:");
                    labelCondicionSimbolo.setText("■");
                }else if(tipoDeCondicion == 2){
                    labelCondicionTexto.setText("Seleccione la(s) figura(s) que cumpla(n) con este símbolo y color:");
                }
            controladorSonido.pararSonido();
            }
        };
        
        timer.schedule(task, tiempoDeEsperaNuevaRonda);
    }

    public void gameOver(){
        controladorSonido.pararSonido();
        tiempoFinal = System.currentTimeMillis();
        tiempoFinal = tiempoFinal - tiempoInicial;
        casillas[0].reiniciarDificultad();
        dispose();
        VentanaEstadisticas ventanaEstadisticas = new VentanaEstadisticas(pruebaJuego, this);
    }

    public boolean getEstadoSonido(){
        return (boolean) buttonSonido.getClientProperty("on");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSonido){
            if((boolean) buttonSonido.getClientProperty("on")){
                controladorSonido.pausarSonido();
                buttonSonido.setText("🔇");
                buttonSonido.putClientProperty("on", false);

            } else if(!(boolean) buttonSonido.getClientProperty("on")){
                buttonSonido.setText("🔊");
                buttonSonido.putClientProperty("on", true);
                controladorSonido.reproducirSonido();
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {
            case 39:
            if(posicionTecla+1<36){
                    posicionTecla +=1;
                    casillas[posicionTecla-1].getJpanel().setBackground(casillas[posicionTecla-1].getColorJpanel());
                    casillas[posicionTecla].getJpanel().setBackground(new Color(85, 227, 237));
                }
                break;
            case 40:
            if(posicionTecla+9<36){
                    posicionTecla +=9;
                    casillas[posicionTecla-9].getJpanel().setBackground(casillas[posicionTecla-9].getColorJpanel());
                    casillas[posicionTecla].getJpanel().setBackground(new Color(85, 227, 237));
                }
                break;

            case 37:
            if(posicionTecla-1 >= 0){
                    posicionTecla -= 1;
                    casillas[posicionTecla+1].getJpanel().setBackground(casillas[posicionTecla+1].getColorJpanel());
                    casillas[posicionTecla].getJpanel().setBackground(new Color(85, 227, 237));
                }
                break;

            case 38:
            if(posicionTecla-9>=0){
                    posicionTecla -= 9;
                    casillas[posicionTecla+9].getJpanel().setBackground(casillas[posicionTecla+9].getColorJpanel());
                    casillas[posicionTecla].getJpanel().setBackground(new Color(85, 227, 237));
                }
                break;

            case 10:
                casillas[posicionTecla].comprobar();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}