package tablero;

public class Coordenada {

    private int x;
    private int y;
    
    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordenada coordenada = (Coordenada) o;

        return x == coordenada.x && y == coordenada.y;
    }


    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }


    public int distanciaAObjetivo(Coordenada objetivo) {
        int xDist = x - objetivo.x;
        int yDist = y - objetivo.y;

        int xSquare = xDist * xDist;
        int ySquare = yDist * yDist;

        return (int) Math.sqrt(xSquare + ySquare);
    }
    
        //new 6
    public Coordenada dameCordenadaAtu (Movimiento dirreccion){
        return new Coordenada(x + dirreccion.getX(),y + dirreccion.getY());
    }

}
