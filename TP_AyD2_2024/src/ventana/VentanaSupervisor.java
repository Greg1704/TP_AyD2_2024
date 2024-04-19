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
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;


public class VentanaSupervisor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldCantCliAtendidos;
	private JTextField textFieldTiempoEspProm;
	private JTextField textFieldTiempoMinEsp;
	private JTextField textFieldTiempoMaxEsp;
	private JTextField textFieldFecha;
	private JButton btnActualizar;
	private ControladorVentanaSupervisor controladorVentanaSupervisor;

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
		setBounds(100, 100, 769, 471);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(236, 236, 236));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MONITOREO Y ANÁLISIS");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(261, 11, 232, 25);
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
		textFieldCantCliAtendidos.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCantCliAtendidos.setBackground(new Color(255, 255, 255));
		textFieldCantCliAtendidos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldCantCliAtendidos.setEditable(false);
		textFieldCantCliAtendidos.setBounds(261, 79, 123, 37);
		contentPane.add(textFieldCantCliAtendidos);
		textFieldCantCliAtendidos.setColumns(10);
		
		textFieldTiempoEspProm = new JTextField();
		textFieldTiempoEspProm.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTiempoEspProm.setBackground(new Color(255, 255, 255));
		textFieldTiempoEspProm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldTiempoEspProm.setEditable(false);
		textFieldTiempoEspProm.setColumns(10);
		textFieldTiempoEspProm.setBounds(261, 148, 123, 37);
		contentPane.add(textFieldTiempoEspProm);
		
		textFieldTiempoMinEsp = new JTextField();
		textFieldTiempoMinEsp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTiempoMinEsp.setBackground(new Color(255, 255, 255));
		textFieldTiempoMinEsp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldTiempoMinEsp.setEditable(false);
		textFieldTiempoMinEsp.setColumns(10);
		textFieldTiempoMinEsp.setBounds(261, 218, 123, 37);
		contentPane.add(textFieldTiempoMinEsp);
		
		textFieldTiempoMaxEsp = new JTextField();
		textFieldTiempoMaxEsp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTiempoMaxEsp.setBackground(new Color(255, 255, 255));
		textFieldTiempoMaxEsp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldTiempoMaxEsp.setEditable(false);
		textFieldTiempoMaxEsp.setColumns(10);
		textFieldTiempoMaxEsp.setBounds(261, 288, 123, 37);
		contentPane.add(textFieldTiempoMaxEsp);
		
		JLabel lblNewLabel_3 = new JLabel("FECHA");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(613, 147, 129, 30);
		contentPane.add(lblNewLabel_3);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBackground(new Color(255, 255, 255));
		textFieldFecha.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFecha.setEditable(false);
		textFieldFecha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldFecha.setBounds(578, 188, 129, 37);
		contentPane.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnActualizar.setBounds(10, 384, 732, 39);
		contentPane.add(btnActualizar);
		
		JLabel lblNewLabel_4 = new JLabel("minutos");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(394, 151, 87, 25);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("minutos");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4_1.setBounds(394, 225, 80, 17);
		contentPane.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("minutos");
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4_2.setBounds(394, 295, 87, 17);
		contentPane.add(lblNewLabel_4_2);
		
		this.setVisible(true);
	}
	
	public void setControlador(ControladorVentanaSupervisor controladorVentanaSupervisor) {
		this.controladorVentanaSupervisor = controladorVentanaSupervisor;
		this.inicializa(controladorVentanaSupervisor);
	}
	
	
	public void inicializa(ActionListener actionListener) { 
		this.btnActualizar.addActionListener(actionListener);
		//System.out.println("hola");
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
