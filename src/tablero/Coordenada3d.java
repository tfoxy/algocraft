package tablero;

public class Coordenada3d extends Coordenada {

    private int z;

    public Coordenada3d(int x, int y, int z) {
        super(x, y);
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

    public Coordenada proyeccion() {
        return new Coordenada(getX(), getY());
    }

    public Coordenada3d dameCordenada3dHacia(Direccion direccion) {
        Coordenada coordenada = this.dameCordenadaHacia(direccion);
        return new Coordenada3d(coordenada.getX(), coordenada.getY(), z);
    }
}
