import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class GroupTest {
    static Dekanat dekanat;
    Set<Student> students_set;

    @Test
    public void choiceHead() {
        Student student = new Student("777", "Zyz");
        DekanatDemo.dekanat = new Dekanat();
        Group group = new Group("Test");
        DekanatDemo.dekanat.needHead = true;
        group.choiceHead(student);
        assertEquals(group.head, student);
    }

}