package grupo2.tpAnual.Procesos;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class RestServiceBajaPois {
	private Client client;
	private static final String API_BAJAPOIS = "http://demo3537367.mockable.io/trash";
	private static final String RESOURCE = "pois";	
	   
    public RestServiceBajaPois() {
    	this.client = Client.create();
	    
    }
	    
	
	public String getPOIs(){ 
		WebResource recurso = this.client.resource(API_BAJAPOIS).path(RESOURCE);
	    WebResource.Builder builder = recurso.accept(MediaType.APPLICATION_JSON);
	    String response = builder.get(String.class);	 
	    return response;
	}		
}