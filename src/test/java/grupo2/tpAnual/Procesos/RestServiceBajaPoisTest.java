package grupo2.tpAnual.Procesos;

import grupo2.tpAnual.Procesos.RestServiceBajaPois;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RestServiceBajaPoisTest {
	
	RestServiceBajaPois requester;
	
	 @Before
	    public void init() throws Exception {
	        this.requester = new RestServiceBajaPois();
	    }

	 @Test
	 public void verificarIdDeServicioRest(){
		 //Se solicita todos los datos de un libro por su isbn.
	        String response = this.requester.getPOIs();
	        assertTrue(response.contains("id"));
	      
	 }
	 @Test
	 public void verificarQueDevuelve(){
		 String response = this.requester.getPOIs();
		 System.out.println(response);
				 						
	 }
	 
	 @Test
	 public void verificarDeletedAtDeServicioRest(){
	        String response = this.requester.getPOIs();
	        assertTrue(response.contains("deletedAt"));
	      
	 }
	 
	 @Test
	 public void verificarNoDaRespuestaVacia(){
		 String response = this.requester.getPOIs();
		 assertTrue(response!=null);
	 }
}
