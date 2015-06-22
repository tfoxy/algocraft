package gui.modelo;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import java.util.List;

public abstract class ListInstanceModel<E>
        extends AbstractListModel<E> implements ComboBoxModel<E> {

    protected void fireListChanged(int oldListSize, int newListSize) {
        if (newListSize < oldListSize)
            fireRemoved();
        else if (newListSize > oldListSize)
            fireAdded();
        else if (oldListSize != 0)
            fireChanged();
    }

    protected abstract List<E> list();

    @Override
    public int getSize() {
        return list().size();
    }

    @Override
    public E getElementAt(int index) {
        return list().get(index);
    }

    protected void fireAdded() {
        fireIntervalAdded(this, list().size() - 1, list().size() - 1);
    }

    protected void fireChanged() {
        fireContentsChanged(this, -1, -1);
    }

    protected void fireRemoved() {
        fireIntervalRemoved(this, 0, 0);
    }
}
