import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class School_CSA {
	private ArrayList<Student> studentList;
	
	public School_CSA()
	{
		studentList = new ArrayList<Student>();
	}
	
	public School_CSA(ArrayList temp)
	{
		studentList = new ArrayList<Student>();

		for (int len = 0; len < temp.size(); len++)
			addStudent((Student) temp.get(len));
		
	}
	
	public void addStudent(String firstName, String lastName, String ID, String grade, String totalServiceHours)
	{
		Integer id = Integer.parseInt(ID);
		Integer aGrade = Integer.parseInt(grade);
		Double serviceHours = Double.parseDouble(totalServiceHours);
		
		studentList.add(new Student(firstName, lastName, id, aGrade, serviceHours));
	}
	
	public void addStudent(Student student)
	{
		studentList.add(student);
	}
	
	public boolean removeStudent(int index)
	{
		Student temp = studentList.get(index);
		return (studentList.remove(index) == temp);
	}
	
	public int getArrayLength()
	{
		return studentList.size();
	}
	
	public Student getRecentStudent()
	{
		return studentList.get(studentList.size() - 1);
	}
	
	public Student getSpecificStudent(int index)
	{
		return studentList.get(index);
	}
	
	public void updateDatabase(String fn)
	{
		try 
		{ 
			BufferedWriter out = new BufferedWriter(new FileWriter(fn)); 
				out.write(toString()); 
				out.close(); 
		} 
		catch (IOException e1) 
		{ 
			System.out.println("Exception Occurred" + e1); 
		} 
	}
	
	public String toString()
	{	
		String result = "Alpharetta High School Roster";
		
		for (int i = 0; i < studentList.size(); i++)
		{
			result += "\nNo. " + (i+1) + " Alpharetta High School Student ------------------------\n"; // 24 dashes
			result += (studentList.get(i));
			result += "\n---";
		}
		
		return result;
	}
}