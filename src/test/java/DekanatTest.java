import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class DekanatTest {
    Dekanat d1=new Dekanat();
    File fileStud=new File("students.txt").getAbsoluteFile();
    File fileGrp=new File("groups.txt").getAbsoluteFile();

    public DekanatTest(){
        d1.addStudentsFromFile(fileStud);
        d1.addGroupsFromFile(fileGrp);

    }

    @Test
    public void addStudentsFromFile() {
        assertEquals(d1.findStudentAll("Скотарев Елизар Егорович").getName(),"Скотарев Елизар Егорович");
        assertEquals(d1.findStudentAll("Скотарев Елизар Егорович").getId(),100);
    }

    @Test
    public void addGroupsFromFile() {
        assertEquals(d1.findGroupAll("КТ-2").getTitle(), "КТ-2");
        assertEquals(d1.findGroupAll("КТ-2").getId(), 203);
    }

    @Test
    public void setAllStudentsMarksRand() {
        d1.setAllStudentsMarksRand();
        assertEquals(d1.findStudentAll("Скотарев Елизар Егорович").getAverageMark(),3.5,1.5);
    }

    @Test
    public void setStudentsMarkByName() {
        d1.setStudentsMarkByName("Скотарев Елизар Егорович",5);
        assertEquals(d1.findStudentAll("Скотарев Елизар Егорович").getAverageMark(),5,0.1);
    }

    @Test
    public void changeGroup() {
        d1.changeGroup("Скотарев Елизар Егорович","КТ-2");
        assertEquals(d1.findStudentAll("Скотарев Елизар Егорович").getGroupName(),"КТ-2");
    }


    @Test
    public void expelStudent() {
        d1.expelStudent("Скотарев Елизар Егорович");
        assertEquals(d1.findStudentAll("Скотарев Елизар Егорович"),null);
    }

    @Test
    public void findStudentAll() {
        Student s1=d1.findStudentAll("Яшнов Герасим Серафимович");
        assertEquals(s1.getName(),"Яшнов Герасим Серафимович");
    }

    @Test
    public void findGroupAll() {
        Group g1=d1.findGroupAll("КТ-1");
        assertEquals(g1.getTitle(),"КТ-1");
    }
}