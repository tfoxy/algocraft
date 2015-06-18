package vista;

import controladores.ControladorFicha;
import ficha.Ficha;
import gui.modelo.ElementObservable;
import gui.modelo.ElementObserver;
import tablero.Coordenada3d;
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


    public FichaView(ControladorFicha control) {
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

        botonAbajo.addActionListener(control.getMovimientoListener(Direccion.ABAJO));
        botonArriba.addActionListener(control.getMovimientoListener(Direccion.ARRIBA));
        botonDerecha.addActionListener(control.getMovimientoListener(Direccion.DERECHA));
        botonIzquierda.addActionListener(control.getMovimientoListener(Direccion.IZQUIERDA));
        // Conectamos esta vista con el modelo

        cambiarFicha(control.ficha());

        control.observarCambioDeFicha(new ElementObserver<Ficha>() {
            @Override
            public void update(ElementObservable<Ficha> o, Ficha prevFicha) {
                cambiarFicha(o.get());
            }
        });

        control.observarMovimiento(new ElementObserver<Coordenada3d>() {
            @Override
            public void update(ElementObservable<Coordenada3d> o, Coordenada3d prevPos) {
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
