package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.PrimitiveIterator.OfDouble;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField pfContrasena;
	private boolean esVisible = false;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setTitle("Login");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(38, 37, 89, 14);
		contentPanel.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {	
			@Override
			public void keyReleased(KeyEvent e) {
				validarCampos();
				 btnLogin.setBackground(new Color(128,0, 0));
			}
		});
		txtUsuario.setBounds(38, 57, 261, 20);
		contentPanel.add(txtUsuario);
		txtUsuario.setColumns(10);
		{
			JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
			lblContrasea.setBounds(38, 88, 89, 14);
			contentPanel.add(lblContrasea);
		}
		
		pfContrasena = new JPasswordField();
		pfContrasena.addKeyListener(new KeyAdapter() {
		
			@Override
			public void keyReleased(KeyEvent e) {
				validarCampos();
			}
		});
		pfContrasena.setEchoChar('*');
		pfContrasena.setBounds(38, 113, 225, 20);
		contentPanel.add(pfContrasena);
		
		JRadioButton rdbtnMostrar = new JRadioButton("Mostrar contrase\u00F1a");
		rdbtnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!esVisible) {
					pfContrasena.setEchoChar((char)0);
					esVisible = true;
					}
				else {
					pfContrasena.setEchoChar('*');
					esVisible = false;
					}
			}
		});
		rdbtnMostrar.setBounds(269, 112, 143, 23);
		contentPanel.add(rdbtnMostrar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnLogin = new JButton("Login");
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				btnLogin.setForeground(new Color(255, 255, 255));
				btnLogin.setBackground(new Color(128, 0, 0));
				btnLogin.setEnabled(false);
				btnLogin.setActionCommand("OK");
				buttonPane.add(btnLogin);
				getRootPane().setDefaultButton(btnLogin);
			}
			
			JButton btnNuevoUsuario = new JButton("Nuevo usuario");
			btnNuevoUsuario.setForeground(new Color(255, 255, 255));
			btnNuevoUsuario.setBackground(new Color(0, 128, 0));
			btnNuevoUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 RegistrarNuevoUsuario registro = new RegistrarNuevoUsuario();
				        registro.setModal(true);
				        registro.setVisible(true);
				        
				}
			});
			btnNuevoUsuario.setActionCommand("OK");
			buttonPane.add(btnNuevoUsuario);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setForeground(new Color(255, 255, 255));
				cancelButton.setBackground(new Color(128, 0, 0));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
		}
		
	}
	private void validarCampos() {
	    boolean usuarioLleno = !txtUsuario.getText().isEmpty();
	    boolean passwordLleno = pfContrasena.getPassword().length > 0;
	    btnLogin.setEnabled(usuarioLleno && passwordLleno);
	    btnLogin.setBackground(new Color(0,128, 0));
	    System.out.println("usuario=" + usuarioLleno + " password=" + passwordLleno);
	}
}
