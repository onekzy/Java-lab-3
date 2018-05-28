import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GroupTest {
    Group groupTest = new Group("Metallica");
    Student studentTest1 = new Student(1, "James Hetfield");
    Student studentTest2 = new Student(2, "Lars Ulrich");
    Student studentTest3 = new Student(3, "Kirk Hammett");
    Student studentTest4 = new Student(4, "Cliff Burton");

    @Test
    public void findingStudentByFIO() {
        groupTest.addStudent(studentTest1);
        groupTest.addStudent(studentTest2);
        groupTest.addStudent(studentTest3);
        groupTest.addStudent(studentTest4);
        assertEquals(groupTest.getStudents().get(3), groupTest.findingStudentByFIO("Cliff Burton"));
    }

    @Test
    public void findingStudentByID() {
        groupTest.addStudent(studentTest1);
        groupTest.addStudent(studentTest2);
        groupTest.addStudent(studentTest3);
        groupTest.addStudent(studentTest4);
        assertEquals(groupTest.getStudents().get(1), groupTest.findingStudentByID(2));
    }

    @Test
    public void findAvMarkGroup() {
        groupTest.addStudent(studentTest1);
        groupTest.addStudent(studentTest2);
        groupTest.addStudent(studentTest3);
        groupTest.addStudent(studentTest4);
        for (int i = 0; i < 5; i++) {
            studentTest1.addMark(2);
            studentTest2.addMark(3);
            studentTest3.addMark(4);
            studentTest4.addMark(5);
        }
        double avMarks = groupTest.findAvMarkGroup(groupTest);
        assertEquals(3.5, avMarks, 0.01);
    }

    @Test
    public void removeStudentFromGroup() {
        groupTest.addStudent(studentTest1);
        groupTest.addStudent(studentTest2);
        groupTest.addStudent(studentTest3);
        groupTest.addStudent(studentTest4);
        groupTest.removeStudentFromGroup(studentTest1.getId());
        assertEquals(null, groupTest.findingStudentByID(1));
    }
}