package Dekanat;

import org.junit.Test;

import static org.junit.Assert.*;

public class DekanatTest {

    @Test
    public void newStudenttTest() {
        Dekanat dekanat = new Dekanat();
        String fio1 = "Irina Gusman Igorevna";
        int id1 = 1;
        String fio2 = "Mark Ivanov Ignatov";
        int id2 = 2;
        Student stud1;
        Student stud2;
        assertNotNull(stud1 = dekanat.newStudent(fio1, id1));
        assertNotNull(stud2 = dekanat.newStudent(fio2, id2));
        assertTrue(dekanat.searchStudent(id1) == stud1);
        assertTrue(dekanat.searchStudent(id2) == stud2);
        assertNull(dekanat.newStudent(fio1, id1));
        assertTrue(stud1.getFio().equals(fio1) && stud1.getId() == id1);
        assertTrue(stud2.getFio().equals(fio2) && stud2.getId() == id2);
    }

    @Test
    public void newGroupTest() {
        Dekanat dekanat = new Dekanat();
        String titleGroup1 = "Group1";
        String titleGroup2 = "Group2";
        Group group1;
        Group group2;
        assertNotNull(group1 = dekanat.newGroup(titleGroup1));
        assertNotNull(group2 = dekanat.newGroup(titleGroup2));
        assertNull(dekanat.newGroup(titleGroup2));
        assertEquals(group1.getTitle(), titleGroup1);
        assertEquals(group2.getTitle(), titleGroup2);
        assertTrue(dekanat.searchGroup(titleGroup1) == group1);
        assertTrue(dekanat.searchGroup(titleGroup2) == group2);
    }

    @Test
    public void addMarksStudent() {
    }

    @Test
    public void initiationOfElectionsInGroups() {
    }

    @Test
    public void removeStudentTest() {



    }

    @Test
    public void addStudentToGroup() {
    }

    @Test
    public void getDataStudentsAndGroup() {
    }

    @Test
    public void loadStudentAndGroupTest() {
    }

    @Test
    public void saveStudentAndGroupTest() {
    }

    @Test
    public void searchGroupTest() {
        Dekanat dekanat = new Dekanat();
        String titleGroup1 = "Group1";
        String titleGroup2 = "Group2";
        Group group1;
        Group group2;
        group1 = dekanat.newGroup(titleGroup1);
        group2 = dekanat.newGroup(titleGroup2);
        assertTrue(group1 == dekanat.searchGroup(titleGroup1));
        assertTrue(group2 == dekanat.searchGroup(titleGroup2));
        assertFalse(group2 == dekanat.searchGroup(titleGroup2.substring(0,4)));
    }
}