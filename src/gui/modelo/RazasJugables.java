package gui.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import juego.Raza;


public class RazasJugables extends AbstractListModel<Raza> implements ComboBoxModel<Raza>  {

	private List<Raza> razas= new ArrayList<Raza>();
	private Raza selccionada;
	
	public RazasJugables(){
		razas.add(Raza.PROTOSS);
		razas.add(Raza.TERRAN);
		selccionada = razas.get(1);
	}
	
	@Override
	public Raza getElementAt(int index) {
		return razas.get(index);
	}

	@Override
	public int getSize() {
		return razas.size();
	}

	@Override
	public Object getSelectedItem() {
		return selccionada;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.selccionada = (Raza) anItem;
        fireContentsChanged(this, -1, -1);
	}

}
