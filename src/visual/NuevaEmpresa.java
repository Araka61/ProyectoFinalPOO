package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import logico.GestorFicheros;
import logico.BolsaEmpleo;
import logico.Empresa;

public class NuevaEmpresa extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JFormattedTextField ftxtRnc;
	private JTextField txtRepresentante;
	private JPasswordField pfClaveDeSeguridad;
	private JComboBox<String> cmbTipo;
	private final Color bgPrincipal = new Color(243, 244, 246); 
	private final Color bgInputs = Color.WHITE;
	private final Color colorTexto = new Color(31, 41, 55);   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NuevaEmpresa dialog = new NuevaEmpresa(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevaEmpresa(Window parent) {
		super(parent, ModalityType.APPLICATION_MODAL);
		setTitle("Registro Empresa");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GestorFicheros.guardarDatosFicheros();
			}
		});
		
		setForeground(colorTexto);
		setBackground(bgPrincipal);
		setResizable(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 642, 398);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setForeground(colorTexto);
		contentPanel.setBackground(bgPrincipal);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(colorTexto);
		lblNombre.setBounds(20, 20, 100, 20);
		contentPanel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setForeground(colorTexto);
		txtNombre.setBackground(bgInputs);
		txtNombre.setBounds(150, 20, 250, 20);
		contentPanel.add(txtNombre);

		JLabel lblRnc = new JLabel("RNC:");
		lblRnc.setForeground(colorTexto);
		lblRnc.setBounds(20, 60, 100, 20);
		contentPanel.add(lblRnc);
		
		ftxtRnc = crearCampoRnc();
		ftxtRnc.setForeground(colorTexto);
		ftxtRnc.setBackground(bgInputs);
		ftxtRnc.setBounds(150, 60, 250, 20);
		contentPanel.add(ftxtRnc);

		JLabel lblRepresentante = new JLabel("Representante:");
		lblRepresentante.setForeground(colorTexto);
		lblRepresentante.setBounds(20, 100, 120, 20);
		contentPanel.add(lblRepresentante);
		
		txtRepresentante = new JTextField();
		txtRepresentante.setForeground(colorTexto);
		txtRepresentante.setBackground(bgInputs);
		txtRepresentante.setBounds(150, 100, 250, 20);
		contentPanel.add(txtRepresentante);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setForeground(colorTexto);
		lblTipo.setBounds(20, 140, 100, 20);
		contentPanel.add(lblTipo);

		JLabel lblClaveDeSeguridad = new JLabel("Clave de seguridad:");
		lblClaveDeSeguridad.setForeground(colorTexto);
		lblClaveDeSeguridad.setBounds(20, 180, 130, 20);
		contentPanel.add(lblClaveDeSeguridad);
		
		pfClaveDeSeguridad = new JPasswordField();
		pfClaveDeSeguridad.setToolTipText("Clave de seguridad necesaria para registrar un usuario asociado a la empresa.");
		pfClaveDeSeguridad.setEchoChar('*');
		pfClaveDeSeguridad.setForeground(colorTexto);
		pfClaveDeSeguridad.setBackground(bgInputs);
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
		rdbtnclave.setForeground(colorTexto);
		rdbtnclave.setBackground(bgPrincipal);
		rdbtnclave.setBounds(408, 178, 127, 25);
		contentPanel.add(rdbtnclave);
		
		cmbTipo = new JComboBox<String>();
		cmbTipo.setForeground(colorTexto);
		cmbTipo.setBackground(bgInputs);
		cmbTipo.setBounds(150, 154, 250, 22);
		llenarTipos();
		contentPanel.add(cmbTipo);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setForeground(colorTexto);
			buttonPane.setBackground(bgPrincipal);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.setForeground(Color.WHITE);
				okButton.setBackground(new Color(16, 185, 129)); // Verde Éxito
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
				cancelButton.setBackground(new Color(239, 68, 68)); // Rojo Peligro
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
		String clave = new String(pfClaveDeSeguridad.getPassword());
		BolsaEmpleo.getInstancia().registrarEmpresa(txtNombre.getText().trim(), ftxtRnc.getText(),
				txtRepresentante.getText().trim(), obtenerTipoSel(),
				clave.trim());
		GestorFicheros.guardarDatosFicheros();
		JOptionPane.showMessageDialog(this, "Empresa registrada con exito.");
		dispose();
	}

	private boolean validarCampos() {
		if (txtNombre.getText().trim().isEmpty() || ftxtRnc.getText().contains("_") ||
				txtRepresentante.getText().trim().isEmpty() || !(cmbTipo.getSelectedIndex() >= 0) ||
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
	
	private void llenarTipos(){
		cmbTipo.removeAllItems();
		for (String aux : BolsaEmpleo.getInstancia().getTiposEmpresa()) {
			cmbTipo.addItem(aux);
		}
	}
	
	private String obtenerTipoSel() {
		String aux = null;
		aux = cmbTipo.getSelectedItem().toString().trim();
		return aux;
	}
}