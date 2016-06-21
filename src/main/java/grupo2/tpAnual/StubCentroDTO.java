package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

public class StubCentroDTO {
	List<CentroDTO> listaDTO = new ArrayList<>();

	public StubCentroDTO() {
		CentroDTO centro1 = new CentroDTO(1, "Juan B Justo 1882");
		CentroDTO centro2 = new CentroDTO(3, "Carlos Calvo 1848");
		listaDTO.add(centro1);
		listaDTO.add(centro2);
	}

	public List<CentroDTO> busqueda(String texto) {
		return this.listaDTO;

	}
}
