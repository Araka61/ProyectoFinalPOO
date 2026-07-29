package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.Grado;
import logico.Persona;
import logico.Tecnico;
import logico.Trabajador;
import logico.Usuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class RegistrarSolicitud extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private Usuario usuario = null;
	private String titulo = null;
	private String genero = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarSolicitud dialog = new RegistrarSolicitud(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarSolicitud(String idSolicitud) {
		setAlwaysOnTop(true);
		setTitle("Generar Solicitud");
		setBounds(100, 100, 549, 260);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		if(BolsaEmpleo.getInstancia().getCookieUsuario() != null) {
			usuario = BolsaEmpleo.getInstancia().getCookieUsuario();
		}
		
		JLabel lblNewLabel = new JLabel("Tipo Trabajo: ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(12, 13, 85, 16);
		contentPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBackground(Color.DARK_GRAY);
		textField.setBounds(119, 10, 162, 22);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		if (usuario != null) {
		    Persona persona = BolsaEmpleo.getInstancia().buscarPersona(usuario.getId());
		    if (persona != null) {
		        if(persona instanceof Grado) {
					titulo = ((Grado) persona).getCarrera();
				} else if(persona instanceof Tecnico) {
					titulo = ((Tecnico) persona).getDiplomaTecnico();
				} else if(persona instanceof Trabajador) {
					titulo = ((Trabajador) persona).getOficio();
				}
		    }
		} else {
		    System.out.println("No hay un usuario autenticado en la sesión.");
		}
		
		JLabel lblNewLabel_1 = new JLabel("Titulo: " + titulo);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(293, 13, 226, 16);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tiempo Trabajo:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(12, 42, 105, 16);
		contentPanel.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.DARK_GRAY);
		textField_1.setBounds(119, 39, 162, 22);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Experiencia");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(293, 42, 85, 16);
		contentPanel.add(lblNewLabel_3);
		
		SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 100, 1); 
		JSpinner spinner = new JSpinner(model);
		spinner.setForeground(Color.WHITE);
		spinner.setBackground(Color.DARK_GRAY);
		spinner.setBounds(390, 39, 129, 22);
		contentPanel.add(spinner);
		
		if(usuario != null) {
			Persona persona = BolsaEmpleo.getInstancia().buscarPersona(usuario.getId());
			if(persona != null) {
				if(persona.getSexo() == 'M') {
					genero = "Masculino";
				}else if(persona.getSexo() == 'F') {
					genero = "Femenino";
				}
			}
		}
		
		JLabel lblNewLabel_4 = new JLabel("Sexo: " + genero);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(12, 69, 269, 16);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Provincia");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(293, 71, 56, 16);
		contentPanel.add(lblNewLabel_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(390, 68, 129, 22);
		contentPanel.add(comboBox);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Dispuesto a mudarse");
		chckbxNewCheckBox.setForeground(Color.WHITE);
		chckbxNewCheckBox.setBackground(Color.GRAY);
		chckbxNewCheckBox.setBounds(12, 92, 269, 23);
		contentPanel.add(chckbxNewCheckBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Editar");
				okButton.setBackground(new Color(0, 128, 0));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(128, 0, 0));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
