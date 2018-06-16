package Dekanat;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StudentTest {
    String titleGroup1 = "Group1";
    Group gr1 = new Group(titleGroup1);
    String fio1 = "Irina Gusman Igorevna";
    int id1 = 1;
    String fio2 = "Ivan Gusman Gorin";
    int id2 = 2;
    Student st1 = new Student(id1, fio1);
    Student st2 = new Student(id2, fio2);

    @Test
    public void setGroup() {
        assertNull(st1.getGroup());
        st1.setGroup(gr1);
        assertEquals(gr1, st1.getGroup());
    }

    @Test
    public void getAverageMark() {
        st1.addMark(2, 4);
        assertEquals(3, st1.getAverageMark(),0.01);
    }

    @Test
    public void getGroup() {
        st1.setGroup(gr1);
        assertEquals(gr1,st1.getGroup());
    }

    @Test
    public void getId() {
        assertEquals(st1.getId(), id1);
    }

    @Test
    public void getFio() {
        assertEquals(st1.getFio(),fio1);
    }

    @Test
    public void addMark() {
        st1.addMark(0,1,2,3,4,5,6);
        List<Integer> mark;
        mark = Arrays.asList(1,2,3,4,5);
        assertEquals(mark, st1.getMarks());
    }

    @Test
    public void getMarks() {
        st1.addMark(1,2,3,4,5);
        List<Integer> mark;
        mark = Arrays.asList(1,2,3,4,5);
        assertEquals(mark, st1.getMarks());
    }
}