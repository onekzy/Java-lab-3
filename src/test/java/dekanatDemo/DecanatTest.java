package dekanatDemo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DecanatTest {
    private Decanat decanat;
    private Decanat.Student student;
    private Decanat.Group group;

    @Before
    public void setUp() {
        decanat = new Decanat();
        student = decanat.getNewStudent("Иванов", "Иван", "Иванович");
        group = decanat.getNewGroup("FirstGroup");
    }

    @Test
    public void getAllStudents() {
        ArrayList<Decanat.Student> list = new ArrayList<Decanat.Student>();
        list.add(student);
        list.add(decanat.getNewStudent("Surname1","Name1","Secondname1"));
        list.add(decanat.getNewStudent("Surname2","Name2","Secondname2"));
        list.add(decanat.getNewStudent("Surname3","Name3","Secondname3"));
        list.add(decanat.getNewStudent("Surname4","Name4","Secondname4"));
        assertTrue(list.containsAll(decanat.getAllStudents()));
    }

    @Test
    public void getGroups() {
        ArrayList<Decanat.Group> list = new ArrayList<Decanat.Group>();
        list.add(group);
        list.add(decanat.getNewGroup("Title1"));
        list.add(decanat.getNewGroup("Title2"));
        list.add(decanat.getNewGroup("Title3"));
        list.add(decanat.getNewGroup("Title4"));
        assertTrue(list.containsAll(decanat.getGroups()));
    }

    @Test
    public void getStudentsFromGroup() {
        ArrayList<Decanat.Student> list = new ArrayList<Decanat.Student>();
        Decanat.Student newStudent1 = decanat.getNewStudent("Surname1","Name1","Secondname1");
        Decanat.Student newStudent2 = decanat.getNewStudent("Surname2","Name2","Secondname2");
        Decanat.Student newStudent3 = decanat.getNewStudent("Surname3","Name3","Secondname3");
        Decanat.Student newStudent4 = decanat.getNewStudent("Surname4","Name4","Secondname4");
        Decanat.Group newGroup = decanat.getNewGroup("Title");
        list.add(newStudent1);
        list.add(newStudent2);
        list.add(newStudent3);
        list.add(newStudent4);
        assertTrue(list.containsAll(decanat.getStudentsFromGroup(newGroup)));
    }

    @Test
    public void getNewGroup() {
        Decanat.Group group = decanat.getNewGroup("GroupForTest");
        assertEquals("GroupForTest",group.getTitle());
        assertEquals(0,group.getCountStudents());
    }

    @Test
    public void getNewStudent() {
        Decanat.Student student = decanat.getNewStudent("TestSurname", "TestName", "TestSecondname");
        StringBuffer[] buff = student.getFIO();
        assertEquals(3,buff.length);
        assertEquals(2,student.getID());//первый создается перед тестом
        assertEquals("TestSurname",buff[0].toString());
        assertEquals("TestName",buff[1].toString());
        assertEquals("TestSecondname",buff[2].toString());
    }

    @Test
    public void addMarkToStudent() throws DecanatException {
        decanat.addMarkToStudent(student,2);
        decanat.addMarkToStudent(student,3);
        decanat.addMarkToStudent(student,4);
        decanat.addMarkToStudent(student,5);
        ArrayList<Integer> marks = student.getMarks();
        Integer [] expectMarks = {2,3,4,5};
        assertArrayEquals(marks.toArray(),expectMarks);
    }

    @Test(expected = DecanatException.class)
    public void addMarkToStudentIncorrectLess2() throws DecanatException {
        decanat.addMarkToStudent(student,1);
    }

    @Test(expected = DecanatException.class)
    public void addMarkToStudentIncorrectMore5() throws DecanatException {
        decanat.addMarkToStudent(student,6);
    }

    @Test(expected = DecanatException.class)
    public void addMarkToStudentNullStudent() throws DecanatException {
        Decanat.Student student1=null;
        decanat.addMarkToStudent(student1,6);
    }

    @Test
    public void setRandomMarks() throws DecanatException {
        assertEquals(0, student.getMarks().size());
        decanat.setRandomMarks(student);
        assertEquals(10, student.getMarks().size());
    }

    @Test(expected = DecanatException.class)
    public void setRandomMarksNullStudent() throws DecanatException {
        Decanat.Student student1=null;
        decanat.setRandomMarks(student1);
    }

    @Test
    public void addStudentToGroup() throws DecanatException {
        decanat.addStudentToGroup(student, group);
        assertEquals("FirstGroup", student.getGroupTitle());
    }

    @Test(expected = DecanatException.class)
    public void addStudentToGroupNotGroup() throws DecanatException {
        student.getGroupTitle();
    }

    @Test
    public void deductStudentsFromGroup() throws DecanatException {
        decanat.addStudentToGroup(student, group);
        assertEquals(1,group.getCountStudents());
        decanat.deductStudentsFromGroup(group, student);
        assertEquals(0,group.getCountStudents());
    }

    @Test
    public void transferStudentToGroup() throws DecanatException {
        decanat.addStudentToGroup(student,group);
        assertEquals("FirstGroup",student.getGroupTitle());
        Decanat.Group gr = decanat.getNewGroup("SecondGroup");
        decanat.transferStudentToGroup(student,gr);
        assertEquals("SecondGroup",student.getGroupTitle());
    }

    @Test(expected = DecanatException.class)
    public void transferStudentToGroupNullStudent() throws DecanatException {
        Decanat.Student student1 = null;
        Decanat.Group gr = decanat.getNewGroup("SecondGroup");
        decanat.transferStudentToGroup(student1,gr);
        assertEquals(0,gr.getCountStudents());
    }

    @Test(expected = DecanatException.class)
    public void transferStudentToGroupNullGroup() throws DecanatException {
        decanat.addStudentToGroup(student,group);
        assertEquals("FirstGroup",student.getGroupTitle());
        Decanat.Group gr = null;
        decanat.transferStudentToGroup(student,gr);

    }

    @Test
    public void findGroup() {
        Decanat.Group foundGroup = decanat.findGroup("FirstGroup");
        assertEquals("FirstGroup",foundGroup.getTitle());
    }

    @Test
    public void findGroupNotFound() {
        Decanat.Group foundGroup = decanat.findGroup("First");
        assertNull(foundGroup);
    }

    @Test
    public void makeHeadOfGroup() throws DecanatException {
        Decanat.Group newGroup = decanat.getNewGroup("TestGroup");
        Decanat.Student newStudent = decanat.getNewStudent("Surname", "Name", "Secondname");
        Decanat.Student newStudent2 = decanat.getNewStudent("Surname2", "Name2", "Secondname2");
        decanat.addStudentToGroup(newStudent,newGroup);
        decanat.addStudentToGroup(newStudent2,newGroup);
        decanat.addMarkToStudent(newStudent,5);
        decanat.addMarkToStudent(newStudent,3);
        decanat.makeHeadOfGroup(newGroup);
        assertEquals(newStudent,newGroup.getHead());
    }

    @Test
    public void makeHeadOfGroupWithoutStudents() throws DecanatException {
        Decanat.Group newGroup = decanat.getNewGroup("TestGroup");
        decanat.makeHeadOfGroup(newGroup);
        assertNull(newGroup.getHead());
    }
}