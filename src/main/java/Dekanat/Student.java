package Dekanat;

import java.util.ArrayList;

public class Student {
    private int id; //Идентификационный номер
    private String fio;
    private Group group;
    private ArrayList<Integer> marks;   //Массив оценок

    Student(int id, String fio){
        this.id = id;
        this.fio = fio;
        this.marks = new ArrayList<Integer>();
    }

    void setGroup(Group gr){
        group = gr;
    }

    //Получение средней оценки
    public Double getAverageMark(){
        int averageMark = 0;
        for(Integer mark: marks){
            averageMark += mark;
        }
        return (double)averageMark/marks.size();
    }

    Group getGroup(){
        return this.group;
    }

    public int getID(){
        return this.id;
    }

    public String getFio(){
        return this.fio;
    }

    //Добавление оценки
     void addMark(int ...marksIn) {
        for (int mark : marksIn) {
            if (mark >= 1 && mark <= 5) {
                this.marks.add(mark);
            }
        }
    }

    ArrayList<Integer> getMarks(){
        return new ArrayList<Integer>(marks);
    }

    public String getTitleGroup(){
        if(group != null){
            return group.getTitle();
        }else{
            return null;
        }
    }

}
