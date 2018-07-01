import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {
    Group g1 = new Group("Орки");
    Group g2 = new Group("Эльфы");
    Student s1 = new Student(1, "Jeff Bezos");
    Student s2 = new Student(2, "Bill Gates");

    @Test
    public void getName() {
        assertEquals(s1.getName(), "Jeff Bezos");
    }

    @Test
    public void getId() {
        assertEquals(s2.getId(), 2);
    }

    @Test
    public void getGroupName() {
        s2.setGroup(g1);
        assertEquals(s1.getGroupName(), "");
        assertEquals(s2.getGroupName(), g1.getTitle());
    }

    @Test
    public void getGroup() {
        s2.setGroup(g1);
        assertEquals(s1.getGroupName(), "");
        assertEquals(s2.getGroupName(), g1.getTitle());
    }

    @Test
    public void setGroup() {
        s1.setGroup(g2);
        assertEquals(s1.getGroupName(), g2.getTitle());
    }

    @Test
    public void addMark() {
        try{
            s1.addMark(4);
            s1.addMark(5);

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(s1.calcAverageMark(), 4.0, 0.5);
    }

    @Test
    public void calcAverageMark() {
        try{
            s1.addMark(4);
            s1.addMark(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}