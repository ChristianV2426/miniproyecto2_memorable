/*
    Archivo: Sonido.java
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
    CLASE: Sonido
    INTENCIÓN: Esta clase se encargará de implementar un controlador para los sonidos del juego.
    RELACIONES:
    -Conoce a un objeto de clase VentanaJuego.
*/

/*
    Todos los sonidos fueron tomados de Pixabay, licencia de uso libre.

    Lista de los archivos de sonidos utilizados: 
        Timer: ./src/co/edu/univalle/vista/sonidos/timer.wav
        Acierto: ./src/co/edu/univalle/vista/sonidos/acierto.wav
        Fallo: ./src/co/edu/univalle/vista/sonidos/fallo.wav
        Partida Finalizada: ./src/co/edu/univalle/vista/sonidos/partidaFinalizada.wav
*/

package co.edu.univalle.logica;

import co.edu.univalle.vista.*;
import java.io.*;
import javax.sound.sampled.*;


public class Sonido {
    private VentanaJuego ventanaJuego;
    private Clip sonidoAReproducir;
    private long tiempoReproducido;

    public Sonido(VentanaJuego ventanaJuego){
        this.ventanaJuego = ventanaJuego;
        // File rutaPorDefecto = new File("./");
        // System.out.println("Ruta por defecto del sistema: " + rutaPorDefecto.getAbsolutePath());
    }

    public AudioInputStream alistarArchivo(String rutaDelArchivo){
        try{
            File archivoSonido = new File(rutaDelArchivo);
            AudioInputStream streamSonido = AudioSystem.getAudioInputStream(archivoSonido);
            return streamSonido;

        } catch (Exception exception){
            System.out.println("Hubo un problema al cargar el archivo de sonido que se encuentra en la ruta " + rutaDelArchivo);
            return null;
        }
    }

    public void reproducirSonido(String rutaDelArchivo){
        AudioInputStream archivoAReproducir = alistarArchivo(rutaDelArchivo);
        try{
            sonidoAReproducir = AudioSystem.getClip();
            sonidoAReproducir.open(archivoAReproducir);
        } catch (Exception exception){
            System.out.println("Hubo un problema al iniciar el sonido que se encuentra en la ruta: " + rutaDelArchivo);
        }
        if (ventanaJuego.getEstadoSonido()){
            sonidoAReproducir.start();
        } else if (!ventanaJuego.getEstadoSonido()){
            tiempoReproducido = (long) 1;
        }
    }

    public void reproducirSonido(){
        if(sonidoAReproducir != null && tiempoReproducido != 0l && ventanaJuego.getEstadoSonido()){
            sonidoAReproducir.setMicrosecondPosition(tiempoReproducido);
            sonidoAReproducir.start();
        }
    }

    public void pausarSonido(){
        if (sonidoAReproducir != null){
            tiempoReproducido = sonidoAReproducir.getMicrosecondPosition();
            sonidoAReproducir.stop();
        }
    }

    public void pararSonido(){
        if (sonidoAReproducir !=  null){
            sonidoAReproducir.close();
        }
    }
}
