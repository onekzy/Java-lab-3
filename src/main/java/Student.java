import java.util.ArrayList;

public class Student {
    public int ID; //идентификационный номер
    public String FIO; //фамилия и инициалы
    public Group group; //ссылка на группу (объект Group)
    public ArrayList <Integer> Marks = new ArrayList(); //оценки

    //конструктор:
    Student(int id, String fio ){
        ID = id;
        FIO = fio;
    }

    public void selectGroup(Group group){  //зачисление в группу
        this.group = group;
        group.students.add(this);
    }

    public void add_mark(int mark){  //добавление оценки
        Marks.add(mark);
    }

    public  double average_mark(){  //вычисление средней оценки
        double sum = 0;
        for(int i=0; i<Marks.size(); i++){
            sum += Marks.get(i);
        }
        return (sum/Marks.size());
    }

    public static String getFIO(Student student){ //получение фамилии и инициалов студента
        return student.FIO;
    }

    public static Integer getID(Student student){  //получение идентификационного номера студента
        return student.ID;
    }

}
