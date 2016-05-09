package com.biggestnerd.cloudnotes;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CloudNotesGUI extends JFrame
{
	static CloudNotesGUI frame;
	
	private JPanel panel;
    private JTextArea notepad;
    private JButton syncButton;
    private JButton loadButton;
    private JButton fileSelectButton;
    private JButton saveLocationButton;
    
    private CloudNotesBackEnd backEnd;
    
    private Logger log;
    
    public CloudNotesGUI()
    {
    	super("Cloud Notes");
    	log = Logger.getLogger("Cloud Notes");
    	backEnd = new CloudNotesBackEnd(log);
        	
        setSize(420, 450);
        
        addWindowListener(new WindowAdapter() 
        {
        	public void windowClosing(WindowEvent e) 
        	{
        		onExit(); 
        	}
        });
                
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        syncButton = new JButton("Sync");
        syncButton.addActionListener(new SyncListener());
        
        loadButton = new JButton("Load");
        loadButton.addActionListener(new LoadListener());
        
        fileSelectButton = new JButton("Select File");
        fileSelectButton.addActionListener(new FileSelectListener());

        saveLocationButton = new JButton("Save Location");
        saveLocationButton.addActionListener(new SaveLocationListener());
        
        notepad = new JTextArea(20, 30);
        notepad.setLineWrap(true);
        notepad.setWrapStyleWord(true);

        JPanel subPanel = new JPanel();
        subPanel.add(syncButton);
        subPanel.add(loadButton);
        subPanel.add(fileSelectButton);
        subPanel.add(saveLocationButton);
        
        panel.add(subPanel, BorderLayout.NORTH);
        panel.add(notepad, BorderLayout.CENTER);
        
        add(panel);
        setVisible(true);    
        
        if (backEnd.loadSettings())
        	load();
    }
    
    public void sync()
    {
    	if (backEnd.getFileName() != null)
    		backEnd.sync(notepad.getText());
    	else
            JOptionPane.showMessageDialog(frame, "No text file selected");
    }
    
    public void load()
    {
    	log.info("File name: " + backEnd.getFileName());
    	if (backEnd.getFileName() != null)
    	{
	    	if (notepad.getText().length() == 0)
	    		notepad.setText(backEnd.load());
	    	else if (!backEnd.getPreviousInput().equals(notepad.getText()))
	    	{
	    		if (JOptionPane.showConfirmDialog(null, "Overwrite Existing Text?", "Exit?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
	    			notepad.setText(backEnd.load());
	    	}
    	}
    	else
            JOptionPane.showMessageDialog(frame, "No text file selected");
    }
    
    private void onExit() 
    {
    	log.info("Closing");
    	
    	if (!backEnd.getPreviousInput().equals(notepad.getText()) && JOptionPane.showConfirmDialog(null, "Sync Before Exiting?", "Exit?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
    		sync();
       System.exit(0);
    }

    private class FileSelectListener implements ActionListener  
    { 
    	public void actionPerformed(ActionEvent e)  
    	{
    		FileDialog fd = new FileDialog(frame, "Choose a TXT File to Load", FileDialog.LOAD);
    		fd.setDirectory("C:\\"); 		
    		fd.setFile("*.txt");
    		fd.setVisible(true);

    		boolean validInput = false;
    		
    		while(!validInput && fd.getFiles().length > 0)
    		{
	    		if (fd.getFile().contains(".txt"))
    			{
    				validInput = true;
    				backEnd.setFile(fd.getFiles()[0]);
	        		load();
    			}
    			else
    			{
    				fd.setVisible(true);
    			}
    		}
    	}
    }

    private class SyncListener implements ActionListener 
    {
    	public void actionPerformed(ActionEvent e)  
    	{
    		sync();
    	} 
    }
    
    private class SaveLocationListener implements ActionListener 
    {
    	public void actionPerformed(ActionEvent e)  
    	{
    		backEnd.saveSettings();
    	} 
    }
    
    private class LoadListener implements ActionListener  
    { 
    	public void actionPerformed(ActionEvent event) 
    	{
    		load();
    	}
    }
    
    public static void main(String[] args) { frame = new CloudNotesGUI(); }

}