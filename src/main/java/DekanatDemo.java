import Dekanat.Dekanat;
import Dekanat.Student;
import Dekanat.Group;

public class DekanatDemo {
    public static void main(String[] args) {
        Dekanat myDeckanat = new Dekanat();
        Student newStud1 = myDeckanat.newStudent(1, "Katia");
        Student newStud2 = myDeckanat.newStudent(2,"Vova");
        Student newStud3 = myDeckanat.newStudent(3,"Irina");
        myDeckanat.addStudentToGroup(myDeckanat.newStudent(4,"Andrei"), myDeckanat.newGroup("Group4"));
        Boolean bool = newStud1.equals(newStud1);
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
        myDeckanat.loadStudentAndGroup();
        myDeckanat.saveStudentAndGroup();
        System.out.println(myDeckanat.getDataStudentsAndGroup());
    }
}
