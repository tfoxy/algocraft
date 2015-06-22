package gui.modelo;

import ficha.Ficha;
import stats.Transportacion;

import java.util.List;

public class FichasCargadas extends ListInstanceModel<Ficha> {
    private final FichaSeleccionada fichaSeleccionada;
    private Ficha transporte;

    public FichasCargadas(FichaSeleccionada fichaSeleccionada) {
        this.fichaSeleccionada = fichaSeleccionada;
        this.transporte = fichaSeleccionada.ficha();
    }


    private Ficha transporte() {
        return transporte;
    }

    @Override
    public List<Ficha> list() {
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

    void cambiarTransporte(Ficha nuevoTransporte) {
        int antiguaCantidad = list().size();

        this.transporte = nuevoTransporte;

        int nuevaCantidad = list().size();

        fireListChanged(antiguaCantidad, nuevaCantidad);
    }
}
