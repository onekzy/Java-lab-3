import Dekanat.Dekanat;

public class DekanatDemo {
    public static void main(String[] args) {
        /*Student stud1 = new Student(1, "Мартьянов Андрей");
        stud1.addMark(1,2,3,4,5,3,4,3,4,3,3,5);
        Student stud2 = new Student(2, "Миронова Екатерина");
        stud2.addMark(5,4,3,5);
        Student stud3 = new Student(3, "Артур");
        stud3.addMark(2,4,5,5,5,5);
        Group gr1 = new Group("Группа №1");
        Group gr2 = new Group("Группа №2");
        stud1.addStudentToGroup(gr1);
        stud3.addStudentToGroup(gr2);
        System.out.print(Dekanat.getDataString());
        Dekanat.writeStudentAndGroupToFile();*/
        Dekanat myDeckanat = new Dekanat();
        myDeckanat.loadStudentAndGroupFromFile();
        System.out.print(myDeckanat.getDataString());
        myDeckanat.newStudent("Andrei", 31);
        myDeckanat.addGroup("Группа №5");
        myDeckanat.initiationOfElectionsInGroups();
        myDeckanat.addMarksStudent(5);
        System.out.print(myDeckanat.getDataString());
        myDeckanat.writeStudentAndGroupToFile();
    }
}
