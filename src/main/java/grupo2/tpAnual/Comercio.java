package grupo2.tpAnual;
import org.joda.time.DateTime;
import org.uqbar.geodds.Point;
import java.util.*;

public class Comercio extends POI{
	private Rubro _Rubro;
	private ArrayList<Rango> rangoDisponibilidad = new ArrayList<Rango>();
	private Map<String,ArrayList<Rango>> diasYRangos= new HashMap<String,ArrayList<Rango>>();
	
	//no haria falta un constructor
	public Comercio(){
		
	}
	
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
	
	public ArrayList<Rango> getRango() {
		return rangoDisponibilidad;
	}

	public void setRango(ArrayList<Rango> rango) {
		this.rangoDisponibilidad = rango;
	}
	
	public void addRango(Rango rango) {
		this.rangoDisponibilidad.add(rango);
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
	
	public int estaDisponible(DateTime momento){	
		int dia = momento.getDayOfWeek();
		//falta calculo
		//desmenuzar momento
		
		return dia;
	}
	
	public boolean estaCerca(Point coordenadaDeseada){
		return (this._Ubicacion.distance(coordenadaDeseada)<_Rubro.getRadioCercania());
	}
		
	//borrar esto
	public boolean estaDisponible (Date fecha){
		return true;
	}
}
