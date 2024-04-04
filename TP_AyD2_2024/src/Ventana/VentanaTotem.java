package Ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaTotem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaTotem frame = new VentanaTotem();
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
	public VentanaTotem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 337, 277);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(108, 53, 100, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDni = new JLabel("Ingrese DNI:");
		lblDni.setBounds(128, 33, 80, 14);
		contentPane.add(lblDni);
		
		JPanel panel = new JPanel();
		panel.setBounds(89, 87, 140, 78);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 3, 0, 0));
		
		JButton btn1 = new JButton("1");
		panel.add(btn1);
		
		JButton btn2 = new JButton("2");
		panel.add(btn2);
		
		JButton btn3 = new JButton("3");
		panel.add(btn3);
		
		JButton btn4 = new JButton("4");
		panel.add(btn4);
		
		JButton btn5 = new JButton("5");
		panel.add(btn5);
		
		JButton btn6 = new JButton("6");
		panel.add(btn6);
		
		JButton btn7 = new JButton("7");
		panel.add(btn7);
		
		JButton btn8 = new JButton("8");
		panel.add(btn8);
		
		JButton btn9 = new JButton("9");
		panel.add(btn9);
		
		JButton btnConfirmarDni = new JButton("Confirmar");
		btnConfirmarDni.setBounds(89, 187, 140, 23);
		contentPane.add(btnConfirmarDni);
		
		JButton btn0 = new JButton("0");
		btn0.setBounds(89, 165, 140, 23);
		contentPane.add(btn0);
	}
}
