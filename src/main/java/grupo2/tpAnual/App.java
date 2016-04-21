package grupo2.tpAnual;
import java.sql.Time;
import org.joda.time.DateTime;

public class App 
{
	public static void main(String[] args) {
		Comercio comercio;
		Rango unRango;
		Rango otroRango;
		Rango rango;
		
		DateTime dt = new DateTime("2016-04-20T14:39:45");
	   
		comercio= new Comercio();
		  unRango = new Rango();
		  otroRango = new Rango();
		  rango = new Rango();
		  
		  unRango.setDay(1);
		  unRango.setHoraD(new Time (9,0,0));
		  unRango.setHoraH(new Time (18,0,0));
		  
		  otroRango.setDay(3);
		  otroRango.setHoraD(new Time (9,0,0));
		  otroRango.setHoraH(new Time (13,0,0));
		  
		  rango.setDay(3);
		  rango.setHoraD(new Time (15,0,0));
		  rango.setHoraH(new Time (18,30,0));
		  
		  comercio.addRango(unRango);
		  comercio.addRango(otroRango);
		  comercio.addRango(otroRango);
	  
	 // String day = comercio.estaDisponible(dt);
	 // int hour = comercio.estaDisponible(dt);
	 //  Time hour = comercio.estaDisponible(dt);
	  
	  
	  boolean disp = comercio.estaDisponible(dt);
	  
	  System.out.println(disp);
	}
    
}