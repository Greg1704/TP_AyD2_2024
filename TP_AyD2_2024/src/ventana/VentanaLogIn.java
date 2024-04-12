package ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class VentanaLogIn {

	private JFrame frame;
	private JTextField UserName;
	private JTextField PassWord;
	private JTextField txtUsuario;
	private JTextField Contrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogIn window = new VentanaLogIn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaLogIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 375, 201);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setText("Usuario:");
		txtUsuario.setEditable(false);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		UserName = new JTextField();
		panel.add(UserName);
		UserName.setColumns(10);
		
		Contrasena = new JTextField();
		Contrasena.setHorizontalAlignment(SwingConstants.CENTER);
		Contrasena.setEditable(false);
		Contrasena.setText("Password:");
		panel.add(Contrasena);
		Contrasena.setColumns(10);
		
		PassWord = new JTextField();
		panel.add(PassWord);
		PassWord.setColumns(10);
	}
	
	public String getPassword() {
		return this.PassWord.getText(); 
	}
	
	public String getUsername() { 
		return this.UserName.getText();
	}

}
