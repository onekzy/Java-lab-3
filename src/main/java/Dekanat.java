
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Dekanat {
	private ArrayList<Student> students;
	private ArrayList<Group> groups;
	private ArrayList<Student> expelledStudents = new ArrayList<Student>();

	public Dekanat() {
		students = new ArrayList<Student>();
		groups = new ArrayList<Group>();
	}

	public void addStudentFromFile(File file) {
		String str;
		String[] strArr;
		int tmpId = 0;
		try {
			FileInputStream fileStream = new FileInputStream("students1.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));
			while ((str = br.readLine()) != null) {
				strArr = str.trim().split(",");
				students.add(new Student(tmpId, str));
				tmpId++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addGroupsFromFile(File file){
		String str;
		String[] strArr;
		try {
			FileInputStream fileStream = new FileInputStream("groups.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
			while((str = reader.readLine())!=null){
				groups.add(new Group(str));
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();

		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void fillGroups() {
		Random rnd  = new Random();
		int grpSize = groups.size();
		Group grpRnd;
		for(Student s:students) {
			grpRnd = groups.get(rnd.nextInt(grpSize));
			s.setGroup(grpRnd);
			grpRnd.addStudent(s);
		}
	}

	public void setRandMarks() {
		Random magic = new Random();
		try {
			for (Student std : students) {
				std.addMark(magic.nextInt(4) + 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void electionHead() {
		for (Group tmpGroup:groups) {
			if (tmpGroup.getHead() == null)
				tmpGroup.setHead();
		}
	}

	public Student searchStudents(String stud) {
		for (Student s : students) {
			if (s.getName().equals(stud))
				return s;
		}
		return null;
	}

	public Group searchGroups(String group) {
		for(Group gr:groups) {
			if(gr.getTitle().equals(group));
			return gr;
		}
		return null;
	}

	public void changeGroup(String stud, String group) {
		Student s = this.searchStudents(stud);
		Group g = this.searchGroups(group);
		try {
			if (null == s) {
				System.out.println("The student is not found " + stud);
			}
			if (null == g) {
				System.out.println("The group is not found " + group);
			}
			s.setGroup(g);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public void removeBadStudents() {
		for(int count = 0; count < students.size(); count++) {
			if(students.get(count).calcAverageMark() < 3.0) {
				students.remove(count);
			}
		}
	}

	public void printStudents() {
		for(Student std:students) {
			System.out.println(std.getId()+" "+std.getName()+" "+std.getMarks()+" "+std.getGroupName());
		}
		for(Group gr:groups) {
			System.out.println(gr.getTitle()+" Head is "+gr.getHead().getName()+" Average mark of group "+gr.getGroupAverageMark(gr));
		}
	}

	public void writeToFile() {
		Writer stdWriter;
		Writer grpWriter;
		try {
			stdWriter = new FileWriter("StudentsOutput.txt");
			for (Student s:students) {
				stdWriter.write(s.getId() + "," + s.getName() + "," +s.getGroupName() + "," + s.getMarks());
				stdWriter.write(System.getProperty("line.separator"));

			}
			stdWriter.flush();
			grpWriter = new FileWriter("GroupsOutput.txt");
			for (Group g:groups) {
				grpWriter.write(g.getTitle() + "," + g.getHead().getName());
				grpWriter.write(System.getProperty("line.separator"));
			}
			grpWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}