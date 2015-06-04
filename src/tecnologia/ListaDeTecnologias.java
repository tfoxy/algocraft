package tecnologia;

import java.util.ArrayList;


public class ListaDeTecnologias {
    private ArrayList<Tecnologia> tecnologias = new ArrayList<>();

    public void agregar(Tecnologia entra) {
        tecnologias.add(entra);
    }

    public void quitar(Tecnologia sale) {
        tecnologias.remove(sale);
    }

    public boolean tiene(Tecnologia esto) {
        return tecnologias.contains(esto);
    }

    public boolean contengo(ListaDeTecnologias tecnologias) {

        return this.tecnologias.containsAll(tecnologias.tecnologias);
    }

}
