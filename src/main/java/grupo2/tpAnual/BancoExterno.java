package grupo2.tpAnual;

public class BancoExterno {
	public double x;
	public double y;

	public BancoExterno(double latitud, double longitud) {
		this.x = latitud;
		this.y = longitud;
	}

	public double getLatitud() {
		return this.x;
	}

	public double getLongitud() {
		return this.y;
	}
}
