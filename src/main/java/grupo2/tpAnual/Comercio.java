package grupo2.tpAnual;
import java.util.Date;
import org.uqbar.geodds.Point;
import java.util.*;

public class Comercio extends POI{
	private Rubro _Rubro;
	private Map<String,ArrayList<Rango>> diasYRangos= new HashMap<String,ArrayList<Rango>>();
	
	public Comercio(){}
	public Comercio(HashMap<String,ArrayList<Rango>> disponibilidadDelComercio){
		diasYRangos.put("lunes", disponibilidadDelComercio.get("lunes"));
		diasYRangos.put("martes", disponibilidadDelComercio.get("martes"));
		diasYRangos.put("miercoles", disponibilidadDelComercio.get("miercoles"));
		diasYRangos.put("jueves", disponibilidadDelComercio.get("jueves"));
		diasYRangos.put("viernes", disponibilidadDelComercio.get("viernes"));
		diasYRangos.put("sabado", disponibilidadDelComercio.get("sabado"));
		diasYRangos.put("domingo", disponibilidadDelComercio.get("domingo"));		
	}
	
	public Map<String,ArrayList<Rango>> getDiasYRangos() {
		return diasYRangos;
	}
	
	public Rubro getRubro() {
		return _Rubro;
	}

	public void setRubro(Rubro rub) {
		this._Rubro = rub;
	}
	
	public boolean Busqueda(String Texto){
		return getPalabraClave().equals(Texto);
	}
	
	public boolean estaDisponible(Date fecha){		
		//falta calculo
		return true;
	}
	
	public boolean estaCerca(Point coordenadaDeseada){
		return (this._Ubicacion.distance(coordenadaDeseada)<_Rubro.getRadioCercania());
	}
		
}
