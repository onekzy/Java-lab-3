import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Group {
    String title;
    ArrayList<Student> students_group = DekanatDemo.dekanat.students;
   // ArrayList<Student> students_group = new ArrayList<Student>();
    Student head;

    Group() { }

    Group(String title) {
        this.title = title;
    }

    public Group createGroup(String title) {
        return new Group(title);
    }

    public void addStudent(Student student) {
        boolean checkStudent = true;
        for (Student stu : students_group) {
            if (student.Fio.equals(stu.Fio)) checkStudent = false;
        }
        if (checkStudent) {

            JSONParser parser = new JSONParser();
            try {
                Object obj = parser.parse(new FileReader(DekanatDemo.dekanat.jsonList));
                JSONArray studentsJSON = (JSONArray) obj;
                JSONObject obj1 = new JSONObject();
                obj1.put("ID", student.ID);
                obj1.put("Fio", student.Fio);
                obj1.put("Group", student.group.title);
                studentsJSON.add(obj1);
                FileWriter writer = new FileWriter(DekanatDemo.dekanat.jsonList);
                writer.write(studentsJSON.toJSONString());
                writer.flush();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            students_group.add(student);
        }
    }

    public void choiceHead(Student head) {
        if (DekanatDemo.dekanat.needHead == true)
        this.head = head;
    }

    public boolean searchStudent(String id, String fio) {
        boolean containStudent = false;
        for(int i = 0; i<students_group.size(); i++) {
            if(id.equals(students_group.get(i).ID) || fio.equals(students_group.get(i).Fio)) {
                containStudent = true;
            }
        }
        return containStudent;
    }

    public int middleMarkInGroup() {
        int sum = 0;
        for(int i = 0; i<students_group.size(); i++) {
            for (int j = 0; j < students_group.get(i).marks.size(); j++) {
                sum += students_group.get(i).marks.get(j);
            }
        }
        return  sum/students_group.size();

    }

    public void deleteStudent(Student student) {
        students_group.remove(student);
    }
















}
