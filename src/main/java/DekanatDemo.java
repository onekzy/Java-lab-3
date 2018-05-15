import java.util.Random;

public class DekanatDemo {
    private static void relocOneStudent(Dekanat dec) {
        int id = dec.getRandId();
        String grT = dec.getAnotherGroup(id);
        System.out.println("Студент " + id + " переведен в " + grT);
        dec.relocStudent(id, grT);
    }
    public static void main(String[] args) {
        Dekanat dec = new Dekanat("groupsFull.json");
        //dec.saveUpdData("groupsPp.json");
        dec.addMarks();
        dec.removeBadStudents();
        relocOneStudent(dec);
        relocOneStudent(dec);
        relocOneStudent(dec);
        dec.printResults();
        dec.saveUpdData("groupsUpd.json");
    }
}
