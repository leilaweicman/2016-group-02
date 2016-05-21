package grupo2.tpAnual;

public class BancoExterno {
	private double latitud;
	private double longitud;

	public BancoExterno(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public double getLatitud() {
		return this.latitud;
	}

	public double getLongitud() {
		return this.longitud;
	}
}
