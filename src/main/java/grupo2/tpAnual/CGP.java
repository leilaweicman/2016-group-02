package grupo2.tpAnual;


import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class CGP extends POI implements ICGP {
	private List<Servicio> servicios;

	
	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> ser) {
		this.servicios = ser;
	}

	//@Override
	public boolean busquedaParticular(String texto) {
		for (Servicio ser : servicios) {
			if (ser.getNombre().contains(texto)) {
				return true;
			}
		}
		return false;
	}

	public boolean estaDisponible(DateTime momento, String nombreServicio) {
		boolean disponible = false;
		if (nombreServicio != "") {

			disponible = servicios.stream().filter(x -> x.getNombre() == nombreServicio)
					.collect(Collectors.toList()).get(0).estaDisponible(momento);

			/*for (Servicio servicio : servicios) {

				if (servicio.getNombre() == nombreServicio) {
					disponible = servicio.estaDisponible(momento);
				}
			}*/
			
		} else {
			
			disponible = servicios.stream().anyMatch(servicio -> servicio.estaDisponible(momento));
			
			/*int cont = 0;
			Servicio[] serviciosArray = new Servicio[servicios.size()];
			serviciosArray = servicios.toArray(serviciosArray);
			while (disponible == false) {
				cont++;
				disponible = serviciosArray[cont].estaDisponible(momento);
			}*/
			
		}
		return disponible;
	}

	// se lo delego a la comuna
	public boolean estaCerca(Point punto) {
		return (this.comuna.estaAdentro(punto));
	}

}