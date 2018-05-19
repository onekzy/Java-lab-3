package Dekanat;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Dekanat {

    private ArrayList<Student> students = new ArrayList<Student>();    //массив студентов
    private ArrayList<Group> groups = new ArrayList<Group>();    //массив групп


    public Student newStudent(String fio, int id) {
        if(students.stream().filter((st)-> st.getID() == id).findFirst().orElse(null) == null) {
            Student newStud = new Student(id, fio);
            students.add(newStud);
            return newStud;
        }else{
            return null;
        }
    }

    public Group addGroup(String title){
        if(groups.stream().filter((gr)-> gr.getTitle().equals(title)).findFirst().orElse(null) == null) {
            Group newGroup = new Group(title);
            groups.add(newGroup);
            return newGroup;
        }else{
            return null;
        }
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
    public boolean transferOfStudentToGroup(Student stud, Group gr) {
        if(students.contains(stud) && groups.contains(gr)) {
            stud.getGroup().removeStudentFromGroup(stud);
            gr.addStudentToGroup(stud);
            return true;
        }
        return false;
    }

    //Получение всей информации о группах и студентах
    public String getDataString(){
        String outStr = "\tГруппы:\n";
        for(int i = 0; i < groups.size(); i++){
            outStr += "Title: " + groups.get(i).getTitle() + ", num students = " +  groups.get(i).getNumStudents() + ", average mark = " + groups.get(i).getAverageMark().toString().substring(0,3);
            if(groups.get(i).getHead()!= null){
                outStr+=", head name = " + groups.get(i).getHead().getFio() + ", head ID = " + groups.get(i).getHead().getID();
            }
            outStr += "\n";
        }
        outStr += "\tСтуденты:\n";
        for(int i = 0; i < students.size(); i++) {
            outStr += "ID: " + students.get(i).getID() + ", fio: " + students.get(i).getFio() + ", average mark = " +
                    students.get(i).getAverageMark().toString().substring(0, 3) +
                    ", group: " + students.get(i).getTitleGroup() + "\n";
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


    //Поиск студента по ID
    public Student searchStudent(int id) {
        return students.stream().filter((st)-> st.getID() == id).findFirst().orElse(null);
    }

    //Поиск студента по ФИО
    public Student searchStudent(String fio) {
        return students.stream().filter((st)-> st.getFio().equals(fio)).findFirst().orElse(null);
    }

    //Поиск группы по title
    public Group searchGroup(String title) {
        return groups.stream().filter((gr)-> gr.getTitle().equals(title)).findFirst().orElse(null);
    }
}

