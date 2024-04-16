package ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorVentanaSupervisor;

import javax.swing.JLabel;

import java.awt.Color;
import javax.swing.JTextField;


import modelo.Estadisticas;
import java.awt.Font;


public class VentanaSupervisor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldCantCliAtendidos;
	private JTextField textFieldTiempoEspProm;
	private JTextField textFieldTiempoMinEsp;
	private JTextField textFieldTiempoMaxEsp;
	private JTextField textFieldFecha;
	private ControladorVentanaSupervisor controladorVentanaSupervisor;
	private Estadisticas estadisticas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSupervisor frame = new VentanaSupervisor();
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
	public VentanaSupervisor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 412);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MONITOREO Y ANÁLISIS");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(229, 11, 232, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad de clientes atendidos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 64, 252, 60);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tiempo de espera promedio");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(10, 151, 252, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tiempo mínimo de espera ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(10, 221, 252, 25);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Tiempo máximo de espera");
		lblNewLabel_2_2.setBackground(new Color(0, 0, 0));
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_2.setBounds(10, 291, 252, 25);
		contentPane.add(lblNewLabel_2_2);
		
		textFieldCantCliAtendidos = new JTextField();
		textFieldCantCliAtendidos.setEditable(false);
		textFieldCantCliAtendidos.setBounds(261, 79, 145, 37);
		contentPane.add(textFieldCantCliAtendidos);
		textFieldCantCliAtendidos.setColumns(10);
		
		textFieldTiempoEspProm = new JTextField();
		textFieldTiempoEspProm.setEditable(false);
		textFieldTiempoEspProm.setColumns(10);
		textFieldTiempoEspProm.setBounds(261, 148, 145, 37);
		contentPane.add(textFieldTiempoEspProm);
		
		textFieldTiempoMinEsp = new JTextField();
		textFieldTiempoMinEsp.setEditable(false);
		textFieldTiempoMinEsp.setColumns(10);
		textFieldTiempoMinEsp.setBounds(261, 218, 145, 37);
		contentPane.add(textFieldTiempoMinEsp);
		
		textFieldTiempoMaxEsp = new JTextField();
		textFieldTiempoMaxEsp.setEditable(false);
		textFieldTiempoMaxEsp.setColumns(10);
		textFieldTiempoMaxEsp.setBounds(261, 288, 145, 37);
		contentPane.add(textFieldTiempoMaxEsp);
		
		JLabel lblNewLabel_3 = new JLabel("FECHA");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(536, 144, 129, 37);
		contentPane.add(lblNewLabel_3);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setEditable(false);
		textFieldFecha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldFecha.setBounds(505, 187, 129, 37);
		contentPane.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		this.setVisible(true);
	}
	
	public void setControlador(ControladorVentanaSupervisor controladorVentanaSupervisor) {
		this.controladorVentanaSupervisor = controladorVentanaSupervisor;
	}

	

	public void CargaEstadistica(Estadisticas estadisticas) {
		System.out.println("Carga estadisticas");
		this.textFieldCantCliAtendidos.setText(String.valueOf(estadisticas.getCantCliAtentidos()));
		this.textFieldTiempoEspProm.setText(String.valueOf(estadisticas.getTiempoEsperaProm()));
		this.textFieldTiempoMaxEsp.setText(String.valueOf(estadisticas.getTiempoEsperaMax()));
		this.textFieldTiempoMinEsp.setText(String.valueOf(estadisticas.getTiempoEsperaMin()));
		this.textFieldFecha.setText(String.valueOf(estadisticas.getFechaActual()));
	}
}
