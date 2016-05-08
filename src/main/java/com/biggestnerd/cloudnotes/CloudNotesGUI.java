package com.biggestnerd.cloudnotes;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.File;

public class CloudNotesGUI extends JFrame
{
	private JPanel panel;
    private JTextArea notepad;
    private JButton syncButton;
    private JButton loadButton;
    
    CloudNotesBackEnd backEnd;
    
    public CloudNotesGUI()
    {
    	super("Cloud Notes");
    	backEnd = new CloudNotesBackEnd();
    	
        backEnd.setFileName((new File(new File(System.getProperty("user.home"), "/Desktop"), "test.txt")).toString());
    	
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
        
        notepad = new JTextArea(20, 30);
        notepad.setEditable(true);

        JPanel subPanel = new JPanel();
        subPanel.add(syncButton);
        subPanel.add(loadButton);
        
        panel.add(subPanel, BorderLayout.NORTH);
        panel.add(notepad, BorderLayout.CENTER);
        
        add(panel);
        setVisible(true);        
    }
    
    public void sync() throws Exception
    {
    	backEnd.sync(notepad.getText());
    }
    
    public void load() throws Exception
    {
    	notepad.setText(backEnd.load());
    }
    
    private void onExit() throws Exception
    {
    	//Check if test has been edited before asking to sync again?
    	System.out.println("Closing");
        if (JOptionPane.showConfirmDialog(null, "Sync Before Exiting?", "Exit?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
        	sync();
       System.exit(0);
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
    
    public static void main(String[] args) { new CloudNotesGUI(); }

}
