import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jdk.nashorn.internal.ir.debug.JSONWriter;


import java.io.*;
import java.util.ArrayList;
import java.util.Random;



public class Dekanat {
    private ArrayList<Student> students;
    private ArrayList<Group> groups;

    public Dekanat(){
        students= new ArrayList<>();
        groups= new ArrayList<>();
    }
    public void addStudentsFromFile(File f ){
        String s;
        String[] strArr;
        try {
            FileInputStream fileStream=new FileInputStream(f);
            BufferedReader reader=new BufferedReader(new InputStreamReader(fileStream));
            while((s = reader.readLine())!=null){
                strArr=s.trim().split(",");
                students.add(new Student(strArr[0],strArr[1]));
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found "+e);

        } catch (IOException e){
            System.out.println("Error of IO "+e);
        }
    }

    public void addGroupsFromFile(File f ){
        String s;
        String[] strArr;
        try {
            FileInputStream fileStream=new FileInputStream(f);
            BufferedReader reader=new BufferedReader(new InputStreamReader(fileStream));
            while((s = reader.readLine())!=null){
                strArr=s.trim().split(",");
                groups.add(new Group(strArr[0],strArr[1]));
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found "+e);

        } catch (IOException e){
            System.out.println("Error of IO "+e);
        }
    }

    public void setAllStudentsMarksRand(){
        Random rand = new Random();
        try {
            for (Student stud : students) {
                stud.addMark(rand.nextInt(4) + 2);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setStudentsMarkByName(String s,int mark){
        Student s1=findStudentAll(s);
        if(s1==null){
            System.out.println("The student did not find");
            return;
        }
        try {
            s1.addMark(mark);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Student findStudentAll(String stud){
        for(Student s:students){
            if(s.getName().equals(stud))
                return s;
        }
        return null;
    }
    public Group findGroupAll(String grp){
        for(Group g:groups){
            if(g.getTitle().equals(grp)) {
                return g;
            }
        }
        return null;
    }

    public void changeGroup(String stud,String grp){
        Student s1=this.findStudentAll(stud);
        Group g1=this.findGroupAll(grp);
        try {
            if (null == s1) {
                System.out.println("The student is not found "+stud);
                return;
            }
            if (null == g1) {
                System.out.println("The group is not found "+grp);
                return;
            }
            s1.setStudentGroup(g1);
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void setGroupRandomAllStudents(){
        Random rand=new Random();
        int sizeGrp=groups.size();
        Group randGrp;
        for(Student s:students){
            randGrp=groups.get(rand.nextInt(sizeGrp));
            s.setStudentGroup(randGrp);
            randGrp.addStudent(s);
        }
    }


    public void expelForUnsuccessInAllGroups(){
        int i;
        for(i=0;i<students.size();i++){
            if(students.get(i).getAverageMark()<3){
                System.out.println("The Student "+students.get(i).getName()+" expelled. His average of marks = "+students.get(i).getAverageMark());
                students.remove(i);
            }
        }
    }

    public void expelStudent(String stud){
        Student s1=findStudentAll(stud);
        students.remove(s1);
    }

    public void setHeadAllGroup(){
        Random rand=new Random();
        int amount;
        try {
            for (Group g : groups) {
                amount=g.getAmountStudentsGroup();
                if (amount==0){
                    System.out.println("This group has not students "+g.getTitle());
                    return;
                }
                g.setHead(g.getStudentByIndexArr(rand.nextInt(amount)));
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void printStudents(){
        for(Student s:students){
                System.out.println(s.getId()+" "+s.getName()+" "+s.getAverageMark()+" "+s.getGroupName());
        }
        for(Group g:groups){
            System.out.println(g.getTitle()+" Head is "+g.getHead().getName()+" Average mark of group "+g.getAverageMarkGroup());
        }
    }

    public void writeToFile() {
        Writer writers;
        Writer writerg;
        try {
            writers = new FileWriter("studentsOutput.txt");
            for (Student s : students) {
                writers.write(s.getId() + "," + s.getName() + "," + s.getGroupName() + "," + s.getAverageMark());
                writers.write(System.getProperty("line.separator"));

            }
            writers.flush();
            writerg = new FileWriter("groupsOutput.txt");
            for (Group g : groups) {
                writerg.write(g.getId() + "," + g.getTitle() + "," + g.getHead().getName());
                writerg.write(System.getProperty("line.separator"));
            }
            writerg.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
