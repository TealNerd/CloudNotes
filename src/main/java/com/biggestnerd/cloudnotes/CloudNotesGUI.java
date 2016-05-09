package com.biggestnerd.cloudnotes;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.*;

public class CloudNotesGUI extends JFrame
{
	static CloudNotesGUI frame;
	
	private JPanel panel;
    private JTextArea notepad;
    private JButton syncButton;
    private JButton loadButton;
    private JButton fileSelectButton;
    
    CloudNotesBackEnd backEnd;
    
    public CloudNotesGUI()
    {
    	super("Cloud Notes");
    	backEnd = new CloudNotesBackEnd();
    	    	
        if (System.getProperty("os.name").startsWith("Mac OS"))
        	setSize(350, 390);
        else 
        	setSize(350, 390);
        
        addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent e) { try { onExit(); } catch (Exception e1) { e1.printStackTrace(); } }});
                
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        syncButton = new JButton("Sync");
        syncButton.addActionListener(new syncListener());
        
        loadButton = new JButton("Load");
        loadButton.addActionListener(new loadListener());
        
        fileSelectButton = new JButton("Select FIle");
        fileSelectButton.addActionListener(new fileSelectListener());

        notepad = new JTextArea(20, 30);
        notepad.setLineWrap(true);
        notepad.setWrapStyleWord(true);

        JPanel subPanel = new JPanel();
        subPanel.add(syncButton);
        subPanel.add(loadButton);
        subPanel.add(fileSelectButton);
        
        panel.add(subPanel, BorderLayout.NORTH);
        panel.add(notepad, BorderLayout.CENTER);
        
        add(panel);
        setVisible(true);        
        
        System.out.println(":" + notepad.getText() + ":" );
    }
    
    public void sync() throws Exception
    {
    	backEnd.sync(notepad.getText());
    }
    
    public void load() throws Exception
    {
    	if (notepad.getText().length() == 0)
    		notepad.setText(backEnd.load());
    	else
    	{
    		if (JOptionPane.showConfirmDialog(null, "Overwrite Existing Text?", "Exit?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
    			notepad.setText(backEnd.load());
    	}
    }
    
    private void onExit() throws Exception
    {
    	System.out.println("Closing");
    	
    	if (!backEnd.getPreviousInput().equals(notepad.getText()) && JOptionPane.showConfirmDialog(null, "Sync Before Exiting?", "Exit?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
    		sync();
       System.exit(0);
    }

    private class fileSelectListener implements ActionListener  
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
	        		try { load();} catch (Exception e1) { e1.printStackTrace(); }
    			}
    			else
    				fd.setVisible(true);
    		}
    	}
    }
    
    private class syncListener implements ActionListener  { public void actionPerformed(ActionEvent e)  { try {
		sync();
	} catch (Exception e1) {
		e1.printStackTrace();
	} } }
    
    private class loadListener implements ActionListener  {  public void actionPerformed(ActionEvent e) { try {
		load();
	} catch (Exception e1) {
		e1.printStackTrace();
	} } }
    
    public static void main(String[] args) { frame = new CloudNotesGUI(); }

}