package modelo;

public class Cronometro {
	 private long tiempoInicio;
	 private long tiempoFin;
	 
	    public void iniciar() {
	        this.tiempoInicio = System.currentTimeMillis();
	    }

	    public void detener() {
	    	this.tiempoFin = System.currentTimeMillis() - this.tiempoInicio;
	    }

		public long getTiempoInicio() {
			return this.tiempoInicio;
		}

		public void setTiempoInicio(long tiempoInicio) {
			this.tiempoInicio = tiempoInicio;
		}

		public long getTiempoFin() {
			return this.tiempoFin;
		}

		public void setTiempoFin(long tiempoFin) {
			this.tiempoFin = tiempoFin;
		}	    
	    
}

