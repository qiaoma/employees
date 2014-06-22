package edu.calstatela.cs245.qiao.project;


import java.awt.GridLayout;

import javax.swing.*;


public class BaseFrame {
	
	protected JFrame frame;
	protected JPanel panel;
	protected JTextField jtfId, jtfLastName, jtfBirthDate, jtfHireDate, jtfSalary, jtfYearS;
	protected JComboBox<String> jcboSex, jcboJobStatus, jcboPayType;
	
	public BaseFrame(int x, int y){
		frame = new JFrame("Employee");
		frame.setSize(x, y);
		frame.setVisible(true);
		frame.setLocation(300, 100);
	}
	
	public void setFillForm(){
		
		panel = new JPanel(new GridLayout(0,2,10,10));
		
		frame.add(panel);
		
		JLabel labId = new JLabel("Employee ID: ");
		panel.add(labId);
		
		jtfId = new JTextField(10);
		panel.add(jtfId);
		
		JLabel labLastName = new JLabel("Last Name: ");
		panel.add(labLastName);
		
		jtfLastName = new JTextField(10);
		panel.add(jtfLastName);
		
		JLabel labSex = new JLabel("Sex: ");
		panel.add(labSex);
		
		String[] sexString = {"Male", "Female"};
		jcboSex = new JComboBox<String>(sexString);
		panel.add(jcboSex);
		
		JLabel labBirthdate = new JLabel("Employee birthdate: ");
		panel.add(labBirthdate);
		
		jtfBirthDate = new JTextField("yyyy/mm/dd");
		panel.add(jtfBirthDate);
		
		JLabel labHireDate = new JLabel("Employee hire date: ");
		panel.add(labHireDate);
		
		jtfHireDate = new JTextField("yyyy/mm/dd");
		panel.add(jtfHireDate);		
		
		JLabel labJobStatus = new JLabel("Job status: ");
		panel.add(labJobStatus);
		
		String[] jobString = {"FT", "PT", "CN"};
		jcboJobStatus = new JComboBox<String>(jobString);
		panel.add(jcboJobStatus);
		
		JLabel labPayType = new JLabel("Pay type: ");
		panel.add(labPayType);
		
		String[] payString = {"S", "H"};
		jcboPayType = new JComboBox<String>(payString);
		panel.add(jcboPayType);
		
		JLabel labSalary = new JLabel("Annual salary: ");
		panel.add(labSalary);
		
		jtfSalary = new JTextField(10);
		panel.add(jtfSalary);
		
		JLabel labYearS = new JLabel("Year of Service: ");
		panel.add(labYearS);
		
		jtfYearS = new JTextField(10);
		panel.add(jtfYearS);
	}
}
