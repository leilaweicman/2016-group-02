package grupo2.tpAnual;

import org.uqbar.geodds.Point;

public abstract class AdapterBancoExterno implements IBanco{
	BancoExterno banco;
	
	
	public void bancoExternoAPoi(BancoExterno banco){
		this.banco = banco;
	}
	
	@Override
	public Point getUbicacion() {
		Point ubicacionBanco = new Point(banco.getX(), banco.getY());
		return ubicacionBanco;
	}


	
}
