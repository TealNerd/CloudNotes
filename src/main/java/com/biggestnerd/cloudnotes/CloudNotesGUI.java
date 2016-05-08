package com.biggestnerd.cloudnotes;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class CloudNotesGUI extends JFrame
{
	private JPanel panel;
    private JTextArea notepad;
    private JButton syncButton;
    private JButton loadButton;
    
    CloudNotesBackEnd backEnd = new CloudNotesBackEnd();
    
    public CloudNotesGUI()
    {
    	super("Cloud Notes");
    	
        if (System.getProperty("os.name").startsWith("Mac OS"))
        	setSize(350, 390);
        else 
        	setSize(350, 390);
        
        addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent e) { onExit(); }});
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        //Code goes here
        syncButton = new JButton("Sync");
        syncButton.addActionListener(new syncListener());
        
        loadButton = new JButton("Load");
        loadButton.addActionListener(new loadListener());
        
        notepad = new JTextArea(20, 30);
        notepad.setEditable(true);

        JPanel subPanel = new JPanel();
        subPanel.add(syncButton);
        subPanel.add(loadButton);
        
        panel.add(subPanel,  BorderLayout.NORTH);
        panel.add(notepad, BorderLayout.CENTER);
        
        add(panel);
        setVisible(true);
    }
    
    public void sync()
    {
    	backEnd.sync(notepad.getText());
    }
    
    public void load()
    {
    	notepad.setText(backEnd.load());
    }
    
    private void onExit()
    {
    	System.out.println("Closing");
        if (JOptionPane.showConfirmDialog(null, "Sync Before Exiting?", "Exit?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
        	sync();
       System.exit(0);
    }

    private class syncListener implements ActionListener  { public void actionPerformed(ActionEvent e)  { sync(); } }
    
    private class loadListener implements ActionListener  {  public void actionPerformed(ActionEvent e) { load(); } }
    
    public static void main(String[] args) { new CloudNotesGUI(); }

}
