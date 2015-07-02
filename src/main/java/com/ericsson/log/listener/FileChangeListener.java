/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: FileChangeListener.java
 * Created by: evijaka
 * Author: evijaka
 * Date : Jun 30, 2015
 */
/**
 * 
 */
package com.ericsson.log.listener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * @author evijaka
 *
 */
public class FileChangeListener {
	
	public static final String fileName = "test.txt";
    public static final String dirName = "/home/eorsvvy/Desktop/FA/test";

    public void fileListener() {           
        try {
            final Path myDir = Paths.get(dirName); 
            final WatchService watcher = myDir.getFileSystem().newWatchService();
            myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_MODIFY);
            while(true){
                final WatchKey watckKey = watcher.take();

                for (WatchEvent<?> event : watckKey.pollEvents()) {
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                    new FileChangedListener() {                       
                        public void fileChanged(Path fileName) {
                            if (fileName.toString().endsWith(FileChangeListener.fileName)) {
                                System.out.println("My file has changed");
                            }                           
                        }
                    }.fileChanged(fileName);
                }
                boolean valid = watckKey.reset();
                if (!valid) {
                    System.out.println("Key has been unregisterede");
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

    }

    public static void main(String[] args) {
        new FileChangeListener().fileListener();
    }


}
