package grupo2.tpAnual;

import java.util.List;


public abstract class AdapterCentroDTO implements ICGP{
	private CentroDTO lugar;
	
	public void centroDTOaPoi(CentroDTO lugarATransformar){
		this.lugar = lugarATransformar;
	}
	
	@Override
	public void setServicios(List<Servicio> serviciosTransformados) {
		serviciosTransformados.add((Servicio) lugar.getServiciosDTO());
	}

	@Override
	public void setDireccion(Direccion direccionTransformada) {
		direccionTransformada.setCalle(this.lugar.getDomicilio());
		
	}

}