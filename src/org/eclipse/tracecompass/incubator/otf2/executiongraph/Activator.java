import org.eclipse.tracecompass.analysis.timing.core.segmentstore.statistics.SegmentStoreStatistics;
import org.eclipse.tracecompass.analysis.timing.core.segmentstore.statistics.SegmentStoreStatisticsAnalysis;
import org.eclipse.tracecompass.tmf.core.dataprovider.DataProviderManager;
import org.eclipse.tracecompass.tmf.core.dataprovider.IDataProvider;
import org.eclipse.tracecompass.tmf.core.dataprovider.IDataProviderFactory;
import org.eclipse.tracecompass.tmf.core.exceptions.TmfAnalysisException;
import org.eclipse.tracecompass.tmf.core.exceptions.TmfTraceException;
import org.eclipse.tracecompass.tmf.core.signal.TmfSignalHandler;
import org.eclipse.tracecompass.tmf.core.signal.TmfTraceOpenedSignal;
import org.eclipse.tracecompass.tmf.core.trace.ITmfTrace;
import org.eclipse.tracecompass.tmf.core.trace.TmfTraceManager;
import org.eclipse.tracecompass.tmf.ui.views.timegraph.AbstractTimeGraphView;
import org.eclipse.tracecompass.tmf.ui.views.timegraph.ITimeGraphDataProvider;
import org.eclipse.tracecompass.tmf.ui.views.timegraph.TimeGraphContentProvider;
import org.eclipse.tracecompass.tmf.ui.views.timegraph.TimeGraphPresentationProvider;
import org.eclipse.tracecompass.tmf.ui.views.timegraph.TimeGraphViewer;
import org.eclipse.tracecompass.tmf.ui.views.timegraph.TmfTimeGraphComposite;
import org.eclipse.tracecompass.tmf.ui.views.timegraph.TmfTimeGraphViewer;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CodeStatsVisualizer {
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);

        TmfTimeGraphViewer viewer = new TmfTimeGraphViewer(shell, SWT.NONE);
        viewer.setTreeColumns(ITimeGraphDataProvider.NAME_COLUMN, ITimeGraphDataProvider.VALUE_COLUMN);
        viewer.setTreeLabelProvider(new TimeGraphPresentationProvider());
        viewer.setTimeGraphContentProvider(new TimeGraphContentProvider());
        viewer.setTimeGraphProvider(new SegmentStoreStatisticsPresentationProvider());

        String tracePath = "/path/to/trace/file";
        File traceFile = new File(tracePath);

        ITmfTrace trace = null;
        try {
            trace = TmfTraceManager.getInstance().getTrace(traceFile.toURI().toString());
        } catch (TmfTraceException e) {
            e.printStackTrace();
        }

        if (trace != null) {
            try {
                trace.traceOpened(new TmfTraceOpenedSignal(CodeStatsVisualizer.this, trace, null));
            } catch (TmfAnalysisException e) {
                e.printStackTrace();
            }
            trace.scheduleAnalysis(new SegmentStoreStatisticsAnalysis());
            DataProviderManager.addDataProvider(trace, new SegmentStoreStatisticsDataProviderFactory());
            viewer.setInput(viewer);
            shell.open();
            while (!shell.isDisposed()) {
                if (!display.readAndDispatch()) {
                    display.sleep();
                }
            }
            display.dispose();
        }
    }

    private static class SegmentStoreStatisticsPresentationProvider extends TimeGraphPresentationProvider {
        @Override
        public String getEventName(ITimeEvent event) {
            return event.getLabel();
        }

        @Override
        public String getEventDescription(ITimeEvent event) {
            return event.getLabel();
        }
    }

   public class SegmentStoreStatisticsDataProviderFactory implements IDataProviderFactory {

    @Override
    public String getName() {
        return "My Segment Store Statistics Data Provider Factory";
    }

    @Override
    public String getId() {
        return "my.segment.store.statistics.data.provider.factory";
    }

    @Override
    public boolean supports(ITmfTrace trace) {
        // Only support traces that have segment store statistics
        return trace.getAdapter(ISegmentStoreStatistics.class) != null;
    }

    @Override
    public AbstractSegmentStoreStatisticsDataProvider createProvider(ITmfTrace trace, SegmentStoreStatisticsDataProviderInput input) {
        ISegmentStoreStatistics segmentStoreStats = trace.getAdapter(ISegmentStoreStatistics.class);
        if (segmentStoreStats != null) {
            return new MySegmentStoreStatisticsDataProvider(trace, segmentStoreStats, input);
        }
        return null;
    }

    @Override
    public IDataProviderRoot createRoot(IDataProviderManager manager) {
        return new SegmentStoreStatisticsDataProviderRoot(manager, getName());
    }

    public static void register() {
        DataProviderManager.getInstance().addFactory(new SegmentStoreStatisticsDataProviderFactory());
    }

}
