package plots;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class LineChart {

    public static void main(String[] args) throws Exception {
        double[] xData = new double[]{0.0, 1.0, 2.0, 3.0, 4.0};
        double[] yData = new double[]{2.0, 10.0, -10.0, 5, 0};

        // Create Chart
        XYChart chart = QuickChart.getChart("Line Chart", "X", "Y", "y(x)", xData, yData);

        // Show it
        new SwingWrapper(chart).displayChart();
    }

}