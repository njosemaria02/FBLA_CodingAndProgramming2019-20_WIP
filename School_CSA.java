import java.util.ArrayList;

public class School_CSA {
	private ArrayList<Student> studentList;
	
	public School_CSA()
	{
		studentList = new ArrayList<Student>();
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