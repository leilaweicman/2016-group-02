package grupo2.tpAnual;

public class Rubro {
	private String _Tipo;
	private int _RadioCercania;
	private Disponibilidad _Disponibilidad;
	
	public String getTipo() {
		return _Tipo;
	}

	public void setTipo(String tip) {
		this._Tipo = tip;
	}
	
	public Disponibilidad getDisponibilidad() {
		return _Disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad dis) {
		this._Disponibilidad = dis;
	}
	
	public int getRadioCercania() {
		return _RadioCercania;
	}

	public void setRadioCercania(int Radio) {
		this._RadioCercania = Radio;
	}
	
	public boolean Busqueda(String tipo){
		return _Tipo.equals(tipo);
	}
}
