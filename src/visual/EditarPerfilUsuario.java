package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.GestorFicheros;
import logico.Persona;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarPerfilUsuario extends JDialog {

    private final JPanel contentPanel = new JPanel();

    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JComboBox<String> cbxCiudad;
    private JTextField txtTiempoDisponible;
    private JCheckBox chkLicencia;
    private JRadioButton rdbMasculino;
    private JRadioButton rdbFemenino;

    private Persona persona;

    // Colores Paleta
    private final Color bgPrincipal = new Color(243, 244, 246); // Gris muy claro
    private final Color bgInputs = Color.WHITE;                 // Blanco puro
    private final Color colorTexto = new Color(31, 41, 55);     // Gris carbón oscuro
    private final Color colorVerde = new Color(16, 185, 129);   // Verde
    private final Color colorRojo = new Color(239, 68, 68);     // Rojo

    public static void main(String[] args) {
        try {
            GestorFicheros.cargarDatosDesdeFicheros();
            String idPrueba = BolsaEmpleo.getInstancia().getLasPersonas().isEmpty()
                    ? null
                    : BolsaEmpleo.getInstancia().getLasPersonas().get(0).getId();
            EditarPerfilUsuario dialog = new EditarPerfilUsuario(idPrueba);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public EditarPerfilUsuario(String idPersonaUsuario) {
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GestorFicheros.guardarDatosFicheros();
            }
        });

        setTitle("Editar Datos Personales");
        setBounds(100, 100, 554, 340);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(bgPrincipal);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        persona = BolsaEmpleo.getInstancia().buscarPersona(idPersonaUsuario);

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setForeground(colorTexto);
        lblCedula.setBounds(15, 15, 90, 16);
        contentPanel.add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBackground(bgInputs);
        txtCedula.setForeground(colorTexto);
        txtCedula.setBounds(110, 12, 175, 22);
        contentPanel.add(txtCedula);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(colorTexto);
        lblNombre.setBounds(15, 48, 90, 16);
        contentPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBackground(bgInputs);
        txtNombre.setForeground(colorTexto);
        txtNombre.setBounds(110, 45, 360, 22);
        contentPanel.add(txtNombre);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setForeground(colorTexto);
        lblTelefono.setBounds(15, 81, 90, 16);
        contentPanel.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBackground(bgInputs);
        txtTelefono.setForeground(colorTexto);
        txtTelefono.setBounds(110, 78, 175, 22);
        contentPanel.add(txtTelefono);

        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setForeground(colorTexto);
        lblSexo.setBounds(295, 81, 50, 16);
        contentPanel.add(lblSexo);

        rdbMasculino = new JRadioButton("M");
        rdbMasculino.setBackground(bgPrincipal);
        rdbMasculino.setForeground(colorTexto);
        rdbMasculino.setBounds(345, 78, 50, 23);

        rdbFemenino = new JRadioButton("F");
        rdbFemenino.setBackground(bgPrincipal);
        rdbFemenino.setForeground(colorTexto);
        rdbFemenino.setBounds(400, 78, 50, 23);

        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(rdbMasculino);
        grupoSexo.add(rdbFemenino);
        contentPanel.add(rdbMasculino);
        contentPanel.add(rdbFemenino);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setForeground(colorTexto);
        lblCorreo.setBounds(15, 114, 90, 16);
        contentPanel.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBackground(bgInputs);
        txtCorreo.setForeground(colorTexto);
        txtCorreo.setBounds(110, 111, 360, 22);
        contentPanel.add(txtCorreo);

        JLabel lblCiudad = new JLabel("Ciudad:");
        lblCiudad.setForeground(colorTexto);
        lblCiudad.setBounds(15, 147, 90, 16);
        contentPanel.add(lblCiudad);

        cbxCiudad = new JComboBox<>();
        for (String ciudad : BolsaEmpleo.getInstancia().getCiudades()) {
            cbxCiudad.addItem(ciudad);
        }
        cbxCiudad.setBackground(bgInputs);
        cbxCiudad.setForeground(colorTexto);
        cbxCiudad.setBounds(110, 144, 175, 22);
        contentPanel.add(cbxCiudad);

        JLabel lblTiempo = new JLabel("Disponibilidad:");
        lblTiempo.setForeground(colorTexto);
        lblTiempo.setBounds(295, 147, 100, 16);
        contentPanel.add(lblTiempo);

        txtTiempoDisponible = new JTextField();
        txtTiempoDisponible.setBackground(bgInputs);
        txtTiempoDisponible.setForeground(colorTexto);
        txtTiempoDisponible.setBounds(295, 168, 175, 22);
        contentPanel.add(txtTiempoDisponible);

        chkLicencia = new JCheckBox("Tiene licencia de conducir");
        chkLicencia.setBackground(bgPrincipal);
        chkLicencia.setForeground(colorTexto);
        chkLicencia.setBounds(15, 178, 220, 23);
        contentPanel.add(chkLicencia);

        cargarDatosEnPantalla();

        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(bgPrincipal);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Guardar");
        okButton.setBackground(colorVerde);
        okButton.setForeground(Color.WHITE);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
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

    private void cargarDatosEnPantalla() {
        if (persona == null) {
            JOptionPane.showMessageDialog(this, "No se encontró la persona solicitada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtCedula.setText(persona.getCedula());
        txtNombre.setText(persona.getNombre());
        txtTelefono.setText(persona.getTelefono());
        txtCorreo.setText(persona.getCorreo());
        cbxCiudad.setSelectedItem(persona.getCiudad());
        txtTiempoDisponible.setText(persona.getTiempoDisponible());
        chkLicencia.setSelected(persona.isTieneLicencia());

        if (persona.getSexo() == 'M') {
            rdbMasculino.setSelected(true);
        } else if (persona.getSexo() == 'F') {
            rdbFemenino.setSelected(true);
        }
    }

    private void guardarCambios() {
        if (persona == null) {
            dispose();
            return;
        }

        String cedula = txtCedula.getText().trim();
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String ciudad = (String) cbxCiudad.getSelectedItem();
        String tiempoDisponible = txtTiempoDisponible.getText().trim();
        boolean licencia = chkLicencia.isSelected();

        char sexo = 'I';
        if (rdbMasculino.isSelected()) sexo = 'M';
        else if (rdbFemenino.isSelected()) sexo = 'F';

        if (cedula.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete los campos requeridos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!correoValido(correo)){
            JOptionPane.showMessageDialog(this, "Ingrese un correo válido (debe contener '@' y '.').", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        persona.setCedula(cedula);
        persona.setNombre(nombre);
        persona.setTelefono(telefono);
        persona.setCorreo(correo);
        persona.setCiudad(ciudad);
        persona.setTiempoDisponible(tiempoDisponible);
        persona.setTieneLicencia(licencia);
        persona.setSexo(sexo);

        GestorFicheros.guardarDatosFicheros();
        JOptionPane.showMessageDialog(this, "Datos actualizados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private boolean correoValido(String correo) {
        if (correo == null) return false;
        if (correo.contains("@") && correo.contains("."))
        	if (BolsaEmpleo.getInstancia().getUsuarioPorCorreo(correo) != null) {
        		if (BolsaEmpleo.getInstancia().getUsuarioPorCorreo(correo).equals(persona));
        	return true;
        	}else {
				return true;
			}
        return false;
    }
}