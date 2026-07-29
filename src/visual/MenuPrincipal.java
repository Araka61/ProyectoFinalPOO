package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.GestorFicheros;
import logico.Usuario;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	private Dimension dim = null;

	private CardLayout cardLayoutCuenta;
	private JPanel panelCuenta;

	private PanelUsuario panelUsuario;
	private PanelEmpresa panelEmpresa;

	private JMenuBar menuBar;

	private static final String CARD_USUARIO = "USUARIO";
	private static final String CARD_EMPRESA = "EMPRESA";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestorFicheros.cargarDatosDesdeFicheros();

					MenuPrincipal frame = new MenuPrincipal();

					Usuario cookie = BolsaEmpleo.getInstancia().getCookieUsuario();
					if (cookie != null && BolsaEmpleo.getInstancia().getUsuarioPorUserName(cookie.getUserName()) != null) {
						frame.cargarInterfazSegunUsuario(cookie);
						frame.setVisible(true);
					} else {
						Login login = new Login(frame);
						login.setModal(true);
						login.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuPrincipal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GestorFicheros.guardarDatosFicheros();
			}
		});

		setTitle("Sistema Bolsa de Empleo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height - 38);
		setLocationRelativeTo(null);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuBase = new JMenu("Menú Principal");
		menuBase.add(new JMenuItem("Cerrar Sesión"));
		menuBase.add(new JMenuItem("Respaldar En Servidor"));
		menuBar.add(menuBase);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		cardLayoutCuenta = new CardLayout();
		panelCuenta = new JPanel(cardLayoutCuenta);
		contentPane.add(panelCuenta, BorderLayout.CENTER);

		panelUsuario = new PanelUsuario();
		panelEmpresa = new PanelEmpresa();

		panelCuenta.add(panelUsuario, CARD_USUARIO);
		panelCuenta.add(panelEmpresa, CARD_EMPRESA);
	}

	public void cargarInterfazSegunUsuario(Usuario usuario) {
		if (usuario == null) return;

		String rol = usuario.getRol();

		if (rol.equalsIgnoreCase("Empresa")) {
			configurarMenuEmpresa();
			panelEmpresa.actualizarDatos(usuario);
			cardLayoutCuenta.show(panelCuenta, CARD_EMPRESA);
		} else {
			configurarMenuCandidato();
			panelUsuario.actualizarDatos(usuario);
			cardLayoutCuenta.show(panelCuenta, CARD_USUARIO);
		}

		revalidate();
		repaint();
	}

	private void configurarMenuCandidato() {
		menuBar.removeAll();

		JMenu menuPerfil = new JMenu("Mi Solicitud");
		menuPerfil.add(new JMenuItem("Editar Perfil / Solicitud"));

		JMenu menuEmpleos = new JMenu("Buscar Empleos");
		menuEmpleos.add(new JMenuItem("Catálogo de Ofertas"));
		menuEmpleos.add(new JMenuItem("Ofertas Recomendadas"));

		JMenu menuCuenta = new JMenu("Cuenta");
		JMenuItem itemCerrarSesion = new JMenuItem("Cerrar Sesión");
		JMenuItem itemRespaldar = new JMenuItem("Respaldar Servidor");
		itemCerrarSesion.addActionListener(e -> cerrarSesion());
		menuCuenta.add(itemCerrarSesion);

		menuBar.add(menuPerfil);
		menuBar.add(menuEmpleos);
		menuBar.add(menuCuenta);
	}

	private void configurarMenuEmpresa() {
		menuBar.removeAll();

		JMenu menuOfertas = new JMenu("Gestión de Ofertas");
		menuOfertas.add(new JMenuItem("Publicar Nueva Vacante"));
		menuOfertas.add(new JMenuItem("Mis Vacantes Publicadas"));

		JMenu menuReclutamiento = new JMenu("Reclutamiento");
		menuReclutamiento.add(new JMenuItem("Candidatos Ideales"));
		menuReclutamiento.add(new JMenuItem("Directorio de Personas"));

		JMenu menuCuenta = new JMenu("Cuenta");
		JMenuItem itemCerrarSesion = new JMenuItem("Cerrar Sesión");
		JMenuItem itemRespaldar = new JMenuItem("Respaldar Servidor");
		itemCerrarSesion.addActionListener(e -> cerrarSesion());
		menuCuenta.add(itemCerrarSesion);

		menuBar.add(menuOfertas);
		menuBar.add(menuReclutamiento);
		menuBar.add(menuCuenta);
	}

	private void cerrarSesion() {
		GestorFicheros.guardarDatosFicheros();
		BolsaEmpleo.getInstancia().setCookieUsuario(null);
		GestorFicheros.guardarCookies();

		setVisible(false);

		Login login = new Login(this);
		login.setModal(true);
		login.setVisible(true);
	}
}