/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: LogTextAsJSON.java
 * Created by: evijaka
 * Author: evijaka
 * Date : Jun 30, 2015
 */
/**
 * 
 */
package com.ericsson.log.json.reporter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @author evijaka
 *
 */
public class LogTextAsJSON {
	private static String LOG_REGEX_PATTERN = "^(.*?) (INFO|DEBUG|ERROR)\\s(.+)-\\s(.+)";
	private static Logger LOGGER = Logger.getLogger(LogTextAsJSON.class);
	public List<AllLog> convertToJSON(String inputTextFile) {
		BufferedReader br  = null;
		List<AllLog> allLogList =null;
		try {
			allLogList = new ArrayList<AllLog>();
			Pattern r = Pattern.compile(LOG_REGEX_PATTERN);
			br = new BufferedReader(new FileReader(inputTextFile));
			for(String line; (line = br.readLine()) != null; ) {
				Matcher m = r.matcher(line);
				if (m.find()) {
					AllLog allLog = new AllLog();
					allLog.setDate(m.group(1));
					allLog.setPriority(m.group(2));
					allLog.setJavaClassName(m.group(3));
					allLog.setDescription(m.group(4));
					allLogList.add(allLog);
				} else {
					LOGGER.trace("NO MATCH" + line);
				}
			}
			br.close();

		}catch(Exception e){
			e.printStackTrace();
		}

		return allLogList;
	}

	public static void main(String[] args) {
		LogTextAsJSON asJSON = new LogTextAsJSON();
		List<AllLog> allLogList = asJSON.convertToJSON("/home/evijaka/My_Workspace/IAMV2/iamv2/logging.log");
		for(AllLog log : allLogList){
			LOGGER.info(log.toString());
		}
		Gson gson = new GsonBuilder()
				.create();
		String s1 = gson.toJson(allLogList);
		LOGGER.info("The JSON String is  ===> " + s1);
	}

}
