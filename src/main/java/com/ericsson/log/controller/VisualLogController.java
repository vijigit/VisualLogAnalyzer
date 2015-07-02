/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: VisualLogController.java
 * Created by: evijaka
 * Author: evijaka
 * Date : Jul 1, 2015
 */
/**
 * 
 */
package com.ericsson.log.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ericsson.log.json.reporter.AllLog;
import com.ericsson.log.json.reporter.LogTextAsJSON;


/**
 * @author evijaka
 *
 */
@Controller
public class VisualLogController {
	@RequestMapping(value = "logAnalyzer", method = RequestMethod.GET)
	public ModelAndView getLogData() {

		LogTextAsJSON asJSON = new LogTextAsJSON();
		List<AllLog> allLogList = asJSON.convertToJSON("/home/evijaka/My_Workspace/IAMV2/iamv2/logging.log");
		//return back to index.jsp
		ModelAndView model = new ModelAndView("welcome");
		model.addObject("allLogList", allLogList);

		return model;
		/*	String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		return new ModelAndView("welcome", "message", message);*/
	}
}
