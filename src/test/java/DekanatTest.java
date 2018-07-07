import org.junit.Test;

import static org.junit.Assert.*;

public class DekanatTest {

    @Test
    public void saveUpdData() {
        Dekanat dec = new Dekanat("groupsFull.json");
        boolean res = dec.saveUpdData("groupsTest.json");
        assertEquals(true, res);
    }

    @Test
    public void addMarks() {
        Dekanat dec = new Dekanat("groupsFull.json");
        double res = dec.addMarks();
        assertEquals(true, res >= 2.0d);
        assertEquals(true, res <= 5.0d);
    }

    @Test
    public void removeBadStudents() {
        Dekanat dec = new Dekanat("groupsFull.json");
        double res = dec.addMarks();
        int num1 = dec.getStNum();
        int num2 = dec.removeBadStudents();
        int num3 = dec.getStNum();
        assertEquals(num1, num2+num3);
    }

    @Test
    public void relocStudent() {
        Dekanat dec = new Dekanat("groupsFull.json");
        int id = dec.getRandId();
        String grT = dec.getAnotherGroup(id);
        boolean res = dec.relocStudent(id, grT);
        assertEquals(true, res);
    }
}