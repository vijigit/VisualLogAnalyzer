/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: PropertiesUtil.java
 * Created by: evijaka
 * Author: evijaka
 * Date : Jul 6, 2015
 */
/**
 * 
 */
package com.ericsson.log.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.ericsson.log.listener.LogMonitor;


/**
 * @author evijaka
 *
 */
public class PropertiesUtil {
	private static Properties properties = null;
	  static {

	        InputStream input = null;

	        try {
	        	properties = new Properties();
	            String filename = "config.properties";
	            input = LogMonitor.class.getClassLoader().getResourceAsStream(
	                    filename);
	            if (input == null) {
	                System.out.println("Sorry, unable to find " + filename);
	            }

	            // load a properties file from class path, inside static method
	            properties.load(input);

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
	            if (input != null) {
	                try {
	                    input.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	  
	  public static String getProperty(String propName){
		  return properties.getProperty(propName);
	  }
}
