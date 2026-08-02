package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
import logico.Usuario;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ListarOfertas extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable tableOfertas;
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
            ListarOfertas dialog = new ListarOfertas();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ListarOfertas() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GestorFicheros.guardarDatosFicheros();
            }
        });
        setTitle("Gestión de Mis Ofertas de Empleo (Empresa)");
        setBounds(100, 100, 1100, 576);
        setLocationRelativeTo(null);
        setModal(true);
        
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(bgPrincipal);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setLayout(new BorderLayout(0, 0));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        usuarioActual = BolsaEmpleo.getInstancia().getCookieUsuario();
        
        String[] columnas = {"ID Oferta", "Tipo Trabajo", "Título / Habilidad", "Disponibilidad", "Exp.", "Provincia", "Salario", "Puestos", "Estado"};
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
        
        // Panel de Botones
        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(bgPrincipal);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
            
        JButton btnNueva = new JButton("Nueva Oferta");
        btnNueva.setBackground(colorVerde);
        btnNueva.setForeground(Color.WHITE);
        btnNueva.addActionListener(e -> {
            RegistrarOferta dialog = new RegistrarOferta(ListarOfertas.this, null);
            dialog.setVisible(true);
            cargarOfertas(); 
        });
        buttonPane.add(btnNueva);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(colorAzul);
        btnEditar.setForeground(Color.WHITE);
        btnEditar.addActionListener(e -> {
            int selectedRow = tableOfertas.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(ListarOfertas.this, "Seleccione una oferta de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String idOferta = (String) modelTabla.getValueAt(selectedRow, 0);
            RegistrarOferta dialog = new RegistrarOferta(ListarOfertas.this, idOferta);
            dialog.setVisible(true);
            cargarOfertas();
        });
        buttonPane.add(btnEditar);
        
        JButton btnDesactivar = new JButton("Desactivar");
        btnDesactivar.setBackground(colorRojo);
        btnDesactivar.setForeground(Color.WHITE);
        btnDesactivar.addActionListener(e -> {
            int fila = tableOfertas.getSelectedRow();
            if(fila == -1) {
                JOptionPane.showMessageDialog(ListarOfertas.this, "Seleccione una oferta para desactivar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String idOferta = (String) modelTabla.getValueAt(fila, 0);
            int resp = JOptionPane.showConfirmDialog(ListarOfertas.this, "¿Está seguro que desea desactivar esta oferta?", "Confirmar", JOptionPane.YES_NO_OPTION);
            
            if(resp == JOptionPane.YES_OPTION) {
                Oferta oferta = BolsaEmpleo.getInstancia().buscarOferta(idOferta);
                if(oferta != null) {
                    oferta.setActivo(false);
                    GestorFicheros.guardarDatosFicheros();
                    cargarOfertas();
                    JOptionPane.showMessageDialog(ListarOfertas.this, "Oferta desactivada con éxito.");
                }
            }
        });
        buttonPane.add(btnDesactivar);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(colorRojo);
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.addActionListener(e -> {
            GestorFicheros.guardarDatosFicheros();
            dispose();
        });
        buttonPane.add(btnCerrar);
    }
    
    private void cargarOfertas() {
        modelTabla.setRowCount(0);
        ArrayList<Oferta> lista = BolsaEmpleo.getInstancia().getLasOfertas();

        if (lista != null) {
            for (Oferta s : lista) {
                
            	if (usuarioActual != null && ("Admin".equalsIgnoreCase(usuarioActual.getRol()) || "Reclutador".equalsIgnoreCase(usuarioActual.getRol()))) {
            	    String[] partesId = usuarioActual.getId().split("-");
            	    String idFiltroEmpresa = partesId[0]; 

            	    if (!s.getIdEmpresa().equals(idFiltroEmpresa)) {
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

                String experiencia = String.format("%d", s.getExperienciaLaboral());
                String salario = "$" + String.format("%.0f", s.getSalario());
                String puestos = String.format("%d", s.getCantPuesto());
                String estado = s.isActivo() ? "Activa" : "Inactiva";

                Object[] fila = {
                    s.getId(),
                    s.getTipoTrabajo(),
                    tituloOHabilidad,
                    s.getTiempoTrabajo(),
                    experiencia,
                    s.getProvincia(),
                    salario,
                    puestos,
                    estado
                };
                modelTabla.addRow(fila);
            }
        }
    }
}
