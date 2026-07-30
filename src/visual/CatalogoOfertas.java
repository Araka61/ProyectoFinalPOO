package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.BolsaEmpleo;
import logico.GestorFicheros;
import logico.Oferta;
import logico.Solicitud;
import logico.Usuario;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CatalogoOfertas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableOfertas;
	private DefaultTableModel modelTabla;
	
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
			CatalogoOfertas dialog = new CatalogoOfertas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CatalogoOfertas() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GestorFicheros.guardarDatosFicheros();
			}
		});
		setTitle("Catalogo de ofertas en el sistema");
		setBounds(100, 100, 1024, 576);
		setLocationRelativeTo(null);
		setModal(true);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(bgPrincipal);
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		String[] columnas = {"Tipo Trabajo", "Título / Habilidad", "Disponibilidad", "Experiencia", "Provincia", "Salario", "Licencia", "Movilidad", "Puestos", "Estado"};
		modelTabla = new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableOfertas = new JTable(modelTabla);
		tableOfertas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableOfertas.setBackground(bgInputs);
		tableOfertas.setForeground(colorTexto);
		tableOfertas.getTableHeader().setReorderingAllowed(false);
		tableOfertas.getTableHeader().setBackground(bgPrincipal);
		tableOfertas.getTableHeader().setForeground(colorTexto);
		JScrollPane scrollPane = new JScrollPane(tableOfertas);
		scrollPane.getViewport().setBackground(bgInputs); 
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		cargarOfertas();
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(bgPrincipal);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestorFicheros.guardarDatosFicheros();
				dispose();
			}
		});
		btnCerrar.setBackground(colorRojo);
		btnCerrar.setForeground(Color.WHITE);
		buttonPane.add(btnCerrar);
	}
	
	private void cargarOfertas() {
		modelTabla.setRowCount(0);

		ArrayList<Oferta> lista = BolsaEmpleo.getInstancia().getLasOfertas();

		if (lista != null) {
			for (Oferta s : lista) {
				String tituloOHabilidad = "n/a";
				if (s.getTitulo() != null && !s.getTitulo().equals("n/a")) {
					tituloOHabilidad = s.getTitulo();
				} else if (s.getTecnico() != null && !s.getTecnico().equals("n/a")) {
					tituloOHabilidad = s.getTecnico();
				} else if (s.getHabilidad() != null && !s.getHabilidad().equals("n/a")) {
					tituloOHabilidad = s.getHabilidad();
				}

				String experiencia = String.format("%d", s.getExperienciaLaboral());
				String salario = "$" + String.format("%.0f", s.getSalario());
				String licencia = s.isLicenciaDeConducir() ? "Requerida" : "No Requerida";
				String movilidad = s.isDispuestoAMudarse() ? "Requerida" : "No Requerida";
				String puestos = String.format("%d", s.getCantPuesto());
				String estado = s.isActivo() ? "Activa" : "Inactiva";

				Object[] fila = {
					s.getTipoTrabajo(),
					tituloOHabilidad,
					s.getTiempoTrabajo(),
					experiencia,
					s.getProvincia(),
					salario,
					licencia,
					movilidad,
					puestos,
					estado
				};
				modelTabla.addRow(fila);
			}
		}
	}
}
