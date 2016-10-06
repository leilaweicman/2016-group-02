package grupo2.tpAnual.OrigenesDeDatos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.uqbar.geodds.Point;

import ServiciosExternos.CentroDTO;
import ServiciosExternos.ServicioExternoCentroDTO;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosCentroDTO;
import grupo2.tpAnual.Pois.CGP;
import grupo2.tpAnual.Pois.POI;

public class OrigenesDeDatosCentroDTOTest {
	ServicioExternoCentroDTO stub = Mockito.mock(ServicioExternoCentroDTO.class);
	OrigenesDeDatosCentroDTO integracion = new OrigenesDeDatosCentroDTO(stub);
	CentroDTO centroDTO = new CentroDTO(9, "Juan B Justo 1882");
	CentroDTO centroDTO2 = new CentroDTO(7, "Corrientes 1234");
	POI cgp = new CGP("Palermo", Point.and(-34.664837, -58.385674));
	POI cgp2 = new CGP("Colegiales", Point.and(-34.664837, -58.385674));

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
		Assert.assertEquals(integracion.transformarDTOaPOI(listaCentroDTO).get(0).getClass(), CGP.class );
	}

	@Test
	public void busquedaIntegracionTest() {
		List<CentroDTO> respuesta = Arrays.asList(centroDTO,centroDTO2);
		Mockito.when(stub.busqueda("curso de cocina")).thenReturn(respuesta);
		Assert.assertEquals(integracion.busqueda("curso de cocina").get(1).getComuna().getNumeroComuna(),7);
	}
}
