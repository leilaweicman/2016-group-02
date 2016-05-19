package grupo2.tpAnual;

import java.util.List;

public class CentroDTO {
	private int numeroComuna;
	private String zona;
	private int telefono;
	private String domicilio;
	private String nombreDirector;
	private List<Servicio> serviciosDTO;

	public CentroDTO(int numeroComuna, String domicilio) {
		this.numeroComuna = numeroComuna;
		this.domicilio = domicilio;
	}

	public int getNumeroComuna() {
		return this.numeroComuna;
	}

	public String getZona() {
		return this.zona;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public List<Servicio> getServiciosDTO() {
		return this.serviciosDTO;
	}

	public String getNombreDirector() {
		return this.nombreDirector;
	}

}
