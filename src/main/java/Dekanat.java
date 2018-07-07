import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//TODO добавить авторизацию или создать builder

public class Dekanat {

    private List<Group> groups = new ArrayList<Group>();
    private List<Student> students = new ArrayList<Student>();


    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Dekanat(int studentAmount, int maxGroupSize, String pathname) {
        int groupAmount = studentAmount / maxGroupSize;
        if (studentAmount % maxGroupSize  > 0) {
            groupAmount++;
        }
        ArrayList<String> fio = readFile(pathname);
        createStudents(studentAmount, fio);
        assignStudents(maxGroupSize);
        assignHead();
    }

// *****************************************************
//Here we are creating a brand new list of students
// *****************************************************

    private void createStudents(int studentAmount, ArrayList<String> fio) {
        List<Student> studentList = new ArrayList<Student>(30);
        for (int i = 0; i < studentAmount; i++ ) {
            Student student = new Student(i, fio.get(i), null,
                    new ArrayList<Integer>());
            studentList.add(student);

        }
        this.students = studentList;
    }

// *****************************************************
//Here we are assigning students to a groups:
// *****************************************************


    public void assignStudents(int maxGroupSize) {
        int temp = 0;
        Group tempGroup = new Group("group" + temp, maxGroupSize);

        for (int i = 0; i < students.size(); i++) {
            if (tempGroup.getStudents().size() >= maxGroupSize) {
                temp++;
                groups.add(tempGroup);
                tempGroup = new Group("group" + temp, maxGroupSize);
            }


            tempGroup.addStudent(students.get(i));

        }
        groups.add(tempGroup);
    }

// *****************************************************
//Here we are randomly assigning heads to a groups:
// *****************************************************


    public void assignHead() {
        for (Group group : groups) {
            group.setHead(group.getStudents().get(new
                    Random().nextInt(group.getStudents().size())));
        }
    }

// *****************************************************
//this method removes student from Group, but keeps him in the list:
// *****************************************************


    public void removeStudent(int studentId) {
        Group tempGroup = null;
        for (Group g : groups) {
            int studentsLeft = g.removeStudent(studentId);
            if (studentsLeft == 0) {
                tempGroup = g;
//                remove data from student
            }

        }
        if(tempGroup != null) {
            groups.remove(tempGroup);
        }
    }

// *****************************************************
//Here we are adding random marks to students:
// *****************************************************

    public void addMarks () {
        for (Student s : students) {
            ArrayList<Integer> marks = new ArrayList<Integer>();
            for (int i = 0; i < (new Random().nextInt(20) + 1); i++) {
                marks.add(new Random().nextInt(4) + 1);
            }

            s.setMarks(marks);
        }
    }

// *****************************************************
//   this method moves student from one group to another
// *****************************************************

    public boolean moveStudent(Student student, Group groupAdd) {

       Group groupTemp = student.getGroup();
       groupTemp.removeStudent(student.getId());
       if (!groupAdd.addStudent(student)) {
           groupTemp.addStudent(student);
           return false;
       }
        System.out.println("Студент " + student.getFio() + " перемещен в группу " + groupAdd.getTitle());
       return true;


    }

// *****************************************************
//This method allows find student by his ID:
// *****************************************************


    public Student findStudent (int studentId) {
            for (Student s : students) {
                if (s.getId()  == studentId) {
                    return s;
                }
            }
        return null;
    }

// *****************************************************
//This method allow to find a student by its name:
// *****************************************************


    public Student findStudentByFio (String fio) {
        for (Student s: students) {
            if (s.getFio().equals(fio)) {
                System.out.println("Да, такой студент есть в базе!");
                return s;
            }
        }
        return null;
    }

// *****************************************************
//This method allow to find a group by its name:
// *****************************************************


    public Group findGroup (String groupName) {
        for (Group g : groups) {
            if (g.getTitle().equals(groupName)) {
                return g;
            }

        }
        return null;
    }
// *****************************************************
//This method allow to remove bad students from groups, but leave them in a main list:
// *****************************************************


    public void removeBadStudentsFromGroups() {
        ArrayList <Integer> ids = new ArrayList<Integer>();
        for (Student s : students) {

            if (s.average() < 2.0d ) {
                ids.add(s.getId());
            }
        }

        for (Integer id : ids) {
            removeStudent(id);
            System.out.println("Bad student " + findStudent(id).getFio() + " has been removed from group");
        }

    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("students: \n " );
        for (Student s : students) {
            stringBuffer.append(s.toString()).append("\n");
        }
        return stringBuffer.toString();
    }


    public ArrayList<String> readFile(String pathname) {
        ArrayList<String> namesList = new ArrayList<String>();
        try {
            File file = new File(pathname);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                namesList.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return namesList;
    }

//    method to write into file using Gson

        public void jsonWriter(List<Student> students) {
            try(Writer writer = new FileWriter("Output.json")) {
                Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
                gson.toJson(students, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

//        method to write into file using FilOutputStream (can't resolve correct reading
        public void writeToFile(List<Student> students) {
            try{
                FileOutputStream fos = new FileOutputStream("newNames.txt");
                ObjectOutputStream outStream = new ObjectOutputStream(fos);
                for (Student s : students) {
                    outStream.writeObject(s);
                }
                outStream.flush();
                outStream.close();
            }
            catch(Exception e) {
                System.out.println("Error" + e.getMessage());
            }

        }
//        method to read from FIS
// TODO implement reading whole file instead of one record
        public void getFromFile() {

            try{
                FileInputStream fis = new FileInputStream("newNames.txt");
                ObjectInputStream inputStream = new ObjectInputStream(fis);
                Student testObject = (Student) inputStream.readObject();
                inputStream.close();
                System.out.println(testObject.toString());
            }catch(Exception e){
                System.out.println("Error" + e.getMessage());

                }
            }



}

