package grupo2.tpAnual;
import java.time.LocalTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ComercioTest 
{
 private Comercio comercio;
 private LocalTime horaUsuario;
 private String diaUsuario;
 private Rango unRango;
 
 @Before
 public void init(){
  comercio= new Comercio();
  unRango = new Rango();
  
  horaUsuario=LocalTime.of(9,30);
  diaUsuario = "lunes";
  
  unRango.setDia("lunes");
  unRango.setHoraDesde(LocalTime.of(9,00));
  unRango.setHoraHasta(LocalTime.of(13,00));  
  comercio.addRango(unRango);  
 }
 
 
 /*compare to: hora > hora2 devuelve 1
  *     hora < hora2 devuelve -1
  *     hora = hora2 devuelve 0
 */
 
 @Test
 public void estaEntre() {
  
  //for 
  //un if preguntando si el rango.getDia es igual al diaUsuario
  //si es igual verfica que el horaUsuario este entre horaDesde y horaHasta
  
  Assert.assertTrue((unRango.getHoraDesde()).compareTo(horaUsuario) == -1);
 }

}