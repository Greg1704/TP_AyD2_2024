package ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;

public class VentanaSupervisor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		setBounds(100, 100, 588, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 302, 61);
		contentPane.add(panel);
		
		JLabel lblCantiadDePersonas = new JLabel("Cantidad de personas atendidas:");
		panel.add(lblCantiadDePersonas);
		lblCantiadDePersonas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JPanel panelCantPersonasAtendidas = new JPanel();
		panelCantPersonasAtendidas.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCantPersonasAtendidas.setBounds(315, 11, 250, 61);
		contentPane.add(panelCantPersonasAtendidas);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 74, 302, 61);
		contentPane.add(panel_2);
		
		JLabel lblTiempoEsperaProm = new JLabel("Tiempo de espera promedio:");
		panel_2.add(lblTiempoEsperaProm);
		lblTiempoEsperaProm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JPanel panelTiempoEsperaProm = new JPanel();
		panelTiempoEsperaProm.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTiempoEsperaProm.setBounds(315, 74, 250, 61);
		contentPane.add(panelTiempoEsperaProm);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(10, 138, 302, 61);
		contentPane.add(panel_4);
		
		JLabel lblTtiempoMinEspera = new JLabel("Tiempo minimo de espera:");
		panel_4.add(lblTtiempoMinEspera);
		lblTtiempoMinEspera.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JPanel panelTiempoMinEspera = new JPanel();
		panelTiempoMinEspera.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTiempoMinEspera.setBounds(315, 138, 250, 61);
		contentPane.add(panelTiempoMinEspera);
		
		JPanel panel_5_1 = new JPanel();
		panel_5_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_1.setBounds(10, 201, 302, 61);
		contentPane.add(panel_5_1);
		
		JLabel lblTiempoMaxEspera = new JLabel("Tiempo maximo de espera:");
		panel_5_1.add(lblTiempoMaxEspera);
		lblTiempoMaxEspera.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JPanel panel_5_2 = new JPanel();
		panel_5_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5_2.setBounds(315, 201, 250, 61);
		contentPane.add(panel_5_2);
	}
}
