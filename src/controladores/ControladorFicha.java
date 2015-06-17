package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VentanaFicha;
import tablero.Direccion;

import ficha.Ficha;

public class ControladorFicha {

	Ficha ficha;
	
	public ControladorFicha(Ficha ficha) {
		this.ficha = ficha;
	}

		//mover en todas las direcciones XD
	private class EscuchaBotonAbajo implements ActionListener
	{	public void actionPerformed(ActionEvent e)
		{	
		ficha.intentarMovimiento(Direccion.ABAJO);
		}
	}
	
	public ActionListener getListenerBotonAbajo() {
		return new EscuchaBotonAbajo();
	}
	
	private class EscuchaBotonArriba implements ActionListener
	{	public void actionPerformed(ActionEvent e)
		{	
		ficha.intentarMovimiento(Direccion.ARRIBA);
		}
	}
	
	public ActionListener getListenerBotonArriba() {
		return new EscuchaBotonArriba();
	}
	
	private class EscuchaBotonDerecha implements ActionListener
	{	public void actionPerformed(ActionEvent e)
		{	
		ficha.intentarMovimiento(Direccion.DERECHA);
		}
	}
	
	public ActionListener getListenerBotonDerecha() {
		return new EscuchaBotonDerecha();
	}
	
	private class EscuchaBotonIzquierda implements ActionListener
	{	public void actionPerformed(ActionEvent e)
		{	
		ficha.intentarMovimiento(Direccion.IZQUIERDA);
		}
	}
	
	public ActionListener getListenerBotonIzquierda() {
		return new EscuchaBotonIzquierda();
	}
	//mover en todas las direcciones XD
}
