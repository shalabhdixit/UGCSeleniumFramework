package com.cigniti.utilities;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
//import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
//import java.util.Set;
//import java.io.IOException;

import com.cigniti.accelerators.ActionEngine;
//import com.thoughtworks.selenium.webdriven.commands.IsAlertPresent;
//import com.ugc.objectRepository.MIGNG_NewQuote;

public class UploadFileRobot extends ActionEngine {
	/**
     * This method will set any parameter string to the system's clipboard.
     */
	public static void setClipboardData(String string) {
			//	StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}

	public static String getClipboardData() throws Exception {
		//	StringSelection is a class that can be used for copy and paste operations.
//	   StringSelection stringSelection = new StringSelection(string);
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard(); 
	   return cb.getData(DataFlavor.stringFlavor).toString();
	}

	public static void uploadFile(String fileLocation) {
        try {
        	//	Setting Clip board with file location
            setClipboardData(fileLocation);
            //	Native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(500);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            Thread.sleep(500);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            Thread.sleep(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
          
/*            ActionEngine ae = new ActionEngine();
            if(ae.isAlertPresent()){
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);                
                ae.switchToMainElement();
            }*/
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}