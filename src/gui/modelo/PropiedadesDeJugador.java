package gui.modelo;

import juego.ColorDeJugador;
import juego.Jugador;
import juego.Raza;
import juego.Recursos;

import javax.swing.DefaultComboBoxModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.Color;

public class PropiedadesDeJugador {
    private static final Raza[] RAZAS = Raza.values();
    private static final Color[] COLORS =
            ColorDeJugador.LISTA.toArray(new Color[ColorDeJugador.LISTA.size()]);

    // Creado por ConfiguracionDeInicio
    PropiedadesDeJugador(String nombre, Raza raza, Color color) {
        this.nombreModel = new PlainDocument();
        try {
            this.nombreModel.insertString(0, nombre, null);
        } catch (BadLocationException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }

        this.razaModel = new DefaultComboBoxModel<>(RAZAS);
        this.razaModel.setSelectedItem(raza);
        this.colorModel = new DefaultComboBoxModel<>(COLORS);
        this.colorModel.setSelectedItem(color);
    }

    private PlainDocument nombreModel;
    private DefaultComboBoxModel<Raza> razaModel;
    private DefaultComboBoxModel<Color> colorModel;

    public PlainDocument getNombreModel() {
        return nombreModel;
    }

    public DefaultComboBoxModel<Raza> getRazaModel() {
        return razaModel;
    }

    public DefaultComboBoxModel<Color> getColorModel() {
        return colorModel;
    }

    public String getNombre() {
        try {
            return nombreModel.getText(0, nombreModel.getLength());
        } catch (BadLocationException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public Raza getRaza() {
        return (Raza) razaModel.getSelectedItem();
    }

    public Color getColor() {
        return (Color) colorModel.getSelectedItem();
    }

    Jugador construirJugador(Recursos recursos) {
        return new Jugador(getNombre(), getRaza(), recursos, getColor());
    }
}
