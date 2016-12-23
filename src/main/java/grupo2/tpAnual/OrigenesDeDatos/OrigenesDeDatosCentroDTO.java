package grupo2.tpAnual.OrigenesDeDatos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.uqbar.geodds.Point;

import ServiciosExternos.CentroDTO;
import ServiciosExternos.ServicioExternoCentroDTO;
import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.AccesoriosPois.Direccion;
import grupo2.tpAnual.Pois.CGP;
import grupo2.tpAnual.Pois.POI;

@Entity
public class OrigenesDeDatosCentroDTO extends OrigenesDeDatos {

	@Transient
	private ServicioExternoCentroDTO mapaCentroDTO;
	
	public OrigenesDeDatosCentroDTO(ServicioExternoCentroDTO servicio){
		this.mapaCentroDTO = servicio;
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