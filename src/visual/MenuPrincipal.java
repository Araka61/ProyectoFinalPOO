package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.GestorFicheros;
import logico.Usuario;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		
				try {
					cargarDatosDesdeFicheros();

					MenuPrincipal frame = new MenuPrincipal();

					Usuario cookie = BolsaEmpleo.getInstancia().getCookieUsuario();
					if (cookie != null) {
						frame.setVisible(true);
					} else {
						Login login = new Login();
						login.setModal(true);
						login.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	

	private static void cargarDatosDesdeFicheros() {
		try {
			GestorFicheros.cargarDatosID();
			GestorFicheros.cargarDatosUsuarios();
			GestorFicheros.cargarDatosPersonas();
			GestorFicheros.cargarDatosEmpresa();
			GestorFicheros.cargarDatosOfertas();
			GestorFicheros.cargarDatosSolicitudes();
			GestorFicheros.cargarCookies();
		} catch (Exception e) {
			System.out.println("[INFO] No hay ficheros previos, el sistema arranca vacío.");
		}
	}

	public MenuPrincipal() {
		setVisible(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}