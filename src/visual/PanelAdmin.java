package visual;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import logico.Usuario;

public class PanelAdmin extends JPanel {

	private JLabel lblBienvenida;
	private JLabel lblSubtitulo;
	/**
	 * Create the panel.
	 */
	public PanelAdmin() {
		setLayout(null);
		setBackground(new Color(243, 244, 246));

		lblBienvenida = new JLabel("Panel de Control - Admin");
		lblBienvenida.setForeground(new Color(31, 41, 55));
		lblBienvenida.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblBienvenida.setBounds(30, 20, 500, 35);
		add(lblBienvenida);

		lblSubtitulo = new JLabel("Accede a las funciones del sistema desde el men\u00FA superior.");
		lblSubtitulo.setForeground(new Color(107, 114, 128));
		lblSubtitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSubtitulo.setBounds(30, 60, 786, 20);
		add(lblSubtitulo);
	}	
}