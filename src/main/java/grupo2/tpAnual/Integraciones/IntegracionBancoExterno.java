package grupo2.tpAnual.Integraciones;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import grupo2.tpAnual.Banco;
import grupo2.tpAnual.BancoExterno;
import grupo2.tpAnual.Comuna;
import grupo2.tpAnual.POI;
import grupo2.tpAnual.ServicioBancosExternos;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public class IntegracionBancoExterno implements Integracion {
	public ObjectMapper mapper = new ObjectMapper();
	private BancoExterno banco;
	private ServicioBancosExternos mapaBancoExterno = new ServicioBancosExternos();

	@Override
	public List<POI> busqueda(String banco) {
		List<BancoExterno> json = new ArrayList<>();
		List<POI> listaPOI = new ArrayList<>();
		// json = this.mapaBancoExterno.busqueda(banco, "");
		listaPOI = transformarAPOI(json);
		return listaPOI;
	}

	public List<POI> transformarAPOI(List<BancoExterno> listaBancosExternos) {
		List<POI> listaPOI = new ArrayList<>();
		for (BancoExterno banco : listaBancosExternos) {
			listaPOI.add(this.adapter(banco));
		}
		return listaPOI;
	}

	public POI adapter(BancoExterno banco) {
		Banco poi = new Banco();
		List<Point> listaVertices = new ArrayList<>();
		//Aca se setea cada item del banco externo con el poi
		
		
		//poi.setComuna(new Comuna(??, listaVertices));	  //Hay que ver como manejar la comuna
		poi.setUbicacion(banco.getLatitud(), banco.getLongitud());
		//poi.setDireccion();
		
		return poi;
	}

	public Point getUbicacion() {
		return new Point(banco.getLatitud(), banco.getLongitud());
	}

}
