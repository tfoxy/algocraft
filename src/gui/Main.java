package gui;


import gui.controlador.ControladorInicio;
import gui.vista.VistaInicio;

public class Main {
	
	public static void main(String[] args){
	ControladorInicio control = new ControladorInicio();
	new VistaInicio(control);
    }
}
