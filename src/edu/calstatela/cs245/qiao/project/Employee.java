/* Author: Qiao Ma
 * Lecturer: Parvaneh Ghaforyfard
 * Course: CS245
 * Date: 06/05/2013
 * Program description: This program provide a GUI user interface to let user 
 * 						add, delete, modify, search, and display the records.
*/
package edu.calstatela.cs245.qiao.project;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Employee {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Employee");	
		frame.setSize(600,400);
		frame.setVisible(true);
		frame.setLocation(200, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		JButton addButton = new JButton("Add an Employee");
		JButton smdButton = new JButton("Search Modify Delete by ID");
		JButton searchButton = new JButton("Advance Search");
		JButton displayButton = new JButton("Display Employee List");
		JButton closeButton = new JButton("Close");
		
		JPanel panel = new JPanel(new GridLayout(0, 2, 20, 20));
	
		panel.add(addButton);
		panel.add(smdButton);
		panel.add(searchButton);
		panel.add(displayButton);
		panel.add(closeButton);
		
		frame.add(panel);
		
		AddListenerClass addListener = new AddListenerClass();
		addButton.addActionListener(addListener);
		
		SmdListenerClass smdListener = new SmdListenerClass();
		smdButton.addActionListener(smdListener);
		
		SearchListenerClass searchListener = new SearchListenerClass();
		searchButton.addActionListener(searchListener);
		
		DisplayListenerClass displayListener = new DisplayListenerClass();
		displayButton.addActionListener(displayListener);
		
		CloseListenerClass closeListener = new CloseListenerClass();
		closeButton.addActionListener(closeListener);
	}
}

class AddListenerClass implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		new AddFrame();
	}
}

class SmdListenerClass implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		new SMDFrame();
	}
}

class SearchListenerClass implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e){
		new SearchFrame();
	}
}

class DisplayListenerClass implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		new DisplayFrame("SELECT * FROM employee;");
	}
}

class CloseListenerClass implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
