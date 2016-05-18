package grupo2.tpAnual;

import java.util.List;

public interface BancoExterno {

	public String getBanco();

	public double getX();

	public double getY();

	public String getSucursal();

	public List<String> getServicios();

	public String getGerente();
	
	//public List<JSON> busqueda(String banco, String servicio);
}
