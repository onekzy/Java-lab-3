import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Group {
    private String title;                                               //название группы
    private List<Student> students;                                     //массив из ссылок на студентов
    private int num;                                                    //количество студентов в группе
    private Student head;                                               //ссылка на старосту

    public Group(String title) {                            //конструктор
        this.title = title;
        students = new ArrayList<Student>();
        num = students.size();
        head = null;
    }

    public String getTitle() {
        return title;
    }
    public List<Student> getStudents() {
        return students;
    }
    public int getNum() {
        return num = students.size();
    }
    public Student getHead(){
        return head;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    //Добавление одного студента в группу
    public void addStudent(Student student) {
        students.add(student);
    }

    //Избрание старосты (случайный студент из состава группы)
    public Student setHead() {
        int randValue = new Random().nextInt(students.size());
        head = students.get(randValue);
        return head;
    }

    //Поиск студента по ФИО
    public Student findingStudentByFIO(String fio) {
        for (int i = 0; i < students.size(); i++) {
            Student tempStudent = getStudents().get(i);
            if (tempStudent.getFio().equals(fio))
                return tempStudent;
        }
        return null;
    }

    //Поиск студента по ID
    public Student findingStudentByID(int id) {
        for (int i = 0; i < students.size(); i++) {
            Student tempStudent = getStudents().get(i);
            if (tempStudent.getId() == id)
                return tempStudent;
        }
        return null;
    }

    //Вычисление среднего балла в группе
    public double findAvMarkGroup(Group group) {
        double averageMarkGroup = 0.0d;
        for (int i = 0; i < students.size(); i++) {
            Student tempStudent = group.getStudents().get(i);
            double averageMark = tempStudent.findAvMarkStudent(tempStudent.getMarks());
            averageMarkGroup += averageMark;
        }
        averageMarkGroup /= students.size();
        return averageMarkGroup;
    }

    //Исключение студента из группы
    public boolean removeStudentFromGroup(int id) {
        Student tempStudent = findingStudentByID(id);
        if (tempStudent.getId() == id){
            students.remove(tempStudent);
            num--;
            if (tempStudent == head)                         //выбираем нового старосту, если старого отчислили
                setHead();
            tempStudent.setGroup(null);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String info = title;
        return info;
    }


}
