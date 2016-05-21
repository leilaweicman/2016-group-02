package grupo2.tpAnual;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		POI bancoPOI = new Banco(); /// no estoy segura de que funcione esto...
		bancoPOI = mapper.convertValue(banco, POI.class);
		return bancoPOI;
	}

	public Point getUbicacion() {
		Point ubicacionBanco = new Point(banco.getLatitud(), banco.getLongitud());
		return ubicacionBanco;
	}

}
