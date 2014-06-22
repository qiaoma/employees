package edu.calstatela.cs245.qiao.project;


import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.*;

public class DisplayFrame extends BaseFrame {
	
	public DisplayFrame(String query){
		
		super(710, 550);		
		frame.setLocation(400, 100);	
		panel = new JPanel(new BorderLayout());
		frame.add(panel);
		try{
			String[][] data = null;
				
			try {
				DBConnection.openConnection();
				data = DBConnection.getQueryData(query);
				DBConnection.closeResultSet();
				DBConnection.closeStatementConnection();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e, "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			
			String[] columnNames = new String[data[0].length];
			
				if (columnNames.length == 9) {
					columnNames[0] = "Emp_ID";
					columnNames[1] = "Emp_Name";
					columnNames[2] = "Hire_Date";
					columnNames[3] = "Birth_Date";
					columnNames[4] = "Sex";
					columnNames[5] = "Job_Status";
					columnNames[6] = "Pay_Type";
					columnNames[7] = "Annual_Salary";
					columnNames[8] = "Year_Of_Service";
				}else{
					for(int i = 0; i < columnNames.length; i++){
						columnNames[i] = " ";
					}
				}
				
				JTable table = new JTable(data, columnNames);
				JScrollPane scrollPane = new JScrollPane(table);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				panel.add(scrollPane, BorderLayout.CENTER);
		}catch(Exception e){
			
		}
		
	/*	
		panel.setLayout(new BorderLayout());
		panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		panel.add(table, BorderLayout.CENTER);
	*/
	}
}
