package visual;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;

import logico.GestorFicheros;
import logico.BolsaEmpleo;
import logico.Empresa;
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
import java.time.LocalDate;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;


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
	private JTextField txtUsuarioLoginEmpresa;
	private JPasswordField pfContrasenaEmpresa;
	private JTextField txtCorreoEmpresa;
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnReclutador;
	private JComboBox<String> cmbEmpresas;
	private JButton btnRegistrarEmpresa;
	private JPasswordField pfClave;
	private boolean contraUsuario =false;
	private boolean contraEmpresa =false;
	private boolean clave =false;
	private JComboBox<String> cmbCiudades;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarNuevoUsuario dialog = new RegistrarNuevoUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			GestorFicheros.cargarDatosDesdeFicheros();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarNuevoUsuario() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GestorFicheros.guardarDatosFicheros();
			}
		});
		setBounds(100, 100, 747, 531);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblTipoRegistro = new JLabel("Tipo de registro:");
		lblTipoRegistro.setForeground(Color.WHITE);
		lblTipoRegistro.setBounds(15, 12, 110, 20);
		contentPanel.add(lblTipoRegistro);

		rdbtnUsuario = new JRadioButton("Usuario");
		rdbtnUsuario.setSelected(true);
		rdbtnUsuario.setForeground(Color.WHITE);
		rdbtnUsuario.setBackground(Color.GRAY);
		rdbtnUsuario.setBounds(135, 11, 100, 23);
		contentPanel.add(rdbtnUsuario);

		rdbtnEmpresa = new JRadioButton("Empresa");
		rdbtnEmpresa.setBackground(Color.GRAY);
		rdbtnEmpresa.setForeground(Color.WHITE);
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
		JPanel panelEmpresaCard = crearPanelEmpresa(); 

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
		buttonPane.setBackground(Color.GRAY);
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
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);

		JLabel lblCedula = new JLabel("Cedula:");
		lblCedula.setForeground(Color.WHITE);
		lblCedula.setBounds(10, 10, 80, 20);
		panel.add(lblCedula);
		txtCedula = new JTextField();
		txtCedula.setForeground(Color.WHITE);
		txtCedula.setBackground(Color.DARK_GRAY);
		txtCedula.setBounds(100, 10, 160, 20);
		panel.add(txtCedula);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(280, 10, 80, 20);
		panel.add(lblNombre);
		txtNombre = new JTextField();
		txtNombre.setForeground(Color.WHITE);
		txtNombre.setBackground(Color.DARK_GRAY);
		txtNombre.setBounds(360, 10, 160, 20);
		panel.add(txtNombre);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setBounds(10, 40, 80, 20);
		panel.add(lblTelefono);
		txtTelefono = new JTextField();
		txtTelefono.setForeground(Color.WHITE);
		txtTelefono.setBackground(Color.DARK_GRAY);
		txtTelefono.setBounds(100, 40, 160, 20);
		panel.add(txtTelefono);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setForeground(Color.WHITE);
		lblCorreo.setBounds(280, 40, 80, 20);
		panel.add(lblCorreo);
		txtCorreo = new JTextField();
		txtCorreo.setForeground(Color.WHITE);
		txtCorreo.setBackground(Color.DARK_GRAY);
		txtCorreo.setBounds(360, 40, 160, 20);
		panel.add(txtCorreo);

		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setForeground(Color.WHITE);
		lblCiudad.setBounds(10, 70, 80, 20);
		panel.add(lblCiudad);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setForeground(Color.WHITE);
		lblSexo.setBounds(280, 70, 80, 20);
		panel.add(lblSexo);

		rdMasculino = new JRadioButton("M");
		rdMasculino.setForeground(Color.WHITE);
		rdMasculino.setBackground(Color.GRAY);
		rdMasculino.setSelected(true);
		rdMasculino.setBounds(360, 70, 55, 20);
		panel.add(rdMasculino);

		rdFemenino = new JRadioButton("F");
		rdFemenino.setBackground(Color.GRAY);
		rdFemenino.setForeground(Color.WHITE);
		rdFemenino.setBounds(415, 70, 55, 20);
		panel.add(rdFemenino);

		ButtonGroup grupoSexo = new ButtonGroup();
		grupoSexo.add(rdMasculino);
		grupoSexo.add(rdFemenino);

		JLabel lblTiempoDisponible = new JLabel("Tiempo disponible:");
		lblTiempoDisponible.setForeground(Color.WHITE);
		lblTiempoDisponible.setBounds(10, 100, 120, 20);
		panel.add(lblTiempoDisponible);
		txtTiempoDisponible = new JTextField();
		txtTiempoDisponible.setForeground(Color.WHITE);
		txtTiempoDisponible.setBackground(Color.DARK_GRAY);
		txtTiempoDisponible.setBounds(140, 100, 120, 20);
		panel.add(txtTiempoDisponible);

		chkLicencia = new JCheckBox("Tiene licencia de conducir");
		chkLicencia.setBackground(Color.GRAY);
		chkLicencia.setForeground(Color.WHITE);
		chkLicencia.setBounds(280, 100, 220, 20);
		panel.add(chkLicencia);

		JLabel lblUsuarioLogin = new JLabel("Usuario:");
		lblUsuarioLogin.setForeground(Color.WHITE);
		lblUsuarioLogin.setBounds(10, 130, 80, 20);
		panel.add(lblUsuarioLogin);
		txtUsuarioLogin = new JTextField();
		txtUsuarioLogin.setForeground(Color.WHITE);
		txtUsuarioLogin.setBackground(Color.DARK_GRAY);
		txtUsuarioLogin.setBounds(100, 130, 160, 20);
		panel.add(txtUsuarioLogin);

		JLabel lblContrasenaPersona = new JLabel("Contrase\u00F1a:");
		lblContrasenaPersona.setForeground(Color.WHITE);
		lblContrasenaPersona.setBounds(280, 130, 80, 20);
		panel.add(lblContrasenaPersona);
		pfContrasenaPersona = new JPasswordField();
		pfContrasenaPersona.setEchoChar('*');
		pfContrasenaPersona.setForeground(Color.WHITE);
		pfContrasenaPersona.setBackground(Color.DARK_GRAY);
		pfContrasenaPersona.setBounds(360, 130, 160, 20);
		panel.add(pfContrasenaPersona);

		JLabel lblTipoPersona = new JLabel("Tipo de persona:");
		lblTipoPersona.setForeground(Color.WHITE);
		lblTipoPersona.setBounds(10, 165, 150, 20);
		panel.add(lblTipoPersona);

		rdbtnGrado = new JRadioButton("Grado");
		rdbtnGrado.setForeground(Color.WHITE);
		rdbtnGrado.setBackground(Color.GRAY);
		rdbtnGrado.setSelected(true);
		rdbtnGrado.setBounds(10, 190, 100, 20);
		panel.add(rdbtnGrado);

		rdbtnTecnico = new JRadioButton("Tecnico");
		rdbtnTecnico.setForeground(Color.WHITE);
		rdbtnTecnico.setBackground(Color.GRAY);
		rdbtnTecnico.setBounds(120, 190, 100, 20);
		panel.add(rdbtnTecnico);

		rdbtnTrabajador = new JRadioButton("Trabajador");
		rdbtnTrabajador.setForeground(Color.WHITE);
		rdbtnTrabajador.setBackground(Color.GRAY);
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

		JRadioButton rdbtnContraUsuario = new JRadioButton("Mostrar Contrase\u00F1a");
		rdbtnContraUsuario.setForeground(Color.WHITE);
		rdbtnContraUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!contraUsuario) {
					pfContrasenaPersona.setEchoChar((char)0);
					contraUsuario = true;
				}else {
					pfContrasenaPersona.setEchoChar('*');
					contraUsuario = false;
				}
			}
		});
		rdbtnContraUsuario.setBackground(Color.GRAY);
		rdbtnContraUsuario.setBounds(543, 128, 147, 25);
		panel.add(rdbtnContraUsuario);
		
		cmbCiudades = new JComboBox<String>();
		cmbCiudades.setForeground(Color.WHITE);
		cmbCiudades.setBackground(Color.DARK_GRAY);
		cmbCiudades.setBounds(100, 70, 160, 20);
		llenarCiudades();
		panel.add(cmbCiudades);

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

	private JPanel crearPanelEmpresa() {
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);



		JLabel lblUsuarioLoginEmpresa = new JLabel("Usuario:");
		lblUsuarioLoginEmpresa.setForeground(Color.WHITE);
		lblUsuarioLoginEmpresa.setBounds(10, 10, 80, 20);
		panel.add(lblUsuarioLoginEmpresa);
		txtUsuarioLoginEmpresa = new JTextField();
		txtUsuarioLoginEmpresa.setForeground(Color.WHITE);
		txtUsuarioLoginEmpresa.setBackground(Color.DARK_GRAY);
		txtUsuarioLoginEmpresa.setBounds(100, 10, 160, 20);
		panel.add(txtUsuarioLoginEmpresa);

		JLabel lblContrasenaEmpresa = new JLabel("Contrase\u00F1a:");
		lblContrasenaEmpresa.setForeground(Color.WHITE);
		lblContrasenaEmpresa.setBounds(280, 10, 80, 20);
		panel.add(lblContrasenaEmpresa);
		pfContrasenaEmpresa = new JPasswordField();
		pfContrasenaEmpresa.setEchoChar('*');
		pfContrasenaEmpresa.setForeground(Color.WHITE);
		pfContrasenaEmpresa.setBackground(Color.DARK_GRAY);
		pfContrasenaEmpresa.setBounds(360, 10, 160, 20);
		panel.add(pfContrasenaEmpresa);

		JLabel lblCorreoEmpresa = new JLabel("Correo:");
		lblCorreoEmpresa.setForeground(Color.WHITE);
		lblCorreoEmpresa.setBounds(10, 40, 80, 20);
		panel.add(lblCorreoEmpresa);
		txtCorreoEmpresa = new JTextField();
		txtCorreoEmpresa.setForeground(Color.WHITE);
		txtCorreoEmpresa.setBackground(Color.DARK_GRAY);
		txtCorreoEmpresa.setBounds(100, 40, 160, 20);
		panel.add(txtCorreoEmpresa);

		JLabel lblEmpresas = new JLabel("Empresa:");
		lblEmpresas.setForeground(Color.WHITE);
		lblEmpresas.setBounds(10, 70, 80, 20);
		panel.add(lblEmpresas);
		cmbEmpresas = new JComboBox<String>();
		cmbEmpresas.setForeground(Color.WHITE);
		cmbEmpresas.setBackground(Color.DARK_GRAY);
		cmbEmpresas.setBounds(100, 70, 200, 20);
		panel.add(cmbEmpresas);

		llenarEmpresas();

		btnRegistrarEmpresa = new JButton("Registrar Empresa");
		btnRegistrarEmpresa.setForeground(Color.WHITE);
		btnRegistrarEmpresa.setBackground(new Color(0, 128, 0));
		btnRegistrarEmpresa.setBounds(310, 70, 139, 20);
		btnRegistrarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAlwaysOnTop(false);
				NuevaEmpresa dialogNuevaEmpresa = new NuevaEmpresa(RegistrarNuevoUsuario.this);
				dialogNuevaEmpresa.setVisible(true);
				llenarEmpresas();
			}

		});
		panel.add(btnRegistrarEmpresa);

		JLabel lblRol = new JLabel("Rol:");
		lblRol.setForeground(Color.WHITE);
		lblRol.setBounds(10, 165, 80, 20);
		panel.add(lblRol);

		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setForeground(Color.WHITE);
		rdbtnAdmin.setBackground(Color.GRAY);
		rdbtnAdmin.setSelected(true);
		rdbtnAdmin.setBounds(100, 165, 100, 20);
		panel.add(rdbtnAdmin);

		rdbtnReclutador = new JRadioButton("Reclutador");
		rdbtnReclutador.setForeground(Color.WHITE);
		rdbtnReclutador.setBackground(Color.GRAY);
		rdbtnReclutador.setBounds(202, 165, 120, 20);
		panel.add(rdbtnReclutador);

		ButtonGroup grupoRolEmpresa = new ButtonGroup();
		grupoRolEmpresa.add(rdbtnAdmin);
		grupoRolEmpresa.add(rdbtnReclutador);

		JRadioButton rdbtnContraEmpresa = new JRadioButton("Mostrar contrase\u00F1a");
		rdbtnContraEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!contraEmpresa) {
					pfContrasenaEmpresa.setEchoChar((char) 0);
					contraEmpresa= true;
				}
				else {
					pfContrasenaEmpresa.setEchoChar('*');
					contraEmpresa = false;
				}
			}
		});
		rdbtnContraEmpresa.setForeground(Color.WHITE);
		rdbtnContraEmpresa.setBackground(Color.GRAY);
		rdbtnContraEmpresa.setBounds(531, 9, 151, 23);
		panel.add(rdbtnContraEmpresa);

		pfClave = new JPasswordField();
		pfClave.setForeground(Color.WHITE);
		pfClave.setEchoChar('*');
		pfClave.setBackground(Color.DARK_GRAY);
		pfClave.setBounds(100, 102, 160, 20);
		panel.add(pfClave);

		JLabel lblClave = new JLabel("Clave:");
		lblClave.setForeground(Color.WHITE);
		lblClave.setBounds(10, 105, 80, 20);
		panel.add(lblClave);

		JRadioButton rdbtnClave = new JRadioButton("Mostrar clave");
		rdbtnClave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!clave)
				{
					pfClave.setEchoChar((char) 0);
					clave = true;
				}
				else {
					pfClave.setEchoChar('*');
					clave = false;
				}

			}
		});
		rdbtnClave.setForeground(Color.WHITE);
		rdbtnClave.setBackground(Color.GRAY);
		rdbtnClave.setBounds(280, 102, 151, 23);
		panel.add(rdbtnClave);

		return panel;
	}


	private JPanel crearPanelGrado() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);

		JLabel lblUniversidad = new JLabel("Universidad:");
		lblUniversidad.setForeground(Color.WHITE);
		lblUniversidad.setBounds(0, 10, 100, 20);
		panel.add(lblUniversidad);
		txtUniversidad = new JTextField();
		txtUniversidad.setForeground(Color.WHITE);
		txtUniversidad.setBackground(Color.DARK_GRAY);
		txtUniversidad.setBounds(110, 10, 200, 20);
		panel.add(txtUniversidad);

		JLabel lblCarrera = new JLabel("Carrera:");
		lblCarrera.setForeground(Color.WHITE);
		lblCarrera.setBounds(0, 40, 100, 20);
		panel.add(lblCarrera);
		txtCarrera = new JTextField();
		txtCarrera.setForeground(Color.WHITE);
		txtCarrera.setBackground(Color.DARK_GRAY);
		txtCarrera.setBounds(110, 40, 200, 20);
		panel.add(txtCarrera);

		JLabel lblTituloUniversitario = new JLabel("Titulo:");
		lblTituloUniversitario.setForeground(Color.WHITE);
		lblTituloUniversitario.setBounds(0, 70, 100, 20);
		panel.add(lblTituloUniversitario);
		txtTituloUniversitario = new JTextField();
		txtTituloUniversitario.setForeground(Color.WHITE);
		txtTituloUniversitario.setBackground(Color.DARK_GRAY);
		txtTituloUniversitario.setBounds(110, 70, 200, 20);
		panel.add(txtTituloUniversitario);

		return panel;
	}

	private JPanel crearPanelTecnico() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.GRAY);
		JLabel lblInstituto = new JLabel("Instituto:");
		lblInstituto.setBounds(0, 10, 100, 20);
		lblInstituto.setForeground(Color.WHITE);
		panel.add(lblInstituto);
		txtInstituto = new JTextField();
		txtInstituto.setBounds(110, 10, 200, 20);
		txtInstituto.setForeground(Color.WHITE);
		txtInstituto.setBackground(Color.DARK_GRAY);
		panel.add(txtInstituto);

		JLabel lblDiplomaTecnico = new JLabel("Diploma tecnico:");
		lblDiplomaTecnico.setBounds(0, 40, 100, 20);
		lblDiplomaTecnico.setForeground(Color.WHITE);
		panel.add(lblDiplomaTecnico);
		txtDiplomaTecnico = new JTextField();
		txtDiplomaTecnico.setBounds(110, 40, 200, 20);
		txtDiplomaTecnico.setForeground(Color.WHITE);
		txtDiplomaTecnico.setBackground(Color.DARK_GRAY);
		panel.add(txtDiplomaTecnico);

		JLabel lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setBounds(0, 70, 100, 20);
		lblEspecialidad.setForeground(Color.WHITE);
		panel.add(lblEspecialidad);
		txtEspecialidad = new JTextField();
		txtEspecialidad.setBounds(110, 70, 200, 20);
		txtEspecialidad.setForeground(Color.WHITE);
		txtEspecialidad.setBackground(Color.DARK_GRAY);
		panel.add(txtEspecialidad);

		return panel;
	}

	private JPanel crearPanelTrabajador() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.GRAY);

		JLabel lblOficio = new JLabel("Oficio:");
		lblOficio.setBounds(0, 10, 100, 20);
		lblOficio.setForeground(Color.WHITE);
		panel.add(lblOficio);
		txtOficio = new JTextField();
		txtOficio.setBounds(110, 10, 200, 20);
		txtOficio.setBackground(Color.DARK_GRAY);
		txtOficio.setForeground(Color.WHITE);
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
			registrarComoUsuarioEmpresa();
		}
	}

	private void registrarComoUsuarioEmpresa(){

		if (!validarCamposUsEmpresa() || !validarClaveEmpresa())
			return;
		else {
			crearYRegistrarPerfil();
			JOptionPane.showMessageDialog(this, "Persona registrada con exito.");
			GestorFicheros.guardarDatosFicheros();
			dispose();
		}

	}
	private boolean validarCamposUsEmpresa () {
		if (txtUsuarioLoginEmpresa.getText().trim().isEmpty() || pfContrasenaEmpresa.getPassword().length == 0||
				txtCorreoEmpresa.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Completa todos los datos.");
			return false;
		}else if (comprovarUsuarioYCorreo(txtUsuarioLoginEmpresa.getText(), txtCorreoEmpresa.getText())) 
			return true;
		JOptionPane.showMessageDialog(this, "Usuario o correo ya registrado");
		return false;
	}
	private boolean validarClaveEmpresa () {
		Empresa aux = obtenerEmpresaSel();
		String clave = new String(pfContrasenaEmpresa.getPassword());
		if (!BolsaEmpleo.getInstancia().claveCorrecta(clave.trim(),aux)) {
			JOptionPane.showMessageDialog(this, "Clave incorrecta");
			return false;
		}
		return true;
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
				cmbCiudades.getSelectedItem().toString().trim().isEmpty() || txtTiempoDisponible.getText().trim().isEmpty() ||
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

		if (rdbtnUsuario.isSelected()) {
			String pass = new String(pfContrasenaPersona.getPassword());
			String cedula = txtCedula.getText().trim();
			String nombre = txtNombre.getText().trim();
			String telefono = txtTelefono.getText().trim();
			String correo = txtCorreo.getText().trim();
			String tiempo = txtTiempoDisponible.getText().trim();
			boolean licencia = chkLicencia.isSelected();
			char sexo = getSexoSeleccionado();
			String ciudad = cmbCiudades.getSelectedItem().toString().trim();
			String user = txtUsuarioLogin.getText().trim();

			if (rdbtnGrado.isSelected()) {
				BolsaEmpleo.getInstancia().registrarPersonaGrado(
						cedula, nombre, telefono, correo, tiempo, licencia, sexo, ciudad,
						txtUniversidad.getText().trim(), txtCarrera.getText().trim(), 
						txtTituloUniversitario.getText().trim(), user, pass, "candidato"
						);
			} else if (rdbtnTecnico.isSelected()) {
				BolsaEmpleo.getInstancia().registrarPersonaTecnico(
						cedula, nombre, telefono, correo, tiempo, licencia, sexo, ciudad,
						txtInstituto.getText().trim(), txtDiplomaTecnico.getText().trim(), 
						txtEspecialidad.getText().trim(), user, pass, "candidato"
						);
			} else {
				BolsaEmpleo.getInstancia().registrarPersonaTrabajador(
						cedula, nombre, telefono, correo, tiempo, licencia, sexo, ciudad,
						txtOficio.getText().trim(), user, pass, "candidato"
						);
			}

			Usuario usuarioCreado = BolsaEmpleo.getInstancia().getUsuarioPorUserName(user);
			if (usuarioCreado != null) {
				BolsaEmpleo.getInstancia().setCookieUsuario(usuarioCreado);
			}
		}else {
			short id = (short)System.currentTimeMillis();
			Empresa aux = obtenerEmpresaSel();
			String idUsuario = aux.getId() + "-" + id;

			Usuario nuevo = new Usuario(idUsuario, txtCorreoEmpresa.getText(),txtUsuarioLoginEmpresa.getText(), new String(pfContrasenaEmpresa.getPassword()), "Admin");
			BolsaEmpleo.getInstancia().registrarUsuarioEmpres(nuevo);
			aux.getReprecentantes().add(nuevo);
			BolsaEmpleo.getInstancia().setCookieUsuario(nuevo);
		}
		GestorFicheros.guardarDatosFicheros();
	}

	private void limpiarCampos ()
	{
		txtCedula.setText("");
		txtNombre.setText("");
		txtTelefono.setText("");
		txtCorreo.setText("");
		cmbCiudades.setSelectedIndex(-1);
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
		txtCorreoEmpresa.setText("");
		pfContrasenaEmpresa.setText("");
		txtCorreo.setText("");
		pfClave.setText("");
		cmbEmpresas.setSelectedIndex(-1);
	}

	private void llenarEmpresas(){

		cmbEmpresas.removeAllItems();
		for (Empresa emp : BolsaEmpleo.getInstancia().getLasEmpresas()) {
			cmbEmpresas.addItem(emp.getNombre());
		}
	}
	private Empresa obtenerEmpresaSel() {
		Empresa aux = null;
		aux = BolsaEmpleo.getInstancia().getEmpresaNombre(cmbEmpresas.getSelectedItem().toString().trim());
		System.out.print(aux.getClaveDeSeguridad());
		return aux;
	}
	private void llenarCiudades() {
	    cmbCiudades.removeAllItems();
	    for (String ciudad : BolsaEmpleo.getInstancia().getCiudades()) {
	        cmbCiudades.addItem(ciudad);
	    }
	}
}