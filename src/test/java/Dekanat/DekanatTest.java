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
        assertNull(dekanat.newStudent(id1,fio2));
        assertEquals(dekanat.getNumStudents(),1);
    }

    @Test
    public void newGroup() {
        Dekanat dekanat = new Dekanat();
        Group gr;
        assertEquals(0, dekanat.getNumGroups());
        assertNotNull(gr = dekanat.newGroup(titleGroup1));
        assertEquals(1, dekanat.getNumGroups());
        assertNull(dekanat.newGroup(titleGroup1));
        assertEquals(1, dekanat.getNumGroups());
    }

    @Test
    public void addMarksStudent1() {
        assertEquals(0, testSt1.getMarks().size());
        assertEquals(0, testSt2.getMarks().size());
        testDekanat.addMarksStudent(5);
        assertEquals(5, testSt1.getMarks().size());
        assertEquals(5, testSt2.getMarks().size());
    }

    @Test
    public void addMarksStudent2() {
        assertEquals(0, testSt1.getMarks().size());
        assertEquals(0, testSt2.getMarks().size());
        testDekanat.addMarksStudent();
        assertEquals(1, testSt1.getMarks().size());
        assertEquals(1, testSt2.getMarks().size());
    }

    @Test
    public void removeStudent1() {
        assertEquals(2, testDekanat.getNumStudents());
        assertEquals(2, testGr1.getNumStudents());
        assertTrue(testDekanat.removeStudent(testSt1));
        assertEquals(1, testDekanat.getNumStudents());
        assertEquals(1, testGr1.getNumStudents());
    }

    @Test
    public void removeStudent2() {
        assertEquals(2, testDekanat.getNumStudents());
        assertEquals(2, testGr1.getNumStudents());
        assertTrue(testDekanat.removeStudent(testSt2.getId()));
        assertEquals(1, testDekanat.getNumStudents());
        assertEquals(1, testGr1.getNumStudents());
    }

    @Test
    public void removeStudentMark() {
        assertEquals(2, testDekanat.getNumStudents());
        assertEquals(2, testGr1.getNumStudents());
        testSt1.addMark(2);
        testSt2.addMark(4);
        testDekanat.removeStudentMark(3);
        assertEquals(1, testDekanat.getNumStudents());
        assertEquals(1, testGr1.getNumStudents());
        testDekanat.removeStudentMark(4.2);
        assertEquals(0, testDekanat.getNumStudents());
        assertEquals(0, testGr1.getNumStudents());
    }

    @Test
    public void addStudentToGroup() {
        Group gr = testDekanat.newGroup(titleGroup3);
        assertEquals(0, gr.getNumStudents());
        assertNotNull(testSt1.getGroup());
        assertNotEquals(gr, testSt1.getGroup());
        testDekanat.addStudentToGroup(testSt1, gr);
        assertEquals(1, gr.getNumStudents());
        assertEquals(gr, testSt1.getGroup());

    }

    @Test
    public void searchStudent() {
        assertEquals(testSt1, testDekanat.searchStudent(id1));
        assertEquals(testSt2, testDekanat.searchStudent(fio2));
        assertNull(testDekanat.searchStudent(fio3));

    }

    @Test
    public void searchGroup() {
        assertEquals(testGr1, testDekanat.searchGroup(titleGroup2));
    }

    @Test
    public void saveStudentAndGroup() {
        Dekanat dekanat = new Dekanat();
        dekanat.saveStudentAndGroup("/fileSave.json");
        assertEquals(0, dekanat.getNumStudents());
        assertEquals(0, dekanat.getNumGroups());
        dekanat.loadStudentAndGroup("/fileSave.json");
        assertEquals(0, dekanat.getNumStudents());
        assertEquals(0, dekanat.getNumGroups());
        dekanat.loadStudentAndGroup();
        assertEquals(3, dekanat.getNumStudents());
        assertEquals(2, dekanat.getNumGroups());
        dekanat.saveStudentAndGroup("/fileSave.json");
        Dekanat newDekanat = new Dekanat();
        assertEquals(0, newDekanat.getNumStudents());
        assertEquals(0, newDekanat.getNumGroups());
        newDekanat.loadStudentAndGroup("/fileSave.json");
        assertEquals(3, newDekanat.getNumStudents());
        assertEquals(2, newDekanat.getNumGroups());
        assertEquals(dekanat.getDataStudentsAndGroup(),newDekanat.getDataStudentsAndGroup());
    }

    @Test
    public void loadStudentAndGroup() {
        Dekanat dekanat = new Dekanat();
        assertEquals(0, dekanat.getNumStudents());
        assertEquals(0, dekanat.getNumGroups());
        dekanat.loadStudentAndGroup();
        assertEquals(3, dekanat.getNumStudents());
        assertEquals(2, dekanat.getNumGroups());
    }
}