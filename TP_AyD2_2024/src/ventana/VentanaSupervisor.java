package ventana;
import modelo.Estadisticas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorVentanaSupervisor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaSupervisor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorVentanaSupervisor controladorVentanaSupervisor;

	private JLabel lblCantClientesAtendidos;
	private JLabel lblTiempoPromedioEspera;
	private JLabel lblTiempoMaxEspera;
	private JLabel lblTiempoMinEspera;
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
		setBounds(100, 100, 637, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTextoCantClientesAtendidos = new JLabel("Cantidad de clientes atendidos:");
		lblTextoCantClientesAtendidos.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTextoCantClientesAtendidos.setBounds(10, 25, 359, 56);
		contentPane.add(lblTextoCantClientesAtendidos);
		
		JLabel lblCantClientesAtendidos = new JLabel("");
		lblCantClientesAtendidos.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCantClientesAtendidos.setBounds(379, 38, 146, 43);
		contentPane.add(lblCantClientesAtendidos);
		
		JLabel lblTextoTiempoMaxEspera = new JLabel("Tiempo maximo de espera:");
		lblTextoTiempoMaxEspera.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTextoTiempoMaxEspera.setBounds(10, 188, 326, 56);
		contentPane.add(lblTextoTiempoMaxEspera);
		
		JLabel lblTiempoMaxEspera = new JLabel("");
		lblTiempoMaxEspera.setBounds(381, 201, 135, 43);
		contentPane.add(lblTiempoMaxEspera);
		
		JLabel lblTextoTiempoMinEspera = new JLabel("Tiempo minimo de espera:");
		lblTextoTiempoMinEspera.setHorizontalAlignment(SwingConstants.LEFT);
		lblTextoTiempoMinEspera.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTextoTiempoMinEspera.setBounds(10, 255, 326, 56);
		contentPane.add(lblTextoTiempoMinEspera);
		
		JLabel lblTiempoPromedioEspera = new JLabel("Tiempo promedio de espera:");
		lblTiempoPromedioEspera.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTiempoPromedioEspera.setBounds(10, 107, 326, 56);
		contentPane.add(lblTiempoPromedioEspera);
		
		JLabel lblTiempoMinEspera = new JLabel("");
		lblTiempoMinEspera.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTiempoMinEspera.setBounds(370, 257, 146, 43);
		contentPane.add(lblTiempoMinEspera);
		
		JLabel lblTiempoPromEspera = new JLabel("");
		lblTiempoPromEspera.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTiempoPromEspera.setBounds(385, 120, 131, 43);
		contentPane.add(lblTiempoPromEspera);

		
		this.setVisible(true);
	}
	
	public void setControlador(ControladorVentanaSupervisor controladorVentanaSupervisor) {
		this.controladorVentanaSupervisor = controladorVentanaSupervisor;
		this.inicializa(controladorVentanaSupervisor); 
	}

	
	private void CargaEstadistica() {
		this.lblCantClientesAtendidos.setText(estadisticas.getCantCliAtentidos());
		this.lblTiempoPromedioEspera.setText(estadisticas.getTiempoEsperaProm());
		this.lblTiempoMaxEspera.setText(estadisticas.getTiempoEsperaMax());
		this.lblTiempoMinEspera.setText(estadisticas.getTiempoEsperaMin());
	}
	
}
