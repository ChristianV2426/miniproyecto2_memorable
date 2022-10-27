# Proyecto 2 - Memorable
Fundamenentos de Programación Orientada a Eventos - Universidad del Valle

Autores: 
- Juan Camilo Narváez Tascón
- Christian David Vargas Gutiérrez


Entregables:
- Mockups de interfaces gráficas
- Diagrama de Clases
- URL del repositorio con el código de la aplicación
- Video con MVP
- Product Backlog en Jira - Evidencias


## Historia de usuario

El departamento de educación y aprendizaje de la Universidad del Valle, requiere construir un software que les permita a los estudiantes entrenar la capacidad de codificación y recuerdo de información visual y espacial. El jefe del departamento ha descubierto un juego en una página de internet y ha decidido contactar al grupo de desarrollo de software de la clase FPOE para que reconstruya el juego, de tal manera que se pueda usar como aplicación de escritorio, dado que hay algunos sitios que no tienen conectividad.

## Requerimientos

La aplicación por desarrollar deberá contar con las mismas funcionalidades de la versión en la web, tales como:
- Cómo Jugar
- Para qué sirve
- Jugar

La versión de escritorio deberá funcionar igual a la versión en línea. Una vez empiece el juego aparecerá en la interfaz un conjunto de recuadros con figuras aleatorias de diferente color durante un determinado tiempo, un vez finalizado el tiempo las figuras se ocultarán y el programa preguntará por algunas características (forma y color) de las figuras mostradas, el usuario deberá selecciona con el clic el recuadro de las figuras que cumplen con las características indagadas. Si la respuesta es correcta, se dará 100 puntos, sino se quitará una de las 3 vidas de la ronda. A medida que avanza el juego, la cantidad de figuras mostradas irá aumentando paulatinamente.

Al iniciar el juego se deberá solicitar el nombre del jugador que deberá aparecer en alguna parte de la interfaz cuando se está realizando el juego.
Se espera que el usuario también pueda usar el teclado para seleccionar las figuras, utilizando las teclas de flecha y finalmente presionando la tecla enter o barra espaciadora sobre el recuadro seleccionado. A medida que se desplaza con el teclado, el recuadro activo debe visualizar que está siendo seleccionado.

Al terminar las 3 vidas asignadas a la ronda del juego, éste terminará y deberá mostrar:
- Cantidad de aciertos
- Cantidad de fallos
- Puntaje total
- Tiempo jugado

