import java.io.File;

public class DekanatDemo {
    public static void main(String[] args){
        File fileStud=new File("students.txt").getAbsoluteFile();
        File fileGrp=new File("groups.txt").getAbsoluteFile();
        Dekanat d1=new Dekanat();
        d1.addStudentsFromFile(fileStud);
        d1.addGroupsFromFile(fileGrp);
        d1.setGroupRandomAllStudents();
        d1.setHeadAllGroup();
        for(int i=0;i<5;i++)
            d1.setAllStudentsMarksRand();
        d1.printStudents();
        d1.setStudentsMarkByName("Яшнов Герасим Серафимович",5);
        //d1.setStudentsMarkByName("Яшнов Герасим Серафимович",10);
        d1.printStudents();
        d1.expelForUnsuccessInAllGroups();
        d1.changeGroup("Скотарев Елизар Егорович","ИСТ");
        //d1.printStudents();
        //d1.writeToFile();
    }
}