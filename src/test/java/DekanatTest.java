import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class DekanatTest {
    Dekanat d1 = new Dekanat();
    File studFile = new File("student1.txt").getAbsoluteFile();
    File grpFile = new File("groups.txt").getAbsoluteFile();


    public DekanatTest() {
        d1.addStudentFromFile(studFile);
        d1.addGroupsFromFile(grpFile);
    }

    @Test
    public void addStudentFromFile() {
        assertEquals(d1.searchStudents("Баева Максим Мечиславович").getName(),"Баева Максим Мечиславович");
    }

    @Test
    public void addGroupsFromFile() {
        assertEquals(d1.searchGroups("КСУ").getTitle(), "КСУ");
    }

    @Test
    public void fillGroups() {
    }

    @Test
    public void setRandMarks() {
        d1.setRandMarks();
        assertEquals(d1.searchStudents("Снеткова Софья Глебовна").calcAverageMark(), 3.5, 1.5);
    }

    @Test
    public void searchStudents() {
        Student s1 = d1.searchStudents("Королев Аким Адрианович");
    }

    @Test
    public void searchGroups() {
        Group g1 = d1.searchGroups("СУ");
    }

    @Test
    public void changeGroup() {
        d1.changeGroup("Снеткова Софья Глебовна", "СУ");
    }

}