package visual;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import logico.Usuario;

public class PanelUsuario extends JPanel {

	private JLabel lblBienvenida;
	private JLabel lblSubtitulo;
	
	/**
	 * Create the panel.
	 */
	public PanelUsuario() {
		setLayout(null);
		setBackground(new Color(243, 244, 246));

		lblBienvenida = new JLabel("Bienvenido, Candidato");
		lblBienvenida.setForeground(new Color(31, 41, 55));
		lblBienvenida.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblBienvenida.setBounds(30, 20, 500, 35);
		add(lblBienvenida);

		lblSubtitulo = new JLabel("Utiliza la barra de menú superior para gestionar tu solicitud y explorar vacantes.");
		lblSubtitulo.setForeground(new Color(107, 114, 128));
		lblSubtitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSubtitulo.setBounds(30, 60, 785, 20);
		add(lblSubtitulo);
	}

	public void actualizarDatos(Usuario usuario) {
		if (usuario != null) {
			lblBienvenida.setText("Bienvenido, " + usuario.getUserName());
		}
	}
}