import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DecanatTest {
    private final ByteArrayOutputStream outStream=new ByteArrayOutputStream();
    private String []outPutString;

    Decanat decanat=new Decanat();
    Group group=new Group();
    Student student=new Student();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outStream));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

    @org.junit.Test
    public void searchHead() {

    }


    @org.junit.Test
    public void chooseHead() {

    }

    @org.junit.Test
    public void findBestAndFailureStud() {

    }

    @org.junit.Test
    public void averageRateOfTheGroup() {
       assertEquals(3.7,(3.4+3.5+3.7+3.8)/4,0.5);
    }

    @org.junit.Test
    public void searchStudentByFio() {
        assertEquals(null, decanat.searchStudentByFio("Дмитриев Осип Андреевич"));

    }

    @org.junit.Test
    public void generateMarks() {
    }

    @org.junit.Test
    public void findFailureStudents() {
    }

    @org.junit.Test
    public void removeFailureStudents() {
    }

    @org.junit.Test
    public void addNewStudent() {
    }
}