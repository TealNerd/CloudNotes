package com.biggestnerd.cloudnotes;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloudNotesBackEnd
{
	public File notepadFile = new File(System.getProperty("user.home") + "/Desktop", "test.txt");
	private String previousInput ="";
	private Logger log;
	
	public CloudNotesBackEnd(Logger log) 
	{
		this.log = log;
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
