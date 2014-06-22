package edu.calstatela.cs245.qiao.project;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;


public class AddFrame extends BaseFrame{
	
	public AddFrame(){
		
		super(500, 400);
		
		setFillForm();
		
		JButton jbtNext = new JButton("Next");
		panel.add(jbtNext);
		
		NextListenerClass nextListener = new NextListenerClass();
		jbtNext.addActionListener(nextListener);
		
		JButton jbtSave = new JButton("Save");
		panel.add(jbtSave);
		
		SaveListenerClass saveListener = new SaveListenerClass();
		jbtSave.addActionListener(saveListener);
	}
	
	//------------- SaveListenerClass ------------------------------------------------
	class SaveListenerClass implements ActionListener{

		private String id, lastName, sex, birthDate, hireDate, jobStatus, payType, salary, yearS;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			id = jtfId.getText();
			lastName = jtfLastName.getText();
			sex = (String)jcboSex.getSelectedItem();
			birthDate = jtfBirthDate.getText();
			hireDate = jtfHireDate.getText();
			jobStatus = (String)jcboJobStatus.getSelectedItem();
			payType = (String)jcboPayType.getSelectedItem();
			salary = jtfSalary.getText();
			yearS =  jtfYearS.getText();
			
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
			
			if( (!id.equals("")) && (!Validity.isInt(id)) ){
				JOptionPane.showMessageDialog(null, "Employee ID need to be number format.", 
						"Error", JOptionPane.ERROR_MESSAGE);
			}else if(lastName.equals("")){
				JOptionPane.showMessageDialog(null, "Employee name cannot be empty.", 
						"Warning", JOptionPane.WARNING_MESSAGE);
			}else if(!Validity.isDouble(salary)){
				JOptionPane.showMessageDialog(null, "Employee salary need to be number format.", 
						"Error", JOptionPane.ERROR_MESSAGE);
			}else if(!Validity.isInt(yearS)){
				JOptionPane.showMessageDialog(null, "Employee year of service need to be number format.", 
						"Error", JOptionPane.ERROR_MESSAGE);
			}else{
				try {
					DBConnection.openConnection();
					DBConnection.insertRow(id, lastName, sex, birthDate, hireDate, 
							jobStatus, payType, salary, yearS);
					JOptionPane.showMessageDialog(null, "Record saved successfully.", 
							"Save", JOptionPane.INFORMATION_MESSAGE);
					DBConnection.closeStatementConnection();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}			
		}	
	}	
	
	//--------------- NextListenerClass ---------------------------------------------------
	class NextListenerClass implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			jtfId.setText(null);
			jtfLastName.setText(null);
			jcboSex.setSelectedIndex(0);
			jtfBirthDate.setText("yyyy/mm/dd");
			jtfHireDate.setText("yyyy/mm/dd");
			jcboJobStatus.setSelectedIndex(0);
			jcboPayType.setSelectedIndex(0);
			jtfSalary.setText(null);
			jtfYearS.setText(null);
		}	
	}
	//-------------  ------------------------------------------------
}

