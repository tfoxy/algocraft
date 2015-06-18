package gui.controlador;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyboardListener {

    private final HashMap<Integer, KeyListener> keyPressedListeners;
    private final HashMap<Integer, KeyListener> keyTypedListeners;
    private final HashMap<Integer, KeyListener> keyReleasedListeners;

    public KeyboardListener() {
        keyPressedListeners = new HashMap<>();
        keyTypedListeners = new HashMap<>();
        keyReleasedListeners = new HashMap<>();

        keyboard().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                HashMap<Integer, KeyListener> listeners = getListeners(e.getID());
                KeyListener listener = listeners.get(e.getKeyCode());

                if (listener != null) {
                    callAction(listener, e);
                }

                return false;
            }
        });
    }

    private static KeyboardFocusManager keyboard() {
        return KeyboardFocusManager.getCurrentKeyboardFocusManager();
    }

    private HashMap<Integer, KeyListener> getListeners(int keyEvent) {
        switch (keyEvent) {
            case KeyEvent.KEY_PRESSED: return keyPressedListeners;
            case KeyEvent.KEY_TYPED: return keyTypedListeners;
            case KeyEvent.KEY_RELEASED: return keyReleasedListeners;
            default: throw new RuntimeException();
        }
    }

    private static void callAction(KeyListener listener, KeyEvent e) {
        switch (e.getID()) {
            case KeyEvent.KEY_PRESSED: listener.keyPressed(e); return;
            case KeyEvent.KEY_TYPED: listener.keyTyped(e); return;
            case KeyEvent.KEY_RELEASED: listener.keyReleased(e); return;
            default: throw new RuntimeException();
        }
    }

    public void addListener(int keyEvent, int keyCode, KeyListener listener) {
        HashMap<Integer, KeyListener> listeners = getListeners(keyEvent);
        listeners.put(keyCode, listener);
    }
}
