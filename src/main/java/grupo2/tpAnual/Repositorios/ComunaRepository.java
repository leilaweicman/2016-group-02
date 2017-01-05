package grupo2.tpAnual.Repositorios;

import java.util.List;

import grupo2.tpAnual.AccesoriosPois.Comuna;


public interface ComunaRepository {
	
	public void deleteComuna(Comuna comuna);
	
	public void saveComuna(Comuna comuna);

	public List<Comuna> getComunas();
	
	public Comuna getComunaByNumero(int numero);

}
