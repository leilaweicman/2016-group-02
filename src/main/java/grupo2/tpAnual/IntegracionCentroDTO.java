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

	private List<POI> transformarDTOaPOI(List<CentroDTO> listaCentroDTO) {
		// TODO Auto-generated method stub
		return null;
	}


	public void setDireccion(Direccion direccionTransformada) {
		direccionTransformada.setCalle(this.lugar.getDomicilio());

	}

	public void setComuna(Comuna comuna) {
		List<Point> listaVertices = new ArrayList<>();
		comuna = new Comuna(this.lugar.getNumeroComuna(), listaVertices);
	}

}
