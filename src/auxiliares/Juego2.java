package auxiliares;

import javax.swing.JFrame;

import ficha.Ficha;
import ficha.protoss.unidad.Zealot;

import juego.Jugador;
import juego.Raza;
import juego.Recursos;

import tablero.Coordenada;
import tablero.Tablero;
import vista.VentanaColor;



public class Juego2 {

	public Recursos recursos;
	public Tablero tablero;
	
    int tamanioGrilla = 20;

    ModeloObservable modelo;

    public void preparar() {
    	
    	tablero = new Tablero(tamanioGrilla,tamanioGrilla);
    }

    public void iniciar() { //primero se deve ejecutar prepararar.
        inicializarModelo();

        completarModelo();

        crearVentanas();
    }

    private void crearVetanaColor() {

        JFrame ventanaColor = new VentanaColor(tamanioGrilla, modelo);

        ventanaColor.setSize(500, 400);

        ventanaColor.setLocation(8, 0);

        ventanaColor.setVisible(true);

        ventanaColor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void crearVentanas() {

       crearVetanaColor();
    }

    private void inicializarModelo() {

        modelo = new ModeloObservable(tamanioGrilla);
    }

    private void completarModelo() { //aca se caraga los datos.

        for (int x = 0; x < tamanioGrilla; x++) {

            for (int y = 0; y < tamanioGrilla; y++) {

                int numeroColor;
                if (tablero.hayEspacioTerreste(new Coordenada (x,y)))
                	{numeroColor = 0;}
                else 
                	{numeroColor = 1;}

                modelo.setModeloDato(new Posicion(x, y), numeroColor);
            }
        }
    }

}
