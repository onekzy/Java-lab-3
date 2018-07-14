import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    Group g1=new Group("АВТ");
    Group g2=new Group("ИСТ");
    Student s1=new Student(1,"Антонов Пётр");
    Student s2=new Student(2,"Полежаева Ольга");


    @Test
    public void average_mark1() {
        s1.add_mark(3);
        s1.add_mark(4);
        s1.add_mark(5);
        assertEquals(s1.average_mark(),4.0,0.1);
    }

    @Test
    public void average_mark2() {
        s2.add_mark(5);
        s2.add_mark(5);
        s2.add_mark(3);
        assertEquals(s2.average_mark(),4.3,0.1);
    }

    @Test
    public void getFIO1() {
        assertEquals(s1.getFIO(s1),"Антонов Пётр");
    }

    @Test
    public void getFIO2() {
        assertEquals(s2.getFIO(s2),"Полежаева Ольга");
    }

    @Test
    public void getID1() {
        assertEquals(s1.ID,1);
    }

    @Test
    public void getID2() {
        assertEquals(s2.ID,2);
    }
}