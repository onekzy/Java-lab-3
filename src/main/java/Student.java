
import java.util.ArrayList;

class Student {
    private int id;
    private String name;
    private ArrayList<Integer> marks;
    private Group grp;

    public Student (int id,String name){
        this.id=id;
        this.name=name;
        marks= new ArrayList<>();
    }
    public Student (String id,String name){
        this(Integer.parseInt(id),name);
    }

    public String getName() { return name; }

    public int getId() {
        return id;
    }

    public String getGroupName(){
        if(grp==null)
            return "";
        return grp.getTitle();
    }

    public void setStudentGroup(Group grp){
        if(grp.findByIdStudent(id)!=null&&grp.findByNameStudent(name)!=null){
            System.out.println("The student has group - "+grp.getTitle());
            return;
        }
        this.grp=grp;
    }
    public void addMark(int mark) throws Exception {
        if(mark<2 || mark>5)
            throw new Exception("Exit out of marks bounds");
        marks.add(mark);
    }
    public double getAverageMark(){
        double avg=0;
        for(int i:marks)
            avg+=i;
        try {
            return Math.round(avg / marks.size()*100)/100.0;
        }
        catch (ArithmeticException e){
            e.printStackTrace();
        }
        return 0;
    }
}