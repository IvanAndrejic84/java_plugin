package org.eclipse.tracecompass.incubator.otf2.executiongraph;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.eclipse.tracecompass.incubator.otf2.executiongraph"; //$NON-NLS-1$

    // The shared instance
    private static Activator plugin;

    /**
     * The constructor
     */
    public Activator() {
    }

    @Override
    public void start(BundleContext context) throws Exception {
        long startTime = System.currentTimeMillis();
        super.start(context);
        plugin = this;
        long endTime = System.currentTimeMillis();
        System.out.println("Activator start() method execution time: " + (endTime - startTime) + " ms");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        long startTime = System.currentTimeMillis();
        plugin = null;
        super.stop(context);
        long endTime = System.currentTimeMillis();
        System.out.println("Activator stop() method execution time: " + (endTime - startTime) + " ms");
    }

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }

}