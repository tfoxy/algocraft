package gui.modelo;

import ficha.Ficha;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import java.util.List;

public class FichasCargadas extends AbstractListModel<Ficha> implements ComboBoxModel<Ficha> {
    private final FichaSeleccionada fichaSeleccionada;

    public FichasCargadas(FichaSeleccionada fichaSeleccionada) {
        this.fichaSeleccionada = fichaSeleccionada;
    }


    private Ficha transporte() {
        return fichaSeleccionada.ficha();
    }

    private List<Ficha> fichas() {
        return transporte().fichasCargadas();
    }


    @Override
    public void setSelectedItem(Object anItem) {
        fichaSeleccionada.descargar((Ficha) anItem);
    }

    @Override
    public Object getSelectedItem() {
        return null;
    }

    @Override
    public int getSize() {
        return fichas().size();
    }

    @Override
    public Ficha getElementAt(int index) {
        return fichas().get(index);
    }
}
