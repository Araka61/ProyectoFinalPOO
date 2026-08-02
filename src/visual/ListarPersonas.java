package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.BolsaEmpleo;
import logico.GestorFicheros;
import logico.Grado;
import logico.Persona;
import logico.Tecnico;
import logico.Trabajador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

public class ListarPersonas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel model;
	private Object[] row;
	private JComboBox cbxFiltrar;
	
	// Colores Paleta 
		private final Color bgPrincipal = new Color(243, 244, 246); // Gris muy claro
		private final Color bgInputs = Color.WHITE;                 // Blanco puro
		private final Color colorTexto = new Color(31, 41, 55);     // Gris carbón oscuro
		private final Color colorVerde = new Color(16, 185, 129);   // Verde 
		private final Color colorRojo = new Color(239, 68, 68);     // Rojo
		private final Color colorAzul = new Color(37, 99, 235);     // Azul estándar
		private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarPersonas dialog = new ListarPersonas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarPersonas() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GestorFicheros.cargarDatosDesdeFicheros();
				GestorFicheros.guardarDatosFicheros();
			}
		});
		setBounds(100, 100, 1500, 540);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(bgPrincipal);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelFiltrar = new JPanel();
			contentPanel.add(panelFiltrar, BorderLayout.WEST);
			{
				JLabel lblNewLabel = new JLabel("Filtrar:");
				panelFiltrar.add(lblNewLabel);
			}
			{
				cbxFiltrar = new JComboBox();
				cbxFiltrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						filtrarPersonas();
					}
				});
				cbxFiltrar.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Universitarios", "Tecnicos", "Trabajadores", "Empleados", "Desempleados"}));
				panelFiltrar.add(cbxFiltrar);
			}
		}
		{
			JPanel panelLista = new JPanel();
			contentPanel.add(panelLista, BorderLayout.CENTER);
			panelLista.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panelLista.add(scrollPane, BorderLayout.CENTER);
				{
					String[] headers = {"Cedula", "Nombre", "Telefono", "Correo", "Empleado", "Tiempo disponible", "Licencia", "Sexo", "Ciudad", "Universidad", "Carrera", "Titulo universitario", "Instituto", "Diploma de tecnico", "Especialidad", "Oficio"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(headers);
					table = new JTable();
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(bgPrincipal);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Atras");
				cancelButton.setBackground(colorRojo);
				cancelButton.setForeground(bgPrincipal);
				cancelButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						GestorFicheros.cargarDatosDesdeFicheros();
						GestorFicheros.guardarDatosFicheros();
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadPersonas();
	}
	private void loadPersonas() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for(Persona persona : BolsaEmpleo.getInstancia().getLasPersonas()) {
			row[0] = persona.getCedula();
			row[1] = persona.getNombre();
			row[2] = persona.getTelefono();
			row[3] = persona.getCorreo();
			if(persona.isEmpleado()) {
				row[4] = "Si";
			}else {
				row[4] = "No";
			}
			row[5] = persona.getTiempoDisponible();
			if(persona.isTieneLicencia()) {
				row[6] = "Si";
			}else {
				row[6] = "No";
			}
			row[7] = persona.getSexo();
			row[8] = persona.getCiudad();
			if(persona instanceof Grado) {
				row[9] = ((Grado) persona).getUniversidad();
				row[10] = ((Grado) persona).getCarrera();
				row[11] = ((Grado) persona).getTituloUniversitario();
				row[12] = null;
				row[13] = null;
				row[14] = null;
				row[15] = null;
			}else if(persona instanceof Tecnico) {
				row[9] = null;
				row[10] = null;
				row[11] = null;
				row[12] = ((Tecnico) persona).getInstituto();
				row[13] = ((Tecnico) persona).getDiplomaTecnico();
				row[14] = ((Tecnico) persona).getEspecialidad();
				row[15] = null;
			}else if(persona instanceof Trabajador) {
				row[9] = null;
				row[10] = null;
				row[11] = null;
				row[12] = null;
				row[13] = null;
				row[14] = null;
				row[15] = ((Trabajador) persona).getOficio();
			}
			model.addRow(row);
		}
	}
	
	private void loadUniversitarios() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for(Persona persona : BolsaEmpleo.getInstancia().getLasPersonas()) {
			if(persona instanceof Grado) {
				row[0] = persona.getCedula();
				row[1] = persona.getNombre();
				row[2] = persona.getTelefono();
				row[3] = persona.getCorreo();
				if(persona.isEmpleado()) {
					row[4] = "Si";
				}else {
					row[4] = "No";
				}
				row[5] = persona.getTiempoDisponible();
				if(persona.isTieneLicencia()) {
					row[6] = "Si";
				}else {
					row[6] = "No";
				}
				row[7] = persona.getSexo();
				row[8] = persona.getCiudad();
				row[9] = ((Grado) persona).getUniversidad();
				row[10] = ((Grado) persona).getCarrera();
				row[11] = ((Grado) persona).getTituloUniversitario();
				row[12] = null;
				row[13] = null;
				row[14] = null;
				row[15] = null;
				model.addRow(row);
			}
		}
	}
	
	private void loadTecnicos() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for(Persona persona : BolsaEmpleo.getInstancia().getLasPersonas()) {
			if(persona instanceof Tecnico) {
				row[0] = persona.getCedula();
				row[1] = persona.getNombre();
				row[2] = persona.getTelefono();
				row[3] = persona.getCorreo();
				if(persona.isEmpleado()) {
					row[4] = "Si";
				}else {
					row[4] = "No";
				}
				row[5] = persona.getTiempoDisponible();
				if(persona.isTieneLicencia()) {
					row[6] = "Si";
				}else {
					row[6] = "No";
				}
				row[7] = persona.getSexo();
				row[8] = persona.getCiudad();
				row[9] = null;
				row[10] = null;
				row[11] = null;
				row[12] = ((Tecnico) persona).getInstituto();
				row[13] = ((Tecnico) persona).getDiplomaTecnico();
				row[14] = ((Tecnico) persona).getEspecialidad();
				row[15] = null;
				model.addRow(row);
			}
		}
	}
	
	private void loadTrabajadores() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for(Persona persona : BolsaEmpleo.getInstancia().getLasPersonas()) {
			if(persona instanceof Trabajador) {
				row[0] = persona.getCedula();
				row[1] = persona.getNombre();
				row[2] = persona.getTelefono();
				row[3] = persona.getCorreo();
				if(persona.isEmpleado()) {
					row[4] = "Si";
				}else {
					row[4] = "No";
				}
				row[5] = persona.getTiempoDisponible();
				if(persona.isTieneLicencia()) {
					row[6] = "Si";
				}else {
					row[6] = "No";
				}
				row[7] = persona.getSexo();
				row[8] = persona.getCiudad();
				row[9] = null;
				row[10] = null;
				row[11] = null;
				row[12] = null;
				row[13] = null;
				row[14] = null;
				row[15] = ((Trabajador) persona).getOficio();
				model.addRow(row);
			}
		}
	}
	
	private void loadEmpleados() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for(Persona persona : BolsaEmpleo.getInstancia().getLasPersonas()) {
			if(persona.isEmpleado()) {
				row[0] = persona.getCedula();
				row[1] = persona.getNombre();
				row[2] = persona.getTelefono();
				row[3] = persona.getCorreo();
				row[4] = "Si";
				row[5] = persona.getTiempoDisponible();
				if(persona.isTieneLicencia()) {
					row[6] = "Si";
				}else {
					row[6] = "No";
				}
				row[7] = persona.getSexo();
				row[8] = persona.getCiudad();
				if(persona instanceof Grado) {
					row[9] = ((Grado) persona).getUniversidad();
					row[10] = ((Grado) persona).getCarrera();
					row[11] = ((Grado) persona).getTituloUniversitario();
					row[12] = null;
					row[13] = null;
					row[14] = null;
					row[15] = null;
				}else if(persona instanceof Tecnico) {
					row[9] = null;
					row[10] = null;
					row[11] = null;
					row[12] = ((Tecnico) persona).getInstituto();
					row[13] = ((Tecnico) persona).getDiplomaTecnico();
					row[14] = ((Tecnico) persona).getEspecialidad();
					row[15] = null;
				}else if(persona instanceof Trabajador) {
					row[9] = null;
					row[10] = null;
					row[11] = null;
					row[12] = null;
					row[13] = null;
					row[14] = null;
					row[15] = ((Trabajador) persona).getOficio();
				}
				model.addRow(row);
			}
		}
	}
	
	private void loadDesempleados() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for(Persona persona : BolsaEmpleo.getInstancia().getLasPersonas()) {
			if(!persona.isEmpleado()) {
				row[0] = persona.getCedula();
				row[1] = persona.getNombre();
				row[2] = persona.getTelefono();
				row[3] = persona.getCorreo();
				row[4] = "No";
				row[5] = persona.getTiempoDisponible();
				if(persona.isTieneLicencia()) {
					row[6] = "Si";
				}else {
					row[6] = "No";
				}
				row[7] = persona.getSexo();
				row[8] = persona.getCiudad();
				if(persona instanceof Grado) {
					row[9] = ((Grado) persona).getUniversidad();
					row[10] = ((Grado) persona).getCarrera();
					row[11] = ((Grado) persona).getTituloUniversitario();
					row[12] = null;
					row[13] = null;
					row[14] = null;
					row[15] = null;
				}else if(persona instanceof Tecnico) {
					row[9] = null;
					row[10] = null;
					row[11] = null;
					row[12] = ((Tecnico) persona).getInstituto();
					row[13] = ((Tecnico) persona).getDiplomaTecnico();
					row[14] = ((Tecnico) persona).getEspecialidad();
					row[15] = null;
				}else if(persona instanceof Trabajador) {
					row[9] = null;
					row[10] = null;
					row[11] = null;
					row[12] = null;
					row[13] = null;
					row[14] = null;
					row[15] = ((Trabajador) persona).getOficio();
				}
				model.addRow(row);
			}
		}
	}
	
	private void filtrarPersonas() {
		if(cbxFiltrar.getSelectedItem().toString().equalsIgnoreCase("Todos")) {
			loadPersonas();
		}else if(cbxFiltrar.getSelectedItem().toString().equalsIgnoreCase("Universitarios")) {
			loadUniversitarios();
		}else if(cbxFiltrar.getSelectedItem().toString().equalsIgnoreCase("Tecnicos")) {
			loadTecnicos();
		}else if(cbxFiltrar.getSelectedItem().toString().equalsIgnoreCase("Trabajadores")) {
			loadTrabajadores();
		}else if(cbxFiltrar.getSelectedItem().toString().equalsIgnoreCase("Empleados")) {
			loadEmpleados();
		}else if(cbxFiltrar.getSelectedItem().toString().equalsIgnoreCase("Desempleados")) {
			loadDesempleados();
		}
	}
}
