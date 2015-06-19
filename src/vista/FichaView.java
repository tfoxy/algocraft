package vista;

import controladores.ControladorFicha;
import ficha.Ficha;
import gui.controlador.KeyboardMap;
import gui.modelo.Observable;
import gui.modelo.Observer;
import tablero.Direccion;

import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;

public class FichaView extends JPanel {

    private Ficha ficha;

    private final JLabel nombreLabel = new JLabel();
    private final JLabel movimientoLabel = new JLabel();
    private final JLabel movimientoMaximoLabel = new JLabel();
    private final JLabel vidaLabel = new JLabel();
    private final JLabel escudoLabel = new JLabel();
    private final JLabel energiaLabel = new JLabel();

    private final JButton botonArriba = new JButton("Arriba");
    private final JButton botonAbajo = new JButton("Abajo");
    private final JButton botonIzquierda = new JButton("Izquierda");
    private final JButton botonDerecha = new JButton("Derecha");
    private final JButton botonAtaque = new JButton("Ataque");


    public FichaView(ControladorFicha control) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel panelStats = new JPanel();
        panelStats.add(nombreLabel);
        panelStats.add(new JLabel(" "));
        panelStats.add(movimientoLabel);
        panelStats.add(new JLabel("/"));
        panelStats.add(movimientoMaximoLabel);
        panelStats.add(new JLabel(" V"));
        panelStats.add(vidaLabel);
        panelStats.add(new JLabel(" E"));
        panelStats.add(escudoLabel);
        panelStats.add(new JLabel(" M"));
        panelStats.add(energiaLabel);
        add(panelStats);

        JPanel panelBotonesMovimiento = new JPanel();
        panelBotonesMovimiento.add(botonArriba);
        panelBotonesMovimiento.add(botonAbajo);
        panelBotonesMovimiento.add(botonIzquierda);
        panelBotonesMovimiento.add(botonDerecha);
        add(panelBotonesMovimiento);

        JPanel panelBotonesDeAccion = new JPanel();
        panelBotonesDeAccion.add(botonAtaque);
        add(panelBotonesDeAccion);

        addActionListeners(control);

        botonAtaque.setMnemonic(KeyEvent.VK_A);

        cambiarFicha(control.ficha());

        control.cambioDeFichaObservable().addObserver(new Observer<Ficha>() {
            @Override
            public void update(Observable<Ficha> o, Ficha ficha) {
                cambiarFicha(ficha);
            }
        });

        control.accionObservables().observe(new Observer<ControladorFicha>() {
            @Override
            public void update(Observable<ControladorFicha> o, ControladorFicha control) {
                actulizarFicha();
            }
        });
    }


    private void addActionListeners(final ControladorFicha control) {
        botonAbajo.addActionListener(control.getMovimientoListener(Direccion.ABAJO));
        botonArriba.addActionListener(control.getMovimientoListener(Direccion.ARRIBA));
        botonDerecha.addActionListener(control.getMovimientoListener(Direccion.DERECHA));
        botonIzquierda.addActionListener(control.getMovimientoListener(Direccion.IZQUIERDA));

        botonAtaque.addActionListener(control.getAtaqueListener());

        KeyboardMap keyboardMap = new KeyboardMap(this);
        keyboardMap.put("UP", control.getMovimientoListener(Direccion.ARRIBA));
        keyboardMap.put("DOWN", control.getMovimientoListener(Direccion.ABAJO));
        keyboardMap.put("LEFT", control.getMovimientoListener(Direccion.IZQUIERDA));
        keyboardMap.put("RIGHT", control.getMovimientoListener(Direccion.DERECHA));
    }


    private void cambiarFicha(Ficha ficha) {
        this.ficha = ficha;
        actulizarFicha();
    }

    private void actulizarFicha() {
        nombreLabel.setText(ficha.nombre());
        movimientoLabel.setText(ficha.movimiento() + "");
        movimientoMaximoLabel.setText(ficha.movimientoMaximo() + "");
        vidaLabel.setText(ficha.barras().vidaActual() + "");
        escudoLabel.setText(ficha.barras().escudoActual() + "");
        energiaLabel.setText(ficha.barras().energiaActual() + "");
    }
}
