package gui.modelo;

public class JuegoLogger extends Observable<String> {

    public static final JuegoLogger EMPTY = new JuegoLogger() {
        @Override
        public void log(String msg) {
            // noop
        }
    };

    private String lastLog = "";

    public void log(String msg) {
        lastLog = msg;

        this.notifyObservers(msg);
    }

    public void log(Exception e) {
        this.log("ERROR: " + e.getLocalizedMessage());
    }

    public String getLastLog() {
        return lastLog;
    }
}
