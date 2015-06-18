package vista;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import controladores.ControladorFicha;
import ficha.Ficha;
import gui.modelo.TableroObservable;
import tablero.Direccion;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FichaView extends JPanel {

    private Ficha ficha;

    private JLabel nombreLabel = new JLabel();
    private JLabel movimientoLabel = new JLabel();
    private JLabel movimientoMaximoLabel = new JLabel();

    private JButton botonArriba = new JButton("Arriba");
    private JButton botonAbajo = new JButton("Abajo");
    private JButton botonIzquierda = new JButton("Izquierda");
    private JButton botonDerecha = new JButton("Derecha");


    public FichaView(final ControladorFicha control) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel panelStats = new JPanel();
        panelStats.add(nombreLabel);
        panelStats.add(new JLabel(" "));
        panelStats.add(movimientoLabel);
        panelStats.add(new JLabel("/"));
        panelStats.add(movimientoMaximoLabel);
        add(panelStats);

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonArriba);
        panelBotones.add(botonAbajo);
        panelBotones.add(botonDerecha);
        panelBotones.add(botonIzquierda);

        add(panelBotones);

        setSize(400, 100);
        setLocation(0, 500);
        setVisible(true);


        botonAbajo.addActionListener(control.getListenerMovimiento(Direccion.ABAJO));
        botonArriba.addActionListener(control.getListenerMovimiento(Direccion.ARRIBA));
        botonDerecha.addActionListener(control.getListenerMovimiento(Direccion.DERECHA));
        botonIzquierda.addActionListener(control.getListenerMovimiento(Direccion.IZQUIERDA));
        // Conectamos esta vista con el modelo

        cambiarFicha(control.ficha());

        control.observarCambioDeFicha(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                cambiarFicha(control.ficha());
            }
        });

        control.observarMovimiento(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                actulizarFicha();
            }
        });
    }


    private void cambiarFicha(Ficha ficha) {
        this.ficha = ficha;
        actulizarFicha();
    }

    private void actulizarFicha() {
        nombreLabel.setText(ficha.nombre());
        movimientoLabel.setText(ficha.movimiento() + "");
        movimientoMaximoLabel.setText(ficha.movimientoMaximo() + "");
    }
}
