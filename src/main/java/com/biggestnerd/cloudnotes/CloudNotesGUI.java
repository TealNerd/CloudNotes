package com.biggestnerd.cloudnotes;

//TODO: Everything

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class CloudNotesGUI extends JFrame
{
    private JPanel panel;
    private JOptionPane pane = new JOptionPane();
    private JTextField notepad;
    private JButton syncButton;
    private JButton loadButton;
    private JButton exitButton;
    
    CloudNotesBackEnd backEnd = new CloudNotesBackEnd();
    
    public CloudNotesGUI()
    {
    	super("Cloud Notes");
    	
    	setSize(700, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        
        //Code goes here
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new exitListener());
       
        syncButton = new JButton("Sync");
        syncButton.addActionListener(new syncListener());
        
        loadButton = new JButton("Load");
        loadButton.addActionListener(new loadListener());
        
        //Fix size of text field
        notepad = new JTextField(20);
       
        panel.add(syncButton);
        panel.add(loadButton);
        panel.add(exitButton);
        panel.add(notepad);
        
        add(panel);
        setVisible(true);
    }
    
    private class exitListener implements ActionListener 
	{ 
    	public void actionPerformed(ActionEvent e) 
		{      
            System.exit(0); 
        } 
    }
    
    private class syncListener implements ActionListener 
	{ 
    	public void actionPerformed(ActionEvent e) 
		{      
			JOptionPane.showMessageDialog(pane, "Sync");
			backEnd.sync();
        } 
    }
    
    private class loadListener implements ActionListener 
	{ 
    	public void actionPerformed(ActionEvent e) 
		{      
			JOptionPane.showMessageDialog(pane, "Load");
			backEnd.load();
        } 
    }
    
    public static void main(String[] args) { CloudNotesGUI gui = new CloudNotesGUI(); }

}
