package grupo2.tpAnual.OrigenesDeDatos;

import java.util.List;
import java.util.stream.Collectors;

import ServiciosExternos.ServicioExternoBanco;
import grupo2.tpAnual.FromJsonToMap;
import grupo2.tpAnual.Pois.POI;

public class BancosExternos extends OrigenesDeDatosBancos {
	
	private ServicioExternoBanco mapaBancoExterno;

	public BancosExternos(ServicioExternoBanco servicio) {
		this.mapaBancoExterno = servicio;
	}

	@Override
	public List<POI> busqueda(String banco) {
		try {
			String json = mapaBancoExterno.busqueda(banco, "");
			return FromJsonToMap.transformarAMap(json).stream().map((poiMap) -> adaptar(poiMap)).collect(Collectors.toList());		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
