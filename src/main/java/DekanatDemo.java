/**
 * @author Pavel Belov
 * http://www.shtanyuk.tk/edu/nniit/java-new/labs/labs3.html
 * Lab №3
 * Dekanat lab.
 */

import java.io.File;

public class DekanatDemo {
    public static void main(String[] args) {
        File studFile = new File("students1.txt").getAbsoluteFile();
        File grpFile = new File("groups.txt").getAbsoluteFile();
        Dekanat dekanat = new Dekanat();
        dekanat.addStudentFromFile(studFile);
        dekanat.addGroupsFromFile(grpFile);
        dekanat.fillGroups();
        dekanat.electionHead();
        for(int i = 0; i < 5; i++)
            dekanat.setRandMarks();
        dekanat.removeBadStudents();
        dekanat.changeGroup("Жаркин Богдан Кондратович", "СУ");
        dekanat.printStudents();
        dekanat.writeToFile();
    }
}