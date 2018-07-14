import java.util.ArrayList;
import java.util.Random;

class Group{
    private String Title;
    private ArrayList<Student> Students;
    private int Num;
    private Student Head;

    Group(String Title){
        this.Title=Title;
        this.Students=new ArrayList<Student>();
        this.Num=0;
        this.Head=new Student();

    }

    public ArrayList<Student> getStudents(){
        return Students;
    }

    public String getHeadName() {
        return Head.getFIO();
    }

    public String getTitle(){
        return Title;
    }

    public void addStudent(Student student){
        Students.add(student);
        Num++;
    }

    public int getNum(){
        return Num;
    }

    public void choiseHead(){
        if (Num!=0){
            Random random = new Random();
            int num = random.nextInt(Num);
            Head=Students.get(num);
        }
    }

    public boolean searchStudent(String fio){
        for (Student name:Students) {
            if (fio.equals(name.getFIO()))
                return true;
        }
        return false;
    }

    public boolean searchStudent(int id){
        for (Student name:Students) {
            if (id==name.getID())
                return true;
        }
        return false;
    }

    public double averageRateGroup(){
        double marksSum=0.0;
        for (Student num:Students) {
            marksSum+=num.averageRate();
        }
        return marksSum/Num;
    }

    public void expellStudent(Student dowager){
        Students.remove(dowager);
        Num--;
        if (dowager==Head) choiseHead();
        dowager.setGroup(null);
    }

}