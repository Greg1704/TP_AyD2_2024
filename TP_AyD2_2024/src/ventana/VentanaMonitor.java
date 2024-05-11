package ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorVentanaMonitor;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JScrollPane;

public class VentanaMonitor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorVentanaMonitor cm;
	private JTable tableServDisp;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMonitor frame = new VentanaMonitor();
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
	public VentanaMonitor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMonitor = new JLabel("Monitor");
		lblMonitor.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblMonitor.setBounds(10, 11, 159, 50);
		contentPane.add(lblMonitor);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 325, 158);
		contentPane.add(scrollPane);
		
		tableServDisp = new JTable();
		scrollPane.setViewportView(tableServDisp);
		tableServDisp.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		this.setVisible(true);
	}
	
	
	public void setControlador(ControladorVentanaMonitor cm,HashMap<Integer, Boolean> list) {
		this.cm = cm;
		HashMapServidoresDisponibles model = new HashMapServidoresDisponibles(list);
		this.tableServDisp.setModel(model);
	}
	
	
	public void actualizaServDisp(HashMap<Integer, Boolean> list) {
		HashMapServidoresDisponibles model = new HashMapServidoresDisponibles(list);
		this.tableServDisp.setModel(model);		
	}
}
