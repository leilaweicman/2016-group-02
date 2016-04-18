package grupo2.tpAnual;

public class Servicio {
	private String _Nombre;
	private Rango _Disponibilidad;
	
	public String getNombre() {
		return _Nombre;
	}

	public void setNombre(String Nom) {
		this._Nombre = Nom;
	}
	
	public Rango getDisponibilidad() {
		return _Disponibilidad;
	}

	public void setDisponibilidad(Rango dis) {
		this._Disponibilidad = dis;
	}
}
