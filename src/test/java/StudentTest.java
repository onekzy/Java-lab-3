import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {


    @org.junit.jupiter.api.Test

    void average1() {
        Student student = new Student();
        List<Integer> marks = new ArrayList<Integer>(2);
        marks.add(5);
        marks.add(5);
        student.setMarks(marks);
        student.average();
        assertEquals(5.0, student.average());
    }
}