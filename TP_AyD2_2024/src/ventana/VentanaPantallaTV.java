package ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import controlador.ControladorVentanaPantallaTV;

import java.awt.Color;
import java.awt.Font;

public class VentanaPantallaTV extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorVentanaPantallaTV controladorVentanaPantallaTV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPantallaTV frame = new VentanaPantallaTV();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
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
		
		JLabel lblPantallaDni1 = new JLabel("43317387");
		lblPantallaDni1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_2.add(lblPantallaDni1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_3);
		
		JLabel lblPantallaBox1 = new JLabel("1");
		lblPantallaBox1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_3.add(lblPantallaBox1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_4);
		
		JLabel lblPantallaDni2 = new JLabel("41234567");
		lblPantallaDni2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_4.add(lblPantallaDni2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_5);
		
		JLabel lblPantallaBox2 = new JLabel("2");
		lblPantallaBox2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_5.add(lblPantallaBox2);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_6);
		
		JLabel lblPantallaDni3 = new JLabel("42345678");
		lblPantallaDni3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_6.add(lblPantallaDni3);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_7);
		
		JLabel lblPantallaBox3 = new JLabel("3");
		lblPantallaBox3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_7.add(lblPantallaBox3);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_8);
		
		JLabel lblPantallaDni3_1 = new JLabel("31234567");
		lblPantallaDni3_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_8.add(lblPantallaDni3_1);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_9);
		
		JLabel lblPantallaBox4 = new JLabel("4");
		lblPantallaBox4.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_9.add(lblPantallaBox4);
	}
	
	public void setControlador (ControladorVentanaPantallaTV controladorVentanaPantallaTV) {
		this.controladorVentanaPantallaTV  = controladorVentanaPantallaTV;
	}

}
