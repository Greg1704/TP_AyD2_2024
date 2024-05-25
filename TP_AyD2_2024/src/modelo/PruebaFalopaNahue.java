package modelo;

import strategy.FactoryStrategy;

public class PruebaFalopaNahue {
	public static void main(String[] args) {
		Cliente c1 = new Cliente("43317387", "Gold", "17/04/2001");
		Cliente c2 = new Cliente("11111111", "Gold", "17/04/2011");
		Cliente c3 = new Cliente("22222222", "Gold", "17/04/2005");

		FactoryStrategy fs = new FactoryStrategy();
		String strategy = "Afinidad";
		
		GestionDeTurnos gdt = new GestionDeTurnos(fs.getStrategy(strategy));
		
		gdt.añadirTurno(c1);
		gdt.añadirTurno(c2);
		gdt.añadirTurno(c3);
		
		Turno t = gdt.extraerPrimerTurno();
		Cliente cliente = t.getCliente();
		
		System.out.println(cliente.getDni());
		
	}
}
