package state;

import interfaces.IStateServidor;
import modelo.Servidor;

public class Secundario implements IStateServidor{
	
	Servidor servidor;
	
	public Secundario(Servidor s) {
		this.servidor = s;
	}

	@Override
	public void principal() {
		// TODO Auto-generated method stub
		this.servidor.setEstado(new Principal(this.servidor));
		
	}

	@Override
	public void secundario() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub
		
	}

}
