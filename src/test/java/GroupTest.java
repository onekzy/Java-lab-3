import static org.junit.Assert.*;

public class GroupTest {
    Group g1=new Group(1,"ИТО");
    Group g2=new Group("2","ИТО");
    Student s1=new Student(1,"Смирнов Иван");
    Student s2=new Student("2","Петров Иван");

    public GroupTest(){
        g1.addStudent(s1);
        g1.addStudent(s2);
        try {
            s1.addMark(4);
            s2.addMark(5);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @org.junit.Test
    public void addStudent() {

        g1.addStudent(s1);
        assertEquals(g1.getStudentByIndexArr(0),s1);
        assertEquals(g1.getStudentByIndexArr(1),s2);
    }

    @org.junit.Test
    public void setHead() {
        g1.setHead(s2);
        assertEquals(g1.getHead(),s2);
    }

    @org.junit.Test
    public void getTitle() {
        assertEquals(g1.getTitle(),"ИТО");

    }

    @org.junit.Test
    public void getHead() {
        assertEquals(g1.getHead(),null);
        g1.setHead(s1);
        assertEquals(g1.getHead(),s1);
    }

    @org.junit.Test
    public void getId() {
        assertEquals(g1.getId(),1);
    }

    @org.junit.Test
    public void findByNameStudent() {
        assertEquals(g1.findByNameStudent("Смирнов Иван"),s1);
        assertEquals(g1.findByNameStudent("Смирнов Ивн"),null);
    }

    @org.junit.Test
    public void getAmountStudentsGroup() {
        assertEquals(g1.getAmountStudentsGroup(),2);
    }

    @org.junit.Test
    public void getStudentByIndexArr() {
        assertEquals(g1.getStudentByIndexArr(1),s2);
    }

    @org.junit.Test
    public void findByIdStudent() {
        assertEquals(g1.findByIdStudent(2),s2);
        assertEquals(g1.findByIdStudent(3),null);
    }

    @org.junit.Test
    public void getAverageMarkGroup() {
        assertEquals(g1.getAverageMarkGroup(),4.5,0.1);
    }

    @org.junit.Test
    public void removeStudent() {
        g1.removeStudent(s1);
        assertEquals(g1.getAmountStudentsGroup(),1);
        assertEquals(g1.getStudentByIndexArr(0),s2);
        g1.removeStudent(s1);
    }

}