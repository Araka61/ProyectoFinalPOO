package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.GestorFicheros;
import logico.Grado;
import logico.Oferta;
import logico.Persona;
import logico.Solicitud;
import logico.Tecnico;
import logico.Trabajador;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class MejoresCoincidencias extends JDialog {

	private final JPanel panelCandidatos = new JPanel();

	
	// Colores Paleta 
		private final Color bgPrincipal = new Color(243, 244, 246); // Gris muy claro
		private final Color bgInputs = Color.WHITE;                 // Blanco puro
		private final Color colorTexto = new Color(31, 41, 55);     // Gris carbón oscuro
		private final Color colorVerde = new Color(16, 185, 129);   // Verde 
		private final Color colorRojo = new Color(239, 68, 68);  // Rojo
		private final Color colorAzul = new Color(37, 99, 235); // Azul estándar

		private JTextField txtNombreCandidato1;
		private JTextField txtCorreoCandidato1;
		private JTextField txtNombreCandidato2;
		private JTextField txtCorreoCandidato2;
		private JTextField txtCorreoCandidato3;
		private JTextField txtNombreCandidato3;
		private JTextField txtCedula;
		private JTextField txtNombre;
		private JTextField txtTelefono;
		private JTextField txtCorreo;
		private JTextField txtDisponibilidad;
		private JTextField txtRangoSalario;
		private JTextField txtProvincia;
		private JTextField txtUniversidad;
		private JTextField txtCarrera;
		private JTextField txtTitulo;
		private JTextField txtDiploma;
		private JTextField txtEspecialidad;
		private JTextField txtOficio;
		private JTextField txtInstituto;
		private JPanel panelUniversitario;
		private JPanel panelTecnico;
		private JPanel panelTrabajador;
		private JCheckBox chkMudarse;
		private JRadioButton rdbSexo;
		private JCheckBox chkLicencia;
		private JTextField txtExperiencia;
		private Solicitud[] candidatos;
		private float[] porcCoincCandidatos;
		private int candidatoSel;
		private JButton btnContratar;
		private JButton btnRechazar;
	/**
	 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MejoresCoincidencias dialog = new MejoresCoincidencias(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MejoresCoincidencias(Oferta oferta) {
		setTitle("Mejores Candidatos");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GestorFicheros.guardarDatosFicheros();
			}
		});
		setBounds(100, 100, 720, 510);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		panelCandidatos.setBounds(0, 0, 320, 437);
		panelCandidatos.setBackground(bgPrincipal);
		panelCandidatos.setBorder(new EmptyBorder(5, 5, 5, 5));
		UIManager.put("RadioButton.disabledText", new Color(31, 41, 55));
		UIManager.put("CheckBox.disabledText", new Color(31, 41, 55));
		candidatos = new Solicitud[3];
		porcCoincCandidatos = new float[3];
		candidatoSel = -1;
		getContentPane().add(panelCandidatos);
		panelCandidatos.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Candidato 1: "+porcCoincCandidatos[0]+"%");
		lblNewLabel.setBounds(10, 11, 117, 14);
		panelCandidatos.add(lblNewLabel);
		
		JLabel nombreCandidato1 = new JLabel("Nombre:");
		nombreCandidato1.setBounds(20, 36, 60, 14);
		panelCandidatos.add(nombreCandidato1);
		
		JLabel correoCandidato1 = new JLabel("Correo:");
		correoCandidato1.setBounds(20, 61, 60, 14);
		panelCandidatos.add(correoCandidato1);
		
		JButton btnSolComplCandidato1 = new JButton("Ver solicitud completa");
		if(candidatos[0] != null) {
			btnSolComplCandidato1.setEnabled(true);
		}else {
			btnSolComplCandidato1.setEnabled(false);
		}
		btnSolComplCandidato1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarPerfil(candidatos[0]);
				candidatoSel = 0;
				btnContratar.setEnabled(true);
				btnRechazar.setEnabled(true);
			}
		});
		btnSolComplCandidato1.setBounds(20, 86, 166, 23);
		btnSolComplCandidato1.setForeground(Color.WHITE);
		btnSolComplCandidato1.setBackground(colorAzul);
		panelCandidatos.add(btnSolComplCandidato1);
		
		JButton btnSolComplCandidato2 = new JButton("Ver solicitud completa");
		if(candidatos[1] != null) {
			btnSolComplCandidato2.setEnabled(true);
		}else {
			btnSolComplCandidato2.setEnabled(false);
		}
		btnSolComplCandidato2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarPerfil(candidatos[1]);
				candidatoSel = 1;
				btnContratar.setEnabled(true);
				btnRechazar.setEnabled(true);
			}
		});
		btnSolComplCandidato2.setForeground(Color.WHITE);
		btnSolComplCandidato2.setBackground(new Color(37, 99, 235));
		btnSolComplCandidato2.setBounds(20, 203, 166, 23);
		panelCandidatos.add(btnSolComplCandidato2);
		
		JLabel label_1 = new JLabel("Correo:");
		label_1.setBounds(20, 178, 60, 14);
		panelCandidatos.add(label_1);
		
		JLabel nombreCandidato2 = new JLabel("Nombre:");
		nombreCandidato2.setBounds(20, 153, 60, 14);
		panelCandidatos.add(nombreCandidato2);
		
		JLabel lblCandidato = new JLabel("Candidato 2: "+porcCoincCandidatos[1]+"%");
		lblCandidato.setBounds(10, 128, 117, 14);
		panelCandidatos.add(lblCandidato);
		
		JLabel lblCandidato_1 = new JLabel("Candidato 3: "+porcCoincCandidatos[2]+"%");
		lblCandidato_1.setBounds(10, 247, 117, 14);
		panelCandidatos.add(lblCandidato_1);
		
		JLabel nombreCandidato3 = new JLabel("Nombre:");
		nombreCandidato3.setBounds(20, 272, 60, 14);
		panelCandidatos.add(nombreCandidato3);
		
		JLabel correoCandidato3 = new JLabel("Correo:");
		correoCandidato3.setBounds(20, 297, 60, 14);
		panelCandidatos.add(correoCandidato3);
		
		JButton btnSolComplCandidato3 = new JButton("Ver solicitud completa");
		if(candidatos[2] != null) {
			btnSolComplCandidato3.setEnabled(true);
		}else {
			btnSolComplCandidato3.setEnabled(false);
		}
		btnSolComplCandidato3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarPerfil(candidatos[2]);
				candidatoSel = 2;
				btnContratar.setEnabled(true);
				btnRechazar.setEnabled(true);
			}
		});
		btnSolComplCandidato3.setForeground(Color.WHITE);
		btnSolComplCandidato3.setBackground(new Color(37, 99, 235));
		btnSolComplCandidato3.setBounds(20, 322, 166, 23);
		panelCandidatos.add(btnSolComplCandidato3);
		
		txtNombreCandidato1 = new JTextField();
		txtNombreCandidato1.setEditable(false);
		txtNombreCandidato1.setBounds(82, 33, 200, 20);
		txtNombreCandidato1.setBackground(bgInputs);
		panelCandidatos.add(txtNombreCandidato1);
		txtNombreCandidato1.setColumns(10);
		
		txtCorreoCandidato1 = new JTextField();
		txtCorreoCandidato1.setEditable(false);
		txtCorreoCandidato1.setColumns(10);
		txtCorreoCandidato1.setBackground(bgInputs);
		txtCorreoCandidato1.setBounds(82, 58, 200, 20);
		panelCandidatos.add(txtCorreoCandidato1);
		
		txtNombreCandidato2 = new JTextField();
		txtNombreCandidato2.setEditable(false);
		txtNombreCandidato2.setColumns(10);
		txtNombreCandidato2.setBackground(bgInputs);
		txtNombreCandidato2.setBounds(82, 150, 200, 20);
		panelCandidatos.add(txtNombreCandidato2);
		
		txtCorreoCandidato2 = new JTextField();
		txtCorreoCandidato2.setEditable(false);
		txtCorreoCandidato2.setColumns(10);
		txtCorreoCandidato2.setBackground(bgInputs);
		txtCorreoCandidato2.setBounds(82, 175, 200, 20);
		panelCandidatos.add(txtCorreoCandidato2);
		
		txtCorreoCandidato3 = new JTextField();
		txtCorreoCandidato3.setEditable(false);
		txtCorreoCandidato3.setColumns(10);
		txtCorreoCandidato3.setBackground(bgInputs);
		txtCorreoCandidato3.setBounds(82, 294, 200, 20);
		panelCandidatos.add(txtCorreoCandidato3);
		
		txtNombreCandidato3 = new JTextField();
		txtNombreCandidato3.setEditable(false);
		txtNombreCandidato3.setColumns(10);
		txtNombreCandidato3.setBackground(bgInputs);
		txtNombreCandidato3.setBounds(82, 269, 200, 20);
		panelCandidatos.add(txtNombreCandidato3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 436, 704, 32);
			buttonPane.setBackground(bgPrincipal);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			
			btnContratar = new JButton("Contratar");
			btnContratar.setEnabled(false);
			btnContratar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(candidatoSel >= 0) {
						if(candidatos[candidatoSel] != null) {
							Persona persona = BolsaEmpleo.getInstancia().buscarPersona(candidatos[candidatoSel].getIdUsuario());
							persona.setEmpleado(true);
							oferta.setCantPuesto(oferta.getCantPuesto()-1);
							JOptionPane.showMessageDialog(null, "El candidato fue contratado", "Candidato contratado", JOptionPane.INFORMATION_MESSAGE);
							if(oferta.getCantPuesto() == 0) {
								JOptionPane.showMessageDialog(null, "El candidato fue contratado, no quedan mas puestos para esta oferta", "Candidato contratado", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
							cargarNuevosCandidatos(oferta);
							candidatoSel = -1;
							vaciarPerfil();
							btnContratar.setEnabled(false);
							btnRechazar.setEnabled(false);
						}
					}
				}
			});
			btnContratar.setForeground(Color.WHITE);
			btnContratar.setBackground(colorVerde);
			btnContratar.setActionCommand("OK");
			buttonPane.add(btnContratar);
			{
				btnRechazar = new JButton("Rechazar");
				btnRechazar.setEnabled(false);
				btnRechazar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(candidatoSel >= 0) {
							if(candidatos[candidatoSel] != null) {
								int opcionSel = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea rechazar este candidato?", "Rechazar candidato",JOptionPane.WARNING_MESSAGE);
								if(opcionSel == JOptionPane.OK_OPTION) {
									oferta.rechazarSolicitud(candidatos[candidatoSel]);
									cargarNuevosCandidatos(oferta);
									candidatoSel = -1;
									vaciarPerfil();
									btnContratar.setEnabled(false);
									btnRechazar.setEnabled(false);
								}
							}
						}
					}
				});
				btnRechazar.setForeground(Color.WHITE);
				btnRechazar.setBackground(colorRojo);
				btnRechazar.setActionCommand("OK");
				buttonPane.add(btnRechazar);
				getRootPane().setDefaultButton(btnRechazar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setForeground(Color.WHITE);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GestorFicheros.guardarDatosFicheros();
						dispose();
					}
				});
				cancelButton.setBackground(colorAzul);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JPanel panelDatos = new JPanel();
		panelDatos.setBackground(bgPrincipal);
		panelDatos.setBounds(320, 0, 384, 334);
		getContentPane().add(panelDatos);
		panelDatos.setLayout(null);
		
		JLabel label = new JLabel("Cedula:");
		label.setForeground(new Color(31, 41, 55));
		label.setBounds(10, 11, 80, 20);
		panelDatos.add(label);
		
		txtCedula = new JTextField();
		txtCedula.setEditable(false);
		txtCedula.setForeground(new Color(31, 41, 55));
		txtCedula.setBackground(Color.WHITE);
		txtCedula.setBounds(100, 11, 261, 20);
		panelDatos.add(txtCedula);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setForeground(new Color(31, 41, 55));
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setBounds(100, 42, 261, 20);
		panelDatos.add(txtNombre);
		
		JLabel label_2 = new JLabel("Nombre:");
		label_2.setForeground(new Color(31, 41, 55));
		label_2.setBounds(10, 42, 80, 20);
		panelDatos.add(label_2);
		
		JLabel label_3 = new JLabel("Telefono:");
		label_3.setForeground(new Color(31, 41, 55));
		label_3.setBounds(10, 104, 80, 20);
		panelDatos.add(label_3);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setForeground(new Color(31, 41, 55));
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBounds(100, 104, 261, 20);
		panelDatos.add(txtTelefono);
		
		JLabel label_4 = new JLabel("Correo:");
		label_4.setForeground(new Color(31, 41, 55));
		label_4.setBounds(10, 73, 80, 20);
		panelDatos.add(label_4);
		
		txtCorreo = new JTextField();
		txtCorreo.setEditable(false);
		txtCorreo.setForeground(new Color(31, 41, 55));
		txtCorreo.setBackground(Color.WHITE);
		txtCorreo.setBounds(100, 73, 261, 20);
		panelDatos.add(txtCorreo);
		
		JLabel label_5 = new JLabel("Disponibilidad:");
		label_5.setForeground(new Color(31, 41, 55));
		label_5.setBounds(10, 138, 95, 16);
		panelDatos.add(label_5);
		
		txtDisponibilidad = new JTextField();
		txtDisponibilidad.setEditable(false);
		txtDisponibilidad.setForeground(new Color(31, 41, 55));
		txtDisponibilidad.setBackground(Color.WHITE);
		txtDisponibilidad.setBounds(100, 135, 261, 22);
		panelDatos.add(txtDisponibilidad);
		
		JLabel lblRangoSalario_1 = new JLabel("Rango Salario:");
		lblRangoSalario_1.setForeground(new Color(31, 41, 55));
		lblRangoSalario_1.setBounds(10, 167, 85, 16);
		panelDatos.add(lblRangoSalario_1);
		
		txtRangoSalario = new JTextField();
		txtRangoSalario.setEditable(false);
		txtRangoSalario.setForeground(new Color(31, 41, 55));
		txtRangoSalario.setBackground(Color.WHITE);
		txtRangoSalario.setBounds(100, 164, 261, 22);
		panelDatos.add(txtRangoSalario);
		
		JLabel label_7 = new JLabel("Sexo:");
		label_7.setForeground(new Color(31, 41, 55));
		label_7.setBounds(10, 280, 80, 20);
		panelDatos.add(label_7);
		
		rdbSexo = new JRadioButton("");
		rdbSexo.setSelected(true);
		rdbSexo.setBounds(100, 279, 109, 23);
		rdbSexo.setEnabled(false);
		rdbSexo.setBackground(bgPrincipal);
		panelDatos.add(rdbSexo);
		
		chkLicencia = new JCheckBox("Tiene licencia de conducir");
		chkLicencia.setForeground(new Color(31, 41, 55));
		chkLicencia.setBackground(new Color(243, 244, 246));
		chkLicencia.setBounds(10, 307, 220, 20);
		chkLicencia.setEnabled(false);
		panelDatos.add(chkLicencia);
		
		chkMudarse = new JCheckBox("Dispuesto a mudarse de provincia");
		chkMudarse.setSelected(false);
		chkMudarse.setForeground(new Color(31, 41, 55));
		chkMudarse.setBackground(new Color(243, 244, 246));
		chkMudarse.setBounds(10, 253, 280, 23);
		chkMudarse.setEnabled(false);
		panelDatos.add(chkMudarse);
		
		JLabel lblRangoSalario = new JLabel("Provincia:");
		lblRangoSalario.setForeground(new Color(31, 41, 55));
		lblRangoSalario.setBounds(10, 227, 95, 16);
		panelDatos.add(lblRangoSalario);
		
		txtProvincia = new JTextField();
		txtProvincia.setEditable(false);
		txtProvincia.setForeground(new Color(31, 41, 55));
		txtProvincia.setBackground(Color.WHITE);
		txtProvincia.setBounds(100, 224, 261, 22);
		panelDatos.add(txtProvincia);
		
		JLabel lblExperiencia = new JLabel("Experiencia:");
		lblExperiencia.setForeground(new Color(31, 41, 55));
		lblExperiencia.setBounds(10, 197, 95, 16);
		panelDatos.add(lblExperiencia);
		
		txtExperiencia = new JTextField();
		txtExperiencia.setForeground(new Color(31, 41, 55));
		txtExperiencia.setEditable(false);
		txtExperiencia.setBackground(Color.WHITE);
		txtExperiencia.setBounds(100, 194, 261, 22);
		panelDatos.add(txtExperiencia);
		
		panelUniversitario = new JPanel();
		panelUniversitario.setBounds(320, 333, 384, 104);
		panelUniversitario.setBackground(bgPrincipal);
		getContentPane().add(panelUniversitario);
		panelUniversitario.setLayout(null);
		
		JLabel lblUniversidad = new JLabel("Universidad:");
		lblUniversidad.setBounds(10, 8, 88, 14);
		lblUniversidad.setForeground(new Color(31, 41, 55));
		panelUniversitario.add(lblUniversidad);
		
		txtUniversidad = new JTextField();
		txtUniversidad.setBounds(100, 5, 261, 20);
		txtUniversidad.setForeground(new Color(31, 41, 55));
		txtUniversidad.setEditable(false);
		txtUniversidad.setBackground(Color.WHITE);
		panelUniversitario.add(txtUniversidad);
		
		JLabel lblCarrera = new JLabel("Carrera:");
		lblCarrera.setForeground(new Color(31, 41, 55));
		lblCarrera.setBounds(10, 36, 88, 14);
		panelUniversitario.add(lblCarrera);
		
		txtCarrera = new JTextField();
		txtCarrera.setForeground(new Color(31, 41, 55));
		txtCarrera.setEditable(false);
		txtCarrera.setBackground(Color.WHITE);
		txtCarrera.setBounds(100, 33, 261, 20);
		panelUniversitario.add(txtCarrera);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setForeground(new Color(31, 41, 55));
		lblTitulo.setBounds(10, 64, 88, 14);
		panelUniversitario.add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setForeground(new Color(31, 41, 55));
		txtTitulo.setEditable(false);
		txtTitulo.setBackground(Color.WHITE);
		txtTitulo.setBounds(100, 61, 261, 20);
		panelUniversitario.add(txtTitulo);
		
		panelTecnico = new JPanel();
		panelTecnico.setBounds(320, 333, 384, 104);
		getContentPane().add(panelTecnico);
		panelTecnico.setLayout(null);
		panelTecnico.setBackground(new Color(243, 244, 246));
		panelTecnico.setVisible(false);
		
		JLabel lblInstituto = new JLabel("Instituto:");
		lblInstituto.setForeground(new Color(31, 41, 55));
		lblInstituto.setBounds(10, 8, 88, 14);
		panelTecnico.add(lblInstituto);
		
		txtInstituto = new JTextField();
		txtInstituto.setForeground(new Color(31, 41, 55));
		txtInstituto.setEditable(false);
		txtInstituto.setBackground(Color.WHITE);
		txtInstituto.setBounds(100, 5, 259, 20);
		panelTecnico.add(txtInstituto);
		
		JLabel lblDiplomaTecnico = new JLabel("Diploma Tecnico:");
		lblDiplomaTecnico.setForeground(new Color(31, 41, 55));
		lblDiplomaTecnico.setBounds(10, 36, 114, 14);
		panelTecnico.add(lblDiplomaTecnico);
		
		txtDiploma = new JTextField();
		txtDiploma.setForeground(new Color(31, 41, 55));
		txtDiploma.setEditable(false);
		txtDiploma.setBackground(Color.WHITE);
		txtDiploma.setBounds(134, 33, 225, 20);
		panelTecnico.add(txtDiploma);
		
		JLabel lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setForeground(new Color(31, 41, 55));
		lblEspecialidad.setBounds(10, 64, 88, 14);
		panelTecnico.add(lblEspecialidad);
		
		txtEspecialidad = new JTextField();
		txtEspecialidad.setForeground(new Color(31, 41, 55));
		txtEspecialidad.setEditable(false);
		txtEspecialidad.setBackground(Color.WHITE);
		txtEspecialidad.setBounds(100, 61, 259, 20);
		panelTecnico.add(txtEspecialidad);
		
		panelTrabajador = new JPanel();
		panelTrabajador.setLayout(null);
		panelTrabajador.setBackground(new Color(243, 244, 246));
		panelTrabajador.setBounds(320, 333, 384, 104);
		panelTrabajador.setVisible(false);
		getContentPane().add(panelTrabajador);
		
		JLabel lblOficio = new JLabel("Oficio:");
		lblOficio.setForeground(new Color(31, 41, 55));
		lblOficio.setBounds(10, 8, 88, 14);
		panelTrabajador.add(lblOficio);
		
		txtOficio = new JTextField();
		txtOficio.setForeground(new Color(31, 41, 55));
		txtOficio.setEditable(false);
		txtOficio.setBackground(Color.WHITE);
		txtOficio.setBounds(100, 5, 261, 20);
		panelTrabajador.add(txtOficio);
	}
	
	private void cargarPerfil(Solicitud solicitud) {
		if(solicitud != null) {
			Persona persona = BolsaEmpleo.getInstancia().buscarPersona(solicitud.getIdUsuario());
			txtCedula.setText(persona.getCedula().trim());
			txtNombre.setText(persona.getNombre().trim());
			txtCorreo.setText(persona.getCorreo().trim());
			txtTelefono.setText(persona.getTelefono().trim());
			txtDisponibilidad.setText(solicitud.getTiempoTrabajo().trim());
			txtRangoSalario.setText(solicitud.getRangoMinSalario()+"-"+solicitud.getRangoMaxSalario());
			txtExperiencia.setText(solicitud.getExperienciaLaboral()+" años");
			txtProvincia.setText(solicitud.getProvincia().trim());
			chkMudarse.setSelected(solicitud.isDispuestoAMudarse());
			if(persona.getSexo() == 'F') {
				rdbSexo.setText("F");
			}else {
				rdbSexo.setText("M");
			}
			chkLicencia.setSelected(persona.isTieneLicencia());
			if(persona instanceof Grado) {
				panelUniversitario.setVisible(true);
				panelTecnico.setVisible(false);
				panelTrabajador.setVisible(false);
				txtUniversidad.setText(((Grado)persona).getUniversidad());
				txtCarrera.setText(((Grado)persona).getCarrera());
				txtTitulo.setText(((Grado)persona).getTituloUniversitario());
			}else if(persona instanceof Tecnico) {
				panelUniversitario.setVisible(false);
				panelTecnico.setVisible(true);
				panelTrabajador.setVisible(false);
				txtInstituto.setText(((Tecnico)persona).getInstituto());
				txtDiploma.setText(((Tecnico)persona).getDiplomaTecnico());
				txtEspecialidad.setText(((Tecnico)persona).getEspecialidad());
			}else if(persona instanceof Trabajador) {
				panelUniversitario.setVisible(false);
				panelTecnico.setVisible(false);
				panelTrabajador.setVisible(true);
				txtOficio.setText(((Trabajador)persona).getOficio());
			}
		}
	}
	
	private void cargarNuevosCandidatos(Oferta oferta) {
		candidatos = BolsaEmpleo.getInstancia().top3Candidatos(oferta);
		if(candidatos[0] != null) {
			porcCoincCandidatos[0] = BolsaEmpleo.getInstancia().calcularPuntosCoincidencia(candidatos[0], oferta);
		}else {
			porcCoincCandidatos[0] = 0;
		}
		if(candidatos[1] != null) {
			porcCoincCandidatos[1] = BolsaEmpleo.getInstancia().calcularPuntosCoincidencia(candidatos[1], oferta);
		}else {
			porcCoincCandidatos[1] = 0;
		}
		if(candidatos[2] != null) {
			porcCoincCandidatos[2] = BolsaEmpleo.getInstancia().calcularPuntosCoincidencia(candidatos[2], oferta);
		}else {
			porcCoincCandidatos[2] = 0;
		}
	}
	
	private void vaciarPerfil() {
		txtCedula.setText("");
		txtNombre.setText("");
		txtCorreo.setText("");
		txtTelefono.setText("");
		txtDisponibilidad.setText("");
		txtRangoSalario.setText("");
		txtExperiencia.setText("");
		txtProvincia.setText("");
		chkMudarse.setSelected(false);
		rdbSexo.setText("");
		chkLicencia.setSelected(false);
		panelUniversitario.setVisible(true);
		panelTecnico.setVisible(false);
		panelTrabajador.setVisible(false);
		txtUniversidad.setText("");
		txtCarrera.setText("");
		txtTitulo.setText("");
		txtInstituto.setText("");
		txtDiploma.setText("");
		txtEspecialidad.setText("");
		txtOficio.setText("");
	}
}
