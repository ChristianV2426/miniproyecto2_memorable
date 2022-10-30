/*
    Archivo: Principal.java
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
    HISTORIA: Se realizará un juego de ahorcado el cual permita elegir la categoría de palabras
    que se quieren adivinar. Este juego preguntará el numbre del participante antes de iniciar,
    para posteriormente darle a seleccionar el número de palabras que desea adivinar y la respectiva
    categoría. Cuando se hayan consumido las palabras se preguntará si desea continuar o no;
    si se desea continuar se volverá a preguntar el número de palabras y la categoría seleccionada
    para la siguiente ronda; si no desea continuar se mostrarán las estadísticas de juego. En la
    VentanaInicio podrá ir a respectivas ventanas que informen de las instrucciones y el objetivo
    educativo de la aplicación.
*/

package co.edu.univalle.vista;

public class Principal {
    public static void main(String[] args) {
        VentanaInicio ventanaInicio = new VentanaInicio();
    }
}