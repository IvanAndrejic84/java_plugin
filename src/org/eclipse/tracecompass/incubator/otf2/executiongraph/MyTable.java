import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class myTableExample {
    private Table table;
    private Text txtMean;
    private Text txtMin;
    private Text txtMax;
    private Text txtStdDev;
    private Label lblTime;

    public void setData(double[] data) {
        long startTime = System.nanoTime();

        // Calculate basic statistics
        double mean = calculateMean(data);
        int min = calculateMin(data);
        int max = calculateMax(data);
        double stdDev = calculateStdDev(data);

        // Set the values in the UI
        txtMean.setText(Double.toString(mean));
        txtMin.setText(Integer.toString(min));
        txtMax.setText(Integer.toString(max));
        txtStdDev.setText(Double.toString(stdDev));

        // Add the data to the table
        for (int i = 0; i < data.length; i++) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[] { Integer.toString(i + 1), Double.toString(data[i]) });
        }

        // Calculate and display the execution time
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1000000.0; // Convert nanoseconds to milliseconds
        lblTime.setText("Execution Time: " + executionTime + " ms");
    }

    // Helper methods for calculating basic statistics
    private double calculateMean(double[] data) {
        double sum = 0.0;
        for (double d : data) {
            sum += d;
        }
        return sum / data.length;
    }

    private int calculateMin(double[] data) {
        int min = Integer.MAX_VALUE;
        for (double d : data) {
            int i = (int) d;
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    private int calculateMax(double[] data) {
        int max = Integer.MIN_VALUE;
        for (double d : data) {
            int i = (int) d;
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    private double calculateStdDev(double[] data) {
        double mean = calculateMean(data);
        double sum = 0.0;
        for (double d : data) {
            sum += (d - mean) * (d - mean);
        }
        return Math.sqrt(sum / data.length);
    }
}
