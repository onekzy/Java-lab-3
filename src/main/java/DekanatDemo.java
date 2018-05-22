import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class DekanatDemo{
    public static void main(String[]args){

        InputStream fileGroup=System.class.getResourceAsStream("/group.txt");
        InputStream fileStudents=System.class.getResourceAsStream("/student.txt");
        Dekanat dek1=new Dekanat(fileGroup,fileStudents);
        dek1.setMarks();
        dek1.choiseHeads();
        dek1.transfer("Козловский Ростислав Тарасович","14ПТК");
        for (String string:dek1.getStatistic()) {
            System.out.println(string);
        }

        dek1.expellStudents();

        for (String string:dek1.getStatistic()) {
            System.out.println(string);
        }
        dek1.PrintToFile();
    }
}


class Dekanat{
    private ArrayList<Student> Students;
    private ArrayList<Group> Groups;

    Dekanat(InputStream fileGroup, InputStream fileStudents){
        Students=new ArrayList<Student>();
        Groups=new ArrayList<Group>();

        addGroups(fileGroup);
        addStudents(fileStudents);
        enrollment();

    }

    public void addGroups(InputStream fileGroup){
        String[]group = new String[0];
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileGroup));
            group=reader.readLine().split(",");
            for (String name:group) {
                Groups.add(new Group(name));
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStudents(InputStream fileStudents){
        String[]student = new String[0];
        String string;
        try{
            BufferedReader readerStudents = new BufferedReader(new InputStreamReader(fileStudents));
            while ((string = readerStudents.readLine()) != null){
                student=string.split(",");
                Students.add(new Student(student, getGroup(student[2])));
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enrollment(){
        for (Student nameS : Students) {
            for (Group nameG:Groups) {
                if (nameS.getGroup()==nameG)
                    nameG.addStudent(nameS);
            }
        }
    }

    public ArrayList<String> getStatistic(){
        int count=0;
        ArrayList<String> statistic=new ArrayList<String>();
        statistic.add("Количество групп в деканате: "+ Groups.size());
        statistic.add("Количество студентов: "+ Students.size());
        for (Student name:Students) {
            if (name.averageRate()==5.0) count++;
        }
        statistic.add("Среди них отличников: "+ count);
        count=0;
        for (Student name:Students) {
            if (name.averageRate()<3.0) count++;
        }
        statistic.add("Двоечников: "+ count);
        return statistic;
    }


    public Group getGroup(String group){
        Group choise = null;
        for (Group iter:Groups) {
            if (group.equals(iter.getTitle())) choise=iter;
        }
        return choise;
    }

    public Student getStudent(String name){
        Student choise = null;
        for (Student iter:Students) {
            if (name.equals(iter.getFIO())) choise=iter;
        }
        return choise;
    }

    public void setMarks(){
        Random random = new Random();

        for (Student student:Students) {
            for (int i=0;i<30;i++){
                student.addMark(2 + random.nextInt(6 - 2));
            }
        }

    }

    public void transfer(String student, String group){
        Student studentTransfer=getStudent(student);
        Group groupToTransfer=getGroup(group);
        studentTransfer.getGroup().expellStudent(studentTransfer);
        studentTransfer.setGroup(groupToTransfer);
        groupToTransfer.addStudent(studentTransfer);
    }

    public void expellStudents(){
        ArrayList<Student> expell = new ArrayList<Student>();
        for (Student name:Students) {
            if (name.averageRate()<3.0){
                name.getGroup().expellStudent(name);
                expell.add(name);
            }
        }
        for (Student name:expell){
            if (name.getGroup()==null) Students.remove(name);
        }
    }

    public void choiseHeads(){
        for (Group group:Groups) {
            group.choiseHead();
        }
    }

    public ArrayList<Student> getStudents(){
        return Students;
    }

    public ArrayList<Group> getGroups(){
        return Groups;
    }


    public void PrintToFile(){

        JSONArray studentsArray = new JSONArray();
        for (Student name:Students) {
            JSONObject newObj=new JSONObject();
            newObj.put("FIO",name.getFIO());
            newObj.put("ID",name.getID());
            newObj.put("Group",name.getGroupName());
            studentsArray.add(newObj);
        }

        try  {
            FileWriter file = new FileWriter("Students.json");
            file.write(studentsArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray groupArray = new JSONArray();
        for (Group group:Groups) {
            JSONObject newObj=new JSONObject();
            newObj.put("Title",group.getTitle());
            newObj.put("Head",group.getHeadName());
            JSONArray StudentsInGroup = new JSONArray();
            for (Student students:group.getStudents()) {
                StudentsInGroup.add(students.getFIO());
            }
            newObj.put("Students",StudentsInGroup);
            groupArray.add(newObj);
        }

        try  {
            FileWriter file = new FileWriter("Groups.json");
            file.write(groupArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}