package gui;


public final class Main {
    private Main() {
        // noop
    }

    public static void main(String[] args) {
        VentanaPrincipal.iniciarPropiedadesGlobales();
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.mostrarInicio();
    }
}
