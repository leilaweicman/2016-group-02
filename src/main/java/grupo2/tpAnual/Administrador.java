package grupo2.tpAnual;

public interface Administrador {

	public void crearPOI(String nombre);

	public void modificarUnPOI(POI poi, String atributo, String valorAtributo);

	public void darDeBajaPOI(POI nombre);
	
	public POI consultarPoi(POI nombre);
}
