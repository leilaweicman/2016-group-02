package grupo2.tpAnual;

import java.util.Date;

public class Parada extends POI{
	private int _Linea;
	
	public int getLinea() {
		return _Linea;
	}

	public void setLinea(int linea) {
		this._Linea = linea;
	}
	
	public boolean CalcularDisponibilidad(Date fecha){
		return true;
	}
}
