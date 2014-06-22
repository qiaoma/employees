package edu.calstatela.cs245.qiao.project;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;


public class SearchFrame extends BaseFrame {
	
	public SearchFrame() {

		super(800, 560);
		frame.setLayout(new FlowLayout());	
		JLabel label = new JLabel("You can design your own search query in any of the text area below.");
		JPanel panelLabel = new JPanel();
		panelLabel.add(label);
		frame.add(panelLabel);
		
		panel = new JPanel(new GridLayout(0, 2, 10, 10));
		frame.add(panel);
		
		String display1 = "SELECT * FROM employee WHERE Birth_Date < '1972-06-01' \n" +
				 "AND Job_Status = 'FT' AND Pay_Type = 'S' \nAND Years_Of_Service > '3';";
		
		String display2 = "SELECT * FROM employee WHERE Sex = 'F' \n" +
				"AND Annual_Salary > '40000.00';";
		
		String display3 = "SELECT * FROM employee WHERE Emp_Name in \n" +
				"(SELECT Emp_Name from employee WHERE \n" +
				"Annual_Salary > 100000 GROUP BY \n" +
				"Emp_Name HAVING COUNT(Emp_Name) > 1) \n" +
				"AND Annual_Salary > 100000;";
		
		searchForm(display1);
		searchForm(display2);	
		searchForm(display3);	
		searchForm("");	
	}
	
	public void searchForm(String display){
				
		JTextArea textArea = new JTextArea(display);		
		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JButton searchButton = new JButton("Search");
		
		panel.add(areaScrollPane);
		panel.add(searchButton);
			
		AdvanceSearchListener advanceListener = new AdvanceSearchListener(textArea);
		searchButton.addActionListener(advanceListener);
	}
	
	class AdvanceSearchListener implements ActionListener{
		
		private JTextArea textArea;
		
		public AdvanceSearchListener(JTextArea textArea){
			this.textArea = textArea;
		}
		@Override
		public void actionPerformed(ActionEvent e){
			String query = textArea.getText().replaceAll("\n", "");		
			new DisplayFrame(query);
		}
	}
}

