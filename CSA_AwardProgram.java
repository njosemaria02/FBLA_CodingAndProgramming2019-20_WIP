import java.awt.*;  
import java.awt.event.*;
import java.awt.Color;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.text.BadLocationException; 
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class CSA_AwardProgram {
	private static boolean mainActive = false;
	private static boolean needUpdateTable = false;
	
	private static School_CSA alpharetta;
	//private  static String fileName = "C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/src/StudentDatabase.txt";
	private static String fileName = "C:/Users/SYFGWC19/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/src/StudentDatabase.txt"; 
	
	
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
			/** register COUNT of how many students in system **/
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
		    
		    in.close();
		    
		    //System.out.println(count); // successfully finding number of students
		    //for (int k = 0; k < count; k++)
		    	//System.out.println(students.get(k).toString()+ "\n"); // successfully creating empty students

		    
		    /** getting information and recreating students for database able to use within file**/
		    String temp = "";
		    
		    for (int numStu = 0; numStu < count; numStu++) // going through each student
			{
			    
			    
		    	Student tempStudent = students.get(numStu);
		    	
		    	/**System.out.println("-------------------\nStudent " + numStu + ": \n");**/
		    	
				for (int i = 0; i < 5; i++) // searching by column
				{
					FileInputStream fstream2 = new FileInputStream(fileName);
				    DataInputStream in2 = new DataInputStream(fstream2);
				    BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
					
			    	String strLine2;	
			    	
			    	ArrayList<String> tempList = new ArrayList<String>();

			    	int add = 0;
			    	
			    	if (i == 0)
			    	{
			    		stringToLookFor = "First";
			    		add = 2;
			    	}
			    	else if (i == 1)
			    	{
			    		stringToLookFor = "Last";
			    		add = 2;
			    	}
			    	else if (i == 2)
			    	{
			    		stringToLookFor = "Id:";
			    		add = 1;
			    	}
			    	else if (i == 3)
			    	{
			    		stringToLookFor = "Grade";
			    		add = 2;
			    	}
			    	else
			    	{
			    		stringToLookFor = "Hours:";
			    		add = 1;
			    	}
			    		
			    	
			    	while ((strLine2 = br2.readLine()) != null)   {
			    		String arr[] = strLine2.split(" ");
			    		
			    		for (int num = 0; num < arr.length; num++)
			    			tempList.add(arr[num]);
			    	}//while close
			    	
				    int tempCount = 0;
				    for (int k = 0; k < tempList.size(); k++)
				    {
				    	if (tempList.get(k).equals(stringToLookFor))
				    	{
				    		if (tempCount == numStu)
				    		{
					  			temp = tempList.get(k+add);
					   			k = tempList.size();
				    		}
				    		tempCount++;
				    	}
				    }
			    		
			    	/**System.out.println("Index " + i + " " + stringToLookFor + ": " + temp);**/
			    	
			    	if (i == 0)
			    		tempStudent.setMyFirstName(temp);
			    	else if (i == 1)
			    		tempStudent.setMyLastName(temp);
			    	else if (i == 2)
			    		tempStudent.setMyStudentId(Integer.parseInt(temp));
				    else if (i == 3)
				    	tempStudent.setMyGradeLevel(Integer.parseInt(temp));
				    else
				    	tempStudent.setMyTotalServiceHours(Double.parseDouble(temp));
			    	
	    	 	    		
			    	temp = "";	
			    	in2.close();
				} //column loop close
			} //stuCount loop close
		  } //try close
		  catch (Exception e)
		  {//Catch exception if any
		    System.err.println("Error: " + e.getMessage());
		  }
		  
		  /** code for checking data successfully transferring
		  	System.out.println("\nstudents array loop:\n");
		 	for (int k = 0; k < count; k++)
		    	System.out.println(students.get(k).toString() + "\n");
		 
		 	System.out.println("\n\nstudents array toString:\n" + students.toString());
		  **/
		  
		  System.out.println();
		  alpharetta = new School_CSA(students);
		  
		  System.out.println("Alpharetta roster array successfully built");
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
		createSchoolRoster();
		
		mainActive = true;
		needUpdateTable = true;
		
		/*** creates GUI's general frame + panels ********************************************************/  
	    JFrame frame = new JFrame("Main Page - Alpharetta High School Roster");  // makes title in upper left hand corner
	   	JPanel mainPanel = new JPanel();
	   	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    
	    JPanel firstPanel = new JPanel();
	    JPanel tablePanel = new JPanel();
	    JPanel directionsPanel = new JPanel();
	    
	    firstPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    tablePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    directionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    
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
	    
	    	//forever loop?			
	    if (needUpdateTable)
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
	    	needUpdateTable = false;
	    }
	
	    
	    
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
	    
	    jt.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e) {
	    		if (e.getClickCount() == 1) {
	    			JTable target = (JTable)e.getSource();
	    			
	    			int row= target.rowAtPoint(e.getPoint());

	    			int col= target.columnAtPoint(e.getPoint());
	    			
	    			if (col == 1) {
	    				Student tempStu = alpharetta.getSpecificStudent(row);
	    				buildIndivStudentProfileGUI(tempStu);
	    				frame.dispose();
	    			}
	    		}
	    	}
	    });
	    
	    
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
	        	mainActive = false;
	        	frame.dispose();
	    	}
	        
	    });
	    
	    /** Delete Student Function *********************************************************************/
	    // deleteStudent button --------------------------------------------------------------------------
	    JButton deleteStudent = new JButton("Delete Student");
	    deleteStudent.setBounds(50,100,95,30);
	    
	    deleteStudent.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		makeDeleteStudentGUI();
	    		
	    		frame.dispose();
	    	}
	    });
	    
	    /** View Service Hours *************************************************************************/
	    JButton viewServiceHours = new JButton("View Service Hours");
	    
	    viewServiceHours.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		//method for service hours
	    		frame.dispose();
	    	}
	    });
	    
	    // prompt to select student name
	    // once selected, find row number and delete row in table in text file

	    
	    /** Print Information Function *******************************************************************/
	    JButton printRoster = new JButton ("Print Roster");
	    printRoster.setBounds(50,100,95,30);
	    
	    printRoster.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		 System.out.println(alpharetta.toString());
	    		 //replace with function that opens up pdf asking if ready to print or some popup or something
	    	}
	    	
	    	
	    });
	    
	    
	    /** Directions **/
	    JLabel directions = new JLabel("To view or edit an individual student's information, please click on the student's name in the table.");
	    
	    
	    
	    /*** Frame Finalizations *************************************************************************/
        //Note: anything you want ordered together with FlowLayout must be in same panel
	    firstPanel.add(welcomeText);
	    directionsPanel.add(directions);
	    tablePanel.add(addStudentFx);
	    tablePanel.add(deleteStudent);
	    tablePanel.add(viewServiceHours);
	    tablePanel.add(printRoster);
	    tablePanel.add(directionsPanel);
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
	     			
	     			//PrintWriter writer = new PrintWriter(fileName);
	     			//writer.print(alpharetta.toString());
	     			//writer.close();
	     			alpharetta.updateDatabase(fileName);

	     		}
	     		catch (IOException e1)
	     		{ 
	     			System.out.println("Exception Occurred" + e1); 
	     		}
	     		
	     		buildMainPageGUI();

	     		popup.dispose();
     		}; 
     		} );
     	// end of entire statement
     			
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
	 
	 public static void makeDeleteStudentGUI() {
		 int alphalen = alpharetta.getArrayLength();		 
		 
		 JFrame frame = new JFrame("Student Deletion");
		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		 
		 JPanel newYearPanel = new JPanel();
		 newYearPanel.setLayout(new BoxLayout(newYearPanel, BoxLayout.X_AXIS));
		 JLabel newYearDirections = new JLabel("New School Year? Click here to adjust grade levels and remove seniors.");
		 JButton adjustRoster = new JButton("Adjust Roster");
		 adjustRoster.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 ArrayList<Student> removedStudents =  new ArrayList<Student>();

				 for (int i = 0; i < alpharetta.getArrayLength(); i++)
				 {
					 Student temp = alpharetta.getSpecificStudent(i);
					 temp.setMyGradeLevel(temp.getGradeLevel() + 1);
					 
					 if (temp.getGradeLevel() > 12)
						 removedStudents.add(temp);
				 }
				 			
				 for (int j = 0; j < removedStudents.size(); j++)
				 {
					 for (int k = 0; k < alpharetta.getArrayLength(); k++)
					 {
						 if (removedStudents.get(j) == alpharetta.getSpecificStudent(k))
						 {
							 alpharetta.removeStudent(k);
							 k--;
							 System.out.println(removedStudents.get(j).getFullName() + " removed");
						 }
					 }
				 }
				 
				 
				 
				 //fixed alpharetta but now need to append changes to text file before recreating system
				 
				try 
				{ 
					BufferedWriter out = new BufferedWriter(new FileWriter(fileName)); 
						out.write(alpharetta.toString()); 
						out.close(); 
				} 
				catch (IOException e1) 
				{ 
					System.out.println("Exception Occurred" + e1); 
				} 
				 
				 
				System.out.println("Database altered");
				
				
				String print = "The following students were successfully removed: \n";
				
				 for (int count = 0; count < removedStudents.size(); count++)
					 print+= removedStudents.get(count).getFullName() + "\n";
				
				JOptionPane.showMessageDialog(frame, print);
				 
		     	CSA_AwardProgram.buildMainPageGUI();
		     	


		     	frame.dispose();
			 }
		 });

		 
		 
		 JPanel directionsPanel =  new JPanel();		 
		 directionsPanel.setLayout(new BoxLayout(directionsPanel, BoxLayout.X_AXIS));
		 JLabel directions = new JLabel("Please click on which student(s) to delete in the list below.");
		 
		 JPanel nameListPanel = new JPanel();
		 nameListPanel.setLayout(new BoxLayout(nameListPanel, BoxLayout.X_AXIS));
		 
		 
		 JTabbedPane tp = new JTabbedPane();
		 JPanel ninePanel = new JPanel();
		 	for (int i = 0; i < alphalen; i++)
		 	{
		 		if (alpharetta.getSpecificStudent(i).getGradeLevel() == 9) {
		 		 JRadioButton rb = new JRadioButton();
		 		 String name = alpharetta.getSpecificStudent(i).getFullName(); 
		 		 rb.setText(name);
		 		 ninePanel.add(rb);
		 		}
		 	}
		 
		 
		 JPanel tenPanel = new JPanel();
			 for (int i = 0; i < alphalen; i++)
			 {
			 	if (alpharetta.getSpecificStudent(i).getGradeLevel() == 10) {
			 	 JRadioButton rb = new JRadioButton();
			 	 String name = alpharetta.getSpecificStudent(i).getFullName(); 
			 	 rb.setText(name);
			 	 tenPanel.add(rb);
			 	}
			 }
		 	
		 JPanel elevenPanel = new JPanel();
			 for (int i = 0; i < alphalen; i++)
			 {
			 	if (alpharetta.getSpecificStudent(i).getGradeLevel() == 11) {
			 	 JRadioButton rb = new JRadioButton();
			 	 String name = alpharetta.getSpecificStudent(i).getFullName(); 
			 	 rb.setText(name);
			 	 elevenPanel.add(rb);
			 	}
			 }
			 
		 JPanel twelvePanel = new JPanel();
			 for (int i = 0; i < alphalen; i++)
			 {
			 	if (alpharetta.getSpecificStudent(i).getGradeLevel() == 12) {
			 	 JRadioButton rb = new JRadioButton();
			 	 String name = alpharetta.getSpecificStudent(i).getFullName(); 
			 	 rb.setText(name);
			 	 twelvePanel.add(rb);
			 	}
			 }
		 
		 tp.setBounds(50,50, 200,200);
		 tp.add("9th Graders", ninePanel);
		 tp.add("10th Graders", tenPanel);
		 tp.add("11th Graders", elevenPanel);
		 tp.add("12th Graders", twelvePanel);
		 
		 
		 /** Finalizations ***********************************************************************/		 
		 newYearPanel.add(newYearDirections);
		 newYearPanel.add(adjustRoster);
		 
		 directionsPanel.add(directions);
		 nameListPanel.add(tp);
		 
		 mainPanel.add(newYearPanel);
		 mainPanel.add(directionsPanel);
		 mainPanel.add(nameListPanel);
		 
		 frame.add(mainPanel);
		 frame.setSize(600, 400);  
		 frame.setLocationRelativeTo(null);  
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //doesn't cancel complete application
		 frame.setVisible(true);	
	 }
	 
	 
	 public static void buildIndivStudentProfileGUI(Student stu)
	 {
		 /** Initializations ********************************************************************/
		JFrame indivStuFrame = new JFrame("Individual Student Information");
		JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel studentInfoPanel = new JPanel();
		studentInfoPanel.setLayout(new GridLayout(5,1));
		
		JPanel buttonPanel = new JPanel();
		//buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		 
		/** studentInfoPanel ***********************************************************************/
		JPanel introPanel = new JPanel();
		introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.X_AXIS));
		
		JLabel generalIntro = new JLabel();
		generalIntro.setText("Student Profile: " + stu.getFullName());
		 
		JLabel firstLabel = new JLabel();
		firstLabel.setText("First name: " + stu.getFirstName());
	     	
		JLabel lastLabel = new JLabel();
		lastLabel.setText("Last name: " + stu.getLastName());
	     	
	    JLabel idLabel = new JLabel();
	    idLabel.setText("ID: " + stu.getStudentId());
	     	
	    JLabel gradeLabel = new JLabel();
	    gradeLabel.setText("Grade level: " + stu.getGradeLevel());
	     	
	    JLabel hoursLabel = new JLabel();
	    hoursLabel.setText("Total service hours: " + stu.getTotalServiceHours());
	    
	    /** buttonPanel *********************************************************************************/
	    JButton viewHours = new JButton("View Service Hours and Projects");
	    viewHours.setBounds(50,100,95,30);
	    
	    viewHours.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		//open new frame with volunteer service activities printed out
	    	}
	    });
	    
	    
	    JButton editInformation = new JButton("Edit " + stu.getFirstName() + "'s Information");
	    editInformation.setBounds(50,100,95,30);
	    
	    editInformation.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		buildEditIndivStuGUI(stu);
	    		indivStuFrame.dispose();
	    	}
	    });

		 
	     
	     /** Finalizations ****************************************************************************/
	    introPanel.add(generalIntro); 
	    
	     studentInfoPanel.add(firstLabel);
	     studentInfoPanel.add(lastLabel);
	     studentInfoPanel.add(idLabel);
	     studentInfoPanel.add(gradeLabel);
	     studentInfoPanel.add(hoursLabel);
	     
	     buttonPanel.add(viewHours);
	     buttonPanel.add(editInformation);
	     
		 
		 mainPanel.add(introPanel);
	     mainPanel.add(studentInfoPanel);
	     mainPanel.add(buttonPanel);
	     
		 indivStuFrame.add(mainPanel);
		 indivStuFrame.setSize(600, 400);  
		 indivStuFrame.setLocationRelativeTo(null);  
		 indivStuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //doesn't cancel complete application
		 indivStuFrame.setVisible(true);	 		 
	 }
	
	
	 public static void buildEditIndivStuGUI(Student stuToEdit)
	 {		
		JFrame frame = new JFrame("Edit Individual Student");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		/** directionsPanel **********************************************************/
		JPanel directionPanel = new JPanel();
		JLabel directions = new JLabel();
		directions.setText("Please confirm " + stuToEdit.getFullName() + "'s information below.");
		directionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		/** fieldPanel **************************************************************/		
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridLayout(10,1));
			
		JTextField firstField, lastField, idField, gradeField, hoursField;
			
		JLabel firstLabel = new JLabel();
     	firstLabel.setText("First name: ");
     	firstField = new JTextField(stuToEdit.getFirstName());  
     	
     	JLabel lastLabel = new JLabel();
     	lastLabel.setText("Last name: ");
     	lastField = new JTextField(stuToEdit.getLastName());  
     	
     	JLabel idLabel = new JLabel();
     	idLabel.setText("ID: ");
     	idField = new JTextField(""+stuToEdit.getStudentId());  

     	JLabel gradeLabel = new JLabel();
     	gradeLabel.setText("Grade level: ");
     	gradeField = new JTextField(""+stuToEdit.getGradeLevel());  

     	JLabel hoursLabel = new JLabel();
     	hoursLabel.setText("Total service hours: ");
     	hoursField = new JTextField(""+stuToEdit.getTotalServiceHours());  
     	
     	/** submitPanel *******************************************************************/
     	JPanel submitPanel = new JPanel();
     	submitPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

     	JButton enterNewInfo = new JButton("Submit Information");
     	enterNewInfo.addActionListener(new ActionListener() {
 	    	public void actionPerformed(ActionEvent e)
 	    	{
 	    		//replace student information
 	    		stuToEdit.setMyFirstName(firstField.getText());
 	    		stuToEdit.setMyLastName(lastField.getText());
 	    		stuToEdit.setMyStudentId(Integer.parseInt(idField.getText()));
 	    		stuToEdit.setMyGradeLevel(Integer.parseInt(gradeField.getText()));
 	    		stuToEdit.setMyTotalServiceHours(Double.parseDouble(hoursField.getText()));
 	    		
 	    		//refresh mainGUI	
 	    		alpharetta.updateDatabase(fileName);
 	    		buildMainPageGUI();
 	    		frame.dispose();
 	    	}
 	    });
	
		/** Finalizations ******************************************************************/ 
     	directionPanel.add(directions);
     	submitPanel.add(enterNewInfo);
     	
     	
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
     	
		mainPanel.add(directionPanel);
     	mainPanel.add(fieldPanel);
     	mainPanel.add(submitPanel);
     	
     	frame.add(mainPanel);
		frame.setSize(600, 400);  
		frame.setLocationRelativeTo(null);  
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //doesn't cancel complete application
		frame.setVisible(true);	
	 }
	 
	 
	
	/*******************************************************************************************************/
	public static void main(String[] args) throws BadLocationException
	{
		CSA_AwardProgram.buildWelcomeGUI();
		
	}

}
