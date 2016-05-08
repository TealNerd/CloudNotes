package com.biggestnerd.cloudnotes;

//TODO: Everything

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class CloudNotes extends JFrame
{
    private JPanel panel;
    private JOptionPane pane = new JOptionPane();
    private JButton exitButton;
    
    public CloudNotes()
    {
    	super("Cloud Notes");
    	
    	setSize(700, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        
        //Code goes here
        
        add(panel);
        setVisible(true);
    }
    
    public static void main(String[] args) { CloudNotes gui = new CloudNotes(); }

}
