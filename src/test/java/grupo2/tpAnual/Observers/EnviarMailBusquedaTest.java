package grupo2.tpAnual.Observers;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.uqbar.geodds.Point;

import grupo2.tpAnual.MailSender;
import grupo2.tpAnual.AccesoriosPois.Rango;
import grupo2.tpAnual.Observers.EnviarMailBusqueda;
import grupo2.tpAnual.Pois.Comercio;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;

public class EnviarMailBusquedaTest {
	Rango unRango ;
	Rango otroRango	;
	Rango rango ;
	List<Rango> listaRangos;
	Comercio comercio ;
	List<POI> pois;
	
	@Before 
	public void init(){
		unRango = new Rango(1, LocalTime.of(9, 0, 0), LocalTime.of(18, 0, 0));
		otroRango = new Rango(3, LocalTime.of(9, 0, 0), LocalTime.of(13, 0, 0));
		rango = new Rango(3, LocalTime.of(15, 0, 0), LocalTime.of(18, 30, 0));
		listaRangos = Arrays.asList(unRango, otroRango, rango);
		comercio = new Comercio("Supermercado argenChino", Point.and(-34.664837, -58.385674), listaRangos);
		pois= new ArrayList<>();
		pois.add(comercio);
	}
		MailSender mockmail = Mockito.mock(MailSender.class);
		DatosDeBusqueda datoBuscado = new DatosDeBusqueda("lasHeras", "libros", 10, 15, new LocalDate(),pois);
		DatosDeBusqueda datoBuscado2 = new DatosDeBusqueda("flores", "asado", 10, 15, new LocalDate(),pois);
	
	@Test
	public void enviarMailTest() {
		EnviarMailBusqueda observer = new EnviarMailBusqueda(1, mockmail, "juan@gmail.com");
		observer.notificarBusqueda(datoBuscado);
		Mockito.verify(mockmail).send("juan@gmail.com", "Notificacion de Busqueda", "La busqueda tardo mas de lo esperado");
			}
}
