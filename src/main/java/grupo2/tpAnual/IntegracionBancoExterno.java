package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public class IntegracionBancoExterno extends Integracion{
	private BancoExterno banco;
	
	@Override
	public List<POI> busqueda(String txtABuscar) {
		List<Object> listaBancos = new ArrayList<>();
		List<POI> listaPOI = new ArrayList<>();
		String banco="";
		String servicio="";
		listaBancos = this.banco.busqueda(banco,servicio);
		listaPOI= transformarListaAPOI(listaBancos);
		return listaPOI;
	}
	
	public List<POI> transformarListaAPOI(List<Object> listaCGP){
		List<POI> listaPOI = new ArrayList<>();
		return listaPOI;
	}
	
	public void bancoExternoAPoi(BancoExterno banco){
		this.banco = banco;
	}
	
	public Point getUbicacion() {
		Point ubicacionBanco = new Point(banco.getX(), banco.getY());
		return ubicacionBanco;
	}
	
	
}
