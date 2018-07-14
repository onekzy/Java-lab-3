package Dekanat;

import org.junit.Test;

import static org.junit.Assert.*;

public class GroupTest {
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

    {
        gr3.addStudentToGroup(st1);
        gr3.addStudentToGroup(st2);
        gr3.addStudentToGroup(st3);
        gr3.setHeadGroup(st3);

    }


    @Test
    public void searchStudent() {
        assertEquals(gr3.searchStudent(2), st2);
        assertEquals(gr3.searchStudent(fio2), st2);
        assertEquals(gr3.searchStudent(1), st1);
        assertEquals(gr3.searchStudent(fio1), st1);
        assertNull(gr3.searchStudent(99999));
    }


    @Test
    public void addStudentToGroup() {
        assertEquals(gr3.searchStudent(3),st3);
        assertEquals(gr3.getNumStudents(),3);
        assertEquals(gr1.getNumStudents(),0);
        gr1.addStudentToGroup(st3);
        assertNull(gr3.getHeadGroup());
        assertNull(gr3.searchStudent(3));
        assertEquals(gr3.getNumStudents(),2);
        assertEquals(gr1.searchStudent(3),st3);
        assertEquals(gr1.getNumStudents(),1);

    }

    @Test
    public void removeStudentFromGroup() {
        assertEquals(gr3.searchStudent(id3),st3);
        assertEquals(gr3.getHeadGroup(),st3);
        gr3.removeStudentFromGroup(st3);
        assertNull(gr3.searchStudent(3));
        assertNull(gr3.getHeadGroup());
    }

    @Test
    public void getAverageMark() {
        gr1.addStudentToGroup(st1);
        gr1.addStudentToGroup(st2);
        assertEquals(gr1.getAverageMark(), 0,0.01);
        st1.addMark(1,3);
        st2.addMark(3,5);
        assertEquals(gr1.getAverageMark(), 3,0.01);

    }

    @Test
    public void setHeadGroup1() {
        assertNull(gr1.getHeadGroup());
        assertFalse(gr1.setHeadGroup(st1));
        gr1.addStudentToGroup(st1);
        gr1.setHeadGroup(st1);
        assertEquals(st1, gr1.getHeadGroup());
    }

    @Test
    public void setHeadGroup2() {
        gr1.addStudentToGroup(st1);
        gr1.addStudentToGroup(st2);
        gr1.setHeadGroup(st1);
        st1.addMark(2, 2, 2, 2);
        st2.addMark(5, 5, 5, 5);
        gr1.setHeadGroup();
        assertEquals(gr1.getHeadGroup(), st2);
    }

    @Test
    public void getTitle() {
        assertEquals(titleGroup1, gr1.getTitle());
    }

    @Test
    public void getHeadGroup() {
        assertEquals(st3, gr3.getHeadGroup());
    }
}