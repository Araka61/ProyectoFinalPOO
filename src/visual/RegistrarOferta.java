package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logico.GestorFicheros;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;

public class RegistrarOferta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarOferta dialog = new RegistrarOferta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarOferta() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GestorFicheros.guardarDatosFicheros();
			}
		});
		setTitle("Registrar Oferta");
		setBounds(100, 100, 604, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Tipo Trabajo:");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBounds(10, 18, 64, 14);
			contentPanel.add(lblNewLabel);
		}
		
		textField = new JTextField();
		textField.setBackground(Color.DARK_GRAY);
		textField.setForeground(new Color(255, 255, 255));
		textField.setBounds(84, 15, 197, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Titulo/Oficio:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(299, 21, 279, 14);
		contentPanel.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(Color.WHITE);
		comboBox.setBackground(Color.DARK_GRAY);
		comboBox.setBounds(373, 18, 205, 20);
		contentPanel.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Disponibilidad:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 53, 68, 14);
		contentPanel.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.WHITE);
		textField_1.setBackground(Color.DARK_GRAY);
		textField_1.setBounds(84, 50, 197, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Exp. (a\u00F1os):");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(299, 56, 279, 14);
		contentPanel.add(lblNewLabel_3);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(373, 53, 205, 20);
		contentPanel.add(spinner);
		
		JLabel lblNewLabel_4 = new JLabel("Sexo:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(10, 86, 38, 14);
		contentPanel.add(lblNewLabel_4);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Masculino");
		rdbtnNewRadioButton.setForeground(Color.WHITE);
		rdbtnNewRadioButton.setBackground(Color.GRAY);
		rdbtnNewRadioButton.setBounds(84, 82, 71, 23);
		contentPanel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Femenino");
		rdbtnNewRadioButton_1.setBackground(Color.GRAY);
		rdbtnNewRadioButton_1.setForeground(Color.WHITE);
		rdbtnNewRadioButton_1.setBounds(210, 82, 71, 23);
		contentPanel.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("Provincia:");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(299, 91, 279, 14);
		contentPanel.add(lblNewLabel_5);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(373, 88, 205, 20);
		contentPanel.add(comboBox_1);
		
		JLabel lblNewLabel_6 = new JLabel("Salario:");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(10, 124, 64, 14);
		contentPanel.add(lblNewLabel_6);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(84, 121, 197, 20);
		contentPanel.add(spinner_1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Necesidad de disposicion a mudarse");
		chckbxNewCheckBox.setForeground(Color.WHITE);
		chckbxNewCheckBox.setBackground(Color.GRAY);
		chckbxNewCheckBox.setBounds(10, 155, 271, 23);
		contentPanel.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Necesidad de licencia de conducir");
		chckbxNewCheckBox_1.setBackground(Color.GRAY);
		chckbxNewCheckBox_1.setForeground(Color.WHITE);
		chckbxNewCheckBox_1.setBounds(299, 123, 279, 23);
		contentPanel.add(chckbxNewCheckBox_1);
		
		JLabel lblNewLabel_8 = new JLabel("Puestos Disp.");
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setBounds(299, 164, 279, 14);
		contentPanel.add(lblNewLabel_8);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(373, 161, 205, 20);
		contentPanel.add(spinner_2);
		
		JLabel lblNewLabel_9 = new JLabel("Descripcion");
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setBounds(10, 194, 64, 14);
		contentPanel.add(lblNewLabel_9);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 219, 568, 98);
		contentPanel.add(textPane);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.setBackground(new Color(0, 128, 0));
				okButton.setForeground(new Color(255, 255, 255));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setForeground(new Color(255, 255, 255));
				cancelButton.setBackground(new Color(128, 0, 0));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GestorFicheros.guardarDatosFicheros();
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
