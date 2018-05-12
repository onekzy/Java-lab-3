import static org.junit.Assert.*;

public class StudentTest {

    @org.junit.Test
    public void getFIO() {
        Group group = new Group("NIIT");
        String[] strings={"Петров", "111"};
        Student student=new Student(strings,group);
        assertEquals("Петров",student.getFIO());
    }

    @org.junit.Test
    public void setGroup() {
        Group group = new Group("NIIT");
        Group group2 = new Group("NGTU");
        String[] strings={"Петров", "111"};
        Student student=new Student(strings,group);
        student.setGroup(group2);
        assertEquals(group2,student.getGroup());

    }

    @org.junit.Test
    public void getGroup() {
        Group group = new Group("NIIT");
        String[] strings={"Петров", "111"};
        Student student=new Student(strings,group);
        assertEquals(group,student.getGroup());
    }

    @org.junit.Test
    public void getID() {
        Group group = new Group("NIIT");
        String[] strings={"Петров", "111"};
        Student student=new Student(strings,group);
        assertEquals(111,student.getID());
    }

    @org.junit.Test
    public void getGroupName() {
        Group group = new Group("NIIT");
        String[] strings={"Петров", "111"};
        Student student=new Student(strings,group);
        assertEquals("NIIT",student.getGroupName());
    }

    @org.junit.Test
    public void addMark() {
        Group group = new Group("NIIT");
        String[] strings={"Петров", "111"};
        Student student=new Student(strings,group);
        student.addMark(5);
        assertEquals(5.0,student.averageRate(),0);
    }

    @org.junit.Test
    public void averageRate() {
        Group group = new Group("NIIT");
        String[] strings={"Петров", "111"};
        Student student=new Student(strings,group);
        student.addMark(5);
        student.addMark(4);
        assertEquals(4.5,student.averageRate(),0);
    }
}