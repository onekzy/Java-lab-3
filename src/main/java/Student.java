import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    @Expose
    private int id;
    @Expose
    private String fio;

    private Group group;
    @Expose
    private List<Integer> marks;
    @Expose
    private int num;

    public Student(int id, String fio, Group group, List<Integer> marks) {
        this.id = id;
        this.fio = fio;
        this.group = group;
        this.marks = marks;
        this.num = marks.size();
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public void setMarks(List<Integer> marks) {
        this.marks = marks;
    }


    @Override
    public String toString() {

        return fio + ", average " + average();
    }

// *****************************************************
//This method allow to calculate averages for each student:
// *****************************************************


    public double average() {
        double average = 0.0d;
        for(Integer mark : marks) {
            average+=mark;
        }
        return average /= marks.size();
    }
}
