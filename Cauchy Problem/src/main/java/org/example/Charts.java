package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.geom.Ellipse2D;
import java.io.File;
import java.util.List;
import java.util.function.Function;

import static java.awt.Color.*;
import static org.example.Milne.xs;
import static org.example.Milne.ys;

public class Charts {
    static int width = 640;
    static int height = 480;
    public static XYSeriesCollection getData(double x0, double y0, double x, double y, Function<List<Double>, Double> f) {

        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries initialCondition = new XYSeries("Начальное условие"); initialCondition.add(x0, y0);
        XYSeries end = new XYSeries("Найденное решение"); end.add(x, y);


        XYSeries analyticalDots = new XYSeries("Аналитическое решение");
        for (int i = 0; i <= 100; i++) {
            analyticalDots.add(xs.get(i), f.apply(List.of(x0, y0, xs.get(i))));
        }

        XYSeries milne_dots = new XYSeries("Решения методом Милна");
        for (int i = 0; i <= 100; i++) {
            milne_dots.add(xs.get(i), ys.get(i));
        }

        dataset.addSeries(initialCondition);
        dataset.addSeries(milne_dots);
        dataset.addSeries(end);
        dataset.addSeries(analyticalDots);

        return dataset;
    }

    public static void render(JFreeChart chart) {

        XYPlot xyPlot = chart.getXYPlot();

        xyPlot.setRenderer(new XYSplineRenderer());
        XYItemRenderer renderer = xyPlot.getRenderer();

        renderer.setSeriesShape(0, new Ellipse2D.Double(-3, -3, 6, 6));
        renderer.setSeriesShape(1, new Ellipse2D.Double(0, 0, 0, 0));
        renderer.setSeriesShape(2, new Ellipse2D.Double(-3, -3, 6, 6));
        renderer.setSeriesShape(3, new Ellipse2D.Double(0, 0, 0, 0));

        renderer.setSeriesPaint(0, blue);
        renderer.setSeriesPaint(1, blue);
        renderer.setSeriesPaint(2, red);
        renderer.setSeriesPaint(3, black);
    }

    public static void draw(Function<List<Double>, Double> f, double x0, double y0, double x, double y) throws Exception {

        XYSeriesCollection dataset = getData(x0, y0, x, y, f);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "График решений дифференциального уравнения",
                "X", "Y", dataset, PlotOrientation.VERTICAL,
                true, false, false);

        render(chart);

        File file = new File( "src/main/resources/Chart.jpeg" );
        ChartUtils.saveChartAsJPEG(file, chart, width, height);
        System.exit(0);
    }
}
