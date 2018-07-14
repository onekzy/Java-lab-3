public class DekanatDemo {
    public static void main(String[] args) {
        Dekanat dekanatDemo = new Dekanat();
        dekanatDemo.addStudentsFromFile("src/main/resources/students.json");//получение списка студентов
        dekanatDemo.addGroupsFromFile("src/main/resources/groups.json");    //получение списка групп
        //создается 3 группы по 30 студентов
        dekanatDemo.fillGroupsByStudents();                                        //распределение студентов по группам
        dekanatDemo.addMarksToStudents(10);                               //начисление по 10 оценок студентам
        dekanatDemo.electionHead();                                                //выборы старост в группах
        dekanatDemo.printData();                                                   //вывод первоначального деканата
        for (int i = 0; i < dekanatDemo.statistic().size(); i++) {                 //статистика по деканату
            System.out.println(dekanatDemo.statistic().get(i));
        }
        //перевод студента с ID=5 в 3-ю группу
        Student studentDemo1 = dekanatDemo.getGroups().get(1).findingStudentByID(5);
        dekanatDemo.transferStudent(studentDemo1, dekanatDemo.getGroups().get(2));
        //отчисление неуспевающих студентов
        dekanatDemo.removeBadStudents();
        //отчисление студентов со средним баллом меньше 3.2 после сессии (начисление по 7 оценок)
        dekanatDemo.addMarksToStudents(7);
        for (int i = 0; i < dekanatDemo.getAllStudents().size(); i++) {
            Student studentDemo2 = dekanatDemo.getAllStudents().get(i);
            if (studentDemo2.findAvMarkStudent(studentDemo2.getMarks()) < 3.2)
                dekanatDemo.removeStudent(studentDemo2);
        }
        dekanatDemo.printData();                                                   //вывод измененного деканата
        for (int i = 0; i < dekanatDemo.statistic().size(); i++) {                 //окончательная статистика
            System.out.println(dekanatDemo.statistic().get(i));
        }
        dekanatDemo.printToFile("src/main/resources/result.json");          //печать в JSON-файл
    }
}
