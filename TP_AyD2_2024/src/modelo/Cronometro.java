package modelo;

import java.io.Serializable;
import java.time.LocalTime;

public class Cronometro implements Serializable {
	 private long tiempoInicio;
	 private long tiempoFin;
	 private LocalTime tiempoRegistro;
	 private LocalTime tiempoAtendido;
	 //Agregar variable inicio y fin pero con el tiempo en el formato correcto
	 
	    public void iniciar() {
	    	this.tiempoRegistro = LocalTime.now().withNano(0);
	        this.tiempoInicio = System.currentTimeMillis();
	    }

	    public void detener() {
	    	this.tiempoAtendido = LocalTime.now().withNano(0);
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

		public LocalTime getTiempoRegistro() {
			return tiempoRegistro;
		}

		public LocalTime getTiempoAtendido() {
			return tiempoAtendido;
		}	 
		
		
	    
}

