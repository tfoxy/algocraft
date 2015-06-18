package vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import controladores.ControladorFicha;
import ficha.Ficha;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaFicha extends JFrame {

    private JButton botonArriba = new JButton("Arriba");
    private JButton botonAbajo = new JButton("Abajo");
    private JButton botonIzquierda = new JButton("Izquierda");
    private JButton botonDerecha = new JButton("Derecha");


    public VentanaFicha(ControladorFicha control) {
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonAbajo);
        panelBotones.add(botonArriba);
        panelBotones.add(botonDerecha);
        panelBotones.add(botonIzquierda);

        add("Center", panelBotones);


        setSize(300, 100);
        setLocation(0, 500);
        setVisible(true);

        addWindowListener(new CloseListener());


        botonAbajo.addActionListener(control.getListenerBotonAbajo());
        botonArriba.addActionListener(control.getListenerBotonArriba());
        botonDerecha.addActionListener(control.getListenerBotonDerecha());
        botonIzquierda.addActionListener(control.getListenerBotonIzquierda());
        // Conectamos esta vista con el modelo

        cambiarFicha(control.ficha());

        control.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                cambiarFicha((Ficha) arg);
            }
        });
    }


    private void cambiarFicha(Ficha ficha) {
        setTitle(ficha.nombre());
    }


    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }
}
