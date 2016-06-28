package grupo2.tpAnual.Procesos;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;


import javax.ws.rs.core.MediaType;

public class RestServiceBajaPois {
	  private Client client;
	    private static final String API_BAJAPOIS = "http://demo3537367.mockable.io/trash";
	    private static final String RESOURCE = "pois";	
	   
	    public RestServiceBajaPois() {
	        this.client = Client.create();
	        //En la documentacion se puede ver como al cliente agregarle un ClientConfig
	        //para agregarle filtros en las respuestas (por ejemplo, para loguear).
	    }
	    
	  //Prueba de concepto de un parametro y los mensajes por separado para identificar los tipos de datos.
	    public String getPOIs(){ 
	    	WebResource recurso = this.client.resource(API_BAJAPOIS).path(RESOURCE);
	        WebResource.Builder builder = recurso.accept(MediaType.APPLICATION_JSON);
	       String response = builder.get(String.class);
	     
	        return response;
	    }
		
	}

