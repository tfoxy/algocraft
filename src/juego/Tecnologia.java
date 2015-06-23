package juego;

public enum Tecnologia {
    PROTOSS("Protoss"),
    TERRAN("Terran"),
    ACCESO("Acceso"),
    PUERTO_ESTELAR("Puerto estelar"),
    GAIA("Gaia"),
    BARRACA("Barraca"),
    FABRICA("Fábrica"),
    ARCHIVOS_TEMPLARIOS("Archivos templarios");

    private final String nombre;

    Tecnologia (String nombre) {
        this.nombre = nombre;
    }

    public String nombre() {
        return nombre;
    }
}
