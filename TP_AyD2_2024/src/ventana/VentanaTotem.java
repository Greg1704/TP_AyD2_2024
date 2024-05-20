package ventana;

import java.awt.Color;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.text.DateFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.ControladorVentanaTotem;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import java.text.ParseException;


public class VentanaTotem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDNI;
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
	private JTextField textFieldSeleccionado;
	private JLabel lblDdmmaaaa;
	private JFormattedTextField formattedTextFieldFechaNac;

	
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
	
	public VentanaTotem() { 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 277);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldDNI= new JTextField();
		textFieldDNI.setBounds(108, 53, 100, 20);
		contentPane.add(textFieldDNI);
		textFieldDNI.setColumns(10);
		
		JLabel lblDni = new JLabel("Ingrese DNI:");
		lblDni.setBounds(128, 28, 80, 14);
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
		
		JLabel lblIngreseSuFecha = new JLabel("Ingrese su fecha de nacimiento");
		lblIngreseSuFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseSuFecha.setBounds(269, 11, 193, 20);
		contentPane.add(lblIngreseSuFecha);

		try {
            MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
            dateFormatter.setPlaceholderCharacter('x');
            formattedTextFieldFechaNac = new JFormattedTextField(dateFormatter);
            formattedTextFieldFechaNac.setText("  /  / ");
            formattedTextFieldFechaNac.setBounds(311, 53, 100, 20);
            contentPane.add(formattedTextFieldFechaNac);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
		FocusAdapter focusListener = new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                textFieldSeleccionado = (JTextField) e.getSource();
            }
        };
        
        textFieldDNI.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                textFieldSeleccionado = textFieldDNI;
            }
        });

        formattedTextFieldFechaNac.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                formattedTextFieldFechaNac.setEditable(true); // Habilita la edición del campo de texto
                formattedTextFieldFechaNac.selectAll(); // Selecciona todo el texto en el campo de texto al obtener el foco
            }
        });
        
        lblDdmmaaaa = new JLabel("(dd/mm/aaaa):");
        lblDdmmaaaa.setVerticalAlignment(SwingConstants.TOP);
        lblDdmmaaaa.setHorizontalAlignment(SwingConstants.CENTER);
        lblDdmmaaaa.setBounds(269, 28, 176, 23);
        contentPane.add(lblDdmmaaaa);

        ActionListener numberListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                textFieldSeleccionado.setText(textFieldSeleccionado.getText() + source.getText());
            }
        };
        
        btn1.addActionListener(numberListener);
        btn2.addActionListener(numberListener);
        btn3.addActionListener(numberListener);
        btn4.addActionListener(numberListener);
        btn5.addActionListener(numberListener);
        btn6.addActionListener(numberListener);
        btn7.addActionListener(numberListener);
        btn8.addActionListener(numberListener);
        btn9.addActionListener(numberListener);
        btn0.addActionListener(numberListener);

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldSeleccionado.getText().length() > 0) {
                    String text = textFieldSeleccionado.getText().substring(0, textFieldSeleccionado.getText().length() - 1);
                    textFieldSeleccionado.setText(text);
                }
            }
        });
        
        	textFieldDNI.getDocument().addDocumentListener(new DocumentListener() { 
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				textFieldDNI.setForeground(Color.BLACK);
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
		this.textFieldDNI.addActionListener(actionListener);
	}
	
	
	public String getDni() { 
		return this.textFieldDNI.getText();
	}
	
	public void setDni(String dniNuevo) { 
		this.textFieldDNI.setText(dniNuevo);
	}
	
	public void errorLargo () { 
	   textFieldDNI.setForeground(Color.RED);   
	}
}
