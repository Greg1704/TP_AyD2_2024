package modelo;

import java.io.Serializable;

public class Cronometro implements Serializable {
	 private long tiempoInicio;
	 private long tiempoFin;
	 //Agregar variable inicio y fin pero con el tiempo en el formato correcto
	 
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

		//devuelve en segundos
		public long getTiempoFin() {
			return this.tiempoFin / 1000;
		}

		public void setTiempoFin(long tiempoFin) {
			this.tiempoFin = tiempoFin;
		}	    
	    
}

