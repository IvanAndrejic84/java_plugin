import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import java.util.Arrays;

public class Activator extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "mytableexample"; //$NON-NLS-1$

    // The shared instance
    private static Activator plugin;

    // Basic Stats
    private static double mean;
    private static int min;
    private static int max;

    // Code Execution Time
    private static long executionTime;

    /**
     * The constructor
     */
    public Activator() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;

        // Generate random data
        int[] data = new int[1000];
        for (int i = 0; i < data.length; i++) {
            data[i] = (int) (Math.random() * 100);
        }

        // Start timing code execution
        long startTime = System.currentTimeMillis();

        // Calculate basic statistics
        mean = Arrays.stream(data).average().orElse(0.0);
        min = Arrays.stream(data).min().orElse(0);
        max = Arrays.stream(data).max().orElse(0);

        // End timing code execution
        executionTime = System.currentTimeMillis() - startTime;

        // Create UI to display basic stats and execution time
        Display.getDefault().asyncExec(() -> {
            Shell shell = new Shell();
            shell.setText("Basic Stats");

            GridLayout layout = new GridLayout(2, false);
            shell.setLayout(layout);

            Label meanLabel = new Label(shell, SWT.NONE);
            meanLabel.setText("Mean: " + mean);

            Label minLabel = new Label(shell, SWT.NONE);
            minLabel.setText("Min: " + min);

            Label maxLabel = new Label(shell, SWT.NONE);
            maxLabel.setText("Max: " + max);

            Label timeLabel = new Label(shell, SWT.NONE);
            timeLabel.setText("Execution Time: " + executionTime + " ms");

            // Create table to display data
            Table table = new Table(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
            table.setLinesVisible(true);
            table.setHeaderVisible(true);

            GridData dataGrid = new GridData(SWT.FILL, SWT.FILL, true, true);
            dataGrid.horizontalSpan = 2;
            table.setLayoutData(dataGrid);

            TableColumn column1 = new TableColumn(table, SWT.NONE);
            column1.setText("Value");

            for (int i = 0; i < data.length; i++) {
                TableItem item = new TableItem(table, SWT.NONE);
                item.setText(new String[] { Integer.toString(data[i]) });
            }

            // Open UI shell
            shell.pack();
            shell.open();
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
    System.out.println("Stopping Basic Stats Plugin");

    // Stop the timer
    timer.cancel();
    timer.purge();

    // Dispose the display
    display.dispose();

    // Shutdown the executor
    executor.shutdownNow();

    super.stop(context);
}
       
