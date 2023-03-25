public class StatsVisualizer {

    private static final String INPUT_TRACE_PATH = "/path/to/trace";
    private static final String OUTPUT_FOLDER_PATH = "/path/to/output/folder";
    private static final String STAT_NAME = "myStatName";

    public static void main(String[] args) {
        // Load the trace
        ITmfTrace trace = TmfTraceManager.getInstance().getTrace(INPUT_TRACE_PATH);
        
        // Get the stat attribute
        ITmfStateSystem ss = TmfStateSystemAnalysisModule.getStateSystem(trace, false);
        int attribute = ss.getQuarkAbsolute(STAT_NAME);

        // Create a new output folder
        File outputFolder = new File(OUTPUT_FOLDER_PATH);
        outputFolder.mkdirs();

        // Create a new XY chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Code Stats Visualizer").xAxisTitle("Time (ns)").yAxisTitle("Value").build();

        // Create a new series for the chart
        XYSeries series = chart.addSeries(STAT_NAME, null, null);

        // Create a new listener to populate the chart
        ITmfTraceUpdatedSignal listener = new ITmfTraceUpdatedSignal() {

            @Override
            public void traceUpdated(TmfTraceUpdatedSignal signal) {
                long start = signal.getStartTime().toNanos();
                long end = signal.getEndTime().toNanos();
                long resolution = (end - start) / chart.getWidth();

                double[] xData = new double[chart.getWidth()];
                double[] yData = new double[chart.getWidth()];

                for (int i = 0; i < chart.getWidth(); i++) {
                    long time = start + i * resolution;
                    try {
                        long value = ss.querySingleState(time, attribute).getStateValue().unboxLong();
                        xData[i] = time;
                        yData[i] = value;
                    } catch (AttributeNotFoundException | StateSystemDisposedException | TimeRangeException e) {
                        // Handle the exception
                    }
                }

                series.replaceData(xData, yData);
                BitmapEncoder.saveBitmap(chart, outputFolder.getAbsolutePath() + "/chart.png", BitmapFormat.PNG);
            }
        };

        // Register the listener and start the trace
        trace.registerUpdatedSignal(listener);
        trace.start();
    }
}