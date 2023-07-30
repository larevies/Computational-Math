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
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.awt.Color.*;

public class Charts {
    static int width = 640;
    static int height = 480;
    public static void get(XYSeries series, List<Double> x, List<Double> y, int n) {
        for (int i = 0; i < n; i++) {
            series.add(x.get(i), y.get(i));
        }
    }

    public static void getFromFunction(XYSeries series, List<Double> x, int number){
        List<Function<Double, Double>> list = Functions.getFunction(number);
        double length = Collections.max(x) - Collections.min(x);

        for (int i = 0; i < 10; i++) {
            double step = Collections.min(x) + i * ((length+1) / 10);
            series.add(step, list.get(0).apply(step));
        }
    }

    public static void renderAndSave(XYSeriesCollection dataset, int number) throws IOException {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "График функции, построенный по точкам",
                "X", "Y", dataset, PlotOrientation.VERTICAL,
                true, false, false);

        XYPlot xyPlot = chart.getXYPlot();

        xyPlot.setRenderer( new XYSplineRenderer() );
        XYItemRenderer renderer = xyPlot.getRenderer();

        renderer.setSeriesShape(0, new Ellipse2D.Double(-3, -3, 6, 6));
        renderer.setSeriesShape(1, new Ellipse2D.Double(-3, -3, 6, 6));
        if (number != 0) {
            renderer.setSeriesShape(2, new Ellipse2D.Double(-0.1, -0.1, 0.2, 0.2));
            renderer.setSeriesPaint(0, red);
            renderer.setSeriesPaint(1, black);
            renderer.setSeriesPaint(2, blue);
        }

        File file = new File( "src/main/resources/Chart.jpeg" );
        ChartUtils.saveChartAsJPEG(file, chart, width, height);
        System.exit(0);

    }

    public static void drawNotSpecified(List<Double> x, List<Double> y, int n, int number) throws Exception {
        XYSeries series = new XYSeries("До интерполяции"); get(series, x, y, n - 1);
        XYSeries newSeries = new XYSeries("После интерполяции"); get(newSeries, x, y, n);

        XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries(series);
        dataset.addSeries(newSeries);

        renderAndSave(dataset, number);
    }

    public static void drawSpecified(List<Double> x, List<Double> y, int n, int number) throws Exception {
        XYSeries series = new XYSeries("Оригинальный график"); getFromFunction(series, x, number);
        XYSeries dots = new XYSeries("Заданные точки"); get(dots, x, y, n);
        XYSeries newSeries = new XYSeries("Найденная точка"); newSeries.add(x.get(n-1), y.get(n-1));

        XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries(newSeries);
        dataset.addSeries(dots);
        dataset.addSeries(series);

        renderAndSave(dataset, number);
    }

    public static void draw(List<Double> x, List<Double> y, int n, int number) throws Exception {
        if (number == 0) { drawNotSpecified(x, y, n, number); } else { drawSpecified(x, y, n, number);}
    }
}
