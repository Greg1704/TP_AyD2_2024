package ejecutables;

import javax.swing.JOptionPane;

public class CosasDePrueba {
	public static void main(String[] args) {
		
		int siOno = JOptionPane.showConfirmDialog(null,"Se presento el cliente?",null, JOptionPane.YES_NO_OPTION);
			
		if (siOno == JOptionPane.YES_OPTION) {
            System.out.println("El usuario seleccionó Sí.");
            // Aquí puedes agregar el código que deseas ejecutar si el usuario seleccionó Sí.
        } else {
            System.out.println("El usuario seleccionó No.");
            // Aquí puedes agregar el código que deseas ejecutar si el usuario seleccionó No.
        }
		
	}
	
}
