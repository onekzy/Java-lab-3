import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Dekanat {
    boolean needHead = false;

    Set<Student> students_set = new TreeSet<Student>(new Comparator<Student>() {
        public int compare(Student o1, Student o2) {
            if (o1.Fio.equals(o2.Fio))
                return 0;
            else return 1;
        }
    });

    ArrayList<Student> students = new ArrayList<Student>(students_set);

    Set<Group> groups_set = new TreeSet<Group>(new Comparator<Group>() {
        public int compare(Group o1, Group o2) {
            if (o1.title.equals(o2.title))
                return 0;
            else return 1;
        }
    });
    ArrayList<Group> groups = new ArrayList<Group>(groups_set);

    ArrayList<Integer> statisticStudents = new ArrayList<Integer>();
    ArrayList<Integer> statisticGroups = new ArrayList<Integer>();


    //URL file = System.class.getResource("list.json");
    File jsonList = new File("list.json");


    public void refreshStudentsAndGroups(File filename) {
        addGroup(filename);
        addSudents(filename);
    }

    public void addGroup(File filename) {
        int id = 1;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filename));
            JSONArray studentsJSON = (JSONArray) obj;
            Iterator studentsIterator = studentsJSON.iterator();
            while (studentsIterator.hasNext()) {
                JSONObject student = (JSONObject) studentsIterator.next();
                String title = student.get("Group").toString();
                groups_set.add(new Group(title));

            }
            groups.addAll(groups_set);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } catch (ParseException ex) {
        }
    }

    public void addSudents(File filename) {
        int id = 1;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filename));
            JSONArray studentsJSON = (JSONArray) obj;
            Iterator studentsIterator = studentsJSON.iterator();
            while (studentsIterator.hasNext()) {
                JSONObject student = (JSONObject) studentsIterator.next();
                String Fio = student.get("Fio").toString();
                String id_stu = student.get("ID").toString();
                String title = student.get("Group").toString();
                switch (title) {
                    case "ЯР":
                        students_set.add(new Student(id_stu, Fio, groups.get(0)));
                        break;
                    case "АЭ":
                        students_set.add(new Student(id_stu, Fio, groups.get(1)));
                        break;
                    case "ТС":
                        students_set.add(new Student(id_stu, Fio, groups.get(2)));
                        break;
                }
            }
            students.addAll(students_set);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } catch (ParseException ex) {
        }
    }

    public void switchGroup(Student student, Group group) {
        student.toGroup(group);
    }

    public void addRandomMarks(Student student) {
        student.addMark((int) (Math.random() * 5));
    }

    public void addStatisticStu(Student student) {
        statisticStudents.add(student.middleMark());
    }

    public void addStatisticGroups(Group group) {
        statisticGroups.add(group.middleMarkInGroup());
    }

    public void deleteBadStudent(Student student) {
        if (student.middleMark() < 3) {
            JSONParser parser = new JSONParser();
            try {
                Object obj = parser.parse(new FileReader(jsonList));
                JSONArray studentsJSON = (JSONArray) obj;
                JSONObject obj1 = new JSONObject();
                obj1.put("ID", student.ID);
                obj1.put("Fio", student.Fio);
                obj1.put("Group", student.group.title);
                studentsJSON.remove(obj1);
                FileWriter writer = new FileWriter(jsonList);
                writer.write(studentsJSON.toJSONString());
                writer.flush();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            students.remove(student);
        }
    }

    public boolean wishHead() {
        return needHead = true;
    }

    public void printGroup() {
        for (Group group : groups) {
            System.out.println(group.title);
        }
    }

    public void printStudents() {
        for (Student student : students) {
            System.out.println(student.ID + " " + student.Fio + " " + student.group.title);
        }
    }

    public void printStudentsInGroup(String groupTitle) {
        for (Student student : students) {
            if (student.group.title.equals(groupTitle)) {
                System.out.println(student.ID + " " + student.Fio + " " + student.group.title);
            }
        }
    }

    public void printStatStudents() {
        for (Integer s : statisticStudents) {
            System.out.print(s.toString()+ " ");
        }
    }

    public void printStatGroups() {
        for (Integer s : statisticGroups) {
            System.out.print(s.toString() + " ");
        }
    }
}





