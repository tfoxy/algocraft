package gui.controlador;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class KeyboardMap {

    private final InputMap inputMap;
    private final ActionMap actionMap;

    public KeyboardMap(JComponent jComponent) {
        this(jComponent, JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    /**
     * @param condition one of javax.swing.JComponent#WHEN_IN_FOCUSED_WINDOW,
     *                  WHEN_FOCUSED, WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
     */
    public KeyboardMap(JComponent jComponent, int condition) {
        this(
                jComponent.getInputMap(condition),
                jComponent.getActionMap()
        );
    }

    public KeyboardMap(InputMap inputMap, ActionMap actionMap) {
        this.inputMap = inputMap;
        this.actionMap = actionMap;
    }

    public void put(String keyString, Action action) {
        final KeyStroke key = KeyStroke.getKeyStroke(keyString);
        inputMap.put(key, keyString);
        actionMap.put(keyString, action);
    }
}
