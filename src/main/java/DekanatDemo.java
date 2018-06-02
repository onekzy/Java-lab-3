import Dekanat.Dekanat;
import Dekanat.Student;
import Dekanat.Group;

public class DekanatDemo {
    public static void main(String[] args) {
        Dekanat myDeckanat = new Dekanat();
        Student newStud1 = myDeckanat.newStudent("Katia", 1);
        Student newStud2 = myDeckanat.newStudent("Vova", 2);
        Student newStud3 = myDeckanat.newStudent("Irina", 3);
        Student newStud4 = myDeckanat.newStudent("Andrei", 3);

        Group group1 = myDeckanat.newGroup("Group1");
        myDeckanat.transferOfStudentToGroup(newStud1, group1);
        myDeckanat.transferOfStudentToGroup(newStud2, group1);
        myDeckanat.transferOfStudentToGroup(newStud3, group1);
        Group group2 = myDeckanat.newGroup("Group2");

        myDeckanat.transferOfStudentToGroup(newStud4, group2);

        myDeckanat.addMarksStudent(10);
        myDeckanat.initiationOfElectionsInGroups();
        System.out.println(myDeckanat.getDataStudentsAndGroup());
        myDeckanat.transferOfStudentToGroup(newStud1, group2);
        myDeckanat.addMarksStudent(10);
        myDeckanat.initiationOfElectionsInGroups();
        System.out.println(myDeckanat.getDataStudentsAndGroup());
    }
}
