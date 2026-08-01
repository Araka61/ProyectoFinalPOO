package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.GestorFicheros;
import logico.Oferta;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class DescripcionOferta extends JDialog {

	private final JPanel contentPanel = new JPanel();

	// Colores Paleta
	private final Color bgPrincipal = new Color(243, 244, 246); // Gris muy claro
	private final Color bgInputs = Color.WHITE;                 // Blanco puro
	private final Color colorTexto = new Color(31, 41, 55);     // Gris carbón oscuro
	private final Color colorVerde = new Color(16, 185, 129);   // Verde
	private final Color colorRojo = new Color(239, 68, 68);     // Rojo
	private final Color colorAzul = new Color(37, 99, 235);     // Azul estándar

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GestorFicheros.cargarDatosDesdeFicheros();
			DescripcionOferta dialog = new DescripcionOferta(BolsaEmpleo.getInstancia().getLasOfertas().get(0));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DescripcionOferta(Oferta oferta) {
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GestorFicheros.guardarDatosFicheros();
			}
		});

		setTitle("Descripción de la Oferta");
		setBounds(100, 100, 660, 300);
		setResizable(false);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(bgPrincipal);
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		{
			JLabel lblTitulo = new JLabel(oferta != null ? oferta.getTipoTrabajo() : "Oferta");
			lblTitulo.setForeground(colorTexto);
			lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 14f));
			lblTitulo.setBounds(10, 5, 624, 20);
			contentPanel.add(lblTitulo);
		}
		{
			JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
			lblDescripcion.setForeground(colorTexto);
			lblDescripcion.setBounds(10, 32, 200, 16);
			contentPanel.add(lblDescripcion);
		}
		{
			JTextPane txtDescripcion = new JTextPane();
			txtDescripcion.setEditable(false);
			txtDescripcion.setBackground(bgInputs);
			txtDescripcion.setForeground(colorTexto);
			txtDescripcion.setText(oferta != null && oferta.getDescripcionTrabajo() != null
					? oferta.getDescripcionTrabajo()
					: "Sin descripción disponible.");

			JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
			scrollDescripcion.setBounds(10, 52, 624, 176);
			contentPanel.add(scrollDescripcion);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(bgPrincipal);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.setForeground(Color.WHITE);
				btnSalir.setBackground(colorRojo);
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GestorFicheros.guardarDatosFicheros();
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
				getRootPane().setDefaultButton(btnSalir);
			}
		}
	}
}