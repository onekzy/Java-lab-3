package Dekanat;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Dekanat {

    private ArrayList<Student> students = new ArrayList<Student>();    //массив студентов
    private ArrayList<Group> groups = new ArrayList<Group>();    //массив групп

    /**
     * Создание нового студента
     * @param fio ФИО студента
     * @param id id студента
     * @return созданный студент
     */
    public Student newStudent(String fio, int id) {
        if(students.stream().filter((st)-> st.getId() == id).findFirst().orElse(null) == null) {
            Student newStud = new Student(id, fio);
            students.add(newStud);
            return newStud;
        }else{
            return null;
        }
    }

    /**
     * Создание новой группы
     * @param title название группы
     * @return созданная группа
     */
    public Group newGroup(String title){
        if(searchGroup(title) == null) {
            Group newGroup = new Group(title);
            groups.add(newGroup);
            return newGroup;
        }else{
            return null;
        }
    }

    /**
     * Добавления переданного количества оценок всем студентам
     * @param namber количество добавляемых оценок
     */
    public void addMarksStudent(int namber) {
        Random rand = new Random();
        students.parallelStream().forEach(student -> {
            for (int i = 0; i < namber; i++) student.addMark(rand.nextInt(4) + 1);
        });
    }

    /**
     * Добавление по одной оценке всем студентам
     */
    public void addMarksStudent() {
        addMarksStudent(1);
    }

    /**
     * Инициализация выборов/перевыборов старост в группах исходя из успеваемости
     */
     public void initiationOfElectionsInGroups() {
         groups.parallelStream().forEach(group -> group.setHeadGroup());
    }

    /**
     * Отчисление переданного студента
     * @param student студент подлежащий отчислению
     * @return успешность выполнения
     */
    public boolean removeStudent(Student student){
       if(students.contains(student)){
           student.getGroup().removeStudentFromGroup(student);
           students.remove(student);
           return false;
       }
       return true;
    }

    /**
     * Отчисление студента по ФИО
      * @param fio ФИО студента подлежащего отчислению
     * @return успешность выполнения
     */
    public boolean removeStudent(String fio) {
        Student student = searchStudent(fio);
        if (student != null) {
            return removeStudent(student);
        }else{
            return false;
        }
    }

    /**
     * Отчисление студента по id
     * @param id id студента подлежащего отчислению
     * @return успешность выполнения
     */
    public boolean removeStudent(int id) {
        Student student = searchStudent(id);
        if (student != null) {
            return removeStudent(student);
        }else{
            return false;
        }
    }

    /**
     * Отчисление студентов чей балл ниже переданного
     * @param averageMark минимально допустимый средний балл
     */
    public void removeStudent(double averageMark) {
        for (Student stud : students) {
            if(stud.getAverageMark() < averageMark){
                removeStudent(stud);
            }
        }
    }

    /**
     * Перевод студента в другую группу
      * @param student переводимый студент
     * @param group группа в которую осуществляется перевод
     * @return успешность выполнения
     */
    public boolean transferOfStudentToGroup(Student student, Group group) {
        if (groups.contains(group) && students.contains(student)) {
            group.addStudentToGroup(student);
            return true;
        } else {
            return false;
        }
    }

    //Получение всей информации о группах и студентах
    public String getDataStudentsAndGroup(){
        String outStr = "\tГруппы:\n";
        for(int i = 0; i < groups.size(); i++){
            outStr += "Title: " + groups.get(i).getTitle() + ", num students = " +  groups.get(i).getNumStudents() + ", average mark = " + groups.get(i).getAverageMark().toString().substring(0,2);
            if(groups.get(i).getHead()!= null){
                outStr+=", head name = " + groups.get(i).getHead().getFio() + ", head ID = " + groups.get(i).getHead().getId();
            }
            outStr += "\n";
        }

        outStr += "\tСтуденты:\n";
        for(int i = 0; i < students.size(); i++) {
            outStr += "ID: " + students.get(i).getId() + ", fio: " + students.get(i).getFio() + ", average mark = " +
                    students.get(i).getAverageMark().toString().substring(0, 3) +
                    ", group: " + students.get(i).getTitleGroup() + "\n";
        }
        return outStr;
    }

    /*
    //Загрузка данных из файла
    public boolean loadStudentAndGroupFromFile(String fileName) {
        //Проверка существования файла
        File meFile = new File(fileName);
        if(!meFile.exists()){
            return false;
        }
        ToFromJson.readJson(students, groups, meFile);
        return true;
    }

    //Загрузка данных из файла
    public boolean loadStudentAndGroupFromFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        File myFile = new File(classLoader.getResource("file.json").getFile());
        if(!myFile.exists()){
            return false;
        }
        ToFromJson.readJson(students, groups, myFile);
        return true;
    }

    //Загрузка данных в файл
    public boolean writeStudentAndGroupToFile(String fileName) {
    //Проверка существования файла
        File meFile = new File(fileName);
        ToFromJson.writeJson(students, groups, meFile);
        return true;
    }

    //Загрузка данных в файл
    public void writeStudentAndGroupToFile() {
        File meFile = new File("fileSave.json");
        ToFromJson.writeJson(students, groups, meFile);
    }
*/
    /**
     * Поиск студента по id
     * @param id id студента
     * @return найденный студент
     */
    public Student searchStudent(int id) {
        return students.stream().filter((st)-> st.getId() == id).findFirst().orElse(null);
    }

    /**
     * Поиск студента по ФИО
      * @param fio ФИО
     * @return найденный студент
     */
    public Student searchStudent(String fio) {
        return students.stream().filter((st)-> st.getFio().equals(fio)).findFirst().orElse(null);
    }

    /**
     * Поиск группы по названию
     * @param title название группы
     * @return найденная группа
     */
    public Group searchGroup(String title) {
        return groups.stream().filter((gr)-> gr.getTitle().equals(title)).findFirst().orElse(null);
    }
}

