package controladores;

//esto aun falta
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import vista.VistaModeloDato;

import modelo.Modelo;

public class ControladorMouse extends MouseAdapter {

	Modelo modelo;

    VistaModeloDato vista;

    public ControladorMouse(Modelo modelo, VistaModeloDato vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void mousePressed(MouseEvent mouseEvent) {

        // Ojo al piojo.
        // El manejo de las coordenadas del mouse debe ser encapsulado por la clase Posicion
        // modelo.inicializarModeloDato(new Posicion(mouseEvent.getX(), mouseEvent.getY()));

        modelo.inicializarModeloDato(vista.coordenada);
    }
}
