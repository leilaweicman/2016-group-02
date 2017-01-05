package grupo2.tpAnual.Repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import grupo2.tpAnual.AccesoriosPois.Comuna;

public class MemoryComunaRepository implements ComunaRepository{

	private List<Comuna> comunas = new ArrayList<>();

	@Override
	public void deleteComuna(Comuna comuna) {
		comunas.remove(comuna);		
	}

	@Override
	public void saveComuna(Comuna comuna) {
		comunas.add(comuna);
	}

	@Override
	public List<Comuna> getComunas() {
		return this.comunas;
	}

	@Override
	public Comuna getObserverByNumero(int numero) {
		return (this.comunas.stream().filter(comuna->(comuna.getNumeroComuna()==numero)).collect(Collectors.toList())).get(0);
	}

	
}
