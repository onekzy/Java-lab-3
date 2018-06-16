package Dekanat;

import org.junit.Test;

import static org.junit.Assert.*;

public class DekanatTest {
    String titleGroup1 = "Group1";
    Group gr1 = new Group(titleGroup1);
    String titleGroup2 = "Group2";
    Group gr2 = new Group(titleGroup2);
    String fio1 = "Irina Gusman Igorevna";
    int id1 = 1;
    String fio2 = "Ivan Gusman Gorin";
    int id2 = 2;
    Student st1 = new Student(id1, fio1);
    Student st2 = new Student(id2, fio2);

    String fio3 = "Vova Gusman Gorin";
    int id3 = 3;
    Student st3 = new Student(id3, fio3);
    String titleGroup3 = "Group3";
    Group gr3 = new Group(titleGroup3);
    Dekanat testDekanat = new Dekanat();


    Student testSt1;
    Student testSt2;
    Group testGr1;


    {
        gr3.addStudentToGroup(st1);
        gr3.addStudentToGroup(st2);
        gr3.addStudentToGroup(st3);
        gr3.setHeadGroup(st3);


        testSt1= testDekanat.newStudent(id1,fio1);
        testSt2 = testDekanat.newStudent(id2,fio2);
        Group gr = testDekanat.newGroup(titleGroup1);
        testGr1 = testDekanat.newGroup(titleGroup2);
        testDekanat.addStudentToGroup(testSt1, testGr1);
        testDekanat.addStudentToGroup(testSt2, testGr1);


    }


    @Test
    public void newStudent() {
        Dekanat dekanat = new Dekanat();
        assertEquals(dekanat.getNumStudents(),0);
        assertNotNull(st1 = dekanat.newStudent(id1,fio1));
        assertEquals(dekanat.getNumStudents(),1);
    }

    @Test
    public void newGroup() {
    }

    @Test
    public void addMarksStudent() {
    }

    @Test
    public void addMarksStudent1() {
    }

    @Test
    public void initiationOfElectionsInGroups() {
    }

    @Test
    public void removeStudent1() {
    }

    @Test
    public void removeStudent2() {
    }

    @Test
    public void removeStudentMark() {
    }

    @Test
    public void addStudentToGroup() {
    }

    @Test
    public void searchStudent1() {

    }

    @Test
    public void searchGroup() {

    }
}