package grupo2.tpAnual.OrigenesDeDatos;

import com.fasterxml.jackson.databind.ObjectMapper;

import grupo2.tpAnual.Banco;
import grupo2.tpAnual.BancoExterno;
import grupo2.tpAnual.POI;
import grupo2.tpAnual.StubBancosExternos;

import java.util.ArrayList;
import java.util.List;

public class OrigenesDeDatosBancoExterno implements OrigenesDeDatos {
	public ObjectMapper mapper = new ObjectMapper();
	private BancoExterno bancoExterno;
	private StubBancosExternos mapaBancoExterno = new StubBancosExternos();

	@Override
	public List<POI> busqueda(String banco) {
		List<BancoExterno> json = new ArrayList<>();
		List<POI> listaPOI = new ArrayList<>();
		// json = this.mapaBancoExterno.busqueda(banco, "");
		listaPOI = transformarAPOI(json);
		return listaPOI;
	}

	public List<POI> transformarAPOI(List<BancoExterno> listaBancosExternos) {
		List<POI> listaPOI = new ArrayList<POI>();
		listaBancosExternos.forEach(banco -> listaPOI.add(adapter(bancoExterno)));
		return listaPOI;
	}

	private POI adapter(BancoExterno bancoExterno) {
		int numeroVerificador = 8000;
		POI bancoPoi = new Banco(numeroVerificador);
		// Aca se setea cada item del banco externo con el poi
		// saqué la info de aca:
		// http://www.anieto2k.com/2009/07/06/todo-lo-que-siempre-quisiste-saber-sobre-json/
	
		bancoPoi.setUbicacion(bancoExterno.x, bancoExterno.y);
		numeroVerificador= numeroVerificador + 1;
		return bancoPoi;
	}

}
