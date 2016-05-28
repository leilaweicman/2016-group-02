package grupo2.tpAnual;

public class Rubro {
	private String tipoRubro;
	private double radioCercania;
	private Rango disponibilidad;

	public String getTipo() {
		return tipoRubro;
	}

	public void setTipo(String tip) {
		this.tipoRubro = tip;
	}

	public Rango getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Rango dis) {
		this.disponibilidad = dis;
	}

	public double getRadioCercania() {
		return radioCercania;
	}

	public void setRadioCercania(double Radio) {
		// El radio de cercania tiene que ser en kilometros.
		this.radioCercania = Radio;
	}

	public boolean Busqueda(String tipo) {
		return tipoRubro.equals(tipo);
	}
}
