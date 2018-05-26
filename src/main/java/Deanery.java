import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Deanery {
    public ArrayList<Student> students = new ArrayList<Student>();
    public ArrayList<Group> group = new ArrayList<Group>();
    public ArrayList<Student> exStudents = new ArrayList<Student>();

    public void readStudents(){
        try {
            //File file = new File("students.txt");
            //FileReader fileReader = new FileReader(file);
            //BufferedReader bufferedReader = new BufferedReader(fileReader);
            //StringBuffer stringBuffer = new StringBuffer();

            BufferedReader br = new BufferedReader(new FileReader("students.txt"));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                students.add(new Student(i,line));
                i++;
            }
            //fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readGroups(){
        try {
            // File file = new File("groups.txt");
            // FileReader fileReader = new FileReader(file);
            //BufferedReader br = new BufferedReader(fileReader);
            //StringBuffer stringBuffer = new StringBuffer();
            BufferedReader br = new BufferedReader(new FileReader("groups.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                group.add(new Group(line));
            }
           // fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addRandMarks(Student student, int marks){
        Random random = new Random();
        for (int i=0; i<=marks; i++){
            student.add_mark(2 + random.nextInt(4));
        }
    }

    public void fillGroups(){  // распределение студентов по группам
        for (int i=0; i<this.students.size(); i++){
            this.students.get(i).selectGroup(this.group.get(0));
            i++;
            this.students.get(i).selectGroup(this.group.get(1));
            i++;
            this.students.get(i).selectGroup(this.group.get(2));
        }
    }

    public void expelStudents(){  //отчисление студентов
        for (int i=0; i<this.students.size(); i++){
            if (this.students.get(i).average_mark() < 3.0){

                // System.out.println("Студент " + this.students.get(i).FIO + " отчислен");
                if (this.students.get(i).group.getHead().equals(this.students.get(i))){
                    this.setRandHead(this.students.get(i).group);
                }
                this.exStudents.add(this.students.get(i));
                this.students.remove(i);
            }
        }

    }

    public void transferStudent(Student student, Group newGroup){
        student.group = null;
        student.selectGroup(newGroup);
    }

    public void setRandHead(Group group){  //выбор старосты группы
        Random random = new Random();
        group.setHead(group.students.get(random.nextInt(group.students.size())));
    }

    public void saveToFile() throws Exception{
        Writer file = new FileWriter("result.txt");
        for(int i = 0; i< this.students.size(); i++){
            String outstr = this.students.get(i).FIO + " " + this.students.get(i).group.title + "\r\n";
            file.write(outstr);
            outstr = null;
        }
        file.write("\r\n" + "Оценки студентов:" + "\r\n");
        for(int i = 0; i< this.students.size(); i++){
            String outstr = this.students.get(i).FIO + " " + this.students.get(i).group.title + " "  + this.students.get(i).Marks + "\r\n";
            file.write(outstr);
            outstr = null;
        }
        file.write("\r\n" + "Отчисленные студенты:" + "\r\n");
        for (int i=0; i<this.exStudents.size(); i++){
            String outstr = this.exStudents.get(i).FIO + " " + this.exStudents.get(i).group.title + "\r\n";
            file.write(outstr);
            outstr = null;
        }
        file.write("\r\n" + "Старосты групп:" + "\r\n");
        for (int i=0; i<this.group.size(); i++){
            String outstr = this.group.get(i).head.FIO + " " + this.group.get(i).title + "\r\n";
            file.write(outstr);
            outstr = null;}
        file.close();
    }

}
