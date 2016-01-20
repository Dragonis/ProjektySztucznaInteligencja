package org.WojtekSasiela;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.layout.FormatLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragonis on 19.01.2016.
 */
public class Perceptron_WojciechSasiela extends JFrame {
    private JButton GenerujBtn;
    private JPanel rootPanel;
    private JTable table1;
    private JPanel wykresPanel;
    private XYSeries series1;


    public Perceptron_WojciechSasiela() {
        super("Perceptron");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        table1.setModel(new FilenameTableModel());
        rootPanel.add(table1,BorderLayout.CENTER);
        rootPanel.add(table1.getTableHeader(), BorderLayout.NORTH);

        table1.setSize(300,300);

        GenerujBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                dataset.setValue(new Double(10),"Value","AAA");
                dataset.setValue(new Double(20),"Value","BBB");
                dataset.setValue(new Double(35),"Value","CCC");
                dataset.setValue(new Double(15),"Value","DDD");

                JFreeChart chart = ChartFactory.createLineChart("Parameter value", "Parameter", "Value", dataset, PlotOrientation.VERTICAL, false, true, false);
                chart.setBackgroundPaint(Color.YELLOW);
                chart.getTitle().setPaint(Color.red);
                CategoryPlot p = chart.getCategoryPlot();
                p.setRangeGridlinePaint(Color.blue);
                ChartPanel frame = new ChartPanel(chart);

                frame.setVisible(true);
                frame.setSize(450,350);
                wykresPanel.removeAll();
                wykresPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                wykresPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // komponent przechodzi do nastepnej lini
                wykresPanel.add(frame);
                wykresPanel.setVisible(true);
            }
        });

        setSize(1000,500);
        setVisible(true);

    }

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createXYLineChart(
                "Dynamic Data Demo",
                "Time",
                "Value",
                dataset);
        final XYPlot plot = result.getXYPlot();
        //ValueAxis axis = plot.getDomainAxis();
        //axis.setAutoRange(true);
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(false);
        yAxis.setAutoRange(true);

        customizeChart(result);
        //axis.setFixedAutoRange(60000.0);  // 60 seconds
        //axis = plot.getRangeAxis();
        //axis.setRange(0.0, 200.0);
        return result;
    }

    private void customizeChart(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer;
        renderer = new XYLineAndShapeRenderer(true, true);
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShapesVisible(1, false);

        // sets paint color for each series
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.BLUE);

        // sets thickness for series (using strokes)
        renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        renderer.setSeriesStroke(1, new BasicStroke(1.0f));

        // sets paint color for plot outlines
        //plot.setOutlinePaint(Color.BLUE);
        //plot.setOutlineStroke(new BasicStroke(2.0f));

        // sets renderer for lines
        plot.setRenderer(renderer);

        // sets plot background
        plot.setBackgroundPaint(Color.WHITE);

        // sets paint color for the grid lines
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
    }



}