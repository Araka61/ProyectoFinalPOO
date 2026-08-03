package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.BolsaEmpleo;
import logico.GestorFicheros;
import logico.Oferta;
import logico.Persona;
import logico.Solicitud;
import logico.Usuario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JSplitPane;

public class OfertasRecomendadas extends JDialog {

	 private final JPanel contentPanel = new JPanel();
	 private JTable tableOfertas;
	 private DefaultTableModel modelTabla;
	 private Usuario usuarioActual = BolsaEmpleo.getInstancia().getCookieUsuario();
	 private JComboBox<String> cbxFiltrar;
	    
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
			OfertasRecomendadas dialog = new OfertasRecomendadas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OfertasRecomendadas() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GestorFicheros.guardarDatosFicheros();
			}
		});
		setTitle("Ofertas Recomendadas por solicitud");
		setBounds(100, 100, 1500, 540);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(bgPrincipal);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panelFiltrar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panelFiltrar.setBackground(bgPrincipal);
		
		panelFiltrar.setPreferredSize(new Dimension(220, 0));
		
		JLabel lblNewLabel = new JLabel("Solicitud");
		lblNewLabel.setForeground(colorTexto);
		panelFiltrar.add(lblNewLabel);
		
		cbxFiltrar = new JComboBox<String>();
		cbxFiltrar.setPreferredSize(new Dimension(140, 25));
		
		cbxFiltrar.addItem("Escoga una solicitud");

		if (usuarioActual != null) {
			Persona p = BolsaEmpleo.getInstancia().buscarPersona(usuarioActual.getId());
			if (p != null && p.getSolicitudes() != null) {
				for (Solicitud s : p.getSolicitudes()) {
					String desc = s.getId() + " - ";
					if(s.getTitulo() != null && !s.getTitulo().equalsIgnoreCase("n/a")) {
						desc += s.getTitulo();
					} else if(s.getTecnico() != null && !s.getTecnico().equalsIgnoreCase("n/a")) {
						desc += s.getTecnico();
					} else if(s.getHabilidad() != null && !s.getHabilidad().equalsIgnoreCase("n/a")) {
						desc += s.getHabilidad();
					}
					cbxFiltrar.addItem(desc);
				}
			}
		}

		cbxFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarOfertasRecomendadas();
			}
		});

		panelFiltrar.add(cbxFiltrar);
		contentPanel.add(panelFiltrar, BorderLayout.WEST);

	    JPanel panelLista = new JPanel(new BorderLayout());
	    
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
	    
	    JScrollPane scrollPane = new JScrollPane(tableOfertas);
	    scrollPane.getViewport().setBackground(bgInputs);
	    
	    panelLista.add(scrollPane, BorderLayout.CENTER); 
	    contentPanel.add(panelLista, BorderLayout.CENTER);

	    JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
	    buttonPane.setBackground(bgPrincipal);
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);

	    JButton cancelButton = new JButton("Atrás");
	    cancelButton.setBackground(colorRojo);
	    cancelButton.setForeground(Color.WHITE);
	    cancelButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            GestorFicheros.cargarDatosDesdeFicheros();
	            GestorFicheros.guardarDatosFicheros();
	            dispose();
	        }
	    });
	    buttonPane.add(cancelButton);

	    JButton btnCerrar = new JButton("Cerrar");
	    btnCerrar.setBackground(colorRojo);
	    btnCerrar.setForeground(Color.WHITE);
	    btnCerrar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            GestorFicheros.guardarDatosFicheros();
	            dispose();
	        }
	    });
	    buttonPane.add(btnCerrar);
	}

	protected void cargarOfertasRecomendadas() {
		if (cbxFiltrar.getSelectedIndex() <= 0) {
			modelTabla.setRowCount(0);
			return;
		}
		
		String itemSel = (String) cbxFiltrar.getSelectedItem();
		if (itemSel != null && itemSel.contains(" - ")) {
			String idSolicitud = itemSel.split(" - ")[0].trim();
			Solicitud s = BolsaEmpleo.getInstancia().buscarSolicitud(idSolicitud);
			if (s != null) {
				cargarOfertas(s);
			} else {
				modelTabla.setRowCount(0);
			}
		}
	}

	private void cargarOfertas(Solicitud s) {
		modelTabla.setRowCount(0);

		if (s == null) {
			return;
		}
		
		ArrayList<Oferta> lista = BolsaEmpleo.getInstancia().mejoresOfertas(s);

		if (lista != null) {
			for (Oferta o : lista) {
				String tituloOHabilidad = "n/a";
				if (o.getTitulo() != null && !o.getTitulo().equals("n/a")) {
					tituloOHabilidad = s.getTitulo();
				} else if (o.getTecnico() != null && !o.getTecnico().equals("n/a")) {
					tituloOHabilidad = s.getTecnico();
				} else if (o.getHabilidad() != null && !o.getHabilidad().equals("n/a")) {
					tituloOHabilidad = s.getHabilidad();
				}

				String experiencia = String.format("%d", o.getExperienciaLaboral());
				String salario = "$" + String.format("%.0f", o.getSalario());
				String licencia = o.isLicenciaDeConducir() ? "Requerida" : "No Requerida";
				String movilidad = o.isDispuestoAMudarse() ? "Requerida" : "No Requerida";
				String puestos = String.format("%d", o.getCantPuesto());
				String estado = o.isActivo() ? "Activa" : "Inactiva";

				Object[] fila = {
					o.getTipoTrabajo(),
					tituloOHabilidad,
					o.getTiempoTrabajo(),
					experiencia,
					o.getProvincia(),
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
