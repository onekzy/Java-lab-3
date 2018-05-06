package Dekanat;

import java.util.ArrayList;
import java.util.Random;

public class Dekanat {

    private static ArrayList<Student> students = new ArrayList<Student>();    //массив студентов
    private static ArrayList<Group> groups = new ArrayList<Group>();    //массив групп
    private static final String sep = System.getProperty("file.separator");
    private static final String fileDir = "src" + sep + "main" + sep + "resources" + sep + "file.json";    //Место расположение базы по умолчанию

    static void addStudent(Student stud) {
        students.add(stud);
    }

    static void addGroup(Group gr){
        groups.add(gr);
    }

    //Добавления переданного количества оценок всем студентам
    public static void addMarksStudent(int namber) {
        Random rand = new Random();
        for (Student stud : students) {
            for (int i = 0; i < namber; i++) {
                stud.addMark(rand.nextInt(4) + 1);
            }
        }
    }

    //Добавление по одной оценке всем студентам
    public static void addMarksStudent() {
        addMarksStudent(1);
    }


    //Инициализация выборов/перевыборов старост в группах исходя из успеваемости
    public static void initiationOfElectionsInGroups() {
        for (Group gr : groups) {
            gr.setHeadGroup();
        }
    }

    //Отчисление студентов чей балл ниже 3
    public static void removeStudentsFromGroup() {
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
    public static void transferOfStudentToGroup(Student stud, Group gr) {
        stud.getGroup().removeStudentFromGroup(stud);
        gr.addStudentToGroup(stud);
    }

    //Получение всей информации о группах и студентах
    public static String getDataString(){
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
    public static void loadStudentAndGroupFromFile(String fileName) {

        ToFromJson.readJson(students, groups, fileName);
    }

    //Загрузка данных из файла
    public static void loadStudentAndGroupFromFile() {

        ToFromJson.readJson(students, groups, fileDir);
    }

    //Загрузка данных в файл
    public static void writeStudentAndGroupToFile(String fileName) {

        ToFromJson.writeJson(students, groups, fileName);
    }

    //Загрузка данных в файл
    public static void writeStudentAndGroupToFile() {

        ToFromJson.writeJson(students, groups, fileDir+1);

    }
}
