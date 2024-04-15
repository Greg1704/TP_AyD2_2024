package ventana;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controlador.ControladorVentanaOperador;
import controlador.ControladorVentanaSupervisor;

public class VentanaLogiNOFUNCIONA extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JFrame frmLogin;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private ControladorVentanaOperador controladorVentanaOperador;
	private ControladorVentanaSupervisor controladorVentanaSupervisor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogiNOFUNCIONA frame = new VentanaLogiNOFUNCIONA();
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
 


		public VentanaLogiNOFUNCIONA() {
			frmLogin = new JFrame();
			frmLogin.setTitle("LogIn");
			frmLogin.setBounds(100, 100, 450, 300);
			((JFrame) frmLogin).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			SpringLayout springLayout = new SpringLayout();
			frmLogin.getRootPane().setLayout(springLayout);
			
			textUsername = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, textUsername, 68, SpringLayout.NORTH, frmLogin.getRootPane());
			springLayout.putConstraint(SpringLayout.WEST, textUsername, 65, SpringLayout.WEST, frmLogin.getRootPane());
			springLayout.putConstraint(SpringLayout.EAST, textUsername, -69, SpringLayout.EAST, frmLogin.getRootPane());
			frmLogin.getRootPane().add(textUsername);
			textUsername.setColumns(10);
			
			passwordField = new JPasswordField();
			springLayout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, textUsername);
			springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, textUsername);
			frmLogin.getRootPane().add(passwordField);
			
			
			JTextArea txtrUser = new JTextArea();
			springLayout.putConstraint(SpringLayout.WEST, txtrUser, 185, SpringLayout.WEST, frmLogin.getRootPane());
			springLayout.putConstraint(SpringLayout.SOUTH, txtrUser, -6, SpringLayout.NORTH, textUsername);
			springLayout.putConstraint(SpringLayout.EAST, txtrUser, -163, SpringLayout.EAST, frmLogin.getRootPane());
			txtrUser.setLineWrap(true);
			txtrUser.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtrUser.setBackground(new Color(240, 240, 240));
			txtrUser.setEditable(false);
			txtrUser.setText("Username");
			frmLogin.getRootPane().add(txtrUser);
			
			JTextArea txtPass = new JTextArea();
			springLayout.putConstraint(SpringLayout.NORTH, passwordField, 6, SpringLayout.SOUTH, txtPass);
			springLayout.putConstraint(SpringLayout.NORTH, txtPass, 6, SpringLayout.SOUTH, textUsername);
			springLayout.putConstraint(SpringLayout.SOUTH, txtPass, -153, SpringLayout.SOUTH, frmLogin.getRootPane());
			springLayout.putConstraint(SpringLayout.WEST, txtPass, 0, SpringLayout.WEST, txtrUser);
			txtPass.setText("Password");
			txtPass.setLineWrap(true);
			txtPass.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtPass.setEditable(false);
			txtPass.setBackground(UIManager.getColor("Button.background"));
			frmLogin.getRootPane().add(txtPass);
			
			btnLogin = new JButton("LogIn");
			springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 163, SpringLayout.NORTH, frmLogin.getRootPane());
			springLayout.putConstraint(SpringLayout.SOUTH, passwordField, -29, SpringLayout.NORTH, btnLogin);
			springLayout.putConstraint(SpringLayout.WEST, btnLogin, 160, SpringLayout.WEST, frmLogin.getRootPane());
			springLayout.putConstraint(SpringLayout.EAST, btnLogin, -7, SpringLayout.EAST, txtrUser);
			btnLogin.setEnabled(true);
			btnLogin.setBackground(new Color(153, 197, 213));
			frmLogin.getRootPane().add(btnLogin);
			
			btnLogin.addActionListener(new ActionListener() { //boton 7
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!textUsername.getText().equals("") && !passwordField.getText().equals(""));
						consultaAdmin();
				}
			});
			
			this.frmLogin.setVisible(true);
			//this.setVisible(true);
			
		}
		
		public void setControladorOperador(ControladorVentanaOperador controladorVentanaOperador) {
			this.controladorVentanaOperador = controladorVentanaOperador;
			//this.inicializa(controladorVentanaTotem); 
		}
		
		public void setControladorSupervisor(ControladorVentanaSupervisor controladorVentanaSupervisor) {
			this.controladorVentanaSupervisor = controladorVentanaSupervisor;
			//this.inicializa(controladorVentanaTotem); 
		}
		
		public boolean consultaAdmin() {
			String user = this.textUsername.getText();
			if (user.equalsIgnoreCase("admin")) {
				this.setControladorSupervisor(ControladorVentanaSupervisor.getInstancia());
				return true;
			}else { 
				this.setControladorOperador(ControladorVentanaOperador.getInstancia());
				return false;

			}
		}

		public void setActionListener(ActionListener actionListener) {
			this.btnLogin.addActionListener(actionListener);
		}

		
		


		
		
	}

