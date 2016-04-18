package grupo2.tpAnual;

import java.util.Date;

public class Parada extends POI{
	private String _Linea;
	
	public String getLinea() {
		return _Linea;
	}

	public void setLinea(String linea) {
		this._Linea = linea;
	}
	
	public boolean CalcularDisponibilidad(Date fecha){
		return true;
	}
	
	public boolean Busqueda (String linea){
		return _Linea.equals(linea);
	}
}

