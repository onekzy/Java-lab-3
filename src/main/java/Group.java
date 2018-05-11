
import java.util.ArrayList;

public class Group {
    private int id;
    private String title;
    private  ArrayList<Student> students;
    private Student head;


    public Group(int id, String name) {
        this.id = id;
        this.title = name;
        students= new ArrayList<>();
    }

    public Group(String id, String name) {
        this(Integer.parseInt(id),name);
    }

    public void addStudent(Student std){
        if(findByNameStudent(std.getName())!=null||findByIdStudent(std.getId())!=null){
            System.out.println("The student "+std.getName()+ " is in this group or ID is busy");
            return;
        }
        students.add(std);
    }

    public void setHead(Student std){
        head=std;
    }

    public String getTitle() {
        return title;
    }

    public Student getHead() {
        if(head==null)
            return null;
        return head;
    }

    public int getId() {
        return id;
    }

    public Student findByNameStudent(String stud){
        for(Student s:students){
            if(stud.equals(s.getName())){
                return s;
            }
        }
        return null;
    }

    public int getAmountStudentsGroup() {
        return students.size();
    }

    public Student getStudentByIndexArr(int i){
        return students.get(i);
    }

    public Student findByIdStudent(int id){
        for(Student s:students){
            if(id==s.getId()) return s;
        }
        return null;
    }

    public double getAverageMarkGroup(){
        double average=0;
        for(Student s:students){
            average+=s.getAverageMark();
        }
        return Math.round(average/students.size()*100)/100.0;
    }

    public void removeStudent(Student s){
        if(students.contains(s)){
            students.remove(s);
            return;
            }
        System.out.println("The group has not this student!");
    }

}