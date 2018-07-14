public class MainDeanery {
    public static void main (String args[]) throws Exception {
        Deanery deanery = new Deanery();
        deanery.readStudents();
        deanery.readGroups();
        deanery.fillGroups();
        for (int i=0; i<deanery.group.size(); i++){
            deanery.setRandHead(deanery.group.get(i));
        }
        for (int i=0; i<deanery.students.size(); i++){
            deanery.addRandMarks(deanery.students.get(i), 10);
        }
        deanery.expelStudents();
        deanery.saveToFile();
    }
}
