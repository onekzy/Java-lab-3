import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Random;

public class Group {
    @Expose
    private String title;
    @Expose
    private ArrayList<Student> students;
    private Student head;
    private Random random;

    Group () {
        title = "";
        students = new ArrayList<Student>();
        head = null;
        random = new Random();
    }

    Group (String title) {
        this.title = title;
        students = new ArrayList<Student>();
        head = null;
        random = new Random();
    }

    public void setTitle(String title) {this.title = title;}
    public void addStudent(Student student) {
        students.add(student);
        student.setGroup(this);
    }
    public void setThisGroup() {
        for (Student st : students) {
            st.setGroup(this);
        }
    }

    public String getTitle() {return title;}
    public ArrayList<Student> getStudents() {return students;}
    public Student getHead() {return head;}

    public void assignHead() {
        int idx = random.nextInt(students.size());
        head = students.get(idx);
    }

    public Student searchStudent(int id) {
        for (Student st : students) {
            if (st.getId() == id) {
                return st;
            }
        }
        return null;
    }

    public double calcAverMark() {
        double average = 0.0d;
        for (Student st : students) {
            average += st.calcAverMark();
        }
        average /= students.size();
        return average;
    }

    public boolean removeStudent(int id) {
        Student st = searchStudent(id);
        if (st != null) {
            students.remove(st);
            if (st == head) {
                assignHead();
            }
            return true;
        }
        return false;
    }
}
