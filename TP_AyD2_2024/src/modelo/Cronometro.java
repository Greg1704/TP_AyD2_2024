package modelo;

public class Cronometro {
	 private long tiempoInicio;

	    public void iniciar() {
	        tiempoInicio = System.currentTimeMillis();
	    }

	    public long detener() {
	        return System.currentTimeMillis() - tiempoInicio;
	    }
}

