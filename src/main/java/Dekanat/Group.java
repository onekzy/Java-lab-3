package Dekanat;

import java.util.ArrayList;

public class Group {
    private String title; //название группы
    private ArrayList<Student> students;    //массив из ссылок на студентов
    private Student head = null;   //ссылка на старосту (из членов группы)

    Group(String title) {
        this.title = title;
        this.students = new ArrayList<Student>();
    }

    //Поиск студента по ID
    Student searchStudent(int id) {
        for (Student stud : students) {
            if (stud.getID() == id) {
                return stud;
            }
        }
        return null;
    }

    //Поиск студента по ФИО
    Student searchStudent(String fio) {
        for (Student stud : students) {
            if (stud.getFio().equals(fio)) {
                return stud;
            }
        }
        return null;
    }

    //Добавление студента в группу
    void addStudentToGroup(Student stud) {
        if(stud.getGroup()!= null){
            stud.getGroup().removeStudentFromGroup(stud);
        }
        students.add(stud);
        stud.setGroup(this);
    }

    //изгнание студента из группы
    boolean removeStudentFromGroup(Student stud) {
        if (students.indexOf(stud) != -1) {
            students.remove(stud);
            stud.setGroup(null);
            return true;
        } else {
            return false;
        }
    }

    //Средний балл группы
    public double getAverageMark() {
        if (students.size() > 0) {
            double averageMark = 0;
            for (Student stud : students) {
                averageMark += stud.getAverageMark();
            }
            averageMark = averageMark / students.size();
            return averageMark;
        } else {
            return 0.0;
        }
    }

    //Избрание/переизбрание старостой переданного студента
    void setHeadGroup(Student stud) {
        head = stud;
    }

    //Избрание/переизбрание старосты исходя из успеваемости
    public void setHeadGroup() {
        Student headStud = null;
        double averageMark = 0.0;
        for(Student student:students){
            if(student.getAverageMark() > averageMark){
                headStud = student;
                averageMark = student.getAverageMark();
            }
        }
        this.head = headStud;
    }

    String getTitle(){
        return this.title;
    }

    int getNumStudents(){
        return students.size();
    }

    String getNameHead(){
        return head.getFio();
    }

    Student getHead(){
        return head;
    }

}