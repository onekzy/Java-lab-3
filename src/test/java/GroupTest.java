import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void getStudents() {
        ArrayList<Student> arr=new ArrayList<Student>();
        Group group=new Group("NIIT");
        String[] strings={"Петров", "111"};
        Student student=new Student(strings,group);
        arr.add(student);
        group.addStudent(student);
        assertEquals(arr,group.getStudents());

    }

    @Test
    public void getHeadName() {
        Group group=new Group("NIIT");
        String[] strings={"Петров", "111"};
        Student student=new Student(strings,group);
        group.addStudent(student);
        group.choiseHead();
        assertEquals("Петров",group.getHeadName());
    }

    @Test
    public void getTitle() {
        Group group=new Group("NIIT");
        assertEquals("NIIT",group.getTitle());
    }

    @Test
    public void addStudent() {
        Group group=new Group("NIIT");
        String[] strings={"Петров", "111"};
        Student student=new Student(strings,group);
        group.addStudent(student);
        assertEquals(1,group.getNum());
    }

    @Test
    public void choiseHead() {
        Group group=new Group("NIIT");
        String[] strings={"Петров", "111"};
        Student student=new Student(strings,group);
        group.addStudent(student);
        group.choiseHead();
        assertEquals("Петров",group.getHeadName());
    }

    @Test
    public void searchStudent() {
        Group group=new Group("NIIT");
        String[] strings={"Петров", "111"};
        Student student=new Student(strings,group);
        group.addStudent(student);
        assertEquals(true,group.searchStudent("Петров"));
    }

    @Test
    public void searchStudent1() {
        Group group=new Group("NIIT");
        String[] strings={"Петров", "111"};
        Student student=new Student(strings,group);
        group.addStudent(student);
        assertEquals(false,group.searchStudent("Иванов"));
    }

    @Test
    public void searchStudent2() {
        Group group=new Group("NIIT");
        String[] strings={"Петров", "111"};
        Student student=new Student(strings,group);
        group.addStudent(student);
        assertEquals(true,group.searchStudent(111));
    }

    @Test
    public void searchStudent3() {
        Group group=new Group("NIIT");
        String[] strings={"Петров", "111"};
        Student student=new Student(strings,group);
        group.addStudent(student);
        assertEquals(false,group.searchStudent(222));
    }

    @Test
    public void averageRateGroup() {
        Group group=new Group("NIIT");
        String[] string1={"Петров", "111"};
        Student student1=new Student(string1,group);
        group.addStudent(student1);
        String[] string2={"Иванов", "222"};
        Student student2=new Student(string2,group);
        group.addStudent(student2);
        student1.addMark(5);
        student2.addMark(4);
        assertEquals(4.5,group.averageRateGroup(),0);
    }

    @Test
    public void expellStudent() {
        Group group=new Group("NIIT");
        String[] string1={"Петров", "111"};
        Student student1=new Student(string1,group);
        group.addStudent(student1);
        String[] string2={"Иванов", "222"};
        Student student2=new Student(string2,group);
        group.addStudent(student2);
        group.expellStudent(student1);
        assertEquals(1,group.getNum());
    }
}