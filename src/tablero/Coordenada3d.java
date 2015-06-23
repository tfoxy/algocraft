package tablero;

// TIERRA = 1;
// AIRE = 2;

public class Coordenada3d extends Coordenada {

    public final int z;

    public Coordenada3d(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public Coordenada3d(Coordenada coordenada, int z) {
        super(coordenada.getX(), coordenada.getY());
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Coordenada3d that = (Coordenada3d) o;

        return z == that.z;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + z;
        return result;
    }

    @Override
    public String toString() {
        return "{" + x + ',' + y + ',' + z + '}';
    }

    @Override
    public Coordenada3d dameCoordenadaHacia(Direccion direccion) {
        Coordenada coordenada = super.dameCoordenadaHacia(direccion);
        return new Coordenada3d(coordenada.getX(), coordenada.getY(), z);
    }

    public Coordenada proyeccion() {
        return new Coordenada(getX(), getY());
    }
}
