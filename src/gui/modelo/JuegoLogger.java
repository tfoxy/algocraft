package gui.modelo;

public class JuegoLogger extends Observable<String> {

    private String lastLog = "";

    public void log(String msg) {
        lastLog = msg;

        this.notifyObservers(msg);
    }

    public String getLastLog() {
        return lastLog;
    }
}
