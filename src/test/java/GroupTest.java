import static org.junit.Assert.*;

public class GroupTest {

    Group g1 = new Group("Орки");
    Group g2 = new Group("Эльфы");
    Student s1 = new Student(0,"Никитин Корней Давидович");
    Student s2 = new Student(1,"Зуев Оскар Парфеньевич");

    public GroupTest(){
        g1.addStudent(s1);
        g1.addStudent(s2);
        try{
            s1.addMark(2);
            s2.addMark(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void addStudent() {
        g1.addStudent(s1);
        assertEquals(g1.searchStudentId(0), s1);
        assertEquals(g1.searchStudentId(1), s2);
    }

    @org.junit.Test
    public void getTitle() {
        assertEquals(g1.getTitle(), "Орки");
    }

    @org.junit.Test
    public void getAmountOfStudents() {
    }

    @org.junit.Test
    public void getStudents() {
    }

    @org.junit.Test
    public void setHead() {
        g1.setHead();
        assertEquals(g1.getHead(),s2);
    }

    @org.junit.Test
    public void searchStudentId() {
        assertEquals(g1.searchStudentId(1), s2);
    }

    @org.junit.Test
    public void searchStudentName() {
        assertEquals(g1.searchStudentName("Никитин Корней Давидович"), s1);
    }

    @org.junit.Test
    public void getGroupAverageMark() {
        assertEquals(g1.getGroupAverageMark(g1), 3.5, 0.1);
    }

    @org.junit.Test
    public void removeStudent() {
        g1.removeStudent(s1);
        assertEquals(g1.searchStudentName("Пудина Доминика Игоревна"), null);
    }
}