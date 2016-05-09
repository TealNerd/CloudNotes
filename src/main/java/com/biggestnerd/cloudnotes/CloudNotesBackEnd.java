package com.biggestnerd.cloudnotes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloudNotesBackEnd
{
	public File notepadFile;
	private String previousInput = "";
	private Logger log;
	
	public CloudNotesBackEnd(Logger log) 
	{
		this.log = log;
	}
	
	public boolean loadSettings()
	{
    	log.log(Level.INFO, "Loading settings...");
			File yourFile = new File(System.getProperty("user.home") + "/.cloudnotes", "CloudNotes.properties"); 
	
			if(yourFile.exists()) 
			{
				log.info("File exists");
				FileInputStream in;
				try {
					in = new FileInputStream(yourFile);
					Properties mySettings = new Properties();
					mySettings.load(in);
					notepadFile = new File(mySettings.getProperty("NotesPath"));
				} catch (Exception ex) 
				{
			    	log.log(Level.INFO, "Failed to load", ex);
				}
		    	
		    	return true;
			}
	
		return false;
	}
	
	public void saveSettings()
	{
		log.info("Saving settings");	
		File yourFile = new File(System.getProperty("user.home") + "/.cloudnotes", "CloudNotes.properties");

		if(!yourFile.exists()) 
		{
			try 
			{
				if (!yourFile.getParentFile().exists())
					yourFile.getParentFile().mkdirs();			
				if (!yourFile.exists())
					yourFile.createNewFile();					
			}	
		    catch (Exception ex) 
		    {
		    	log.log(Level.SEVERE, "Failed to create new file", ex);
		    } 
		}
		
		try
		{
			FileOutputStream oFile = new FileOutputStream(yourFile, false); 
			Properties mySettings = new Properties();
			mySettings.setProperty("NotesPath", notepadFile.toString());
			mySettings.store(oFile, null);
			oFile.close();
		}	
	    catch (Exception ex) 
	    {
	    	log.log(Level.SEVERE, "Failed to save", ex);
	    } 		
    	
	}
	
	public void sync(String input)
	{
		log.info("Syncing: " + input);	
		
	    try 
	    {
	    	PrintWriter pWriter = new PrintWriter (new FileWriter (notepadFile));
		    pWriter.println(input);
		    pWriter.close();
	    }
	    catch (Exception ex) 
	    {
	    	log.log(Level.SEVERE, "Failed to sync!", ex);
	    }
	}
	
	public String load()
	{
		log.info("Loading");

		String input = "";
				
	    try 
	    {
	    	Scanner sc = new Scanner (notepadFile);
		    while (sc.hasNextLine())
		    	input += sc.nextLine() + "\n";
		    sc.close();
			
		    previousInput = input;
	    }
	    catch (Exception ex)
	    {
	    	log.log(Level.SEVERE, "Failed to load!", ex);
	    }
		return input;
	}
	
	public void setFile(File newFile)
	{
		notepadFile = newFile;
		log.info("New File: " + notepadFile.toString());
	}
	
	public String getPreviousInput()
	{
		return previousInput;
	}
}
