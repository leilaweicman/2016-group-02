package grupo2.tpAnual;

public class Banco extends POI {
	private Rango _Disponibilidad;
	
	public Rango getDisponibilidad() {
		return _Disponibilidad;
	}

	public void setDisponibilidad(Rango dis) {
		this._Disponibilidad = dis;
	}
	
	public boolean Busqueda (String Texto){
		return getPalabraClave().equals(Texto);
	}
}
