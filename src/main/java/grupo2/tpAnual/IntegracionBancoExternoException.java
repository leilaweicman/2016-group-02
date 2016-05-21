package grupo2.tpAnual;

public class IntegracionBancoExternoException extends RuntimeException {
	public IntegracionBancoExternoException(String message, Exception e) {
		super(message, e);
	}
}
