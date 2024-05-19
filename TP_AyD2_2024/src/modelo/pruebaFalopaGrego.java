package modelo;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class pruebaFalopaGrego {
	public static void main(String[] args) {
		LocalTime fechaActual = LocalTime.now();
		System.out.println(fechaActual.withNano(0));
	}
}
