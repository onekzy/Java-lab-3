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

    /**
     * Поиск студента по id
     * @param id id студента
     * @return найденный студент
     */
    public Student searchStudent(int id) {
        return students.stream().filter((st)-> st.getId() == id).findFirst().orElse(null);
    }

    /**
     * Поиск студента по ФИО
     * @param fio ФИО
     * @return найденный студент
     */
    public Student searchStudent(String fio) {
        return students.stream().filter((st)-> st.getFio().equals(fio)).findFirst().orElse(null);
    }

    /**
     * Добавление/перевод студента в группу
     * @param student переводимый студент
     */
    void addStudentToGroup(Student student) {
        if(student.getGroup()!= null){
            student.getGroup().removeStudentFromGroup(student);
        }
        students.add(student);
        student.setGroup(this);
    }

    /**
     * Отчисление переданного студента
     * @param student отчисляемый студент
     * @return успешность операции
     */
    boolean removeStudentFromGroup(Student student) {
        if(student.getGroup().getHead() == student){
            student.getGroup().setHeadGroup(null);
        }
        return students.remove(student);
    }

    /**
     * Вычисление среднего балла группы
      * @return
     */
    public Double getAverageMark() {
        if (students.size() > 0) {
            double averageMark = 0;
            for (Student stud : students) {
                averageMark += stud.getAverageMark();
            }
            return averageMark / students.size();
        } else {
            return null;
        }
    }

    /**
     * Избрание старостой переданного студента
     * @param student студент
     */
    boolean setHeadGroup(Student student) {
        if (students.contains(student)) {
            this.head = student;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Избрание старостой студента с наивысшим средним баллом
      */
    public void setHeadGroup() {
        Student headStud = null;
        double averageMark = 0.0;
        for(Student student:students){
            if(student.getAverageMark() >= averageMark){
                headStud = student;
                averageMark = student.getAverageMark();
            }
        }
        this.head = headStud;
    }

    public String getTitle(){
        return this.title;
    }

    public int getNumStudents(){
        return students.size();
    }

    public Student getHead(){
        return head;
    }

    public String toString() {
        String outStr = "Title: " + title + ", num students: " + students.size() + ", average mark = " + (getAverageMark() == null ? 0 : getAverageMark().toString().substring(0, 3));
        if (head != null) {
            outStr += ", head: " + head.getFio();
        }
        return outStr;
    }
}