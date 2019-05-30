package parkingManagementSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;






/**
 * @author kkeogh
 * @version 1.0
 * @created 21-Sep-2018 4:55:20 PM
 */
public class College {

	private static final int MAXNOCOURSES = 10; // constant: maximum number of courses available at the College
	static final int maxStudentsC = 20;  // constant: maximum number of students in each course
	static final int maxStudentsT = 100;
	private String name;
	public ArrayList<Course> listOfCourses = new ArrayList<Course>(MAXNOCOURSES);  // create memory for an array of courses
	public ArrayList<Student> listOfStudents = new ArrayList<Student>();
	private int courseCount=0;
	public College(){
	}

	/**
	 * 
	 * @param aCourse
	 * Add a new course to the list of courses on offer at the college
	 */
	private void addACourse(Course aCourse){
		if (aCourse!=null && courseCount<MAXNOCOURSES) {  // only add a course if you have one and it will fit in list
			//listOfCourses[courseCount++] = aCourse;
			listOfCourses.add(aCourse);

		}
	}
	
	public void addAStudent(Student aStudent) {
		if(aStudent != null)
			listOfStudents.add(aStudent);
	}

	public void saveCourse(Course c) {
		try {	
			FileOutputStream outputFile = new FileOutputStream("Courses.txt", true);
			PrintStream outFile = new PrintStream (outputFile);
			outFile.println(c);
			outFile.close();
		}
		catch (Exception e) {
			System.out.println("File Not Found");
		}
		addACourse(c);	
	}
	
	public String showAllStudents() {
		String result =" ";
		if (this.courseCount >0)
			for (Course c : listOfCourses) {
				if (c!=null)
					result += c.getAllEnrolled();
		}
		return result;
	}

//	public Student[][] getAllStudentsLists() {
//		// return an array of student lists including all the students for each courses in the college, 
//		Student[][] allStudents  = new Student[College.MAXNOCOURSES][College.maxStudentT];
//		int index=0;
//		
//		for (Course c : listOfCourses) {
//			if (c!=null) allStudents[index++] = c.getAllStudentsList();
//		}
//			
//		return allStudents;
//	}
	public Student[] getAllStudents() {
		Student[] myList = new Student[College.maxStudentsT];
		int index = 0;
		for (Student s: listOfStudents)
			myList[index++]=s;
		//System.out.println(myList);
		return myList;
	}
	
	public Course[] getAllCourses() {
		// return a collection containing all the courses offered by College
		Course[] myList = new Course[College.MAXNOCOURSES];
		int index = 0;
		for (Course c : listOfCourses) // build an array with details of each Course 
			myList[index++] = c;
		return myList;
	}
	
	
	public int getCourseCount() {
		return this.courseCount;
	}

	
	public String toString () {
		String allCoursesString = "";
		for (Course c : listOfCourses) // build a string with details of each Course in the array of courses
			allCoursesString += c.toString() + "\n";
		return ("College offers the following courses : \n" + allCoursesString);
	}
	
	public void opencourse () {
		try {	
			Scanner inputFile = new Scanner (new File ( "Courses.txt" ));
				String firstLine = inputFile.nextLine();
				System.out.println("Data from file: " + firstLine);
				inputFile.close();
		}
		catch (Exception e) {
			System.out.println("File Not Found");
		}
	}
	
	//bennett gen all courses to file
	public void gencourse () {
		try {
			File f = new File("Coursereport.txt");
			PrintWriter pw = new PrintWriter (f);
			for (Course a: this.listOfCourses) {
				pw.println(a.toString() + "\n");
			}
			pw.flush();
			pw.close();
			System.out.println("Generated into Coursereport.txt");
		}
			catch (FileNotFoundException e) {
				e.printStackTrace();
				}
			
	}
	//bennett delete course (broken)
	public void remove(String cans) {
		if (listOfCourses.contains(cans)) {
			int ind = listOfCourses.indexOf(cans);
			listOfCourses.remove(ind);
			System.out.println(cans + " has been deleted.");
	}
		else {
			System.out.println(cans + " not found");
		}
	}

}

	
	

	//end College