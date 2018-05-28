import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;                                                 //id студента
    private String fio;                                             //ФИО студента
    private Group group;                                            //группа студента
    private List<Integer> marks;                                    //оценки студента
    private int num;                                                //количество оценок

    public Student(int id, String fio) {                            //конструктор
        this.id = id;
        this.fio = fio;
        group = null;
        marks = new ArrayList<Integer>();
        num = marks.size();
    }

    public int getId() {
        return id;
    }
    public String getFio() {
        return fio;
    }
    public Group getGroup() {
        return group;
    }
    public List<Integer> getMarks() {
        return marks;
    }
    public int getNum() {
        return num = marks.size();
    }

    public void setMarks(List<Integer> marks) {
        this.marks = marks;
    }

    //Зачисление в группу
    public void setGroup(Group group) {
        this.group = group;
    }

    //Добавление оценки студенту
    public void addMark(int mark) {
        marks.add(mark);
    }

    //Вычисление средней оценки студента
    public double findAvMarkStudent(List<Integer> marks){
        double averageMark = 0.0d;
        for(Integer mark: marks)
            averageMark += mark;
        averageMark /= marks.size();
        return averageMark;
    }

    @Override
    public String toString() {
        return  "ID:" + id +
                ", ФИО: \"" + fio + "\"" +
                ", Группа: " + group +
                ", Оценки:" + marks;
    }

}
