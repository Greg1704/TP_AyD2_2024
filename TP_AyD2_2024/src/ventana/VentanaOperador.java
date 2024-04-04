package ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorVentanaOperador;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaOperador extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton btnOperador;
	private JPanel contentPane;
	private ControladorVentanaOperador ControladorVentanaOperador;

	
	public VentanaOperador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnOperador = new JButton("Llamar siguiente");
		btnOperador.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnOperador.setBounds(111, 106, 267, 108);
		contentPane.add(btnOperador);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 494, 312);
		contentPane.add(panel);
	}

	
	public void setControlador(ControladorVentanaOperador controladorVentanaOperador) {
		this.ControladorVentanaOperador = controladorVentanaOperador;
		this.inicializa(ControladorVentanaOperador); 
	}
	
	public void inicializa(ActionListener actionListener) { 
		this.btnOperador.addActionListener(actionListener);
		System.out.println("hola");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
