package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.sql.rowset.FilteredRowSet;
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
import logico.Oferta;
import logico.Persona;
import logico.Solicitud;
import logico.Usuario;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class CatalogoOfertas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableOfertas;
	private DefaultTableModel modelTabla;
	private ArrayList<Oferta> listaActual = new ArrayList<>();
	
	// Colores Paleta 
	private final Color bgPrincipal = new Color(243, 244, 246); // Gris muy claro
	private final Color bgInputs = Color.WHITE;                 // Blanco puro
	private final Color colorTexto = new Color(31, 41, 55);     // Gris carbón oscuro
	private final Color colorVerde = new Color(16, 185, 129);   // Verde 
	private final Color colorRojo = new Color(239, 68, 68);     // Rojo
	private final Color colorAzul = new Color(37, 99, 235);     // Azul estándar
	private JTextField txtIndice;
	private int i;
	private Persona auxPersona = BolsaEmpleo.getInstancia().buscarPersona(BolsaEmpleo.getInstancia().getCookieUsuario().getId());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GestorFicheros.cargarDatosDesdeFicheros();
			CatalogoOfertas dialog = new CatalogoOfertas(false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CatalogoOfertas(boolean filtro) {
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
		cargarOfertas(filtro);
		
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
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setForeground(Color.WHITE);
		btnAnterior.setBackground(colorAzul);
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (i>0)
					i--;
				cargarOfertas(filtro);
				txtIndice.setText("Solicitud : " + (i+1));
				if (i==0)
					btnAnterior.setVisible(false);
				
			}
		});
		btnAnterior.setVisible(false);
		buttonPane.add(btnAnterior);
		
		txtIndice = new JTextField();
		txtIndice.setEditable(false);
		txtIndice.setText("Solicitud : " + (i+1));
		buttonPane.add(txtIndice);
		txtIndice.setColumns(10);
		txtIndice.setVisible(filtro);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setForeground(Color.white);
		btnSiguiente.setBackground(colorAzul);
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i++;
				if (i>=auxPersona.getSolicitudes().size())
					i = 0;
				if (i>0)
				{btnAnterior.setVisible(true);}
				cargarOfertas(filtro);
				txtIndice.setText("Solicitud : " + (i+1));
			}
		});
		btnSiguiente.setVisible(filtro);
		buttonPane.add(btnSiguiente);
		
		JButton btnDescripcion = new JButton("Descripcion");
		btnDescripcion.setBackground(colorVerde);
		btnDescripcion.setForeground(Color.WHITE);
		btnDescripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Oferta seleccionada = getOfertaSeleccionada();
				if (seleccionada == null) {
					JOptionPane.showMessageDialog(null, "Seleccione una oferta de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				DescripcionOferta dialog = new DescripcionOferta(seleccionada);
				dialog.setVisible(true);
			}
		});
		buttonPane.add(btnDescripcion);
		
		btnCerrar.setBackground(colorRojo);
		btnCerrar.setForeground(Color.WHITE);
		buttonPane.add(btnCerrar);
	}
	
	private void cargarOfertas(boolean filtro) {
		modelTabla.setRowCount(0);
		

		ArrayList<Oferta> lista = BolsaEmpleo.getInstancia().getLasOfertas();
		if (filtro)
			lista = BolsaEmpleo.getInstancia().misRecomentaciones(auxPersona.getSolicitudes().get(i));
		listaActual = lista;

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
	private Oferta getOfertaSeleccionada() {
		int fila = tableOfertas.getSelectedRow();
		if (fila < 0 || listaActual == null || fila >= listaActual.size()) {
			return null;
		}
		return listaActual.get(fila);
	}
}
