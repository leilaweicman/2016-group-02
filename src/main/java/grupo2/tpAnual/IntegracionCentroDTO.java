package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public class IntegracionCentroDTO implements Integracion {
	private ServicioCentroDTO mapaCentroDTO = new ServicioCentroDTO();
	private CentroDTO lugar;

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
		for (CentroDTO dto : listaCentroDTO) {
			listaPOI.add(adapter(dto));
		}
		return listaPOI;
	}

	public POI adapter(CentroDTO dto) {
		POI poi = new CGP();
		List<Point> listaVertices = new ArrayList<>();
		this.lugar = dto;
		poi.setComuna(new Comuna(this.lugar.getNumeroComuna(), listaVertices));
		poi.setDireccion(new Direccion(this.lugar.getDomicilio(), this.lugar.getZona()));
		return poi;

	}
}