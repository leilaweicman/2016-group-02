package grupo2.tpAnual;


public abstract class POI {
	private Direccion _Direccion;
	//public Point Point; 
	
	public Direccion getDireccion() {
		return _Direccion;
	}

	public void setDireccion(Direccion dir) {
		this._Direccion = dir;
	}
	
	abstract boolean Busqueda(String texto);
	
}
