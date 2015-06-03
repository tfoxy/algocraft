package Tablero;

public class Cordenada {
	
	private int X;
	private int Y;
	static private int XMax = 100000000;
	static private int YMax = 100000000;
	
	public static void Limites(int x, int y){
		XMax = x;
		YMax = y;
	}
	
	public Cordenada(int x, int y){
		X = x;
		Y = y;
	}

	
	public boolean equals(Object Otra) {
        //noinspection SimplifiableIfStatement
        if (Otra instanceof Cordenada){
            return (X == ((Cordenada)Otra).X && Y == ((Cordenada)Otra).Y);
        }

		return false;
	}

	public int hashCode() {
        return X;
    }


	
	public int DistanciaAObjetivo (Cordenada Objetivo){
//MasDificilDeLoQueParece. QUe pasa si conviernteo bollean a int?
		return 	(int) Math.sqrt((X - Objetivo.X)*(X - Objetivo.X) + (Y - Objetivo.Y)*(Y - Objetivo.Y));
	}
	
	//movimiento entre cordenandas. Si llegas al limite no te avisan. Quedas caminando contra la pered.
	
	public Cordenada arriba() {
		if(Y+1 <= YMax)
		return new Cordenada(X,Y+1);
		
		return this;
	}
	public Cordenada abajo() {
		if(Y+1 >= 0)
		return new Cordenada(X,Y-1);
		
		return this;
	}
	public Cordenada derecha() {
		if(X+1 <= XMax)
		return new Cordenada(X+1,Y);
	
		return this;
	}
	public Cordenada izquierda() {
		if(X+1 >= 0)
		return new Cordenada(X-1,Y);
		
		return this;
	}

}
