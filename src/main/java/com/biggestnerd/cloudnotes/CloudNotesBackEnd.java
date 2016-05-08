package com.biggestnerd.cloudnotes;

public class CloudNotesBackEnd
{
	public void sync(String input)
	{
		System.out.println("Syncing: " + input);
	}
	
	public String load()
	{
		System.out.println("Loading");
		return "Test text";
	}
}
