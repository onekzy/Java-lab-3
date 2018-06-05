package Dekanat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

class ToFromJson {
    /**
     * Запись в JSON файл ArrayList<Student> и ArrayList<Group>
     * @param arrayListStudent список студентов
     * @param arrayListGroup список групп
     * @param JSONfile имя файла для чтения
     */
    static void writeJson(ArrayList<Student> arrayListStudent, ArrayList<Group> arrayListGroup, String JSONfile) {

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
            student.put("id", arrayListStudent.get(i).getId());
            student.put("group", arrayListStudent.get(i).getTitleGroup());

            JSONArray arrMarks = new JSONArray();   //Массив оценок студентов
            arrMarks.addAll(arrayListStudent.get(i).getMarks());
            student.put("marks", arrMarks);
            arrStudentsJson.add(student);
        }
        outObj.put("students", arrStudentsJson);


        //Запись Сформированных данных в файл
        FileWriter writer;
        try {
            writer = new FileWriter(JSONfile);
            writer.write(outObj.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Загрузка из JSON файла списка студентов и групп
     * @param arrayListStudents список студентов
     * @param arrayListGroups список групп
     * @param JSONfile имя JSON файла
     */

    //Парсинг JSON файла в ArrayList<Student> и ArrayList<Group>
    static void readJson(ArrayList<Student> arrayListStudents, ArrayList<Group> arrayListGroups, String JSONfile) {
        JSONParser parser = new JSONParser();
        try {
            InputStream in=ToFromJson.class.getResourceAsStream(JSONfile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            JSONObject jsonStrGlobal = (JSONObject) parser.parse(reader);

            //Чтение JSON структуры групп
            JSONArray arrayGroupJson = (JSONArray) jsonStrGlobal.get("groups");
            for (int i = 0; i < arrayGroupJson.size(); i++) {
                Group group = new Group((String) arrayGroupJson.get(i));
                if(arrayListGroups.stream().filter(gr->gr.getTitle().equals(group.getTitle())).count()==0){
                    arrayListGroups.add(group);
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
                    int mark = ((Long)arrayMarks.get(j)).intValue();
                    newStudent.addMark(mark);
                }
                arrayListStudents.add(newStudent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}