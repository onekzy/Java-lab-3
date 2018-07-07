import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {
    Group g1=new Group(1,"ИТО");
    Group g2=new Group("2","ИТО");
    Student s1=new Student(1,"Смирнов Иван");
    Student s2=new Student("2","Петров Иван");

    @Test
    public void getName() {
        assertEquals(s1.getName(),"Смирнов Иван");
    }

    @Test
    public void getId() {
        assertEquals(s2.getId(),2);
    }

    @Test
    public void getGroupName() {
        s2.setStudentGroup(g1);
        assertEquals(s1.getGroupName(),"");
        assertEquals(s2.getGroupName(),g1.getTitle());
    }

    @Test
    public void setStudentGroup() {
        s1.setStudentGroup(g2);
        assertEquals(s1.getGroupName(),g2.getTitle());

    }

    @Test
    public void addMark() {
        try {
            s1.addMark(3);
            s1.addMark(5);
            //s1.addMark(6);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(s1.getAverageMark(),4.0,0.1);

    }

    @Test
    public void getAverageMark() {
        try {
            s1.addMark(3);
            s1.addMark(5);
            //s1.addMark(6);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(s1.getAverageMark(),4.0,0.1);
    }
}