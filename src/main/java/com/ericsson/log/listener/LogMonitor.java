package com.ericsson.log.listener;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import com.ericsson.log.util.PropertiesUtil;

public class LogMonitor extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String FOLDER = PropertiesUtil.getProperty(
            "source_directory").toString();
    public static final String DEST_FOLDER = PropertiesUtil.getProperty(
            "destination_directory").toString();
    public static final String POLLING_INTERVAL = PropertiesUtil.getProperty(
            "polling_interval").toString();

    public void init() {
        // The monitor will perform polling on the folder every 5 seconds
        final long pollingInterval = Long.parseLong(POLLING_INTERVAL)*1000;

        File folder = new File(FOLDER);

        if (!folder.exists()) {
            // Test to see if monitored folder exists
            throw new RuntimeException("Directory not found: " + FOLDER);
        }

        FileAlterationObserver observer = new FileAlterationObserver(folder);
        FileAlterationMonitor monitor = new FileAlterationMonitor(
                pollingInterval);
        FileAlterationListener listener = new FileAlterationListenerAdaptor() {
            // Is triggered when a file is created in the monitored folder
            @Override
            public void onFileCreate(File file) {
                try {
                    // "file" is the reference to the newly created file
                    System.out.println("File created: "
                            + file.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }

            // Is triggered when a file is deleted from the monitored folder
            @Override
            public void onFileDelete(File file) {
                try {
                    // "file" is the reference to the removed file
                    System.out.println("File removed: "
                            + file.getCanonicalPath());
                    // "file" does not exists anymore in the location
                    System.out.println("File still exists in location: "
                            + file.exists());
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }

            @Override
            public void onFileChange(final File file) {
                try {
                    try {
                        FileUtils.copyFile(new File(file.getCanonicalPath()),
                                new File(DEST_FOLDER + "/logging.log"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("File changed: "
                            + file.getCanonicalPath());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };

        observer.addListener(listener);
        monitor.addObserver(observer);
        try {
            monitor.start();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

  
}