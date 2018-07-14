import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DekanatTest {
    Dekanat dekanatTest = new Dekanat();
    List<Group> groupList = new ArrayList<Group>();
    List<Student> studentList = new ArrayList<Student>();
    Group groupTest1 = new Group("Metallica");
    Group groupTest2 = new Group("Manowar");
    Student studentTest1 = new Student(1, "James Hetfield");
    Student studentTest2 = new Student(2, "Lars Ulrich");
    Student studentTest3 = new Student(3, "Kirk Hammett");
    Student studentTest4 = new Student(4, "Cliff Burton");

    @Test
    public void addMarksToStudents() {
        groupTest1.addStudent(studentTest1);
        groupTest1.addStudent(studentTest2);
        groupList.add(groupTest1);
        studentList.add(studentTest1);
        studentList.add(studentTest2);
        dekanatTest.setGroups(groupList);
        dekanatTest.setAllStudents(studentList);
        assertEquals(0, studentTest1.getMarks().size());  //проверка количества оценок до начисления
        dekanatTest.addMarksToStudents(5);               //всем студентам начислено по 5 случайных оценок
        assertEquals(5, studentTest1.getMarks().size());  //проверка на количество оценок у студента
    }

    @Test
    public void removeStudent() {
        groupTest1.addStudent(studentTest1);
        groupTest1.addStudent(studentTest2);
        studentTest1.setGroup(groupTest1);
        studentTest2.setGroup(groupTest1);
        groupList.add(groupTest1);
        studentList.add(studentTest1);
        studentList.add(studentTest2);
        dekanatTest.setGroups(groupList);
        dekanatTest.setAllStudents(studentList);
        assertTrue(studentTest1.getGroup() == groupTest1);//проверка до отчисления студента из группы
        dekanatTest.removeStudent(studentTest1);
        assertTrue(studentTest1.getGroup() == null);      //проверка состоит ли студент в группе после удаления
    }

    @Test
    public void transferStudent() {
        groupTest1.addStudent(studentTest1);
        groupTest1.addStudent(studentTest2);
        studentTest1.setGroup(groupTest1);
        studentTest2.setGroup(groupTest1);
        groupList.add(groupTest1);
        groupList.add(groupTest2);
        studentList.add(studentTest1);
        studentList.add(studentTest2);
        dekanatTest.setGroups(groupList);
        dekanatTest.setAllStudents(studentList);
        dekanatTest.transferStudent(studentTest1, groupTest2);    //перевод студента из 1й группы во 2ю
        assertTrue(studentTest1.getGroup() != groupTest1);//проверка не состоит ли он до сих пор в 1й группе
        assertEquals(groupTest2, studentTest1.getGroup());        //проверка находится ли студент во 2й группе
    }

    @Test
    public void removeBadStudents() {
        groupTest1.addStudent(studentTest1);
        groupTest1.addStudent(studentTest2);
        studentTest1.setGroup(groupTest1);
        studentTest2.setGroup(groupTest1);
        groupList.add(groupTest1);
        studentList.add(studentTest1);
        studentList.add(studentTest2);
        dekanatTest.setGroups(groupList);
        dekanatTest.setAllStudents(studentList);
        for (int i = 0; i < 5; i++) {                              //создаем студента-двоичника и студента-хорошиста
            studentTest1.addMark(2);
            studentTest2.addMark(4);
        }
        dekanatTest.removeBadStudents();                           //отчисляем студентов со средним баллом < 3.0
        assertEquals(null, studentTest1.getGroup());       //проверка отчислен ли двоичник
        assertEquals(groupTest1, studentTest2.getGroup());         //проверка остался ли хорошист
    }

    @Test
    public void electionHead() {
        groupTest1.addStudent(studentTest1);
        groupTest1.addStudent(studentTest2);
        groupTest2.addStudent(studentTest3);
        groupTest2.addStudent(studentTest4);
        studentTest1.setGroup(groupTest1);
        studentTest2.setGroup(groupTest1);
        studentTest3.setGroup(groupTest2);
        studentTest4.setGroup(groupTest2);
        groupList.add(groupTest1);
        groupList.add(groupTest2);
        studentList.add(studentTest1);
        studentList.add(studentTest2);
        studentList.add(studentTest3);
        studentList.add(studentTest4);
        dekanatTest.setGroups(groupList);
        dekanatTest.setAllStudents(studentList);
        assertTrue(groupTest1.getHead() == null);           //проверка был ли староста в 1й группе
        assertTrue(groupTest2.getHead() == null);           //проверка был ли староста во 2й группе
        dekanatTest.electionHead();
        assertTrue(groupTest1.getHead() != null);           //проверка появился ли староста в 1й группе
        assertTrue(groupTest2.getHead() != null);           //проверка появился ли староста во 2й группе
    }
}