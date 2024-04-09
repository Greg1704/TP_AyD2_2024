package ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controlador.ControladorVentanaOperador;
import controlador.ControladorVentanaTotem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaTotem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	private JButton btn0;
	private JButton btnConfirmarDni;
	private JButton btnBorrar;
	private ControladorVentanaTotem controladorVentanaTotem;

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
	public VentanaTotem() { //falta boton de borrar
		
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
		
		btn1 = new JButton("1");
		panel.add(btn1);
		
		btn2 = new JButton("2");
		panel.add(btn2);
		
		btn3 = new JButton("3");
		panel.add(btn3);
		
		btn4 = new JButton("4");
		panel.add(btn4);
		
		btn5 = new JButton("5");
		panel.add(btn5);
		
		btn6 = new JButton("6");
		panel.add(btn6);
		
		btn7 = new JButton("7");
		panel.add(btn7);
		
		btn8 = new JButton("8");
		panel.add(btn8);
		
		btn9 = new JButton("9");
		panel.add(btn9);
		
		btnBorrar = new JButton("←");
		btnBorrar.setBounds(89, 187, 45, 23);
		contentPane.add(btnBorrar);
		
		btnConfirmarDni = new JButton("Confirmar");
		btnConfirmarDni.setBounds(134, 187, 95, 23);
		contentPane.add(btnConfirmarDni);
		
		
		
		btn0 = new JButton("0");
		btn0.setBounds(89, 165, 140, 23);
		contentPane.add(btn0);
		
		
		btn1.addActionListener(new ActionListener() { //boton 1
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + '1');
			}
		});
		btn2.addActionListener(new ActionListener() { //boton 2
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + '2');
			}
		});
		btn3.addActionListener(new ActionListener() { //boton 3
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + '3');
			}
		});
		btn4.addActionListener(new ActionListener() { //boton 4
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + '4');
			}
		});
		btn5.addActionListener(new ActionListener() { //boton 5
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + '5');
			}
		});
		btn6.addActionListener(new ActionListener() { //boton 6
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + '6');
			}
		});
		btn7.addActionListener(new ActionListener() { //boton 7
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + '7');
			}
		});
		btn8.addActionListener(new ActionListener() { //boton 8
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + '8');
			}
		});
		btn9.addActionListener(new ActionListener() { //boton 9
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + '9');
			}
		});
		btn0.addActionListener(new ActionListener() { //boton 0
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + '0');
			}
		});
		
		
		btnBorrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 if (textField.getText().length() > 0) {
					 String text = textField.getText().substring(0, textField.getText().length() - 1); 
					 textField.setText(text);
				 }
			}
		});
		
		textField.getDocument().addDocumentListener(new DocumentListener() { 
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				textField.setForeground(Color.BLACK);
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		
		this.setVisible(true);
	}
		
	public void setControlador(ControladorVentanaTotem controladorVentanaTotem) {
		this.controladorVentanaTotem = controladorVentanaTotem;
		//this.inicializa(controladorVentanaTotem); 
	}
	public void setActionListener(ActionListener actionListener) {
		this.btnConfirmarDni.addActionListener(actionListener);
		this.btnBorrar.addActionListener(actionListener);
		this.textField.addActionListener(actionListener);
	}
	
	
	public String getDni() { 
		return this.textField.getText();
	}
	
	public void setDni(String dniNuevo) { 
		this.textField.setText(dniNuevo);
	}
	
	public void errorLargo () { 
		this.textField.setForeground(Color.RED);
	}
	}
