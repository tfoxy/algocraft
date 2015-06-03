package Tablero;

public class Cordenada {
	
	private int x;
	private int y;
	private static int xMax = 100000000;
	private static int yMax = 100000000;
	
	public static void Limites(int x, int y){
		xMax = x;
		yMax = y;
	}
	
	public Cordenada(int x, int y){
		this.x = x;
		this.y = y;
	}

	
	@Override
	public boolean equals(Object Otra) {
        //noinspection SimplifiableIfStatement
        if (Otra instanceof Cordenada){
            return (x == ((Cordenada)Otra).x && y == ((Cordenada)Otra).y);
        }

		return false;
	}

	@Override
	public int hashCode() {
        return x;
    }


	
	public int DistanciaAObjetivo (Cordenada Objetivo){
//MasDificilDeLoQueParece. QUe pasa si conviernteo bollean a int?
		return 	(int) Math.sqrt((x - Objetivo.x)*(x - Objetivo.x) + (y - Objetivo.y)*(y - Objetivo.y));
	}
	
	//movimiento entre cordenandas. Si llegas al limite no te avisan. Quedas caminando contra la pered.
	
	public Cordenada arriba() {
		if(y +1 <= yMax)
		return new Cordenada(x, y +1);
		
		return this;
	}
	public Cordenada abajo() {
		if(y +1 >= 0)
		return new Cordenada(x, y -1);
		
		return this;
	}
	public Cordenada derecha() {
		if(x +1 <= xMax)
		return new Cordenada(x +1, y);
	
		return this;
	}
	public Cordenada izquierda() {
		if(x +1 >= 0)
		return new Cordenada(x -1, y);
		
		return this;
	}

}
