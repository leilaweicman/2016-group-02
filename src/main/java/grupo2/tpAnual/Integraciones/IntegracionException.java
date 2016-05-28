package grupo2.tpAnual.Integraciones;

public class IntegracionException extends RuntimeException {

	public IntegracionException(String message, Exception e) {
		super(message, e);
	}
}
