package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.BolsaEmpleo;
import logico.GestorFicheros;
import logico.Solicitud;
import logico.Usuario;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ListarSolicitud extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableSolicitudes;
	private DefaultTableModel modelTabla;
	private Usuario usuarioActual;

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
			ListarSolicitud dialog = new ListarSolicitud();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarSolicitud() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GestorFicheros.guardarDatosFicheros();
			}
		});
		setTitle("Gestión de Solicitudes de Empleo");
		setBounds(100, 100, 750, 420);
		setLocationRelativeTo(null);
		setModal(true);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(bgPrincipal);
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		usuarioActual = BolsaEmpleo.getInstancia().getCookieUsuario();

		String[] columnas = {"ID Solicitud", "Tipo Trabajo", "Título / Habilidad", "Provincia", "Rango Salario", "Estado"};
		modelTabla = new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableSolicitudes = new JTable(modelTabla);
		tableSolicitudes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSolicitudes.setBackground(bgInputs);
		tableSolicitudes.setForeground(colorTexto);
		tableSolicitudes.getTableHeader().setReorderingAllowed(false);
		tableSolicitudes.getTableHeader().setBackground(bgPrincipal);
		tableSolicitudes.getTableHeader().setForeground(colorTexto);
		JScrollPane scrollPane = new JScrollPane(tableSolicitudes);
		scrollPane.getViewport().setBackground(bgInputs); 
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		cargarSolicitudes();

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(bgPrincipal);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnNueva = new JButton("Nueva Solicitud");
		btnNueva.setBackground(colorVerde);
		btnNueva.setForeground(Color.WHITE);
		btnNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usuarioActual == null) {
					JOptionPane.showMessageDialog(ListarSolicitud.this, "Debe iniciar sesión para crear una solicitud.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				RegistrarSolicitud dialog = new RegistrarSolicitud(ListarSolicitud.this, null);
				dialog.setVisible(true);
				cargarSolicitudes(); 
			}
		});
		buttonPane.add(btnNueva);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(colorAzul);
		btnEditar.setForeground(Color.WHITE);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableSolicitudes.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(ListarSolicitud.this, "Por favor, seleccione una solicitud de la lista.", "Selección Requerida", JOptionPane.WARNING_MESSAGE);
					return;
				}

				String idSolicitud = (String) modelTabla.getValueAt(selectedRow, 0);
				
				RegistrarSolicitud dialog = new RegistrarSolicitud(ListarSolicitud.this, idSolicitud);
				dialog.setVisible(true);
				cargarSolicitudes();
			}
		});
		buttonPane.add(btnEditar);
		
		JButton btnDesactivar = new JButton("Desactivar");
		btnDesactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tableSolicitudes.getSelectedRow();
				
				if(filaSeleccionada == -1) {
					JOptionPane.showMessageDialog(ListarSolicitud.this, "Seleccione una solicitud", 
							"Seleccion Requerida", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				String idSolicitud = (String) modelTabla.getValueAt(filaSeleccionada, 0);
				
				int respuesta = JOptionPane.showConfirmDialog(ListarSolicitud.this, "¿Seguro que desea desactivar esta solicitud?", 
						"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(respuesta == JOptionPane.YES_OPTION) {
					Solicitud solicitud = BolsaEmpleo.getInstancia().buscarSolicitud(idSolicitud);
					if(solicitud != null) {
						solicitud.setActivo(false);
						
						GestorFicheros.guardarDatosFicheros();
						
						JOptionPane.showMessageDialog(ListarSolicitud.this,
								"La Solicitud ha sido desactivada correctamente.", "Exito",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnDesactivar.setBackground(colorRojo);
		btnDesactivar.setForeground(Color.WHITE);
		buttonPane.add(btnDesactivar);

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

	private void cargarSolicitudes() {
		modelTabla.setRowCount(0);

		ArrayList<Solicitud> lista = BolsaEmpleo.getInstancia().getLasSolicitudes();

		if (lista != null) {
			for (Solicitud s : lista) {
				if (usuarioActual != null && "candidato".equalsIgnoreCase(usuarioActual.getRol())) {
					if (!s.getIdUsuario().equals(usuarioActual.getId())) {
						continue;
					}
				}

				String tituloOHabilidad = "n/a";
				if (s.getTitulo() != null && !s.getTitulo().equals("n/a")) {
					tituloOHabilidad = s.getTitulo();
				} else if (s.getTecnico() != null && !s.getTecnico().equals("n/a")) {
					tituloOHabilidad = s.getTecnico();
				} else if (s.getHabilidad() != null && !s.getHabilidad().equals("n/a")) {
					tituloOHabilidad = s.getHabilidad();
				}

				String rangoSalario = "$" + String.format("%.0f", s.getRangoMinSalario()) + " - $" + String.format("%.0f", s.getRangoMaxSalario());
				String estado = s.isActivo() ? "Activa" : "Inactiva";

				Object[] fila = {
					s.getId(),
					s.getTipoTrabajo(),
					tituloOHabilidad,
					s.getProvincia(),
					rangoSalario,
					estado
				};
				modelTabla.addRow(fila);
			}
		}
	}
}