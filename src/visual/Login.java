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

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField pfContrasena;
	private static boolean esVisible = false;

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
		txtUsuario.setBounds(38, 57, 261, 20);
		contentPanel.add(txtUsuario);
		txtUsuario.setColumns(10);
		{
			JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
			lblContrasea.setBounds(38, 88, 89, 14);
			contentPanel.add(lblContrasea);
		}
		
		pfContrasena = new JPasswordField();
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
				JButton btnLogin = new JButton("Login");
				btnLogin.setEnabled(false);
				btnLogin.setActionCommand("OK");
				buttonPane.add(btnLogin);
				getRootPane().setDefaultButton(btnLogin);
			}
			
			JButton btnNuevoUsuario = new JButton("Nuevo usuario");
			btnNuevoUsuario.setActionCommand("OK");
			buttonPane.add(btnNuevoUsuario);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
