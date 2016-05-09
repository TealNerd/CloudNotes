package com.biggestnerd.cloudnotes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CloudNotesBackEnd
{
	public File notepadFile = new File(new File(System.getProperty("user.home"), "/Desktop"), "test.txt");
	private String previousInput ="";
	
	public void sync(String input) throws IOException
	{
		System.out.println("Syncing: " + input);	
		
	    PrintWriter pWriter = new PrintWriter (new FileWriter (notepadFile));
	    pWriter.println(input);
	    pWriter.close();
	}
	
	public String load()throws Exception 
	{
		System.out.println("Loading");

		String input = "";
				
	    Scanner sc = new Scanner (notepadFile);
	    while (sc.hasNextLine())
	    	input += sc.nextLine() + "\n";
	    sc.close();
		
	    previousInput = input;
		return input;
	}
	
	public void setFile(File newFile)
	{
		notepadFile = newFile;
		System.out.println("New File: " + notepadFile.toString());
	}
	
	public String getPreviousInput()
	{
		return previousInput;
	}
}
