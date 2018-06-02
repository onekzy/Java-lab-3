import Dekanat.Dekanat;
import Dekanat.Student;
import Dekanat.Group;

public class DekanatDemo {
    public static void main(String[] args) {
        Dekanat myDeckanat = new Dekanat();
        Student newStud1 = myDeckanat.newStudent("Katia", 1);
        Student newStud2 = myDeckanat.newStudent("Vova", 2);
        Student newStud3 = myDeckanat.newStudent("Irina", 3);
        myDeckanat.addStudentToGroup(myDeckanat.newStudent("Andrei", 4), myDeckanat.newGroup("Group4"));

        Group group1 = myDeckanat.newGroup("Group1");
        myDeckanat.addStudentToGroup(newStud1, group1);
        myDeckanat.addStudentToGroup(newStud2, group1);
        myDeckanat.addStudentToGroup(newStud3, group1);
        Group group2 = myDeckanat.newGroup("Group2");

        myDeckanat.addStudentToGroup(newStud2, group2);

        myDeckanat.addMarksStudent(10);
        myDeckanat.initiationOfElectionsInGroups();
        System.out.println(myDeckanat.getDataStudentsAndGroup());
        myDeckanat.addStudentToGroup(newStud1, group2);
        myDeckanat.addMarksStudent(10);
        myDeckanat.initiationOfElectionsInGroups();
        System.out.println(myDeckanat.getDataStudentsAndGroup());
        myDeckanat.removeStudent("Andrei");
        myDeckanat.removeStudent(2.5);
        System.out.println(myDeckanat.getDataStudentsAndGroup());

    }
}
