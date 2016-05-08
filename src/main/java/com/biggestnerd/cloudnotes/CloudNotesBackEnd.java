package com.biggestnerd.cloudnotes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CloudNotesBackEnd
{
	public String filename;
	
	public void sync(String input) throws IOException
	{
		System.out.println("Syncing: " + input);	
		
	    PrintWriter pWriter = new PrintWriter (new FileWriter (new File (filename)));
	    pWriter.println(input);
	    pWriter.close();
	}
	
	public String load()throws Exception 
	{
		System.out.println("Loading");

		String input = "";
				
	    Scanner sc = new Scanner (new File (filename));
	    while (sc.hasNextLine())
	    	input += sc.nextLine() + "\n";
	    sc.close();
		
		return input;
	}
	
	public void setFileName(String newFileName)
	{
		filename = newFileName;
	}
}
