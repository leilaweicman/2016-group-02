package grupo2.tpAnual.OrigenesDeDatos;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import ServiciosExternos.CentroDTO;
import ServiciosExternos.ServicioExternoCentroDTO;
import grupo2.tpAnual.CGP;
import grupo2.tpAnual.Comuna;
import grupo2.tpAnual.Direccion;
import grupo2.tpAnual.POI;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class OrigenesDeDatosCentroDTO implements OrigenesDeDatos {
	private ServicioExternoCentroDTO mapaCentroDTO;
	private Jedis jedis;
	
	public OrigenesDeDatosCentroDTO(ServicioExternoCentroDTO servicio){
		this.mapaCentroDTO = servicio;
		jedis= new Jedis("localhost");
	}
	
	@Override
	public List<POI> busqueda(String txtABuscar) {
		List<CentroDTO> listaCentroDTO = new ArrayList<>();
		List<POI> listaPOI = new ArrayList<>();
		listaCentroDTO = this.mapaCentroDTO.busqueda(txtABuscar);
		listaPOI = transformarDTOaPOI(listaCentroDTO);
		return listaPOI;
	}

	public List<POI> transformarDTOaPOI(List<CentroDTO> listaCentroDTO) {
		List<POI> listaPOI = new ArrayList<>();
		listaCentroDTO.forEach(elementoDto -> listaPOI.add(adapter(elementoDto)));
		return listaPOI;
	}

	public POI adapter(CentroDTO dto) {
		CGP poi = new CGP("",null);
		List<Point> listaVertices = new ArrayList<>();
		poi.setComuna(new Comuna(dto.getNumeroComuna(), listaVertices));
		poi.setDireccion(new Direccion(dto.getDomicilio(), dto.getZona()));
		return poi;
	}
}