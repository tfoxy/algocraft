package gui.modelo;

import ficha.Ficha;
import stats.Transportacion;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import java.util.List;

public class FichasCargadas extends AbstractListModel<Ficha> implements ComboBoxModel<Ficha> {
    private final FichaSeleccionada fichaSeleccionada;
    private Ficha transporte;

    public FichasCargadas(FichaSeleccionada fichaSeleccionada) {
        this.fichaSeleccionada = fichaSeleccionada;
        this.transporte = fichaSeleccionada.ficha();
    }


    private Ficha transporte() {
        return transporte;
    }

    private List<Ficha> fichas() {
        if (transporte() == null)
            return Transportacion.VACIA.fichasCargadas();
        else
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

    void fireAdded() {
        fireIntervalAdded(this, fichas().size() - 1, fichas().size() - 1);
    }

    void fireRemoved() {
        fireIntervalRemoved(this, 0, 0);
    }

    void cambiarTransporte(Ficha nuevoTransporte) {
        int antiguaCantidad = fichas().size();
        int nuevaCantidad = nuevoTransporte != null
                ? nuevoTransporte.fichasCargadas().size()
                : 0;

        this.transporte = nuevoTransporte;

        if (nuevaCantidad < antiguaCantidad)
            fireRemoved();
        else if (nuevaCantidad > antiguaCantidad)
            fireAdded();
        else if (antiguaCantidad != 0)
            fireContentsChanged(this, -1, -1);
    }
}
