import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Student {
    @Expose
    private int id;
    @Expose
    private String fio;
    private Group group;
    private ArrayList<Integer> marks;

    Student() {
        id = -1;
        fio = "";
        group = null;
        marks = new ArrayList<Integer>();
    }

    Student(int id, String fio) {
        this.id = id;
        this.fio = fio;
        group = null;
        marks = new ArrayList<Integer>();
    }

    public void setId(int id) {this.id = id;}
    public void setFio(String fio) {this.fio = fio;}
    public void setGroup(Group group) {this.group = group;}
    public void addMark(int mark) {marks.add(mark);}

    public int getId() {return id;}
    public String getFio() {return fio;}
    public Group getGroup() {return group;}
    public ArrayList<Integer> getMarks() {return marks;}

    public void clearData() {
        id = -1;
        group = null;
        marks = null;
    }

    public double calcAverMark() {
        double average = 0.0d;
        for (int mark : marks) {
            average += mark;
        }
        average /= marks.size();
        return average;
    }
}
