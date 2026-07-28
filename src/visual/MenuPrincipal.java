package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.GestorFicheros;
import logico.Usuario;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	private Dimension dim = null;
	
	private CardLayout cardLayoutCuenta;
	private JPanel panelCuenta;
	private JPanel panelUsuario;
	private JPanel panelEmpresa;

	private static final String CARD_USUARIO = "USUARIO";
	private static final String CARD_EMPRESA = "EMPRESA";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestorFicheros.cargarDatosDesdeFicheros();
 
					MenuPrincipal frame = new MenuPrincipal();
 
					Usuario cookie = BolsaEmpleo.getInstancia().getCookieUsuario();
					if (cookie != null && BolsaEmpleo.getInstancia().getUsuarioPorUserName(BolsaEmpleo.getInstancia().getCookieUsuario().getUserName()) != null) {
						frame.cargarInterfazSegunUsuario(cookie);
						frame.setVisible(true);
					} else {
						//if (!(BolsaEmpleo.getInstancia().getUsuarioPorUserName(BolsaEmpleo.getInstancia().getCookieUsuario().getUserName()) != null))
							//System.out.print("El problema es este");
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
	// Se corto el codigo cuando lo pase de mi laptop a mi PC principal Sorry
	// No quise subir el commit desde la laptop para que no pase lo que paso con 
	// Manuelle y los 3 commits

	

	public MenuPrincipal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GestorFicheros.guardarDatosFicheros();
			}
		});
		setAlwaysOnTop(true);
		setVisible(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height - 38);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		cardLayoutCuenta = new CardLayout();
		panelCuenta = new JPanel(cardLayoutCuenta);
		contentPane.add(panelCuenta, BorderLayout.CENTER);
		panelUsuario = crearPanelUsuario();
		panelEmpresa = crearPanelEmpresa();
		
		panelCuenta.add(panelUsuario, CARD_USUARIO);
		panelCuenta.add(panelEmpresa, CARD_EMPRESA);
	}
	
	public void cargarInterfazSegunUsuario(Usuario usuario) {
		if(usuario == null) return;
		
		String rol = usuario.getRol();
		
		if(rol.equalsIgnoreCase("Empresa")) {
			actualizarPanelEmpresa(usuario);
			cardLayoutCuenta.show(panelCuenta, CARD_EMPRESA);
		}else {
			actualizarPanelUsuario(usuario);
			cardLayoutCuenta.show(panelCuenta, CARD_USUARIO);
		}
	}
	
	private JPanel crearPanelUsuario() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel lbl = new JLabel("Bienvenido Candidato", SwingConstants.CENTER);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 39));
		panel.add(lbl, BorderLayout.CENTER);
		// aqui van los menus etc
		return panel;
	}
	
	private JPanel crearPanelEmpresa() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel lbl = new JLabel("Bienvenido Empresa", SwingConstants.CENTER);
		panel.add(lbl, BorderLayout.NORTH);
		// aqui van los menus etc
		return panel;
	}
	
	private void actualizarPanelUsuario(Usuario usuario) {
		// Aqui se refrescaran datos dinámicos del panel de usuario (nombre, solicitudes, etc.)
	}

	private void actualizarPanelEmpresa(Usuario usuario) {
		// Aqui se refrescaran datos dinámicos del panel de empresa (vacantes publicadas, etc.)
	}
}