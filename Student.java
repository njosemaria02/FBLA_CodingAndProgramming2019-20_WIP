public class Student {
	private String myFirstName, myLastName;
	private int myStudentId, myGradeLevel;
	private double myTotalServiceHours;
	private boolean communityEligibility, serviceEligibility, achieveEligibility = false;
	
	// constructors --------------------------------------------------------------------------
	public Student(String firstName, String lastName, int ID, int grade, double totalHours)
	{
		myFirstName = firstName;
		myLastName = lastName;
		myStudentId = ID;
		myGradeLevel = grade;
		myTotalServiceHours = totalHours;
	}
	
	public Student ()
	{
		myFirstName = "";
		myLastName = "";
		myStudentId = 0;
		myGradeLevel = 0;
		myTotalServiceHours = 0;
	}
	
	// public methods ----------------------------------------------------------------------
	// accessor methods **********************************
	public String getFirstName()
	{	return myFirstName; }
	
	public String getLastName()
	{	return myLastName;	}
	
	public String getFullName()
	{	return myFirstName + " " + myLastName; }
	
	public int getStudentId()
	{	return myStudentId;	}
	
	public int getGradeLevel()
	{	return myGradeLevel; }
	
	public double getTotalServiceHours()
	{	return myTotalServiceHours;	}
	
	/* CSA Community (50 hours)
	CSA Service (200 hours)
	CSA Achievement (500 hours) */
	
	public boolean isCommunityEligibility() 
	{	
		if (myTotalServiceHours >= 50)
			setCommunityEligibility(true);
		
		return communityEligibility;
	}

	public boolean isServiceEligibility() 
	{
		if (myTotalServiceHours >= 200)
			setServiceEligibility(true);
		
		return serviceEligibility;
	}

	public boolean isAchieveEligibility() 
	{
		if (myTotalServiceHours >= 500)
			setAchieveEligibility(true);
		
		return achieveEligibility;
	}
	// mutator methods ***************************************

	public void setMyFirstName(String newFirstName) {
		myFirstName = newFirstName;
	}

	public void setMyLastName(String newLastName) {
		myLastName = newLastName;
	}

	public void setMyStudentId(int newStudentId) {
		myStudentId = newStudentId;
	}
	
	public void setMyGradeLevel(int newGradeLevel) {
		myGradeLevel = newGradeLevel;
	}

	public void setMyTotalServiceHours(double newTotalServiceHours) {
		myTotalServiceHours = newTotalServiceHours;
	}

	public void setCommunityEligibility(boolean newCommunityEligibility) {
		communityEligibility = newCommunityEligibility;
	}

	public void setServiceEligibility(boolean newServiceEligibility) {
		serviceEligibility = newServiceEligibility;
	}

	public void setAchieveEligibility(boolean newAchieveEligibility) {
		achieveEligibility = newAchieveEligibility;
	}
	
	// toString ----------------------------------------------------------------
	public String toString()
	{
		String result = "Full Name: " + myFirstName + " " + myLastName;
		
		result += "\nFirst Name: " + myFirstName;
		result += "\nLast Name: " + myLastName;
		result += "\nStudent Id: " + myStudentId;
		result += "\nGrade Level: " + myGradeLevel;
		result += "\nTotal Service Hours: " + myTotalServiceHours;
		result += "\nCommunity Award Eligibility (50+ hours): " + (this.isCommunityEligibility());
		result += "\nService Award Eligibility (200+ Hours): " + (this.isServiceEligibility());
		result += "\nAchieve Award Eligibility (500+ Hours): " + (this.isAchieveEligibility());
		
		return result;		
	}

}
