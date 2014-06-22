package edu.calstatela.cs245.qiao.project;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class SMDFrame extends BaseFrame {
	public SMDFrame() {

		super(500, 450);

		setFillForm();
		
		JButton jbtSearch = new JButton("Search by ID");
		panel.add(jbtSearch);

		JButton jbtModify = new JButton("Modify");
		panel.add(jbtModify);

		JButton jbtDelete = new JButton("Delete");
		panel.add(jbtDelete);

		SearchListener searchListener = new SearchListener();
		jbtSearch.addActionListener(searchListener);

		DeleteListener deleteListener = new DeleteListener();
		jbtDelete.addActionListener(deleteListener);

		UpdateListener updateListener = new UpdateListener();
		jbtModify.addActionListener(updateListener);
	}

	// ---------------------- SearchListener ----------------------------------------
	class SearchListener implements ActionListener {
		
		private String id, lastName, sex, birthDate, hireDate, jobStatus, payType, salary, yearS;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			id = jtfId.getText();
			try {
				DBConnection.openConnection();
				ResultSet rs = DBConnection.searchID(id);
				if (rs.next()) {
					lastName = rs.getString(2);
					sex = rs.getString(5);
					birthDate = rs.getString(4);
					hireDate = rs.getString(3);
					jobStatus = rs.getString(6);
					payType = rs.getString(7);
					salary = rs.getString(8);
					yearS = rs.getString(9);
					
					jtfLastName.setText(lastName);
					jtfBirthDate.setText(birthDate);
					jtfHireDate.setText(hireDate);
					jtfSalary.setText(salary);
					jtfYearS.setText(yearS);
					
					if(sex.equals("M")){
						jcboSex.setSelectedIndex(0);
					}else{
						jcboSex.setSelectedIndex(1);
					}	
					jcboJobStatus.setSelectedItem(jobStatus);
					jcboPayType.setSelectedItem(payType);
					
				} else {
					if(id.equals("")){
						JOptionPane.showMessageDialog(null,
								"Please enter Employee ID for search.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null,
							"Record does not exist.", "Error",
							JOptionPane.ERROR_MESSAGE);
					}				
				}
				DBConnection.closeResultSet();
				DBConnection.closeStatementConnection();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1, "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// ------------------------DeleteListener------------------------------------
	class DeleteListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = jtfId.getText();
			
			try {
				DBConnection.openConnection();
				ResultSet rs = DBConnection.searchID(id);
				if (rs.next()) {
					int reply = JOptionPane.showConfirmDialog(null, "Dou you want to delete the record?", 
							"Delete Confirmation", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION){
						DBConnection.deleteRow(id);
						
						jtfId.setText(null);
						jtfLastName.setText(null);
						jcboSex.setSelectedIndex(0);
						jtfBirthDate.setText("yyyy/mm/dd");
						jtfHireDate.setText("yyyy/mm/dd");
						jcboJobStatus.setSelectedIndex(0);
						jcboPayType.setSelectedIndex(0);
						jtfSalary.setText(null);
						jtfYearS.setText(null);
						
						JOptionPane.showMessageDialog(null, "Record deleted.", 
							"Delete", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} else {
					if(id.equals("")){
						JOptionPane.showMessageDialog(null,
								"Please enter Employee ID.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null,
							"Record does not exist.", "Error",
							JOptionPane.ERROR_MESSAGE);
					}				
				}
				DBConnection.closeResultSet();
				DBConnection.closeStatementConnection();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1, "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//-------------------------UpdateListener-------------------------------------
	class UpdateListener implements ActionListener {

		private String id, lastName, sex, birthDate, hireDate, jobStatus, payType,
				salary, yearS;

		@Override
		public void actionPerformed(ActionEvent e) {
			id = jtfId.getText();
			lastName = jtfLastName.getText();
			sex = (String) jcboSex.getSelectedItem();
			birthDate = jtfBirthDate.getText();
			hireDate = jtfHireDate.getText();
			jobStatus = (String) jcboJobStatus.getSelectedItem();
			payType = (String) jcboPayType.getSelectedItem();
			salary = jtfSalary.getText();
			yearS = jtfYearS.getText();
			
			if(sex.equals("Male")){
				sex = "M";
			}else{
				sex = "F";
			}
			if(birthDate.equals("yyyy/mm/dd") || birthDate.equals("")){
				birthDate = "1900/01/01";
			}
			if(hireDate.equals("yyyy/mm/dd") || hireDate.equals("")){
				hireDate = "1900/01/01";
			}
			if(salary.equals("")){
				salary = "-1";
			}
			if(yearS.equals("")){
				yearS = "-1";
			}
			
			try {
				DBConnection.openConnection();
				ResultSet rs = DBConnection.searchID(id);
				if (rs.next()) {
					int reply = JOptionPane.showConfirmDialog(null, "Dou you want to update the record?", 
							"Update Confirmation", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION){
						DBConnection.updateRow(id, lastName, sex, birthDate, hireDate, 
								jobStatus, payType, salary, yearS);				
						JOptionPane.showMessageDialog(null, "Record updated.", 
							"Update", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} else {
					if(id.equals("")){
						JOptionPane.showMessageDialog(null,
								"Please enter Employee ID.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null,
							"Record does not exist.", "Error",
							JOptionPane.ERROR_MESSAGE);
					}				
				}
				DBConnection.closeResultSet();
				DBConnection.closeStatementConnection();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1, "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

