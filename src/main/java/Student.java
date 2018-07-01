
import java.util.ArrayList;

public class Student {
	private int id;
	private String name;
	private ArrayList<Integer> marks;
	private Group group;

	public Student(int id, String name) {
		this.id = id;
		this.name = name;
		marks = new ArrayList<>();
		group = null;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Integer> getMarks() {
		return marks;
	}

	public String getGroupName() {
		if(group == null)
			return "";
		return group.getTitle();
	}

	public Group getGroup() {
		return group;
	}

	//Enrollment in a group
	public void setGroup(Group group){
		this.group = group;
	}

	//Adding a mark
	public void addMark(int mark) throws Exception {
		if(mark < 2 || mark > 5)
			throw new Exception("This number isn't a mark!");
		marks.add(mark);
	}

	//Average mark
	public double calcAverageMark() {
		double average = 0.0d;
		for (int mark : marks) {
			average += mark;
		}
		average /= marks.size();
		return average;
	}
}