package grupo2.tpAnual;


public abstract class POI {
	private Direccion _Direccion;
	private String _PalabraClave;

	//public Point Point; 
	
	public Direccion getDireccion() {
		return _Direccion;
	}

	public void setDireccion(Direccion dir) {
		this._Direccion = dir;
	}
	
	public void setPalabraClave(String pc){
		this._PalabraClave = pc;
	}
	public String getPalabraClave(){
		return _PalabraClave;
	}
	
	abstract boolean Busqueda(String texto);
	
}
