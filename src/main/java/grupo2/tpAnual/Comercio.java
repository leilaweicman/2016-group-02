package grupo2.tpAnual;

public class Comercio extends POI{
	private Rubro _Rubro;
	
	public Rubro getRubro() {
		return _Rubro;
	}

	public void setRubro(Rubro rub) {
		this._Rubro = rub;
	}
	
	public boolean Busqueda(String Texto){
		return getPalabraClave().equals(Texto);
	}
}
