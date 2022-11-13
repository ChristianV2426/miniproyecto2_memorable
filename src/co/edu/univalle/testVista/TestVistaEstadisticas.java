package co.edu.univalle.testVista;

import co.edu.univalle.vista.*;
import co.edu.univalle.logica.*;

public class TestVistaEstadisticas {
    public static void main(String[] args) {
        VentanaJuego ventanaJuego = new VentanaJuego();
        ventanaJuego.setVisible(false);
        VentanaEstadisticas ventana = new VentanaEstadisticas(new Juego(1, ventanaJuego));
    }   
}