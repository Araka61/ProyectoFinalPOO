package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.*;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.*;
import org.jfree.data.general.*;

import logico.Empresa;
import logico.Oferta;

public class GraficaRecepcionOfertas extends JDialog {

	private static final long serialVersionUID = 1L;

	private final Color bgPrincipal = new Color(243, 244, 246);
	private final Color colorTexto = new Color(31, 41, 55);
	private final Color colorAzul = new Color(37, 99, 235);
	private final Color colorVerde = new Color(16, 185, 129);
	private final Color colorRojo = new Color(239, 68, 68);
	private final Color colorAmarillo = new Color(245, 158, 11);
	private final Color colorMorado = new Color(139, 92, 246);
	private final Color colorNaranja = new Color(249, 115, 22);
	private final Color colorCian = new Color(6, 182, 212);

	private ArrayList<Oferta> lasOfertas;

	public GraficaRecepcionOfertas(Empresa empresa) {
		
		this.lasOfertas = empresa != null ? empresa.getLasOfertas() : new ArrayList<>();

		setTitle("Contrataciones por Oferta");
		setBounds(100, 100, 820, 560);
		setLocationRelativeTo(null);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
		
		getContentPane().setBackground(bgPrincipal);
		getContentPane().setLayout(new BorderLayout());

		String tituloVentana = empresa != null ? "Contrataciones por oferta de: " + empresa.getNombre() : "Contrataciones por oferta";
		JLabel lblTitulo = new JLabel(tituloVentana, SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setForeground(colorTexto);
		lblTitulo.setBorder(new EmptyBorder(12, 10, 12, 10));
		getContentPane().add(lblTitulo, BorderLayout.NORTH);

		if (lasOfertas == null || lasOfertas.isEmpty()) {
			JLabel lblVacio = new JLabel("La empresa no tiene ofertas registradas para graficar.", SwingConstants.CENTER);
			lblVacio.setForeground(colorTexto);
			lblVacio.setFont(new Font("Tahoma", Font.PLAIN, 13));
			getContentPane().add(lblVacio, BorderLayout.CENTER);
			return;
		}

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(bgPrincipal);
		tabbedPane.setForeground(colorTexto);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.addTab("Cantidad total", crearPanelBarras());
		tabbedPane.addTab("Porcentaje (%)", crearPanelTorta());
	}

	private int cantidadContratados(Oferta oferta) {
		if (oferta.getIdContratados() != null) {
			return oferta.getIdContratados().size();
		}
		return 0;
	}

	private String etiquetaOferta(Oferta oferta) {
		String titulo = oferta.getTitulo();
		if (titulo == null || titulo.trim().isEmpty() || titulo.equalsIgnoreCase("n/a")) {
			titulo = oferta.getDescripcionTrabajo();
		}
		return titulo + " [" + oferta.getId() + "]";
	}

	private ChartPanel crearPanelBarras() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Oferta oferta : lasOfertas) {
			dataset.addValue(cantidadContratados(oferta), "Contratados", etiquetaOferta(oferta));
		}

		JFreeChart barChart = ChartFactory.createBarChart(
				"Cantidad de contratados por oferta",
				"Oferta",
				"Cantidad de contrataciones",
				dataset,
				PlotOrientation.VERTICAL,
				false,
				true,
				false);

		barChart.setBackgroundPaint(bgPrincipal);
		barChart.getTitle().setPaint(colorTexto);

		CategoryPlot plot = barChart.getCategoryPlot();
		plot.setBackgroundPaint(Color.WHITE); 
		plot.setRangeGridlinePaint(new Color(229, 231, 235));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, colorAzul);
		renderer.setShadowVisible(false);
		renderer.setMaximumBarWidth(0.08);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setBackground(bgPrincipal);
		chartPanel.setMouseWheelEnabled(true);
		return chartPanel;
	}

	private ChartPanel crearPanelTorta() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		int totalGeneral = 0;
		
		for (Oferta oferta : lasOfertas) {
			int cantidad = cantidadContratados(oferta);
			totalGeneral += cantidad;
			if (cantidad > 0) {
				dataset.setValue(etiquetaOferta(oferta), cantidad);
			}
		}

		JFreeChart pieChart = ChartFactory.createPieChart(
				"Porcentaje de contrataciones por oferta",
				dataset,
				true,
				true,
				false);

		pieChart.setBackgroundPaint(bgPrincipal);
		pieChart.getTitle().setPaint(colorTexto);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(bgPrincipal); 
		plot.setOutlineVisible(false);
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));

		if (totalGeneral == 0) {
			plot.setNoDataMessage("Ninguna oferta de esta empresa tiene contrataciones todavía.");
		} else {
			Color[] paletaTorta = { colorAzul, colorVerde, colorAmarillo, colorRojo, colorMorado, colorNaranja, colorCian };
			int i = 0;
			for (Object key : dataset.getKeys()) {
				plot.setSectionPaint((Comparable<?>) key, paletaTorta[i % paletaTorta.length]);
				i++;
			}
		}

		ChartPanel chartPanel = new ChartPanel(pieChart);
		chartPanel.setBackground(bgPrincipal);
		chartPanel.setMouseWheelEnabled(true);
		return chartPanel;
	}
}