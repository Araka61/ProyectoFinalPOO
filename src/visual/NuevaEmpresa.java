package visual;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import logico.GestorFicheros;
import logico.BolsaEmpleo;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class NuevaEmpresa extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JFormattedTextField ftxtRnc;
	private JTextField txtRepresentante;
	private JTextField txtTipo;
	private JPasswordField pfClaveDeSeguridad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NuevaEmpresa dialog = new NuevaEmpresa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevaEmpresa() {
		setTitle("Registro Empresa");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GestorFicheros.guardarDatosFicheros();
			}
		});
		setForeground(Color.WHITE);
		setBackground(Color.GRAY);
		setResizable(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 642, 398);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(Color.WHITE);
		contentPanel.setBackground(Color.GRAY);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(20, 20, 100, 20);
		contentPanel.add(lblNombre);
		txtNombre = new JTextField();
		txtNombre.setForeground(Color.WHITE);
		txtNombre.setBackground(Color.DARK_GRAY);
		txtNombre.setBounds(150, 20, 250, 20);
		contentPanel.add(txtNombre);

		JLabel lblRnc = new JLabel("RNC:");
		lblRnc.setForeground(Color.WHITE);
		lblRnc.setBounds(20, 60, 100, 20);
		contentPanel.add(lblRnc);
		ftxtRnc = crearCampoRnc();
		ftxtRnc.setForeground(Color.WHITE);
		ftxtRnc.setBackground(Color.DARK_GRAY);
		ftxtRnc.setBounds(150, 60, 250, 20);
		contentPanel.add(ftxtRnc);

		JLabel lblRepresentante = new JLabel("Representante:");
		lblRepresentante.setForeground(Color.WHITE);
		lblRepresentante.setBounds(20, 100, 120, 20);
		contentPanel.add(lblRepresentante);
		txtRepresentante = new JTextField();
		txtRepresentante.setForeground(Color.WHITE);
		txtRepresentante.setBackground(Color.DARK_GRAY);
		txtRepresentante.setBounds(150, 100, 250, 20);
		contentPanel.add(txtRepresentante);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setBounds(20, 140, 100, 20);
		contentPanel.add(lblTipo);
		txtTipo = new JTextField();
		txtTipo.setForeground(Color.WHITE);
		txtTipo.setBackground(Color.DARK_GRAY);
		txtTipo.setBounds(150, 140, 250, 20);
		contentPanel.add(txtTipo);

		JLabel lblClaveDeSeguridad = new JLabel("Clave de seguridad:");
		lblClaveDeSeguridad.setForeground(Color.WHITE);
		lblClaveDeSeguridad.setBounds(20, 180, 130, 20);
		contentPanel.add(lblClaveDeSeguridad);
		pfClaveDeSeguridad = new JPasswordField();
		pfClaveDeSeguridad.setEchoChar('*');
		pfClaveDeSeguridad.setForeground(Color.WHITE);
		pfClaveDeSeguridad.setBackground(Color.DARK_GRAY);
		pfClaveDeSeguridad.setBounds(150, 180, 250, 20);
		contentPanel.add(pfClaveDeSeguridad);
		
		JRadioButton rdbtnclave = new JRadioButton("Mostrar clave");
		rdbtnclave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnclave.isSelected())
					pfClaveDeSeguridad.setEchoChar((char) 0);
				else 
					pfClaveDeSeguridad.setEchoChar('*');
			}
		});
		rdbtnclave.setForeground(Color.WHITE);
		rdbtnclave.setBackground(Color.GRAY);
		rdbtnclave.setBounds(408, 178, 127, 25);
		contentPanel.add(rdbtnclave);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setForeground(Color.WHITE);
			buttonPane.setBackground(Color.GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.setForeground(Color.WHITE);
				okButton.setBackground(new Color(0, 128, 0));
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						registrarEmpresa();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GestorFicheros.guardarDatosFicheros();
						dispose();
					}
				});
				cancelButton.setForeground(Color.WHITE);
				cancelButton.setBackground(new Color(128, 0, 0));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private JFormattedTextField crearCampoRnc() {
		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("#-##-#####-#");
			mascara.setPlaceholderCharacter('_');
		} catch (ParseException e) {
		}
		return new JFormattedTextField(mascara);
	}

	private void registrarEmpresa() {
		if (!validarCampos()|| !noExisteEmpresa() || !noExisteRNC()) {
			return;
		}
		BolsaEmpleo.getInstancia().registrarEmpresa(txtNombre.getText().trim(), ftxtRnc.getText(),
				txtRepresentante.getText().trim(), txtTipo.getText().trim(),
				new String(pfClaveDeSeguridad.getPassword()));
		GestorFicheros.guardarDatosFicheros();
		JOptionPane.showMessageDialog(this, "Empresa registrada con exito.");
		dispose();
	}

	private boolean validarCampos() {
		if (txtNombre.getText().trim().isEmpty() || ftxtRnc.getText().contains("_") ||
				txtRepresentante.getText().trim().isEmpty() || txtTipo.getText().trim().isEmpty() ||
				pfClaveDeSeguridad.getPassword().length == 0) {
			JOptionPane.showMessageDialog(this, "Completa todos los datos. El RNC debe tener el formato 0-00-00000-0.");
			return false;
		}
		return true;
	}
	private boolean noExisteEmpresa() {
		if (BolsaEmpleo.getInstancia().getEmpresaNombre(txtNombre.getText().trim()) != null) {
			JOptionPane.showMessageDialog(this, "Empresa ya registrada en el sistema");
			return false;
		}
		return true;
	}
	private boolean noExisteRNC() {
		if (BolsaEmpleo.getInstancia().getEmpresaRNC(ftxtRnc.getText().trim() )!= null) {
			JOptionPane.showMessageDialog(this, "RNC ya registrada en el sistema");
			return false;
		}
		return true;
	} 
}