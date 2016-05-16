package grupo2.tpAnual;

import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class BancoExterno extends POI {
	private String banco;
	private double x;
	private double y;
	private String sucursal;
	private String gerente;
	private List<String> servicios;

	@Override
	boolean estaDisponible(DateTime momento, String nombreServicio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean estaCerca(Point coordenadaDeseada) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public List<String> getServicios() {
		return servicios;
	}

	public void setServicios(List<String> servicios) {
		this.servicios = servicios;
	}

	public String getGerente() {
		return gerente;
	}

	public void setGerente(String gerente) {
		this.gerente = gerente;
	}
}