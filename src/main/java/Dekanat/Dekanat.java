package Dekanat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        students.stream().forEach(student -> {
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
        List<Student> studToRemove = students.stream().filter(student -> student.getAverageMark() < averageMark).collect(Collectors.toList());
        for(Student student:studToRemove){
            removeStudent(student);
        }
    }

    /**
     * Перевод студента в другую группу
      * @param student переводимый студент
     * @param group группа в которую осуществляется перевод
     * @return успешность выполнения
     */
    public boolean addStudentToGroup(Student student, Group group) {
        if (groups.contains(group) && students.contains(student)) {
            group.addStudentToGroup(student);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Получение данных о студентах и группах
     * @return данные о студентах и группах деканата
     */
    public String getDataStudentsAndGroup() {
        String outStr = "Группы:\n";
        for (int i = 0; i < groups.size(); i++) {
            outStr += i+1 + ". " + groups.get(i).toString() + "\n";
        }
        return outStr;
    }

    /**
     * Загрузка данных из файла
     */
    public void loadStudentAndGroupFromFile() {
        String fileName = "/file.json";
        students.clear();
        groups.clear();
        ToFromJson.readJson(students, groups, fileName);
    }


    /**
     * Сохранение данных в файл
     */
    public void writeStudentAndGroupToFile() {
        String fileName = "fileSave.json";
        ToFromJson.writeJson(students, groups, fileName);
    }

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

