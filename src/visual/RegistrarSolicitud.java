package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.GestorFicheros;
import logico.Grado;
import logico.Persona;
import logico.Solicitud;
import logico.Tecnico;
import logico.Trabajador;
import logico.Usuario;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegistrarSolicitud extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTipoTrabajo;
	private JTextField txtTiempoTrabajo;
	private JSpinner spnExperiencia;
	private JComboBox<String> cbxProvincia;
	private JCheckBox chkMudarse;
	private JSpinner spnSalarioMin;
	private JSpinner spnSalarioMax;

	private Usuario usuario = null;
	private Persona persona = null;
	private String titulo = "n/a";
	private String genero = "n/a";

	public static void main(String[] args) {
		try {
			GestorFicheros.cargarDatosDesdeFicheros();
			RegistrarSolicitud dialog = new RegistrarSolicitud(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegistrarSolicitud(Window parent, String idSolicitud) {
	    super(parent, ModalityType.APPLICATION_MODAL);
	    addWindowListener(new WindowAdapter() {
	    	@Override
	    	public void windowClosing(WindowEvent e) {
	    		GestorFicheros.guardarDatosFicheros();
	    	}
	    });
	    setTitle(idSolicitud != null ? "Editar Solicitud" : "Generar Solicitud");
	    setBounds(100, 100, 550, 320);
	    setLocationRelativeTo(parent);
	    setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		if (BolsaEmpleo.getInstancia().getCookieUsuario() != null) {
			usuario = BolsaEmpleo.getInstancia().getCookieUsuario();
			persona = BolsaEmpleo.getInstancia().buscarPersona(usuario.getId());
		}

		if (persona != null) {
			if (persona instanceof Grado) {
				titulo = ((Grado) persona).getCarrera();
			} else if (persona instanceof Tecnico) {
				titulo = ((Tecnico) persona).getDiplomaTecnico();
			} else if (persona instanceof Trabajador) {
				titulo = ((Trabajador) persona).getOficio();
			}
			genero = (persona.getSexo() == 'M') ? "Masculino" : "Femenino";
		}

		JLabel lblTipo = new JLabel("Tipo Trabajo:");
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setBounds(12, 15, 95, 16);
		contentPanel.add(lblTipo);

		txtTipoTrabajo = new JTextField();
		txtTipoTrabajo.setForeground(Color.WHITE);
		txtTipoTrabajo.setBackground(Color.DARK_GRAY);
		txtTipoTrabajo.setBounds(115, 12, 160, 22);
		contentPanel.add(txtTipoTrabajo);

		JLabel lblTitulo = new JLabel("Título/Oficio: " + titulo);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(290, 15, 230, 16);
		contentPanel.add(lblTitulo);

		JLabel lblTiempo = new JLabel("Disponibilidad:");
		lblTiempo.setForeground(Color.WHITE);
		lblTiempo.setBounds(12, 48, 95, 16);
		contentPanel.add(lblTiempo);

		txtTiempoTrabajo = new JTextField();
		txtTiempoTrabajo.setForeground(Color.WHITE);
		txtTiempoTrabajo.setBackground(Color.DARK_GRAY);
		txtTiempoTrabajo.setBounds(115, 45, 160, 22);
		contentPanel.add(txtTiempoTrabajo);

		JLabel lblExperiencia = new JLabel("Exp. (Años):");
		lblExperiencia.setForeground(Color.WHITE);
		lblExperiencia.setBounds(290, 48, 85, 16);
		contentPanel.add(lblExperiencia);

		spnExperiencia = new JSpinner(new SpinnerNumberModel(0, 0, 60, 1));
		spnExperiencia.setBounds(380, 45, 140, 22);
		contentPanel.add(spnExperiencia);

		JLabel lblSexo = new JLabel("Sexo: " + genero);
		lblSexo.setForeground(Color.WHITE);
		lblSexo.setBounds(12, 81, 260, 16);
		contentPanel.add(lblSexo);

		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setForeground(Color.WHITE);
		lblProvincia.setBounds(290, 81, 85, 16);
		contentPanel.add(lblProvincia);

		cbxProvincia = new JComboBox<>();
		cbxProvincia.addItem("-- Seleccione --");
		cbxProvincia.addItem("Santo Domingo");
		cbxProvincia.addItem("Santiago");
		cbxProvincia.addItem("La Vega");
		cbxProvincia.addItem("Puerto Plata");
		cbxProvincia.addItem("San Cristóbal");
		cbxProvincia.addItem("Otra");
		cbxProvincia.setBounds(380, 78, 140, 22);
		contentPanel.add(cbxProvincia);

		JLabel lblSalMin = new JLabel("Salario Mín:");
		lblSalMin.setForeground(Color.WHITE);
		lblSalMin.setBounds(12, 114, 95, 16);
		contentPanel.add(lblSalMin);

		spnSalarioMin = new JSpinner(new SpinnerNumberModel(15000.0, 0.0, 1000000.0, 1000.0));
		spnSalarioMin.setBounds(115, 111, 160, 22);
		contentPanel.add(spnSalarioMin);

		JLabel lblSalMax = new JLabel("Salario Máx:");
		lblSalMax.setForeground(Color.WHITE);
		lblSalMax.setBounds(290, 114, 85, 16);
		contentPanel.add(lblSalMax);

		spnSalarioMax = new JSpinner(new SpinnerNumberModel(25000.0, 0.0, 1000000.0, 1000.0));
		spnSalarioMax.setBounds(380, 111, 140, 22);
		contentPanel.add(spnSalarioMax);

		chkMudarse = new JCheckBox("Dispuesto a mudarse de provincia");
		chkMudarse.setForeground(Color.WHITE);
		chkMudarse.setBackground(Color.GRAY);
		chkMudarse.setBounds(12, 148, 280, 23);
		contentPanel.add(chkMudarse);

		if (idSolicitud != null) {
			Solicitud solicitud = BolsaEmpleo.getInstancia().buscarSolicitud(idSolicitud);
			if (solicitud != null) {
				txtTipoTrabajo.setText(solicitud.getTipoTrabajo());
				txtTiempoTrabajo.setText(solicitud.getTiempoTrabajo());
				spnExperiencia.setValue(solicitud.getExperienciaLaboral());
				cbxProvincia.setSelectedItem(solicitud.getProvincia());
				chkMudarse.setSelected(solicitud.isDispuestoAMudarse());
				spnSalarioMin.setValue((double) solicitud.getRangoMinSalario());
				spnSalarioMax.setValue((double) solicitud.getRangoMaxSalario());
			}
		}
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.GRAY);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton(idSolicitud != null ? "Modificar" : "Aceptar");
		okButton.setBackground(new Color(0, 128, 0));
		okButton.setForeground(Color.WHITE);
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usuario == null || persona == null) {
					JOptionPane.showMessageDialog(null, "No se encontró un perfil de usuario activo.", "Error de Sesión", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String tipo = txtTipoTrabajo.getText().trim();
				String tiempoTrabajo = txtTiempoTrabajo.getText().trim();
				String provincia = (String) cbxProvincia.getSelectedItem();
				int experiencia = (int) spnExperiencia.getValue();
				float minSal = ((Double) spnSalarioMin.getValue()).floatValue();
				float maxSal = ((Double) spnSalarioMax.getValue()).floatValue();
				boolean dispuestoAMudarse = chkMudarse.isSelected();

				if (tipo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor introduzca el tipo de trabajo deseado.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
					txtTipoTrabajo.requestFocus();
					return;
				}

				if (tiempoTrabajo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor introduzca el tiempo/disponibilidad de trabajo.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
					txtTiempoTrabajo.requestFocus();
					return;
				}

				if (provincia == null || provincia.equals("-- Seleccione --")) {
					JOptionPane.showMessageDialog(null, "Por favor seleccione una provincia válida.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (maxSal < minSal) {
					JOptionPane.showMessageDialog(null, "El salario máximo no puede ser menor que el salario mínimo.", "Rango Salarial Inválido", JOptionPane.WARNING_MESSAGE);
					return;
				}

				String tituloCarrera = "n/a";
				String diplomaTecnico = "n/a";
				String habilidadOficio = "n/a";

				if (persona instanceof Grado) {
					tituloCarrera = ((Grado) persona).getCarrera();
				} else if (persona instanceof Tecnico) {
					diplomaTecnico = ((Tecnico) persona).getDiplomaTecnico();
				} else if (persona instanceof Trabajador) {
					habilidadOficio = ((Trabajador) persona).getOficio();
				}

				if (idSolicitud != null) {
					BolsaEmpleo.getInstancia().modificarSolicitud(
						idSolicitud, tipo, tituloCarrera, diplomaTecnico, habilidadOficio,
						tiempoTrabajo, experiencia, persona.getSexo(), provincia,
						persona.isTieneLicencia(), dispuestoAMudarse, minSal, maxSal
					);
					JOptionPane.showMessageDialog(null, "Solicitud modificada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				} else {
					BolsaEmpleo.getInstancia().registrarSolicitud(persona.getId(), tipo, 
						tituloCarrera, diplomaTecnico, habilidadOficio, tiempoTrabajo, 
						experiencia, persona.getSexo(), provincia, persona.isTieneLicencia(), 
						dispuestoAMudarse, minSal, maxSal
					);
					JOptionPane.showMessageDialog(null, "Solicitud creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				}

				GestorFicheros.guardarDatosFicheros();
				dispose();
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestorFicheros.guardarDatosFicheros();
				dispose();
			}
		});
		cancelButton.setBackground(new Color(128, 0, 0));
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
}
