package visual;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import logico.Usuario;

public class PanelEmpresa extends JPanel {

	private JLabel lblBienvenida;
	private JLabel lblSubtitulo;
	
	/**
	 * Create the panel.
	 */
	public PanelEmpresa() {
		setLayout(null);
		setBackground(Color.GRAY);

		lblBienvenida = new JLabel("Panel de Control - Empresa");
		lblBienvenida.setForeground(Color.WHITE);
		lblBienvenida.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblBienvenida.setBounds(30, 20, 500, 35);
		add(lblBienvenida);

		lblSubtitulo = new JLabel("Publica vacantes y revisa candidatos con coincidencia desde el menú superior.");
		lblSubtitulo.setForeground(Color.LIGHT_GRAY);
		lblSubtitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSubtitulo.setBounds(30, 60, 786, 20);
		add(lblSubtitulo);
	}

	public void actualizarDatos(Usuario usuario) {
		if (usuario != null) {
			lblBienvenida.setText("Empresa: " + usuario.getUserName());
		}
	}
}
