import org.junit.After;
//import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static junit.framework.TestCase.assertEquals;

public class StudentTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getId() {
    }

    @Test
    public void getMarks() {
    }

    @Test
    public void getNumOfMarks() {

    }

    @Test
    public void getSumOfMarks() {
    }

    @Test
    public void getAverageMark() {
        double sum=0;
        int num=0;
        Integer []marks={3,5,3,4,5,3,2};
       for(Integer mark:marks){
            num++;
           sum+=mark;
       }
        assertEquals(3.57, sum/num, 0.1);
    }




}