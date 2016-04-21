package grupo2.tpAnual;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

import org.joda.time.DateTime;

public class App 
{
	public static void main(String[] args) {
		 Comercio comercio;
		 LocalTime hora;
		 LocalTime hora2;
		 Rango unRango;
		 Rango otroRango;
		 Rango miRango;
		 DateTime dt = new DateTime("2016-04-20T21:39:45");

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
		
		int day = comercio.estaDisponible(dt);
		
		
		
	    	System.out.println(day);
	}
    
}