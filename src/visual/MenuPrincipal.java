package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.ClienteBackup;
import logico.GestorFicheros;
import logico.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

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
		menuBar.setForeground(Color.WHITE);
		menuBar.setBackground(new Color(128, 128, 128));
		setJMenuBar(menuBar);

		JMenu menuBase = new JMenu("Menú Principal");
		JMenuItem menuItem = new JMenuItem("Cerrar Sesión");
		menuItem.setBackground(new Color(192, 192, 192));
		menuBase.add(menuItem);
		JMenuItem menuItem_1 = new JMenuItem("Respaldar En Servidor");
		menuItem_1.setBackground(Color.LIGHT_GRAY);
		menuBase.add(menuItem_1);
		menuBar.add(menuBase);

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		cardLayoutCuenta = new CardLayout();
		panelCuenta = new JPanel(cardLayoutCuenta);
		contentPane.add(panelCuenta, BorderLayout.CENTER);

		panelUsuario = new PanelUsuario();
		panelUsuario.setBackground(Color.DARK_GRAY);
		panelEmpresa = new PanelEmpresa();
		panelEmpresa.setBackground(Color.DARK_GRAY);

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
		JMenuItem itemEditarDatos = new JMenuItem("Editar Datos Personales");
		//itemEditarDatos.addActionListener();
		menuPerfil.add(itemEditarDatos);
		JMenuItem itemCrearSolicitud = new JMenuItem("Crear Solicitud");
		itemCrearSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarSolicitud genSoli = new RegistrarSolicitud(null);
				genSoli.setModal(true);
				genSoli.setVisible(true);
			}
		});
		menuPerfil.add(itemCrearSolicitud);
		JMenuItem itemEditarSolicitud = new JMenuItem("Editar Solicitud");
		itemEditarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarSolicitud edit = new ListarSolicitud();
				edit.setModal(true);
				edit.setVisible(true);
			}
		});
		menuPerfil.add(itemEditarSolicitud);
		
		JMenu menuEmpleos = new JMenu("Buscar Empleos");
		JMenuItem itemCatalogoOfertas = new JMenuItem("Catalogo de Ofertas");
		itemCatalogoOfertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CatalogoOfertas catalogo = new CatalogoOfertas();
				catalogo.setModal(true);
				catalogo.setVisible(true);
			}
		});
		menuEmpleos.add(itemCatalogoOfertas);
		JMenuItem itemOfertasRecomendadas = new JMenuItem("Ofertas Recomendadas");
		itemCatalogoOfertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OfertasRecomendadas recomendacion = new OfertasRecomendadas();
				recomendacion.setModal(true);
				recomendacion.setVisible(true);
			}
		});
		menuEmpleos.add(itemOfertasRecomendadas);

		JMenu menuCuenta = new JMenu("Cuenta");
		JMenuItem itemCerrarSesion = new JMenuItem("Cerrar Sesión");
		itemCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		menuCuenta.add(itemCerrarSesion);
		JMenuItem itemRespaldar = new JMenuItem("Respaldar Servidor");

		menuBar.add(menuPerfil);
		menuBar.add(menuEmpleos);
		menuBar.add(menuCuenta);
	}

	private void configurarMenuEmpresa() {
		menuBar.removeAll();

		JMenu menuOfertas = new JMenu("Gestión de Ofertas");
		JMenuItem itemNuevaVacante = new JMenuItem("Publicar Nueva Vacante");
		itemNuevaVacante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarOferta oferta = new RegistrarOferta();
				oferta.setModal(true);
				oferta.setVisible(true);
			}
		});
		menuOfertas.add(itemNuevaVacante);
		JMenuItem itemListarVacantes = new JMenuItem("Vacantes Publicadas");
		itemListarVacantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarOfertas list = new ListarOfertas();
				list.setModal(true);
				list.setVisible(true);
			}
		});
		menuOfertas.add(itemListarVacantes);

		JMenu menuReclutamiento = new JMenu("Reclutamiento");
		JMenuItem itemCandidatos = new JMenuItem("Candidatos Ideales");
		itemCandidatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MejoresCoincidencias best = new MejoresCoincidencias();
				best.setModal(true);
				best.setVisible(true);
			}
		});
		menuReclutamiento.add(itemCandidatos);
		
		JMenuItem itemDirectorio = new JMenuItem("Directorio de Personas");
		itemDirectorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarPersonas list = new ListarPersonas();
				list.setModal(true);
				list.setVisible(true);
			}
		});
		menuReclutamiento.add(itemDirectorio);

		JMenu menuCuenta = new JMenu("Cuenta");
		JMenuItem itemCerrarSesion = new JMenuItem("Cerrar Sesión");
		itemCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		menuCuenta.add(itemCerrarSesion);
		JMenuItem itemRespaldar = new JMenuItem("Respaldar Servidor");

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