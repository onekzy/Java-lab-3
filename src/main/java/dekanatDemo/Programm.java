package dekanatDemo;

import java.io.File;

public class Programm {
    public static void main(String[] args) {
        //1 создание деканата
        File studentsFile = new File("src/main/resources/students.xml");
        File groupsFile = new File("src/main/resources/groups.xml");
        Decanat decanat = new Decanat(studentsFile, groupsFile);
        //2-4 распределение студентов по группам и добавление им оценок
        distribution(decanat);
        printGroupData(decanat);
        //5 исключение неуспевающего студента во второй группе
        Decanat.Group second = decanat.findGroup("second");
        try {
            decanat.deductStudentsFromGroup(second,second.getIdLowAverageMark());
        } catch (DecanatException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        //6 перевод старосты из первой группы во вторую
        Decanat.Group first = decanat.findGroup("first");
        try {
            decanat.transferStudentToGroup(first.getHead(),second);
        } catch (DecanatException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        //7 исключить старосту из 2 группы
        try {
            decanat.deductStudentsFromGroup(second,second.getHead());
        } catch (DecanatException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        //8-9добавить нового человека в 3 и 1 группу
        Decanat.Student newStudent = decanat.getNewStudent("Хачипурян","Иван","Аванесович");
        Decanat.Group third = decanat.findGroup("third");
        try {
            decanat.setRandomMarks(newStudent);
            decanat.addStudentToGroup(newStudent,third);
        } catch (DecanatException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        newStudent = decanat.getNewStudent("Иванов","Евгений","Игоревич");
        try {
            decanat.setRandomMarks(newStudent);
            decanat.addStudentToGroup(newStudent,first);
        } catch (DecanatException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        decanat.writeDataToXML(new File("src/main/resources/data.xml"));
        Decanat decanat1 = new Decanat(new File("src/main/resources/data.xml"));
        printGroupData(decanat1);
    }

    private static void distribution(Decanat decanat) {
        try {
            for (int j = 0; j < decanat.getGroups().size(); j++) {
            Decanat.Group group = decanat.getGroups().get(j);
            for (int i = j*30; i < 30+j*30; i++) {

                    Decanat.Student nextStudent = decanat.getAllStudents().get(i);
                    decanat.addStudentToGroup(nextStudent, group);
                    decanat.setRandomMarks(nextStudent);
                }
                decanat.makeHeadOfGroup(group);
            }
        } catch (DecanatException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    private static void printGroupData(Decanat decanat) {
        for (int j = 0; j < decanat.getGroups().size(); j++) {
            Decanat.Group group = decanat.getGroups().get(j);
            System.out.println("group.getTitle() = " + group.getTitle());
            System.out.println("group.getCountStudents() = " + group.getCountStudents());
            System.out.println("group.getHead().getFIO().toString() = " + group.getHead().getFIO()[0]);
            System.out.println("group.getAverageMark() = " + group.getAverageMark());
            System.out.println();
        }
    }
}

/*
 это тестовый сценарий
 1 создание деканата в первый раз начинается с чтения данных о группах и студентах из xml файлов
 2 Первичное распределение по 30 человек в группу. всего 150 человек и 5 групп
 3 Назначаем оценки в рандоме каждому человеку по 10 шт функция setRandomMarks()
 4 после назначения оценок, выбираем старосту по самому высокому среднему баллу makeHeadOfGroup()
 5 найдем и исключим самого отстающего из 2 группы
 6 переведем старосту из 1 группы в 2 группу
 7 исключим старосту из 2 группы
 8 добавим нового человека в 1 и 3 группу
 9 добавим им оценки
 10 записать данные в data.xml
 11 создать новый деканат с чтением данных из data.xml
*/