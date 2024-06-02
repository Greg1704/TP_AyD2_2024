package ventana;

import java.awt.Color;
import java.awt.EventQueue;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import controlador.ControladorVentanaTotem;

import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

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
    private MaskFormatter dateFormatter;

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

        textFieldDNI = new JTextField();
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
            dateFormatter = new MaskFormatter("##/##/####");
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

        textFieldDNI.addFocusListener(focusListener);
        formattedTextFieldFechaNac.addFocusListener(focusListener);

        lblDdmmaaaa = new JLabel("(dd/mm/aaaa):");
        lblDdmmaaaa.setVerticalAlignment(SwingConstants.TOP);
        lblDdmmaaaa.setHorizontalAlignment(SwingConstants.CENTER);
        lblDdmmaaaa.setBounds(269, 28, 176, 23);
        contentPane.add(lblDdmmaaaa);

        ActionListener numberListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textFieldSeleccionado != null) {
                    JButton source = (JButton) e.getSource();
                    String currentText = textFieldSeleccionado.getText();
                    int pos = textFieldSeleccionado.getCaretPosition();

                    if (textFieldSeleccionado == formattedTextFieldFechaNac) {
                        try {
                            textFieldSeleccionado.getDocument().insertString(pos, source.getText(), null);
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        textFieldSeleccionado.setText(currentText.substring(0, pos) + source.getText() + currentText.substring(pos));
                        textFieldSeleccionado.setCaretPosition(pos + 1);
                    }
                }
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
                if (textFieldSeleccionado != null && textFieldSeleccionado.getText().length() > 0) {
                    int pos = textFieldSeleccionado.getCaretPosition();
                    if (pos > 0) {
                        try {
                            textFieldSeleccionado.getDocument().remove(pos - 1, 1);
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                        }
                    }
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
                // No se necesita implementar
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // No se necesita implementar
            }
        });

        formattedTextFieldFechaNac.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                formattedTextFieldFechaNac.setForeground(Color.BLACK);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // No se necesita implementar
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // No se necesita implementar
            }
        });

        // Filtrar el documento para permitir solo caracteres válidos en el formattedTextField
        ((AbstractDocument) formattedTextFieldFechaNac.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws BadLocationException {
                if (string.matches("[0-9]")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
                if (text.matches("[0-9]")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        this.setVisible(true);
    }

    public void setControlador(ControladorVentanaTotem controladorVentanaTotem) {
        this.controladorVentanaTotem = controladorVentanaTotem;
        // this.inicializa(controladorVentanaTotem);
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

    public void errorLargo() {
        textFieldDNI.setForeground(Color.RED);
    }

    public String getFechaNacimiento() {
        return formattedTextFieldFechaNac.getText();
    }
    
    public void setFechaNacimiento() {
        this.formattedTextFieldFechaNac.setText("");;
        this.formattedTextFieldFechaNac.setValue(null);
    }

    public void errorLargoFechaNacimiento() {
        this.formattedTextFieldFechaNac.setForeground(Color.RED);
    }
}
