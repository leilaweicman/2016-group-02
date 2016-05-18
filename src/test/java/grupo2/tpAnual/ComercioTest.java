package grupo2.tpAnual;
import java.time.LocalTime;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ComercioTest 
{
 private Comercio comercio;
 private Rango unRango;
 private Rango otroRango;
 private Rango rango;
 private DateTime momento;
 
 @Before
 public void init(){
  comercio= new Comercio();
  unRango = new Rango();
  otroRango = new Rango();
  rango = new Rango();
  
  unRango.setDay(1);
  unRango.setHoraDesde(LocalTime.of(9,0,0));
  unRango.setHoraHasta(LocalTime.of(18,0,0));
  
  otroRango.setDay(3);
  otroRango.setHoraDesde(LocalTime.of(9,0,0));
  otroRango.setHoraHasta(LocalTime.of(13,0,0));
  
  rango.setDay(3);
  rango.setHoraDesde(LocalTime.of(15,0,0));
  rango.setHoraHasta(LocalTime.of(18,30,0));
  
  comercio.addRango(unRango); 
  comercio.addRango(otroRango);
  comercio.addRango(rango);
 }
 
 @Test
 public void estaDisponibleMiercolesALas10() {
	 momento = new DateTime("2016-04-20T10:00:00");
	 Assert.assertTrue(comercio.estaDisponible(momento, "")); 
 }
 
 //@Test
 /*public void noEstaDisponibleMiercolesALas14() {
	 momento = new DateTime("2016-04-20T14:00:00");
	 Assert.assertFalse(comercio.estaDisponible(momento, "")); 
 }*/


}