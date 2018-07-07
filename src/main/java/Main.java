import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String pathname = "/Users/igor/Desktop/Dekanat/src/main/resources/names.txt";
        Dekanat dekanat = new Dekanat(31, 10, pathname);
        dekanat.removeStudent(20);
        System.out.println("Students are created");
        dekanat.addMarks();
        System.out.println("marks added");
        dekanat.moveStudent(dekanat.findStudent(5), dekanat.getGroups().get(2));
        System.out.println(dekanat.toString());
        System.out.println("Averages calculated");
        dekanat.removeBadStudentsFromGroups();
        dekanat.findStudentByFio("Страхов Владилен Михеевич");
//        creating a new list of students (without bad marks and displaying it)
        System.out.println(dekanat.toString());
        updateStudentsList(dekanat.getStudents());
//        writing students to file:
        dekanat.jsonWriter(dekanat.getStudents());


    }


    public static void updateStudentsList(List<Student> students) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Student s : students) {
            if (s.average() > 2.0) {
//            write s.getFio to the file
                stringBuffer.append(s.getFio()).append("\n");
            }
        }
        try {
            File newTextFile = new File("/Users/igor/Desktop/Dekanat/src/main/resources/updNames.txt");
            FileWriter fw = new FileWriter(newTextFile);
            fw.write(String.valueOf(stringBuffer));
            fw.close();

        }
        catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }

    }
}
