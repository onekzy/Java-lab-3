import Dekanat.Dekanat;

public class DekanatDemo {
    public static void main(String[] args) {
        Dekanat myDeckanat = new Dekanat();
        myDeckanat.loadStudentAndGroupFromFile();
        System.out.print(myDeckanat.getDataString());
        myDeckanat.newStudent("Andrei", 24);
        myDeckanat.addGroup("Группа №5");
        myDeckanat.initiationOfElectionsInGroups();
        myDeckanat.addMarksStudent(5);
        System.out.print(myDeckanat.getDataString());
        myDeckanat.writeStudentAndGroupToFile();
    }
}
