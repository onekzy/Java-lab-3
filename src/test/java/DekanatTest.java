import org.junit.Test;

import static org.junit.Assert.*;

public class DekanatTest {

    @Test
    public void refreshStudentsAndGroups() {
        Dekanat dekanat = new Dekanat();
        Group group = new Group("Xyz");
        dekanat.refreshStudentsAndGroups(dekanat.jsonList);
        assertEquals(dekanat.students.get(0), dekanat.students.get(0));

    }

}