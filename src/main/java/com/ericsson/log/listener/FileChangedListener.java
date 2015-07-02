/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: FileChangedListener.java
 * Created by: evijaka
 * Author: evijaka
 * Date : Jul 2, 2015
 */
/**
 * 
 */
package com.ericsson.log.listener;

import java.nio.file.Path;


/**
 * @author evijaka
 *
 */
public interface FileChangedListener {
	public void fileChanged(Path fileName) ;

}
