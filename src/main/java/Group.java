import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Group  implements Serializable {
    private String title; //имя группы
    private List<Student> students;
    private Student head; //имя старосты
    private int maxStudentSize;

    public int getMaxStudentSize() {
        return maxStudentSize;
    }

    public void setMaxStudentSize(int maxStudentSize) {
        this.maxStudentSize = maxStudentSize;
    }

    public Group(String title, int maxStudentSize) {
        this.title = title;
        students = new ArrayList<Student>();
        this.maxStudentSize = maxStudentSize;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getHead() {
        return head;
    }

    public void setHead(Student head) {
        this.head = head;
    }

// *****************************************************
//Here we are calculating averages for each of the groups
// *****************************************************


    public double groupAverage() {

        double ave = 0.0d;
        for (Student stud : students) {
            double studAve = 0.0d;
            for (Integer mark : stud.getMarks()) {
                studAve+=mark;
            }
            ave += studAve / stud.getMarks().size();

        }
        return ave;
    }

// *****************************************************
//This method allow to add student to a group
// *****************************************************


    public boolean addStudent(Student student)  {
        if (students.size() == maxStudentSize ) {
            return false;
        }
        students.add(student);
        student.setGroup(this);

        return true;
    }

// *****************************************************
//This method allow to remove student from group using its id:
// *****************************************************

    public int removeStudent(int studentId) {
        Student student = null;
        for (Student s : students) {
            if (s.getId() == studentId) {
                student = s;
            }
        }
        if(student != null) {
            students.remove(student);
            student.setGroup(null);

        }
        return students.size();
    }
}
