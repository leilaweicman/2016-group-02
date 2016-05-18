package grupo2.tpAnual;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BancoTest {	
	
	 private Banco banco;
	 private DateTime momento;
	
	 @Before
	 public void init(){
		 banco= new Banco();
	 }
	 
	 @Test
	 public void estaDisponibleLunesALas11() {
		 momento = new DateTime("2016-04-25T11:00:00");
		 Assert.assertTrue(banco.estaDisponible(momento, "")); 
	 }
	 
	 @Test
	 public void noEstaDisponibleViernesALas16() {
		 momento = new DateTime("2016-04-22T16:00:00");
		 Assert.assertFalse(banco.estaDisponible(momento, "")); 
	 }

}