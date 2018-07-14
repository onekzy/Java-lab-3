import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;

public class Dekanat {
    private List<Student> allStudents;              //все студенты
    private List<Group> groups;                     //группы
    private List<Student> blackList;                //отчисленные студенты (ср.балл < 3.0, либо по инициативе деканата)

    public Dekanat() {                              //конструктор
        allStudents = new ArrayList<Student>();
        groups = new ArrayList<Group>();
        blackList = new ArrayList<Student>();
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Student> getBlackList() {
        return blackList;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void setAllStudents(List<Student> allStudents) {
        this.allStudents = allStudents;
    }

    //Создание студентов на основе данных из файла
    public List<Student> addStudentsFromFile(String filename) {
        try {
            File file = new File(filename);
            FileReader reader = new FileReader(file);
            JsonParser jsonParser = new JsonParser();                              //создание парсера
            JsonObject jsonObject = (JsonObject) jsonParser.parse(reader);         //объект из JSON файла
            JsonArray jsonStudents = (JsonArray) jsonObject.get("students");       //массив из JSON объектов
            for (JsonElement student : jsonStudents) {
                JsonObject tempStudent = student.getAsJsonObject();                //временный студент
                int id = tempStudent.get("id").getAsInt();                         //id студента
                String fio = tempStudent.get("fio").getAsString();                 //fio студента
                Student studentNew = new Student(id, fio);                         //создание объекта Студент
                allStudents.add(studentNew);                                       //добавление Студента в общий лист
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found!");
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Data not found!");
        }
        return allStudents;
    }

    //Создание групп на основе данных из файла
    public List<Group> addGroupsFromFile(String filename) {
        try {
            File file = new File(filename);
            FileReader reader = new FileReader(file);
            JsonParser jsonParser = new JsonParser();                                 //создание парсера
            JsonObject jsonObject = (JsonObject) jsonParser.parse(reader);            //объект из JSON файла
            JsonArray jsonGroups = jsonObject.getAsJsonArray("groups");    //массив из JSON объектов
            for (JsonElement group : jsonGroups) {
                JsonObject tempGroup = group.getAsJsonObject();                       //временная группа
                String title = tempGroup.get("title").getAsString();                  //заголовок группы
                Group groupNew = new Group(title);                                    //создание объекта Группа
                groups.add(groupNew);                                                 //добавление Группы в общий лист
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found!");
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Data not found!");
        }
        return groups;
    }

    //Заполнение групп студентами (для трех групп)
    public void fillGroupsByStudents() {
        for (int j = 0; j < allStudents.size(); j++) {
            if (j % 3 == 0) {
                groups.get(0).addStudent(allStudents.get(j));      //добавление студента в 1ю группу
                allStudents.get(j).setGroup(groups.get(0));        //зачисляем студента в группу
            } else if (j % 3 == 1) {
                groups.get(1).addStudent(allStudents.get(j));      //добавление студента во 2ю группу
                allStudents.get(j).setGroup(groups.get(1));        //зачисляем студента в группу
            } else {
                groups.get(2).addStudent(allStudents.get(j));      //добавление студента в 3ю группу
                allStudents.get(j).setGroup(groups.get(2));        //зачисляем студента в группу
            }
        }
    }

    //Добавление случайных оценок студентам
    public void addMarksToStudents(int numMarks) {                  //numMarks - количество добавляемых оценок
        for (int i = 0; i < numMarks; i++) {
            for (int j = 0; j < allStudents.size(); j++) {
                int randMark = new Random().nextInt(4) + 2;   //случайная оценка от 2 до 5
                allStudents.get(j).addMark(randMark);
                allStudents.get(j).getNum();
            }
        }
    }

    //Накопление статистики по успеваемости студентов и групп
    public ArrayList<String> statistic() {
        ArrayList<String> stat = new ArrayList<String>();
        stat.add("Количество групп: " + groups.size());
        stat.add("Средний балл по группам: ");
        for (int i = 0; i < groups.size(); i++) {
            Group tempGroup = groups.get(i);
            String avMark = String.format("%.2f", tempGroup.findAvMarkGroup(tempGroup));
            stat.add((i + 1) + ". " + tempGroup.getTitle() + ": " + avMark);
        }
        stat.add("Количество студентов: " + allStudents.size());
        int five = 0, four = 0, three = 0, two = 0;
        for (Student student : allStudents) {
            double avMark = student.findAvMarkStudent(student.getMarks());
            if (avMark >= 4.5)
                five++;
            else if (avMark >= 3.5 && avMark < 4.5)
                four++;
            else if (avMark >= 3.0 && avMark < 3.5)
                three++;
            else two++;
        }
        stat.add("Отличники: " + five);
        stat.add("Хорошисты: " + four);
        stat.add("Троечники: " + three);
        stat.add("Двоечники: " + two);
        return stat;
    }

    //Исключение студента из списка студентов
    public boolean removeStudent(Student student) {
        blackList.add(student);                                       //добавление студента в список отчисленных
        student.getGroup().removeStudentFromGroup(student.getId());   //сначала исключаем студента из группы
        if (student.getGroup() == null) {
            allStudents.remove(student);                              //затем из списка всех студентов
            return true;
        }
        return false;
    }

    //Перевод студента из группы в группу
    public boolean transferStudent(Student student, Group groupIn) {  //groupIn - группа, в которую переводим
        if (student.getGroup() != null) {
            Group groupOut = student.getGroup();
            groupOut.removeStudentFromGroup(student.getId());
            groupIn.addStudent(student);
            student.setGroup(groupIn);
            return true;
        }
        return false;
    }

    //Отчисление студентов за неуспеваемость
    public void removeBadStudents() {
        for (int i = 0; i < allStudents.size(); i++) {
            Student tempStudent = allStudents.get(i);
            if (tempStudent.findAvMarkStudent(tempStudent.getMarks()) < 3.0)
                removeStudent(tempStudent);
        }
    }

    //Сохранение обновленных данных в файлах
    public void printToFile(String filename) {
        JSONArray outputJson = new JSONArray();                             //создание списка групп
        for (int i = 0; i < groups.size(); i++) {
            JSONObject group = new JSONObject();
            outputJson.add(groups.get(i).getTitle());                       //название группы
            group.put("Head", groups.get(i).getHead().getFio());            //староста группы
            group.put("Number of students", groups.get(i).getNum());        //количество студентов
            JSONArray list = new JSONArray();                               //список студентов
            for (int j = 0; j < groups.get(i).getStudents().size(); j++) {
                JSONObject student = new JSONObject();                      //студент как Объект с полями
                Student tempStudent = groups.get(i).getStudents().get(j);
                student.put("ID", tempStudent.getId());
                student.put("FIO", tempStudent.getFio());
                String avMark = String.format("%.2f", tempStudent.findAvMarkStudent(tempStudent.getMarks()));
                student.put("AverageMark", avMark);
                list.add(student);
            }
            group.put("Students", list);
            outputJson.add(group);
        }
        try {
            FileWriter file = new FileWriter(filename);
            file.write(outputJson.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Инициация выборов старост в группах
    public void electionHead() {
        for (Group tempGroup : groups) {
            if (tempGroup.getHead() == null)
                tempGroup.setHead();
        }
    }

    //Вывод данных на консоль
    public void printData() {
        System.out.println("-Деканат-");
        for (int i = 0; i < groups.size(); i++) {
            System.out.println("Группа: " + groups.get(i).getTitle());              //название группы
            if (groups.get(i).getHead() != null)
                System.out.println("Староста: " + groups.get(i).getHead().getFio());//староста
            String avMark = String.format("%.2f", groups.get(i).findAvMarkGroup(groups.get(i)));
            System.out.println("Средний балл по группе: " + avMark);                //средний балл по группе
            System.out.println("Студентов в группе: " + groups.get(i).getNum());    //количество студентов в группе
            for (int j = 0; j < groups.get(i).getStudents().size(); j++) {          //студенты группы
                System.out.println(groups.get(i).getStudents().get(j));
            }
        }
        if (blackList.size() != 0) {
            System.out.println("Отчислено студентов: " + blackList.size());         //отчисленные студенты
            for (int i = 0; i < blackList.size(); i++) {
                System.out.print("ID:" + blackList.get(i).getId());
                System.out.print(", ФИО: \"" + blackList.get(i).getFio() + "\"");
                String avMark = String.format("%.2f", blackList.get(i).findAvMarkStudent(blackList.get(i).getMarks()));
                System.out.println(", Средний балл: " + avMark);
            }
        }
    }
}
