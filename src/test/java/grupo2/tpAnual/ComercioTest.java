package grupo2.tpAnual;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ComercioTest 
{
	private Comercio comercio;
	private LocalTime hora;
	private LocalTime hora2;
	private Rango unRango;
	private Rango otroRango;
	private Rango miRango;
	@Before
	public void init(){
		comercio= new Comercio();
		unRango = new Rango();
		otroRango = new Rango();
		miRango = new Rango();
		hora2= LocalTime.of(9,30);
		hora= LocalTime.of(9,00);		
		unRango.setDia("lunes");
		unRango.setHoraDesde(hora);
		unRango.setHoraHasta(hora2);
		otroRango.setDia("martes");
		otroRango.setHoraDesde(hora);
		otroRango.setHoraHasta(hora2);
		LocalTime.of(9,15);
		//unRango.setHora(hora);
		comercio.addRango(unRango);
		comercio.addRango(otroRango);
	}
	
	@Test
	public void estaEntre() {
		Assert.assertTrue(comercio.getRango().contains(miRango));
		Assert.assertTrue(hora.compareTo(hora2) == -1);
	}
	
	@Test
	public void test() {
		//fail("Not yet implemented");
	}

}