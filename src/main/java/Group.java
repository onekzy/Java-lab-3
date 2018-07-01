
import java.util.ArrayList;
import java.util.Random;

public class Group {
	private String title;
	private ArrayList<Student> students;
	private Student head;

	//Creating a group with a name
	Group (String title) {
		this.title = title;
		students = new ArrayList<Student>();
		head = null;
	}

	//Adding a student
	public void addStudent(Student stud) {
		if(searchStudentName(stud.getName()) != null || searchStudentId(stud.getId()) != null) {
			System.out.println("This student " + stud.getName() + " is ");
		}
		students.add(stud);
	}

	public String getTitle() {
		return title;
	}

	public int getAmountOfStudents() {
		return students.size();
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	//Election of the head
	public Student setHead() {
		int randomVal = new Random().nextInt(students.size());
		head = students.get(randomVal);
		return head;
	}

	public Student getHead() {
		if(head == null)
			return null;
		return head;
	}

	//Student search by ID
	public Student searchStudentId(int id) {
		for (Student stud: students) {
			if(id == stud.getId())
				return stud;
		}
		return null;
	}

	//Search for a student by name
	public Student searchStudentName(String stud) {
		for(Student s:students) {
			if(stud.equals(s.getName())) {
				return s;
			}
		}
		return null;
	}

	//Average score in the group
	public double getGroupAverageMark(Group group) {
		double averageMarkGroup = 0.0d;

		for (int i = 0; i < students.size(); i++) {
			Student tmp = group.getStudents().get(i);
			double average = tmp.calcAverageMark();
			averageMarkGroup += average;
		}
		averageMarkGroup /= students.size();
		return averageMarkGroup;
	}

	//Removing student from a group
	public void removeStudent(Student stud) {
		if (students.contains(stud)) {
			students.remove(stud);
		}
	}
}