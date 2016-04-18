package grupo2.tpAnual;

public class Servicio {
	private String _Nombre;
	private Disponibilidad _Disponibilidad;
	
	public String getNombre() {
		return _Nombre;
	}

	public void setNombre(String Nom) {
		this._Nombre = Nom;
	}
	
	public Disponibilidad getDisponibilidad() {
		return _Disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad dis) {
		this._Disponibilidad = dis;
	}
}
