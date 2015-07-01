/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: AllLog.java
 * Created by: evijaka
 * Author: evijaka
 * Date : Jul 1, 2015
 */
/**
 * 
 */
package com.ericsson.log.json.reporter;


/**
 * @author evijaka
 *
 */
public class AllLog {
	
	private String date;
	private String priority;
	private String javaClassName;
	private String description;
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	/**
	 * @return the javaClassName
	 */
	public String getJavaClassName() {
		return javaClassName;
	}
	/**
	 * @param javaClassName the javaClassName to set
	 */
	public void setJavaClassName(String javaClassName) {
		this.javaClassName = javaClassName;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AllLog [date=" + date + ", priority=" + priority
				+ ", javaClassName=" + javaClassName + ", description="
				+ description + "]";
	}
	
	
	

}
