package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import grupo2.tpAnual.OrigenesDeDatos.StubCentroDTO;

public class IntegracionCentroDTOTest {
	StubCentroDTO integracion = new StubCentroDTO();
	CentroDTO centroDTO = new CentroDTO(9, "Juan B Justo 1882");
	CentroDTO centroDTO2 = new CentroDTO(7, "Corrientes 1234");
	POI cgp = new CGP();
	POI cgp2 = new CGP();

	@Test
	public void adapterPOIaCentroDTODireccionTest() {
		cgp = integracion.adapter(centroDTO);
		Assert.assertEquals(cgp.getDireccion().getCalle(), centroDTO.getDomicilio());
	}

	@Test
	public void adapterPOIaCentroDTOComunaTest() {
		cgp = integracion.adapter(centroDTO);
		Assert.assertEquals(cgp.getComuna().getNumeroComuna(), centroDTO.getNumeroComuna());
	}

	@Test
	public void transformarCentroDTOaPOITest() {
		List<CentroDTO> listaCentroDTO = new ArrayList<CentroDTO>();
		listaCentroDTO.add(centroDTO);
		listaCentroDTO.add(centroDTO2);
		integracion.transformarDTOaPOI(listaCentroDTO);
		Assert.assertEquals(listaCentroDTO.size(), 2);

	}

	@Test
	public void busquedaIntegracionTest() {
		List<POI> listaPOI = new ArrayList<POI>();
		listaPOI = integracion.busqueda("hola");
		Assert.assertEquals(listaPOI.size(), 2);
	}
}
