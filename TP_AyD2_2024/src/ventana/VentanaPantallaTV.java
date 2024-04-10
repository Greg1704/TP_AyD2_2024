package ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import controlador.ControladorVentanaPantallaTV;
import modelo.Turno;

import java.awt.Color;
import java.awt.Font;

public class VentanaPantallaTV extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorVentanaPantallaTV controladorVentanaPantallaTV;
	private JLabel lblPantallaDni1;
	private JLabel lblPantallaDni2; 
	private JLabel lblPantallaDni3;
	private JLabel lblPantallaDni4; 
	private JLabel lblPantallaBox1;
	private JLabel lblPantallaBox2;
	private JLabel lblPantallaBox3;
	private JLabel lblPantallaBox4; 
	private HashMap<Integer, Turno> turnosMuestra = new HashMap<>();
	
	public VentanaPantallaTV() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel);
		
		JLabel lblPantallaDniTexto = new JLabel("DNI");
		lblPantallaDniTexto.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(lblPantallaDniTexto);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_1);
		
		JLabel lblPantallaBoxTexto = new JLabel("Box");
		lblPantallaBoxTexto.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_1.add(lblPantallaBoxTexto);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_2);
		
		//de aca abajo estan los casilleros
		
		lblPantallaDni1 = new JLabel("");
		lblPantallaDni1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_2.add(lblPantallaDni1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_3);
		
		lblPantallaBox1 = new JLabel("");
		lblPantallaBox1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_3.add(lblPantallaBox1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_4);
		
		lblPantallaDni2 = new JLabel("");
		lblPantallaDni2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_4.add(lblPantallaDni2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_5);
		
		lblPantallaBox2 = new JLabel("");
		lblPantallaBox2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_5.add(lblPantallaBox2);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_6);
		
		lblPantallaDni3 = new JLabel("");
		lblPantallaDni3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_6.add(lblPantallaDni3);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_7);
		
		lblPantallaBox3 = new JLabel("");
		lblPantallaBox3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_7.add(lblPantallaBox3);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_8);
		
		lblPantallaDni4 = new JLabel("");
		lblPantallaDni4.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_8.add(lblPantallaDni4);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_9);
		
		lblPantallaBox4 = new JLabel("");
		lblPantallaBox4.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_9.add(lblPantallaBox4);
		
		
		this.setVisible(true);
	}
	
	/*public void setActionListener(ActionListener actionListener) { 
		this.lblPantallaBox1).add 
		this.lblPantallaBox2
		this.lblPantallaBox3
		this.lblPantallaBox4
		
	}*/
	
	public void agregaTurno(Turno turno)  { 
		String dni = turno.getDni(); 
		int box = turno.getNumeroDeBox(); 
		
		turnosMuestra.remove(4);
		
		 for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
	            int key = entry.getKey();
	            int value = entry.getValue();
	            if (key >= posicion) {
	                hashMap.put(key, value + incremento);
	            }
	        }

		
        turnosMuestra.put(dni, turno);
        actualizaPantalla(); 
	}
	
	public void actualizaPantalla() { 
		this.lblPantallaBox1.setText(turnosMuestra.);
		this.lblPantallaDni1
		this.lblPantallaBox2
		this.lblPantallaDni2
		this.lblPantallaBox3
		this.lblPantallaDni3
		this.lblPantallaBox4
		this.lblPantallaBox4
	}
	
	public void setControlador (ControladorVentanaPantallaTV controladorVentanaPantallaTV) {
		this.controladorVentanaPantallaTV  = controladorVentanaPantallaTV;
	}
}
