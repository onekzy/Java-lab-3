import static org.junit.Assert.*;

public class GroupTest {

    Group g1 = new Group("ИСТ");
    Group g2 = new Group("АВТ");
    Student s1 = new Student(1, "Антонов Пётр");
    Student s2 = new Student(2, "Полежаева Ольга");

    public GroupTest() {
        g1.addStudent(s1);
        g2.addStudent(s2);
    }

    @org.junit.Test
    public void addStudent() {
        g1.addStudent(s1);
        assertEquals(g1.findId(1), s1);
        g2.addStudent(s2);
        assertEquals(g2.findId(2), s2);
    }


    @org.junit.Test
    public void findId() {
    }

    @org.junit.Test
    public void findFio1() {
        assertEquals(g1.findFio("Антонов Пётр"),s1);

    }

    @org.junit.Test
    public void findFio2() {

        assertEquals(g2.findFio("Полежаева Ольга"),s2);
    }



    @org.junit.Test
    public void getAvgMark1() {
        s1.add_mark(5);
        s1.add_mark(5);
        s1.add_mark(4);
        assertEquals(g1.getAvgMark(),4.6,0.1);
    }

    @org.junit.Test
    public void getAvgMark2() {
        s2.add_mark(5);
        s2.add_mark(4);
        s2.add_mark(3);
        s2.add_mark(2);
        assertEquals(g2.getAvgMark(),3.5,0.1);
    }
}
