package Dekanat;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id; //Идентификационный номер
    private String fio;
    private Group group = null;
    private ArrayList<Integer> marks = new ArrayList<Integer>();   //Массив оценок

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

    ArrayList<Integer> getMarks() {
        return new ArrayList<Integer>(marks);
    }

    public String getTitleGroup() {
        if (group != null) {
            return group.getTitle();
        } else {
            return null;
        }
    }

    public String toString() {
        String outStr = "ID = " + id + ", FIO: " + fio + ", average mark: " + getAverageMark().toString().substring(0, 3);
        if (group != null) {
            outStr += ", group: " + getGroup().getTitle();
        }
        return outStr;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().getTypeName().equals("Dekanat.Student")) {
            Student stud = (Student) obj;
            if (stud.getFio().equals(this.getFio()) &&
                    stud.getId() == this.getId() &&
                    this.getMarks().size() == stud.getMarks().size()) {
                List marksIn = stud.getMarks();
                List markThis = this.getMarks();
                int size = stud.getMarks().size();
                for (int i = 0; i < size; i++) {
                    if (marksIn.get(i) != markThis.get(i)) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
}
