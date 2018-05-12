import java.util.ArrayList;

class Student{
    private String FIO;
    private int ID;
    private Group group;
    private ArrayList<Integer> marks;
    private int Num;

    Student(String[] student, Group group){
        this.FIO=student[0];
        this.ID=Integer.parseInt(student[1]);
        this.group=group;
        this.Num=0;
        this.marks=new ArrayList<Integer>();
    }

    Student(){

    }


    public String getFIO(){
        return FIO;
    }

    public void setGroup(Group newGroup){
        group = newGroup;
    }


    public Group getGroup(){
        return group;
    }

    public int getID(){
        return ID;
    }

    public String getGroupName(){
        return this.group.getTitle();
    }

    public void addMark(int mark){
        marks.add(mark);
        Num++;
    }

    public double averageRate(){
        double marksSum=0.0;
        for (Integer num:marks) {
            marksSum+=num;
        }
        return marksSum/Num;
    }
}