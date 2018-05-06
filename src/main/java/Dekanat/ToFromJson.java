package Dekanat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class ToFromJson {

    //Запись в JSON файл ArrayList<Student> и ArrayList<Group>
    static void writeJson(ArrayList<Student> arrayListStudent, ArrayList<Group> arrayListGroup, String fileName) {

        JSONObject outObj = new JSONObject();   //результирующий JSON объект
        JSONArray arrStudentsJson = new JSONArray();    //Массив студентов
        JSONArray arrGroupsJson = new JSONArray();    //Массив Групп

        //Формирование JSON структуры групп
        for (int i = 0; i < arrayListGroup.size(); i++) {
            arrGroupsJson.add(arrayListGroup.get(i).getTitle());
        }
        outObj.put("groups", arrGroupsJson);


        //Формирование JSON структуры студентов
        for (int i = 0; i < arrayListStudent.size(); i++) {
            JSONObject student = new JSONObject();  //Студент
            student.put("fio", arrayListStudent.get(i).getFio());
            student.put("id", arrayListStudent.get(i).getID());
            student.put("group", arrayListStudent.get(i).getTitleGroup());

            JSONArray arrMarks = new JSONArray();   //Массив оценок студентов
            for (Integer mark : arrayListStudent.get(i).getMarks()) {
                arrMarks.add(mark);
            }
            student.put("marks", arrMarks);
            arrStudentsJson.add(student);
        }
        outObj.put("students", arrStudentsJson);


        //Запись Сформированных данных в файл
        FileWriter writer;
        try {
            writer = new FileWriter(fileName);
            writer.write(outObj.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Парсинг JSON файла в ArrayList<Student> и ArrayList<Group>
    static boolean readJson(ArrayList<Student> arrayListStudents, ArrayList<Group> arrayListGroups, String fileName) {
        JSONParser parser = new JSONParser();
        try {
            //Проверка существования файла
            File meFile = new File(fileName);
            if(!meFile.exists()){
                return false;
            }
            JSONObject jsonStrGlobal = (JSONObject) parser.parse(new FileReader(meFile));

            //Чтение JSON структуры групп
            JSONArray arrayGroupJson = (JSONArray) jsonStrGlobal.get("groups");
            for (int i = 0; i < arrayGroupJson.size(); i++) {
                String titleGroup = (String) arrayGroupJson.get(i);
                boolean flag = false;
                for (int j = 0; j < arrayListGroups.size(); j++) {
                    if (arrayListGroups.get(j).getTitle().equals(titleGroup)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    Group gr = new Group(titleGroup);
                    arrayListGroups.add(gr);
                }
            }

            //Чтение JSON структуры студентов
            JSONArray arrayStudentsJson = (JSONArray) jsonStrGlobal.get("students");
            for (int i = 0; i < arrayStudentsJson.size(); i++) {
                JSONObject stud = (JSONObject) arrayStudentsJson.get(i);
                int id = ((Long) stud.get("id")).intValue();
                String fio = (String) stud.get("fio");
                String titleGroup = (String) stud.get("group");
                Student newStudent = new Student(id, fio);
                for (Group gr : arrayListGroups) {
                    if (gr.getTitle().equals(titleGroup)) {
                        gr.addStudentToGroup(newStudent);
                        break;
                    }
                }
                JSONArray arrayMarks = (JSONArray) stud.get("marks");
                for (int j = 0; j < arrayMarks.size(); j++) {
                    int mark = ((Long) arrayMarks.get(j)).intValue();
                    newStudent.addMark(mark);
                }
                arrayListStudents.add(newStudent);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }
}