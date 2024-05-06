package ventana;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorVentanaOperador;
import controlador.ControladorVentanaSupervisor;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class VentanaLogin extends JFrame implements MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private ControladorVentanaOperador controladorVentanaOperador;
	private ControladorVentanaSupervisor controladorVentanaSupervisor;
	 private boolean botonPresionado = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
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
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(176, 40, 88, 14);
		contentPane.add(lblUsuario);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(91, 65, 232, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		textFieldUsuario.addKeyListener(this);
		
		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setBounds(176, 101, 88, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(91, 126, 232, 20);
		contentPane.add(passwordField);
		passwordField.addKeyListener(this);
		
		btnLogin = new JButton("Confirmar");
		btnLogin.setBounds(153, 183, 104, 23);
		contentPane.add(btnLogin);
		btnLogin.addMouseListener(this);
		btnLogin.setEnabled(false);
		
		this.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.textFieldUsuario || e.getSource() == this.passwordField) {
			if(this.textFieldUsuario.getText().isEmpty() || this.passwordField.getPassword().length<1) {
				this.btnLogin.setEnabled(false);
			}else
				this.btnLogin.setEnabled(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == this.btnLogin) {
			this.botonPresionado = true;
			this.btnLogin.setEnabled(false);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setControladorOperador(ControladorVentanaOperador controladorVentanaOperador) {
		this.controladorVentanaOperador = controladorVentanaOperador;
		//this.inicializa(controladorVentanaTotem); 
	}
	
	public void setControladorSupervisor(ControladorVentanaSupervisor controladorVentanaSupervisor) {
		this.controladorVentanaSupervisor = controladorVentanaSupervisor;
		//this.inicializa(controladorVentanaTotem); 
	}
	
	
	 public boolean esperarBoton() {
	        while (!botonPresionado) {
	            try {
	                Thread.sleep(100); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        return botonPresionado;
	 }

	public JTextField getTextFieldUsuario() {
		return textFieldUsuario;
	}

	public void setTextFieldUsuario(JTextField textFieldUsuario) {
		this.textFieldUsuario = textFieldUsuario;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}
	 
	 
}
