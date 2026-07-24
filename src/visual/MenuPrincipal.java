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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestorFicheros.cargarDatosDesdeFicheros();
 
					MenuPrincipal frame = new MenuPrincipal();
 
					Usuario cookie = BolsaEmpleo.getInstancia().getCookieUsuario();
					if (cookie != null) {
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
	// Se corto el codigo cuando lo pase de mi laptop a mi PC principal Sorry
	// No quise subir el commit desde la laptop para que no pase lo que paso con 
	// Manuelle y los 3 commits

	

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