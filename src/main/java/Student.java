import java.util.ArrayList;

public class Student {
    String ID;
    String Fio;
    Group group;
    ArrayList<Integer> marks = new ArrayList<Integer>();

    Student(String ID, String Fio, Group group) {
        this.ID = ID;
        this.Fio = Fio;
        this.group = group;
    }

    Student(String ID, String Fio) {
        this.ID = ID;
        this.Fio = Fio;
    }

    public static Student createStudent(String ID, String Fio, Group group) {
        return new Student(ID, Fio, group);
    }

    public void toGroup(Group group) {
        this.group = group;
    }

    public void addMark(Integer mark) {
        marks.add(mark);
    }

    public int middleMark() {
        int sum = 0;
        for(Integer i : marks) {
            sum = sum + i;
        }
        return sum/marks.size();
    }
}
