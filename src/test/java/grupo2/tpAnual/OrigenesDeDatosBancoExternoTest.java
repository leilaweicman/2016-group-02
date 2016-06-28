package grupo2.tpAnual;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosBancoExterno;

public class OrigenesDeDatosBancoExternoTest {

	private OrigenesDeDatosBancoExterno origenDeDatos;

	 @Before
	 public void init(){
		 origenDeDatos = new OrigenesDeDatosBancoExterno();
	 }
	 
	 @Test
	 public void busquedaTest() throws Exception{
		 List<POI> bancos = origenDeDatos.busqueda("Hola");
		 Assert.assertEquals(bancos.size(), 2);
	 }
	
}
