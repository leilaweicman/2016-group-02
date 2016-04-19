package grupo2.tpAnual;

public class Rubro {
	private String _Tipo;
	private double _RadioCercania;
	private Rango _Disponibilidad;
	
	public String getTipo() {
		return _Tipo;
	}

	public void setTipo(String tip) {
		this._Tipo = tip;
	}
	
	public Rango getDisponibilidad() {
		return _Disponibilidad;
	}

	public void setDisponibilidad(Rango dis) {
		this._Disponibilidad = dis;
	}
	
	public double getRadioCercania() {
		return _RadioCercania;
	}

	public void setRadioCercania(double Radio) {
	//El radio de cercania tiene que ser en kilometros.
		this._RadioCercania = Radio;
	}
	
	public boolean Busqueda(String tipo){
		return _Tipo.equals(tipo);
	}
}
