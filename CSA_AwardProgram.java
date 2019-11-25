import java.awt.*;  
import java.awt.event.*;
import java.awt.Color;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.text.BadLocationException; 

import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class CSA_AwardProgram {
	private static boolean mainActive = false;
	private static School_CSA alpharetta = new School_CSA();
	private  static String fileName = "C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/src/StudentDatabase.txt";
	
	public static void setMainActive(boolean decision)
	{
		mainActive = decision;
	}
	
	private static void createSchoolRoster()
	{	int count = 0;
		//need to recreate array each time program opens since will not remember from last use
		 ArrayList<Student> students = new ArrayList<Student>();
		 
		  try
		  {
		    FileInputStream fstream = new FileInputStream(fileName);
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    
		    String stringToLookFor = "Alpharetta High School Student", strLine;
		    
		    while ((strLine = br.readLine()) != null)   {
			      int startIndex = strLine.indexOf(stringToLookFor);
			      while (startIndex != -1) {
			        count++;
			        students.add(new Student());
			        startIndex = fileName.indexOf(stringToLookFor, startIndex + stringToLookFor.length());
			      }
			}
		    
		    System.out.println(count); // successfully finding number of students
		    
		    String endString = "";

			for (int i = 0; i < 5; i++) // searching by column
			{
				for (int numStu = 0; numStu < count; numStu++)
				{
			    	Student tempStudent = students.get(numStu);
			    	
			    	String strLine2;
			    	if (i == 0)
			    	{
			    		stringToLookFor = "First Name: ";
			    		endString = "Last Name: ";
			    	}
			    	else if (i == 1)
			    	{
			    		stringToLookFor = "Last Name: ";
			    		endString = "Student Id: ";
			    	}
			    	else if (i == 2)
			    	{
			    		stringToLookFor = "Student Id: ";
			    		endString = "Grade Level: ";
			    	}
			    	else if (i == 3)
			    	{
			    		stringToLookFor = "Grade Level: ";
			    		endString = "Total Service Hours: ";
			    	}
			    	else
			    	{
			    		stringToLookFor = "Total Service Hours: ";
			    		endString = "---";
			    	}
			    		
			    	String temp = "";
			    	
			    	while ((strLine2 = br.readLine()) != null)   {
					      int startIndex = strLine2.indexOf(stringToLookFor);
					      while (startIndex != -1) {
					        temp = fileName.substring(startIndex);
					        startIndex = fileName.indexOf(stringToLookFor, startIndex + stringToLookFor.length());
					        
					        if (i == 0)
					    		tempStudent.setMyFirstName(temp);
					    	else if (i == 1)
					    		tempStudent.setMyLastName(temp);
					    	else if (i == 2)
					    		tempStudent.setMyStudentId(Integer.parseInt(temp));
					    	else if (i == 3)
					    		tempStudent.setMyGradeLevel(Integer.parseInt(temp));
					    	else
					    		tempStudent.setMyTotalServiceHours(Integer.parseInt(temp));
					      }
			    	}
			    }
		    }
			    
		    in.close();
		  }
		  catch (Exception e)
		  {//Catch exception if any
		    System.err.println("Error: " + e.getMessage());
		  }
		 
		 
//		  
//		  for (int i = 0; i < count; i++)
//		  {
//			  tempStu = 
//			  alpharetta.addStudent(tempStu);
//		  }
		  
		  for (int j = 0; j < students.size(); j++)
			  alpharetta.addStudent(students.get(j));
		  
		  System.out.println(alpharetta.toString());
	}
	
	public static void buildWelcomeGUI()
	{
		createSchoolRoster();
		
		JFrame welcomeFrame = new JFrame("Welcome to CSA_AwardProgram");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		

		JLabel FBLAIntro = new JLabel("Future Business Leaders of America "); 
		JLabel CSAIntro = new JLabel("Community Service Awards");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		JButton registerNewStudent = new JButton("Register New Student");
		JButton viewRoster = new JButton("View School Roster");
		
		registerNewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent register)
			{
				CSA_AwardProgram.buildRegisterNewStudentGUI();
				welcomeFrame.dispose();
			}
			
			
		}); 
		
		viewRoster.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent view)
		    { 
		       	CSA_AwardProgram.buildMainPageGUI();
		    	welcomeFrame.dispose();
		    }
     	});
		 
		 mainPanel.add(FBLAIntro);
		 mainPanel.add(CSAIntro);
		 
		 buttonPanel.add(registerNewStudent);
		 buttonPanel.add(viewRoster);
		 
		 mainPanel.add(buttonPanel);
		 welcomeFrame.add(mainPanel);
		 
		 welcomeFrame.setSize(1400, 700);  
		 welcomeFrame.setLocationRelativeTo(null);  
		 welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		 welcomeFrame.setVisible(true);	  
	}
	
	
	public static void buildMainPageGUI()  
	{
		mainActive = true;
		/*** creates GUI's general frame + panels ********************************************************/  
	    JFrame frame = new JFrame("CSA_AwardProgram");  // makes title in upper left hand corner
	   	JPanel mainPanel = new JPanel();
	   	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    
	    JPanel firstPanel = new JPanel();
	    JPanel tablePanel = new JPanel();
	    
	    firstPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    tablePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    
	    /*** Panel 1 + Welcome Label *********************************************************************/
	    firstPanel.setOpaque(true);
	    firstPanel.setBackground(Color.white);
	    firstPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    firstPanel.setMaximumSize(new Dimension(400,20));
	    
	    JLabel welcomeText = new JLabel();
	    welcomeText.setText("Alpharetta High School Community Service Award Management");
	    
	    /*** Panel 2 + Table + EditRoster Button *********************************************************/
	    //tablePanel ------------------------------------------------------------------------------------
    		tablePanel.setOpaque(true);
    		tablePanel.setBackground(Color.gray);
	    	tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    	tablePanel.setMaximumSize(new Dimension(1000,400)); // remember to switch back to 700 width!!!
	    	
	    //table ------------------------------------------------------------------------------------------
	    	
	    //hardcode data input; will adjust with text file code
	    	String column[]={"STUDENT ID", "FULL NAME","GRADE LEVEL","SERVICE HOURS", "AWARD ELIGIBLE?"};  
	    	// 5 col
	    	
	    	String data[][] = new String[alpharetta.getArrayLength()][5];
	    			
	    			
//	    		{ 
//			    		{"1100299029","Nathaly Jose-Maria","12", "200", "Yes" },    //row 0, col 0, 1, 2, 3, ...
//			    		{"", "1100288888","Emily Jose-Maria","9", "50", "Yes" },     
//		                {"", "101","Sachin","700000", "", ""}
//		                }   
	    
	    JButton testTableCreation = new JButton("Test Table Creation");
	    testTableCreation.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ee)
	    	{	    		
	    		for (int r = 0; r < alpharetta.getArrayLength(); r++)
	    		{
	    			Student student = alpharetta.getSpecificStudent(r);
	    			
	    			for (int c = 0; c < 5; c++)
	    			{
	    				if (c == 0)
	    					data[r][c] = "" + student.getStudentId();
	    				else if (c == 1)
	    					data[r][c] = student.getFullName();
	    				else if (c == 2)
	    					data[r][c] = "" + student.getGradeLevel();
	    				else if (c == 3)
	    					data[r][c] = "" + student.getTotalServiceHours();
	    				else
	    					data[r][c] = "" + (student.isCommunityEligibility() || student.isServiceEligibility() || student.isAchieveEligibility());
	    			}
		    	}
	    	}
	    });
	    
	    
		// adjusts column sizes
	    JTable jt = new JTable(data, column);
	    JScrollPane sp = new JScrollPane(jt);    
    	
	    	TableColumn changeColumn = null;
	    	for (int ci = 0; ci < jt.getColumnCount(); ci++)
	    	{
	    		changeColumn = jt.getColumnModel().getColumn(ci);
	    		
	    		if (ci == 1)
	    			changeColumn.setPreferredWidth(200);
	    		else  if (ci == 3)
	    				changeColumn.setPreferredWidth(120);
	    		else
	    			changeColumn.setPreferredWidth(150);
	    		
	    	}
	    	
	    jt.setPreferredScrollableViewportSize(jt.getPreferredSize());
	    jt.setFillsViewportHeight(true);
	    
	    // addStudentFx button -----------------------------------------------------------------------
	    JButton addStudentFx= new JButton("Add Student");
	    addStudentFx.setBounds(50,100,95,30);
	    
	    //create new popup asking for student information (DONE)
	    //append student information to text file
	    //from text file, print new student row in table
	    
	    /*** Popup Window for Inserting New Student Information *****************************************/
	    addStudentFx.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	    	{ 
	        	CSA_AwardProgram.buildRegisterNewStudentGUI();
	    	}
	        
	    });
	    
	   
	    /********************************************************************************************************/    
	    
	    // deleteStudent button --------------------------------------------------------------------------
	    JButton deleteStudent = new JButton("Delete Student");
	    deleteStudent.setBounds(50,100,95,30);
	    
	    // prompt to select student name
	    // once selected, find row number and delete row in table in text file

	    
	    
	    /*** Frame Finalizations *************************************************************************/
        //Note: anything you want ordered together with FlowLayout must be in same panel
	    firstPanel.add(welcomeText);
	    tablePanel.add(addStudentFx);
	    tablePanel.add(deleteStudent);
	    tablePanel.add(testTableCreation);
	    tablePanel.add(sp);
	    
	    mainPanel.add(firstPanel);
	    mainPanel.add(tablePanel);
	    
	    frame.add(mainPanel);
        
	    frame.setSize(1400, 700);  
	    frame.setLocationRelativeTo(null);  
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    frame.setVisible(true);	    
	}
	
	 public static void buildRegisterNewStudentGUI()
	 {		
		 
     	//popup window appear
     	JFrame popup = new JFrame("New Student Registration");
     	JPanel subMainPanel = new JPanel();
         subMainPanel.setLayout(new BoxLayout(subMainPanel, BoxLayout.Y_AXIS));
         
         JPanel fieldPanel = new JPanel();
         fieldPanel.setLayout(new GridLayout(10,1));
         
     	//enter text fields for information and save to text file
     	JLabel directions = new JLabel();
     	directions.setText("Please enter the following information for your new student:");
     	
     	JTextField firstField, lastField, idField, gradeField, hoursField;
     	
     	JLabel firstLabel = new JLabel();
     	firstLabel.setText("First name: ");
     	firstField = new JTextField();  
     	
     	JLabel lastLabel = new JLabel();
     	lastLabel.setText("Last name: ");
     	lastField = new JTextField();  
     	
     	JLabel idLabel = new JLabel();
     	idLabel.setText("ID: ");
     	idField = new JTextField();  

     	JLabel gradeLabel = new JLabel();
     	gradeLabel.setText("Grade level: ");
     	gradeField = new JTextField();  

     	JLabel hoursLabel = new JLabel();
     	hoursLabel.setText("Total service hours: ");
     	hoursField = new JTextField();  
     	
     	
     	JButton enterNewInfo = new JButton("Submit Information");
     	enterNewInfo.setBounds(50,100,95,30);
     	enterNewInfo.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e)
     		{
     			// add information to text document
	     		
	     		//user changes with each system; must find a way of getting user information
	     			
	     		try (FileWriter fw = new FileWriter(fileName, true)) {
	     			// gets input from textField and appends to text file
	     			String myFirst = firstField.getText();
	     			String myLast = lastField.getText();
	     			String myID = idField.getText();
	     			String myGrade = gradeField.getText();
	     			String myHours = hoursField.getText();
	     			
	     			alpharetta.addStudent(myFirst, myLast, myID, myGrade, myHours);
	     			
	     			PrintWriter writer = new PrintWriter(fileName);
	     			writer.print(alpharetta.toString());
	     			writer.close();

	     		}
	     		catch (IOException e1)
	     		{ 
	     			System.out.println("Exception Occurred" + e1); 
	     		}

	     		if (!mainActive)
	         		CSA_AwardProgram.buildMainPageGUI();
     			
	     		
	     		popup.dispose();
     		}; 
     		} ); // end of entire statement
     			
     	// update information on table OR call method that does it
     	//		- have method that actively keeps searching for update??
     	
     
         
     	fieldPanel.add(firstLabel);
     	fieldPanel.add(firstField);
     	
     	fieldPanel.add(lastLabel);
     	fieldPanel.add(lastField);
     	
     	fieldPanel.add(idLabel);
     	fieldPanel.add(idField);
     	
     	fieldPanel.add(gradeLabel);
     	fieldPanel.add(gradeField);
     	
     	fieldPanel.add(hoursLabel);       	
     	fieldPanel.add(hoursField);
     	
     	subMainPanel.add(directions);
     	subMainPanel.add(fieldPanel);
     	subMainPanel.add(enterNewInfo);
     	
     	popup.add(subMainPanel);
         popup.setSize(600, 400);  
         popup.setLocationRelativeTo(null);  
         popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //doesn't cancel complete application
         popup.setVisible(true);	   
	    }

	
	
	
	/*******************************************************************************************************/
	public static void main(String[] args) throws BadLocationException
	{
//		School_CSA AHS_CSA = new School_CSA();
//		
//		alpharetta.addStudent("Nathaly", "Jose-Maria", 1100299029, 12, 200);
//		alpharetta.addStudent("Emily", "Jose-Maria", 1100288888, 9, 50);
//		
//		System.out.println(AHS_CSA.toString());
//		
		CSA_AwardProgram.buildWelcomeGUI();
		//CSA_AwardProgram.buildRegisterNewStudentGUI();

		
	}

}
