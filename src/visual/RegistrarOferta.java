package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PRIVATE_MEMBER;

import logico.BolsaEmpleo;
import logico.Empresa;
import logico.GestorFicheros;
import logico.Oferta;
import logico.Usuario;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;

public class RegistrarOferta extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtTipoTrabajo;
    private JTextField txtDisponibilidad;
    private JSpinner spnExperiencia;
    private JComboBox<String> cbxProvincia;
    private JComboBox<String> cbxTituloOficio;
    private JRadioButton rdbMasculino;
    private JRadioButton rdbFemenino;
    private JSpinner spnSalario;
    private JSpinner spnPuestos;
    private JCheckBox chkMudarse;
    private JCheckBox chkLicencia;
    private JTextPane txtDescripcion;

    private Usuario usuario = null;

    // Colores Paleta 
    private final Color bgPrincipal = new Color(243, 244, 246); // Gris muy claro
    private final Color bgInputs = Color.WHITE;                 // Blanco puro
    private final Color colorTexto = new Color(31, 41, 55);     // Gris carbón oscuro
    private final Color colorVerde = new Color(16, 185, 129);   // Verde 
    private final Color colorRojo = new Color(239, 68, 68);     // Rojo
    private JTextField textField;

    public static void main(String[] args) {
        try {
            GestorFicheros.cargarDatosDesdeFicheros();
            RegistrarOferta dialog = new RegistrarOferta(null, null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RegistrarOferta(Window parent, String idOferta) {
        super(parent, ModalityType.APPLICATION_MODAL);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GestorFicheros.guardarDatosFicheros();
            }
        });
        setTitle(idOferta != null ? "Editar Oferta de Empleo" : "Registrar Oferta de Empleo");
        setBounds(100, 100, 600, 450);
        setLocationRelativeTo(parent);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(bgPrincipal);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        usuario = BolsaEmpleo.getInstancia().getCookieUsuario();

        JLabel lblTipo = new JLabel("Tipo Trabajo:");
        lblTipo.setForeground(colorTexto);
        lblTipo.setBounds(15, 15, 95, 16);
        contentPanel.add(lblTipo);

        txtTipoTrabajo = new JTextField();
        txtTipoTrabajo.setBackground(bgInputs);
        txtTipoTrabajo.setForeground(colorTexto);
        txtTipoTrabajo.setBounds(110, 12, 170, 22);
        contentPanel.add(txtTipoTrabajo);

        JLabel lblTitulo = new JLabel("Título/Oficio:");
        lblTitulo.setForeground(colorTexto);
        lblTitulo.setBounds(300, 15, 90, 16);
        contentPanel.add(lblTitulo);

        cbxTituloOficio = new JComboBox<>();
        cbxTituloOficio.addItem("-- Seleccione --");
        cbxTituloOficio.addItem("Grado / Ingenieria");
        cbxTituloOficio.addItem("Tecnico");
        cbxTituloOficio.addItem("Oficio");
        cbxTituloOficio.setBackground(bgInputs);
        cbxTituloOficio.setForeground(colorTexto);
        cbxTituloOficio.setBounds(395, 12, 175, 22);
        contentPanel.add(cbxTituloOficio);

        JLabel lblDisp = new JLabel("Disponibilidad:");
        lblDisp.setForeground(colorTexto);
        lblDisp.setBounds(15, 48, 95, 16);
        contentPanel.add(lblDisp);

        txtDisponibilidad = new JTextField();
        txtDisponibilidad.setBackground(bgInputs);
        txtDisponibilidad.setForeground(colorTexto);
        txtDisponibilidad.setBounds(110, 45, 170, 22);
        contentPanel.add(txtDisponibilidad);

        JLabel lblExp = new JLabel("Exp. (Años):");
        lblExp.setForeground(colorTexto);
        lblExp.setBounds(300, 48, 90, 16);
        contentPanel.add(lblExp);

        spnExperiencia = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        spnExperiencia.setBounds(395, 45, 175, 22);
        contentPanel.add(spnExperiencia);

        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setForeground(colorTexto);
        lblSexo.setBounds(15, 81, 50, 16);
        contentPanel.add(lblSexo);

        rdbMasculino = new JRadioButton("Masculino");
        rdbMasculino.setBackground(bgPrincipal);
        rdbMasculino.setBounds(71, 78, 95, 23);
        rdbFemenino = new JRadioButton("Femenino");
        rdbFemenino.setBackground(bgPrincipal);
        rdbFemenino.setBounds(168, 78, 112, 23);

        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(rdbMasculino);
        grupoSexo.add(rdbFemenino);
        contentPanel.add(rdbMasculino);
        contentPanel.add(rdbFemenino);

        JLabel lblProvincia = new JLabel("Provincia:");
        lblProvincia.setForeground(colorTexto);
        lblProvincia.setBounds(300, 81, 90, 16);
        contentPanel.add(lblProvincia);

        cbxProvincia = new JComboBox<>();
        cbxProvincia.addItem("-- Seleccione --");
        for (String ciudad : BolsaEmpleo.getInstancia().getCiudades()) {
            cbxProvincia.addItem(ciudad);
        }
        cbxProvincia.setBounds(395, 78, 175, 22);
        contentPanel.add(cbxProvincia);

        JLabel lblSal = new JLabel("Salario:");
        lblSal.setForeground(colorTexto);
        lblSal.setBounds(15, 114, 95, 16);
        contentPanel.add(lblSal);
		
		spnSalario = new JSpinner();
		spnSalario.setModel(getSalioMinimo());
		spnSalario.setBounds(83, 112, 197, 20);
		contentPanel.add(spnSalario);
		
		chkMudarse = new JCheckBox("Dispuesto a mudarse");
        chkMudarse.setBackground(bgPrincipal);
        chkMudarse.setBounds(300, 173, 170, 23);
        contentPanel.add(chkMudarse);

        chkLicencia = new JCheckBox("Licencia requerida");
        chkLicencia.setBackground(bgPrincipal);
        chkLicencia.setBounds(300, 145, 145, 23);
        contentPanel.add(chkLicencia);

        JLabel lblPuestos = new JLabel("Puestos Disp.:");
        lblPuestos.setForeground(colorTexto);
        lblPuestos.setBounds(300, 114, 90, 16);
        contentPanel.add(lblPuestos);

        spnPuestos = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spnPuestos.setBounds(395, 111, 175, 22);
        contentPanel.add(spnPuestos);

        JLabel lblDesc = new JLabel("Descripción de la Oferta:");
        lblDesc.setForeground(colorTexto);
        lblDesc.setBounds(15, 180, 200, 16);
        contentPanel.add(lblDesc);

        txtDescripcion = new JTextPane();
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        scrollDesc.setBounds(15, 200, 555, 120);
        contentPanel.add(scrollDesc);
        
        JCheckBox chkEspecificado = new JCheckBox("Solo Especificado");
        chkEspecificado.setBounds(447, 145, 123, 23);
        contentPanel.add(chkEspecificado);
        
        JLabel lblNewLabel = new JLabel("Minimo Coincidencia: ");
        lblNewLabel.setBounds(15, 331, 106, 14);
        contentPanel.add(lblNewLabel);
        
        JSpinner spnCoincidencia = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 100.0, 1.0));
        spnCoincidencia.setBounds(131, 328, 149, 20);
        contentPanel.add(spnCoincidencia);
        
        JLabel lblNewLabel_1 = new JLabel("Area");
        lblNewLabel_1.setBounds(15, 149, 46, 14);
        contentPanel.add(lblNewLabel_1);
        
        JTextField txtArea = new JTextField();
        txtArea.setBounds(83, 146, 197, 20);
        contentPanel.add(txtArea);
        txtArea.setColumns(10);

        if (idOferta != null) {
            Oferta oferta = BolsaEmpleo.getInstancia().buscarOferta(idOferta);
            if (oferta != null) {
                txtTipoTrabajo.setText(oferta.getTipoTrabajo());
                txtDisponibilidad.setText(oferta.getTiempoTrabajo());
                spnExperiencia.setValue(oferta.getExperienciaLaboral());
                cbxProvincia.setSelectedItem(oferta.getProvincia());
                chkMudarse.setSelected(oferta.isDispuestoAMudarse());
                chkLicencia.setSelected(oferta.isLicenciaDeConducir());
                spnSalario.setValue((double) oferta.getSalario());
                spnPuestos.setValue(oferta.getCantPuesto());
                if(oferta.getDescripcionTrabajo() != null) txtDescripcion.setText(oferta.getDescripcionTrabajo());

                if (oferta.getSexo() == 'M') rdbMasculino.setSelected(true);
                else if (oferta.getSexo() == 'F') rdbFemenino.setSelected(true);
            }
        }

        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(bgPrincipal);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton(idOferta != null ? "Modificar" : "Registrar");
        okButton.setBackground(colorVerde);
        okButton.setForeground(Color.WHITE);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (usuario == null) {
                    JOptionPane.showMessageDialog(null, "Debe iniciar sesión para registrar una oferta.", "Sesión requerida", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String tipo = txtTipoTrabajo.getText().trim();
                String tiempoTrabajo = txtDisponibilidad.getText().trim();
                String provincia = (String) cbxProvincia.getSelectedItem();
                int experiencia = (int) spnExperiencia.getValue();
                float sal = ((Double) spnSalario.getValue()).floatValue();
                int puestos = (int) spnPuestos.getValue();
                boolean dispMudarse = chkMudarse.isSelected();
                boolean licRequerida = chkLicencia.isSelected();
                boolean solEspecif = chkEspecificado.isSelected();
                String descripcion = txtDescripcion.getText().trim();
                float coincidencia = ((Double) spnCoincidencia.getValue()).floatValue();

                char sexo = 'I';
                if (rdbMasculino.isSelected()) sexo = 'M';
                else if (rdbFemenino.isSelected()) sexo = 'F';

                if (tipo.isEmpty() || tiempoTrabajo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete los campos requeridos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (provincia == null || provincia.equals("-- Seleccione --")) {
                    JOptionPane.showMessageDialog(null, "Seleccione una provincia válida.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String tituloSelect = (String) cbxTituloOficio.getSelectedItem();
                String titulo = "n/a", tecnico = "n/a", habilidad = "n/a";
                if ("Grado / Ingenieria".equals(tituloSelect)) {
                    titulo = txtArea.getText();
                } else if ("Tecnico".equals(tituloSelect)) {
                    tecnico = txtArea.getText();
                } else if ("Oficio".equals(tituloSelect)) {
                    habilidad = txtArea.getText();
                }
                
                if (idOferta != null) {
                    BolsaEmpleo.getInstancia().modificarOferta(
                        idOferta, tipo, titulo, tecnico, habilidad, tiempoTrabajo, 
                        experiencia, sexo, provincia, licRequerida, dispMudarse, descripcion,
                        sal, coincidencia, puestos, solEspecif
                    );
                    JOptionPane.showMessageDialog(null, "Oferta modificada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    BolsaEmpleo.getInstancia().registrarOferta(
                        usuario.getId(), tipo, titulo, tecnico, habilidad, tiempoTrabajo, 
                        experiencia, sexo, provincia, licRequerida, dispMudarse, descripcion,
                        sal, coincidencia, puestos, solEspecif 
                    );
                    JOptionPane.showMessageDialog(null, "Oferta registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
                GestorFicheros.guardarDatosFicheros();
                dispose();
            }
        });
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(colorRojo);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestorFicheros.guardarDatosFicheros();
                dispose();
            }
        });
        buttonPane.add(cancelButton);
	}
    
	private SpinnerNumberModel getSalioMinimo (){
		
		SpinnerNumberModel salario = null;
		Empresa aux = BolsaEmpleo.getInstancia().getEmpresaPorEmpleado(BolsaEmpleo.getInstancia().getCookieUsuario());
		if (aux.getTipo().equalsIgnoreCase("micro"))
			salario = new SpinnerNumberModel(16993.0, 16993.0, null, 1.0);
		else if (aux.getTipo().equalsIgnoreCase("pequeña"))
			salario = new SpinnerNumberModel(18421.0, 18421.0, null, 1.0);
		else if (aux.getTipo().equalsIgnoreCase("Mediana"))
			salario = new SpinnerNumberModel(27489.0, 27489.0, null, 1.0);
		else if (aux.getTipo().equalsIgnoreCase("Grande"))
			salario = new SpinnerNumberModel(29988.0, 29988.0, null, 1.0);
		else if (aux.getTipo().equalsIgnoreCase("Zona Franca"))
			salario = new SpinnerNumberModel(20875.0, 20875.0, null, 1.0);
		else if (aux.getTipo().equalsIgnoreCase("Hotel o Casino (mediano/pequeño)"))
			salario = new SpinnerNumberModel(18409.0, 18409.0, null, 1.0);
		else if (aux.getTipo().equalsIgnoreCase("Hotel o Casino (Grande)"))
			salario = new SpinnerNumberModel(21840.0, 21840.0, null, 1.0);
		else {
			salario = new SpinnerNumberModel(21840.0, 21840.0, null, 1.0);
		}
		return salario;
	}
}
