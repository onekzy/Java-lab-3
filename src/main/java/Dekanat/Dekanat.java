package Dekanat;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Dekanat {

    private ArrayList<Student> students = new ArrayList<Student>();    //массив студентов
    private ArrayList<Group> groups = new ArrayList<Group>();    //массив групп

    public void addStudent(Student stud) {
        students.add(stud);
    }

    public Student newStudent(String fio, int id) {
        Student newStud = new Student(id, fio);
        students.add(newStud);
        return newStud;
    }

    void addGroup(Group gr){
        groups.add(gr);
    }

    public Group addGroup(String gr){
        Group newGroup = new Group(gr);
        groups.add(newGroup);
        return newGroup;
    }

    //Добавления переданного количества оценок всем студентам
    public void addMarksStudent(int namber) {
        Random rand = new Random();
        for (Student stud : students) {
            for (int i = 0; i < namber; i++) {
                stud.addMark(rand.nextInt(4) + 1);
            }
        }
    }

    //Добавление по одной оценке всем студентам
    public void addMarksStudent() {
        addMarksStudent(1);
    }


    //Инициализация выборов/перевыборов старост в группах исходя из успеваемости
    public void initiationOfElectionsInGroups() {
        for (Group gr : groups) {
            gr.setHeadGroup();
        }
    }

    //Отчисление студентов чей балл ниже 3
    public void removeStudentsFromGroup() {
        for (Student stud : students) {
            if (stud.getAverageMark() < 3) {
                for (Group gr : groups) {
                    gr.removeStudentFromGroup(stud);
                }
                students.remove(stud);
                break;
            }
        }
    }

    //перевод студента из группы в группу
    public void transferOfStudentToGroup(Student stud, Group gr) {
        stud.getGroup().removeStudentFromGroup(stud);
        gr.addStudentToGroup(stud);
    }

    //Получение всей информации о группах и студентах
    public String getDataString(){
        String outStr = "\tГруппы:\n";
        for(int i = 0; i < groups.size(); i++){
            outStr += "Title: " + groups.get(i).getTitle() + ", num students = " +  groups.get(i).getNumStudents() + ", average mark = " + groups.get(i).getAverageMark() + "\n";
        }
        outStr += "\tСтуденты:\n";
        for(int i = 0; i < students.size(); i++){
            outStr += "ID: " + students.get(i).getID() + ", fio: " +  students.get(i).getFio() + ", average mark = " + students.get(i).getAverageMark() + ", group: " + students.get(i).getTitleGroup() + "\n";
        }
        return outStr;
    }

    //Загрузка данных из файла
    public boolean loadStudentAndGroupFromFile(String fileName) {
        //Проверка существования файла
        File meFile = new File(fileName);
        if(!meFile.exists()){
            return false;
        }
        ToFromJson.readJson(students, groups, meFile);
        return true;
    }

    //Загрузка данных из файла
    public boolean loadStudentAndGroupFromFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        File myFile = new File(classLoader.getResource("file.json").getFile());
        if(!myFile.exists()){
            return false;
        }
        ToFromJson.readJson(students, groups, myFile);
        return true;
    }

    //Загрузка данных в файл
    public boolean writeStudentAndGroupToFile(String fileName) {
    //Проверка существования файла
        File meFile = new File(fileName);
        ToFromJson.writeJson(students, groups, meFile);
        return true;
    }

    //Загрузка данных в файл
    public void writeStudentAndGroupToFile() {
        File meFile = new File("fileSave.json");
        ToFromJson.writeJson(students, groups, meFile);

    }
}

