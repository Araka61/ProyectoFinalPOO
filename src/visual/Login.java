package visual;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

import logico.BolsaEmpleo;
import logico.GestorFicheros;
import logico.Usuario;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField pfContrasena;
	private boolean esVisible = false;
	private JButton btnLogin;
	private final MenuPrincipal frame;

	// Colores Paleta 
	private final Color bgPrincipal = new Color(243, 244, 246); // Gris muy claro
	private final Color bgInputs = Color.WHITE;                 // Blanco puro
	private final Color colorTexto = new Color(31, 41, 55);     // Gris carbón oscuro
	private final Color colorVerde = new Color(16, 185, 129);   // Verde 
	private final Color colorRojo = new Color(239, 68, 68);  // Rojo
	private final Color colorAzul = new Color(37, 99, 235); // Azul estándar

	/**
	 * Launch the application.
	 */
	
	/*
	 * public static void main(String[] args) {
		try {
			Login dialog = new Login(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

	/**
	 * Create the dialog.
	 */
	public Login(MenuPrincipal frame) {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GestorFicheros.guardarDatosFicheros();
				frame.dispose();
			}
		});
		this.frame = frame;
		setTitle("Login");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setForeground(colorTexto);
		contentPanel.setBackground(bgPrincipal);
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setForeground(colorTexto);
		lblNewLabel.setBounds(38, 37, 89, 14);
		contentPanel.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.setBackground(bgInputs);
		txtUsuario.setForeground(colorTexto);
		txtUsuario.addKeyListener(new KeyAdapter() {	
			@Override
			public void keyReleased(KeyEvent e) {
				validarCampos();
			}
		});
		txtUsuario.setBounds(38, 57, 261, 20);
		contentPanel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		{
			JLabel lblContrasea = new JLabel("Contraseña:");
			lblContrasea.setForeground(colorTexto);
			lblContrasea.setBounds(38, 88, 89, 14);
			contentPanel.add(lblContrasea);
		}
		
		pfContrasena = new JPasswordField();
		pfContrasena.setForeground(colorTexto);
		pfContrasena.setBackground(bgInputs);
		pfContrasena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarCampos();
			}
		});
		pfContrasena.setEchoChar('*');
		pfContrasena.setBounds(38, 113, 225, 20);
		contentPanel.add(pfContrasena);
		
		JRadioButton rdbtnMostrar = new JRadioButton("Mostrar contraseña");
		rdbtnMostrar.setBackground(bgPrincipal);
		rdbtnMostrar.setForeground(colorTexto);
		rdbtnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!esVisible) {
					pfContrasena.setEchoChar((char)0);
					esVisible = true;
				} else {
					pfContrasena.setEchoChar('*');
					esVisible = false;
				}
			}
		});
		rdbtnMostrar.setBounds(269, 112, 143, 23);
		contentPanel.add(rdbtnMostrar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(bgPrincipal);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				btnLogin = new JButton("Login");
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String contra = new String(pfContrasena.getPassword());
						if (BolsaEmpleo.getInstancia().login(txtUsuario.getText(), contra)) {
							Usuario u = BolsaEmpleo.getInstancia().getUsuarioPorUserName(txtUsuario.getText());
							BolsaEmpleo.getInstancia().setCookieUsuario(BolsaEmpleo.getInstancia().getUsuarioPorUserName(txtUsuario.getText()));
							GestorFicheros.guardarCookies();
							frame.cargarInterfazSegunUsuario(u);
							frame.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Advertencia de inicio de sesión", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				btnLogin.setForeground(Color.WHITE);
				btnLogin.setBackground(colorRojo);
				btnLogin.setEnabled(false);
				btnLogin.setActionCommand("OK");
				buttonPane.add(btnLogin);
				getRootPane().setDefaultButton(btnLogin);
			}
			
			JButton btnNuevoUsuario = new JButton("Nuevo usuario");
			btnNuevoUsuario.setForeground(Color.WHITE);
			btnNuevoUsuario.setBackground(colorAzul); 
			btnNuevoUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setAlwaysOnTop(false);
					RegistrarNuevoUsuario registro = new RegistrarNuevoUsuario();
					registro.setModal(true);
					registro.setVisible(true);
					Usuario cookie = BolsaEmpleo.getInstancia().getCookieUsuario();
					if (cookie != null) {
						frame.cargarInterfazSegunUsuario(cookie);
						frame.setVisible(true);
						dispose();
					}
				}
			});
			btnNuevoUsuario.setActionCommand("OK");
			buttonPane.add(btnNuevoUsuario);
			
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GestorFicheros.guardarDatosFicheros();
						frame.dispose();
						dispose();
					}
				});
				cancelButton.setForeground(Color.WHITE);
				cancelButton.setBackground(colorRojo);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}	
	}
	private void validarCampos() {
		boolean usuarioLleno = !txtUsuario.getText().isEmpty();
		boolean passwordLleno = pfContrasena.getPassword().length > 0;
		
		if (usuarioLleno && passwordLleno) {
			btnLogin.setEnabled(true);
			btnLogin.setBackground(colorVerde); 
		} else {
			btnLogin.setEnabled(false);
			btnLogin.setBackground(colorRojo);
		}
	}	
}