package Dekanat;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id; //Идентификационный номер
    private String fio;
    private Group group = null;
    private List<Integer> marks = new ArrayList<Integer>();   //Массив оценок

    Student(int id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    void setGroup(Group gr) {
        group = gr;
    }

    /**
     * Рассчёт среднего балла
     *
     * @return средний балл
     */
    public Double getAverageMark() {
        int averageMark = 0;
        if (marks.size() == 0) {
            return 0.0;
        }
        for (Integer mark : marks) {
            averageMark += mark;
        }
        return (double) averageMark / marks.size();
    }

    Group getGroup() {
        return this.group;
    }

    public int getId() {
        return this.id;
    }

    public String getFio() {
        return this.fio;
    }

    //Добавление оценки
    void addMark(int... marksIn) {
        for (int mark : marksIn) {
            if (mark >= 1 && mark <= 5) {
                this.marks.add(mark);
            }
        }
    }

    List<Integer> getMarks() {
        return new ArrayList<Integer>(marks);
    }

    public String toString() {
        String outStr = "ID = " + id + ", FIO: " + fio + ", average mark: " + getAverageMark().toString().substring(0, 3);
        if (group != null) {
            outStr += ", group: " + getGroup().getTitle();
        }
        return outStr;
    }
}