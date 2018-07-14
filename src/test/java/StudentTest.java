import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StudentTest {
    @Test
    public void findAvMarkStudent() {
        double avMark = 0;
        List<Integer> marks = new ArrayList<Integer>();
        marks.add(5);
        marks.add(5);
        marks.add(4);
        marks.add(4);
        marks.add(3);
        marks.add(3);
        for(Integer mark: marks)
            avMark += mark;
        avMark /= marks.size();
        assertEquals(4.0, avMark, 0.01);
    }
}