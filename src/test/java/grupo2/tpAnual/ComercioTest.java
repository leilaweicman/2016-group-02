package grupo2.tpAnual;
import static org.junit.Assert.*;
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
	@Before
	public void init(){
		comercio= new Comercio();
		unRango = new Rango();
		hora2= LocalTime.of(9,30);
		hora= LocalTime.of(9,30);		
		unRango.setDia("lunes");
		unRango.setHora(hora);
		//unRango.setHora(hora);
		comercio.addRango(unRango);
	}
	
	@Test
	public void EstaEntre() {
		//no se pueden comparar dos horas
		//Assert.assertTrue(hora2 < hora);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
