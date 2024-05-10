package modelo;

import java.time.LocalDate;
import java.util.List;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

//VER: en que esta el resultado del cronometro (seg, miliseg, etc)

public class Estadisticas implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Long> tiemposEspera = new ArrayList<>();
	public int cantCliAtentidos;
	public float tiempoEsperaProm;
	public float tiempoEsperaMin;
	public float tiempoEsperaMax;
	private LocalDate fechaActual;
	
	
	public Estadisticas() {
		super();
		this.cantCliAtentidos = 0;
		this.tiempoEsperaProm =0.0f;
		this.tiempoEsperaMin =0.0f;
		this.tiempoEsperaMax = 0.0f;
		this.fechaActual = LocalDate.now();
	}
	

	public List<Long> getTiempos() {
		return tiemposEspera;
	}

	public void agregarTiempos(long tiempo) {
	    tiemposEspera.add(tiempo);
	    GeneraEstadisticas();
	}
	
	public void agregarClienteAtendidos() {
		this.cantCliAtentidos++;
	}
	
	public void GeneraEstadisticas() {
		if (tiemposEspera.isEmpty()) {
            throw new IllegalStateException("La lista de tiempos está vacía");
        }
        
		setTiempoEsperaMin(Collections.min(tiemposEspera));
        setTiempoEsperaMax(Collections.max(tiemposEspera));
        
        long suma = 0;
        for (long tiempo : tiemposEspera) {
            suma += tiempo;
        }
        if (this.cantCliAtentidos>0)
        	setTiempoEsperaProm((float)suma / this.cantCliAtentidos);
        else {
        	setTiempoEsperaProm(0);
        }
        	
	}
	
	
	public float getTiempoEsperaProm() {
		return tiempoEsperaProm;
	}
	
	public void setTiempoEsperaProm(float tiempoEsperaProm) {
		this.tiempoEsperaProm = tiempoEsperaProm;
	}
	
	public int getCantCliAtentidos() {
		return cantCliAtentidos;
	}
	
	public void setCantCliAtentidos(int cantCliAtentidos) {
		this.cantCliAtentidos = cantCliAtentidos;
	}
	
	public float getTiempoEsperaMin() {
		return tiempoEsperaMin;
	}
	
	public void setTiempoEsperaMin(float tiempoEsperaMin) {
		this.tiempoEsperaMin = tiempoEsperaMin;
	}
	
	public float getTiempoEsperaMax() {
		return tiempoEsperaMax;
	}
	
	public void setTiempoEsperaMax(float tiempoEsperaMax) {
		this.tiempoEsperaMax = tiempoEsperaMax;
	}


	public List<Long> getTiemposEspera() {
		return tiemposEspera;
	}


	public void setTiemposEspera(List<Long> tiemposEspera) {
		this.tiemposEspera = tiemposEspera;
	}


	public LocalDate getFechaActual() {
		return fechaActual;
	}


	public void setFechaActual(LocalDate fechaActual) {
		this.fechaActual = fechaActual;
	}
	
	
	
}
