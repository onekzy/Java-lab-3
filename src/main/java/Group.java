import java.util.ArrayList;

public class Group {
    public String title;  //название группы
    public ArrayList <Student> students = new ArrayList<Student>();
    public int num;  //количество студентов в группе
    public Student head;  //староста

    Group (String title){
        this.title = title;
    }

    public void addStudent(Student student){
        this.students.add(student);
        student.group = this;

    }

    public void setHead(Student student){  // сеттер для старосты
        if (this.students.contains(student)){
            this.head = student;

        }
    }

    public String getHead(){  // геттер для старосты
        return Student.getFIO(this.head);
    }

    public Student findId (int id){
        for (int i=0; i<this.students.size(); i++){
            if (this.students.get(i).ID == id)
                return this.students.get(i);
        }
        return null;
    }
    public Student findFio (String student){
        for (int i=0; i<this.students.size(); i++){
            if (this.students.get(i).FIO == student)
                return this.students.get(i);
        }
        return null;
    }
    public void deleteStudent(Student student){
        if (this.students.contains(student))
            this.students.remove(student);
    }

    public Double getAvgMark(){
        double avg = 0;
        for (int i=0; i<this.students.size(); i++){
            avg += this.students.get(i).average_mark();}
        return avg/this.students.size();

    }


}
