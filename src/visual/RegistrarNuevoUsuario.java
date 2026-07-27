package visual;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import logico.GestorFicheros;
import logico.BolsaEmpleo;
import logico.Usuario;
import logico.Grado;
import logico.Tecnico;
import logico.Trabajador;
import logico.Solicitud;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JRadioButton;

public class RegistrarNuevoUsuario extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JRadioButton rdbtnUsuario;
	private JRadioButton rdbtnEmpresa;
	private JPanel panelContenedor;
	private CardLayout cardLayoutPrincipal;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JTextField txtCiudad;
	private JRadioButton rdMasculino;
	private JRadioButton rdFemenino;
	private JTextField txtTiempoDisponible;
	private JCheckBox chkLicencia;
	private JTextField txtUsuarioLogin;
	private JPasswordField pfContrasenaPersona;
	private JRadioButton rdbtnGrado;
	private JRadioButton rdbtnTecnico;
	private JRadioButton rdbtnTrabajador;
	private JPanel panelNivel;
	private CardLayout cardLayoutNivel;
	private JTextField txtUniversidad;
	private JTextField txtCarrera;
	private JTextField txtTituloUniversitario;
	private JTextField txtInstituto;
	private JTextField txtDiplomaTecnico;
	private JTextField txtEspecialidad;
	private JTextField txtOficio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarNuevoUsuario dialog = new RegistrarNuevoUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarNuevoUsuario() {
		setAlwaysOnTop(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GestorFicheros.guardarDatosFicheros();
			}
		});
		setBounds(100, 100, 747, 531);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblTipoRegistro = new JLabel("Tipo de registro:");
		lblTipoRegistro.setBounds(15, 12, 110, 20);
		contentPanel.add(lblTipoRegistro);

		rdbtnUsuario = new JRadioButton("Usuario");
		rdbtnUsuario.setSelected(true);
		rdbtnUsuario.setBounds(135, 11, 100, 23);
		contentPanel.add(rdbtnUsuario);

		rdbtnEmpresa = new JRadioButton("Empresa");
		rdbtnEmpresa.setBounds(245, 11, 100, 23);
		contentPanel.add(rdbtnEmpresa);

		ButtonGroup grupoTipoRegistro = new ButtonGroup();
		grupoTipoRegistro.add(rdbtnUsuario);
		grupoTipoRegistro.add(rdbtnEmpresa);

		cardLayoutPrincipal = new CardLayout();
		panelContenedor = new JPanel(cardLayoutPrincipal);
		panelContenedor.setBounds(15, 42, 690, 510);
		contentPanel.add(panelContenedor);

		JPanel panelUsuarioCard = crearPanelUsuario();
		JPanel panelEmpresaCard = new JPanel(); 

		panelContenedor.add(panelUsuarioCard, "USUARIO");
		panelContenedor.add(panelEmpresaCard, "EMPRESA");

		rdbtnUsuario.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					cardLayoutPrincipal.show(panelContenedor, "USUARIO");
			}
		});
		rdbtnEmpresa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					cardLayoutPrincipal.show(panelContenedor, "EMPRESA");
			}
		});

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setForeground(new Color(255, 255, 255));
		btnRegistrar.setBackground(new Color(0, 128, 0));
		btnRegistrar.setActionCommand("OK");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
			}
		});
		buttonPane.add(btnRegistrar);
		getRootPane().setDefaultButton(btnRegistrar);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestorFicheros.guardarDatosFicheros();
				dispose();
			}
		});
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		btnLimpiar.setForeground(new Color(0, 0, 0));
		btnLimpiar.setBackground(new Color(255, 255, 0));
		btnLimpiar.setActionCommand("OK");
		buttonPane.add(btnLimpiar);
		cancelButton.setForeground(new Color(255, 255, 255));
		cancelButton.setBackground(new Color(128, 0, 0));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

	private JPanel crearPanelUsuario() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel lblCedula = new JLabel("Cedula:");
		lblCedula.setBounds(10, 10, 80, 20);
		panel.add(lblCedula);
		txtCedula = new JTextField();
		txtCedula.setBounds(100, 10, 160, 20);
		panel.add(txtCedula);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(280, 10, 80, 20);
		panel.add(lblNombre);
		txtNombre = new JTextField();
		txtNombre.setBounds(360, 10, 160, 20);
		panel.add(txtNombre);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(10, 40, 80, 20);
		panel.add(lblTelefono);
		txtTelefono = new JTextField();
		txtTelefono.setBounds(100, 40, 160, 20);
		panel.add(txtTelefono);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(280, 40, 80, 20);
		panel.add(lblCorreo);
		txtCorreo = new JTextField();
		txtCorreo.setBounds(360, 40, 160, 20);
		panel.add(txtCorreo);

		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(10, 70, 80, 20);
		panel.add(lblCiudad);
		txtCiudad = new JTextField();
		txtCiudad.setBounds(100, 70, 160, 20);
		panel.add(txtCiudad);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(280, 70, 80, 20);
		panel.add(lblSexo);

		rdMasculino = new JRadioButton("M");
		rdMasculino.setSelected(true);
		rdMasculino.setBounds(360, 70, 55, 20);
		panel.add(rdMasculino);

		rdFemenino = new JRadioButton("F");
		rdFemenino.setBounds(415, 70, 55, 20);
		panel.add(rdFemenino);

		ButtonGroup grupoSexo = new ButtonGroup();
		grupoSexo.add(rdMasculino);
		grupoSexo.add(rdFemenino);

		JLabel lblTiempoDisponible = new JLabel("Tiempo disponible:");
		lblTiempoDisponible.setBounds(10, 100, 120, 20);
		panel.add(lblTiempoDisponible);
		txtTiempoDisponible = new JTextField();
		txtTiempoDisponible.setBounds(140, 100, 120, 20);
		panel.add(txtTiempoDisponible);

		chkLicencia = new JCheckBox("Tiene licencia de conducir");
		chkLicencia.setBounds(280, 100, 220, 20);
		panel.add(chkLicencia);

		JLabel lblUsuarioLogin = new JLabel("Usuario:");
		lblUsuarioLogin.setBounds(10, 130, 80, 20);
		panel.add(lblUsuarioLogin);
		txtUsuarioLogin = new JTextField();
		txtUsuarioLogin.setBounds(100, 130, 160, 20);
		panel.add(txtUsuarioLogin);

		JLabel lblContrasenaPersona = new JLabel("Contrase\u00F1a:");
		lblContrasenaPersona.setBounds(280, 130, 80, 20);
		panel.add(lblContrasenaPersona);
		pfContrasenaPersona = new JPasswordField();
		pfContrasenaPersona.setBounds(360, 130, 160, 20);
		panel.add(pfContrasenaPersona);

		JLabel lblTipoPersona = new JLabel("Tipo de persona:");
		lblTipoPersona.setBounds(10, 165, 150, 20);
		panel.add(lblTipoPersona);

		rdbtnGrado = new JRadioButton("Grado");
		rdbtnGrado.setSelected(true);
		rdbtnGrado.setBounds(10, 190, 100, 20);
		panel.add(rdbtnGrado);

		rdbtnTecnico = new JRadioButton("Tecnico");
		rdbtnTecnico.setBounds(120, 190, 100, 20);
		panel.add(rdbtnTecnico);

		rdbtnTrabajador = new JRadioButton("Trabajador");
		rdbtnTrabajador.setBounds(230, 190, 110, 20);
		panel.add(rdbtnTrabajador);

		ButtonGroup grupoNivel = new ButtonGroup();
		grupoNivel.add(rdbtnGrado);
		grupoNivel.add(rdbtnTecnico);
		grupoNivel.add(rdbtnTrabajador);

		cardLayoutNivel = new CardLayout();
		panelNivel = new JPanel(cardLayoutNivel);
		panelNivel.setBounds(10, 215, 660, 200);
		panel.add(panelNivel);

		panelNivel.add(crearPanelGrado(), "GRADO");
		panelNivel.add(crearPanelTecnico(), "TECNICO");
		panelNivel.add(crearPanelTrabajador(), "TRABAJADOR");

		rdbtnGrado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					cardLayoutNivel.show(panelNivel, "GRADO");
			}
		});
		rdbtnTecnico.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					cardLayoutNivel.show(panelNivel, "TECNICO");
			}
		});
		rdbtnTrabajador.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					cardLayoutNivel.show(panelNivel, "TRABAJADOR");
			}
		});

		return panel;
	}

	private JPanel crearPanelGrado() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel lblUniversidad = new JLabel("Universidad:");
		lblUniversidad.setBounds(0, 10, 100, 20);
		panel.add(lblUniversidad);
		txtUniversidad = new JTextField();
		txtUniversidad.setBounds(110, 10, 200, 20);
		panel.add(txtUniversidad);

		JLabel lblCarrera = new JLabel("Carrera:");
		lblCarrera.setBounds(0, 40, 100, 20);
		panel.add(lblCarrera);
		txtCarrera = new JTextField();
		txtCarrera.setBounds(110, 40, 200, 20);
		panel.add(txtCarrera);

		JLabel lblTituloUniversitario = new JLabel("Titulo:");
		lblTituloUniversitario.setBounds(0, 70, 100, 20);
		panel.add(lblTituloUniversitario);
		txtTituloUniversitario = new JTextField();
		txtTituloUniversitario.setBounds(110, 70, 200, 20);
		panel.add(txtTituloUniversitario);

		return panel;
	}

	private JPanel crearPanelTecnico() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel lblInstituto = new JLabel("Instituto:");
		lblInstituto.setBounds(0, 10, 100, 20);
		panel.add(lblInstituto);
		txtInstituto = new JTextField();
		txtInstituto.setBounds(110, 10, 200, 20);
		panel.add(txtInstituto);

		JLabel lblDiplomaTecnico = new JLabel("Diploma tecnico:");
		lblDiplomaTecnico.setBounds(0, 40, 100, 20);
		panel.add(lblDiplomaTecnico);
		txtDiplomaTecnico = new JTextField();
		txtDiplomaTecnico.setBounds(110, 40, 200, 20);
		panel.add(txtDiplomaTecnico);

		JLabel lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setBounds(0, 70, 100, 20);
		panel.add(lblEspecialidad);
		txtEspecialidad = new JTextField();
		txtEspecialidad.setBounds(110, 70, 200, 20);
		panel.add(txtEspecialidad);

		return panel;
	}

	private JPanel crearPanelTrabajador() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel lblOficio = new JLabel("Oficio:");
		lblOficio.setBounds(0, 10, 100, 20);
		panel.add(lblOficio);
		txtOficio = new JTextField();
		txtOficio.setBounds(110, 10, 200, 20);
		panel.add(txtOficio);

		return panel;
	}

	private char getSexoSeleccionado() {
		if (rdFemenino.isSelected())
			return 'F';
		return 'M';
	}

	private void registrar() {
		if (rdbtnUsuario.isSelected()) {
			registrarComoPersona();
		} else {
			// No hay Nada por el momento 
			// Cuando tengamos claro lo de empresa lo hacemos 
		}
	}

	private void registrarComoPersona() {
		if (!validarCamposComunes() || !validarCamposEspecificos()) {
			return; 
		}
		crearYRegistrarPerfil();
		JOptionPane.showMessageDialog(this, "Persona registrada con exito.");
		GestorFicheros.guardarDatosFicheros();
		dispose();
	}

	private boolean validarCamposComunes() {
		if (txtCedula.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty() ||
			txtTelefono.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty() ||
			txtCiudad.getText().trim().isEmpty() || txtTiempoDisponible.getText().trim().isEmpty() ||
			txtUsuarioLogin.getText().trim().isEmpty() || pfContrasenaPersona.getPassword().length == 0) {
			
			JOptionPane.showMessageDialog(this, "Completa todos los datos.");
			return false;
		} else if (comprovarUsuarioYCorreo(txtUsuarioLogin.getText(), txtCorreo.getText())) {
		return true;
		
		}
		JOptionPane.showMessageDialog(this, "Usuario o correo ya registrado");
		return false;
	}
	
	private boolean comprovarUsuarioYCorreo (String userName,String correo){
		if (BolsaEmpleo.getInstancia().getUsuarioPorCorreo(correo) != null ||
			BolsaEmpleo.getInstancia().getUsuarioPorUserName(userName) != null) {
		return false;
	}
		return true;
		
	}

	private boolean validarCamposEspecificos() {
		if (rdbtnGrado.isSelected()) return validarGrado();
		if (rdbtnTecnico.isSelected()) return validarTecnico();
		return validarTrabajador();
	}

	private boolean validarGrado() {
		if (txtUniversidad.getText().trim().isEmpty() || txtCarrera.getText().trim().isEmpty() || 
			txtTituloUniversitario.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Completa Universidad, Carrera y Titulo.");
			return false;
		}
		return true;
	}

	private boolean validarTecnico() {
		if (txtInstituto.getText().trim().isEmpty() || txtDiplomaTecnico.getText().trim().isEmpty() || 
			txtEspecialidad.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Completa Instituto, Diploma y Especialidad.");
			return false;
		}
		return true;
	}

	private boolean validarTrabajador() {
		if (txtOficio.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Completa el campo de Oficio.");
			return false;
		}
		return true;
	}

	private void crearYRegistrarPerfil() {
		String id = "P-" + BolsaEmpleo.generadorIdPersona;
		String pass = new String(pfContrasenaPersona.getPassword());
		Usuario user = new Usuario(id, txtCorreo.getText(), txtUsuarioLogin.getText(), pass, "candidato");

		if (rdbtnGrado.isSelected()) registrarGrado(id, user);
		else if (rdbtnTecnico.isSelected()) registrarTecnico(id, user);
		else registrarTrabajador(id, user);
	}

	private void registrarGrado(String id, Usuario user) {
		Grado g = new Grado(id, txtCedula.getText(), txtNombre.getText(), txtTelefono.getText(),
				txtCorreo.getText(), false, new ArrayList<Solicitud>(), txtTiempoDisponible.getText(),
				chkLicencia.isSelected(), getSexoSeleccionado(), txtCiudad.getText(), 
				txtUniversidad.getText(), txtCarrera.getText(), txtTituloUniversitario.getText());
		BolsaEmpleo.getInstancia().registrarPersona(g, user);
		BolsaEmpleo.getInstancia().setCookieUsuario(user);
		GestorFicheros.guardarDatosFicheros();
	}

	private void registrarTecnico(String id, Usuario user) {
		Tecnico t = new Tecnico(id, txtCedula.getText(), txtNombre.getText(), txtTelefono.getText(),
				txtCorreo.getText(), false, new ArrayList<Solicitud>(), txtTiempoDisponible.getText(),
				chkLicencia.isSelected(), getSexoSeleccionado(), txtCiudad.getText(), 
				txtInstituto.getText(), txtDiplomaTecnico.getText(), txtEspecialidad.getText());
		BolsaEmpleo.getInstancia().registrarPersona(t, user);
		BolsaEmpleo.getInstancia().setCookieUsuario(user);
		GestorFicheros.guardarDatosFicheros();
	}

	private void registrarTrabajador(String id, Usuario user) {
		Trabajador tr = new Trabajador(id, txtCedula.getText(), txtNombre.getText(), txtTelefono.getText(),
				txtCorreo.getText(), false, new ArrayList<Solicitud>(), txtTiempoDisponible.getText(),
				chkLicencia.isSelected(), getSexoSeleccionado(), txtCiudad.getText(), txtOficio.getText());
		BolsaEmpleo.getInstancia().registrarPersona(tr, user);
		BolsaEmpleo.getInstancia().setCookieUsuario(user);
		GestorFicheros.guardarDatosFicheros();
	}
	
	private void limpiarCampos ()
	{
		txtCedula.setText("");
		txtNombre.setText("");
		txtTelefono.setText("");
		txtCorreo.setText("");
		txtCiudad.setText("");
		txtTiempoDisponible.setText("");
		chkLicencia.setSelected(false);
		txtUsuarioLogin.setText("");
		pfContrasenaPersona.setText("");
		rdMasculino.setSelected(true);
		txtUniversidad.setText("");
		txtCarrera.setText("");
		txtTituloUniversitario.setText("");
		txtInstituto.setText("");
		txtDiplomaTecnico.setText("");
		txtEspecialidad.setText("");
		txtOficio.setText("");
		rdbtnGrado.setSelected(true);
		cardLayoutNivel.show(panelNivel, "GRADO");
		rdbtnUsuario.setSelected(true);
		cardLayoutPrincipal.show(panelContenedor, "USUARIO");
	}
}