import static org.junit.Assert.*;

public class StudentTest {

    @org.junit.Test
    public void middleMark() {
        Student student = new Student("99", "Abcd");
        student.addMark(10);
        student.addMark(20);
        student.addMark(30);
        int x = 20;
        assertEquals(student.middleMark(), x);
    }
}