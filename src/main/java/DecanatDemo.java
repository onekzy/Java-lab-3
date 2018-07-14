import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.*;

    class DecanatDemo{
         public static void main(String args[]) throws Exception {
            Decanat decanat=new Decanat();
            Student student = new Student();
            Group group=new Group();
            List<Student>list=null;
            decanat.readStudentsFromFile();
            decanat.printSetOfStudents(student);
         //    System.out.println(group.getStudentsInGroup());
            decanat.chooseHead();
            decanat.addNewStudent("Иванов Иван Иванович", 61,group, "A");
            decanat.generateMarks(10);
            decanat.printSetOfStudents(student);
            decanat.averageRateOfTheGroup();
            decanat.findFailureStudents();
            decanat.removeFailureStudents();
            decanat.averageRateOfTheGroup();
            decanat.printStudInGroup();
            decanat.searchHead();
            decanat.findBestAndFailureStud();
            decanat.searchStudentByFio("Дмитриев Осип Андреевич");
            decanat.searchStudentByFio("Хохлов Станислав Якунович");
            decanat.makeJsonObject();

         }
        }


     class Decanat {

         List<Student> setOfStudents = new ArrayList<Student>();
         ArrayList<Group> groups = new ArrayList<Group>();

         public Decanat(List<Student> setOfStudents, List<? super Student> tempList, List<Student> newSetOfStudent, ArrayList<Group> groups) {
             this.setOfStudents = setOfStudents;
             this.groups = groups;
         }

         Gson GSON=new GsonBuilder().setPrettyPrinting().create();

         Group group=new Group();
         Student student=new Student();

         Group group1 = new Group("A");
         Group group2 = new Group("B");
         Group group3 = new Group("C");
         Group group4 = new Group("D");
         Group group5 = new Group("E");

         public Decanat() {

         }

//
         public void readStudentsFromFile() throws FileNotFoundException {
             int idCount = 0;
             String line = null;
             Group tokenForGroup = null;
             String[] tokens;
             HashMap<String, String> hm = new HashMap<>();
             BufferedReader readFromFile = null;
             try {
                 readFromFile = new BufferedReader(
                         new FileReader("C:\\Users\\kseni\\IdeaProjects\\Decanat\\src\\main\\resources\\setOfStud.txt"));
                 while ((line = readFromFile.readLine()) != null) {

                     Student newStudent = new Student();
                     idCount++;
                     newStudent.setFio(line);
                     newStudent.setId(idCount);
                     newStudent.setGroup(tokenForGroup);
                     newStudent.setMarks(new ArrayList<Integer>());

                     setOfStudents.add(newStudent);

                         tokens = line.split(",");
                         hm.put(tokens[0], tokens[1]);
                         newStudent.setFio(tokens[0]);
                         if (tokens[1].contains("A")) {
                             groups.add(group1);
                             group1.studentsInGroup.add(newStudent);
                             group1.setStudentsInGroup(group1.studentsInGroup);
                             group1.setTitle("A");
                             newStudent.setGroup(group1);
                             group1.numberOfStudents++;


                         } else if (tokens[1].contains("B")) {
                             groups.add(group2);
                             group2.studentsInGroup.add(newStudent);
                             group2.setStudentsInGroup(group2.studentsInGroup);
                             group2.setTitle("B");
                             newStudent.setGroup(group2);
                             group2.numberOfStudents++;

                         } else if (tokens[1].contains("C")) {
                             groups.add(group3);
                             group3.studentsInGroup.add(newStudent);
                             group3.setStudentsInGroup(group3.studentsInGroup);
                             group3.setTitle("C");
                             newStudent.setGroup(group3);
                             group3.numberOfStudents++;

                         } else if (tokens[1].contains("D")) {
                             groups.add(group4);
                             group4.studentsInGroup.add( newStudent);
                             group4.setStudentsInGroup(group4.studentsInGroup);
                             group4.setTitle("D");
                             newStudent.setGroup(group4);
                             group4.numberOfStudents++;

                         } else if (tokens[1].contains("E")) {
                             groups.add(group5);
                             group5.studentsInGroup.add(newStudent);
                             group5.setStudentsInGroup(group5.studentsInGroup);
                             group5.setTitle("E");
                             newStudent.setGroup(group5);
                             group5.numberOfStudents++;

                         }

                 }
                 System.out.println(group1.studentsInGroup);
                 System.out.println(group2.numberOfStudents);
                 System.out.println(group3.numberOfStudents);
                 System.out.println(group4.numberOfStudents);
                 System.out.println(group5.numberOfStudents);

             } catch (IOException e) {
                 e.printStackTrace();
             } finally {
                 if (readFromFile != null)
                     try {
                         readFromFile.close();
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
             }
         }

//проверка: есть ли среди отчисленных студентов- старосты групп,
// если есть - перевыбираем новых из оставшихся студентов
         public void searchHead(){
             System.out.println("in searchHead");
             int size=0;
             for(Group gr:groups){
                 size=gr.numberOfStudents;
                 int item=new Random().nextInt(size);
                        if(gr.getHead().getGroup()==null){
//                            System.out.println(gr.getTitle());
                            gr.setHead((Student) gr.getStudentsInGroup().get(item));//

                        }else
                           continue;
                    }


//             System.out.println("Head of group A"+" "+group1.getHead());
//             System.out.println("Head of group B"+" "+group2.getHead());
//             System.out.println("Head of group C"+" "+group3.getHead());
//             System.out.println("Head of group D"+" "+group4.getHead());
//             System.out.println("Head of group E"+" "+group5.getHead());
         }

//            выбираем старосту
         public void chooseHead() {
             int size=0;
             for (Group group : groups) {
                 size=group.numberOfStudents;
                 int item=new Random().nextInt(size);
                 List<Student> shuffledList = new ArrayList<Student>(group.getStudentsInGroup());
                 Collections.shuffle(Arrays.asList("shuflist:"+shuffledList));
                 group.setHead((Student) shuffledList.get(item));//

             }

//             System.out.println("Head of group A "+group1.getHead());
//             System.out.println("Head of group B "+group2.getHead());
//             System.out.println("Head of group C "+group3.getHead());
//             System.out.println("Head of group D "+group4.getHead());
//             System.out.println("Head of group E "+group5.getHead());

         }



         public void findBestAndFailureStud(){
             System.out.println("The best and the worst average mark.");
             double maxMark=0.0;
             double minMark=0.0;
             maxMark=2.0;
             minMark=5.0;
             String currFioMaxMark = null;
             String currFioMinMark = null;
             for (Student st:setOfStudents){

                 if(st.getAverageMark()>maxMark){
                     maxMark=st.getAverageMark();
                     currFioMaxMark=st.getFio();
                 }
                  else if(st.getAverageMark()<minMark){
                     minMark=st.getAverageMark();
                     currFioMinMark=st.getFio();

                 }
             }
             System.out.println("MinMark: "+minMark+" "+currFioMinMark);
             System.out.println("MaxMark: "+maxMark+" "+currFioMaxMark);

         }


         //средний балл в группе
         public double averageRateOfTheGroup() {

             double rate=0;
             group1.averageRateSum=0;
             group2.averageRateSum=0;
             group3.averageRateSum=0;
             group4.averageRateSum=0;
             group5.averageRateSum=0;

             for(Student st: setOfStudents){
               if(st.getGroup().equals(group1)){
                   group1.averageRateSum+=st.getAverageMark();
                   group1.rate=group1.averageRateSum/group1.numberOfStudents;
                 }
                 if(st.getGroup().equals(group2)){
                     group2.averageRateSum+=st.getAverageMark();
                     group2.rate=group2.averageRateSum/group2.numberOfStudents;
                 }
                 if(st.getGroup().equals(group3)){
                     group3.averageRateSum+=st.getAverageMark();
                     group3.rate=group3.averageRateSum/group3.numberOfStudents;
                 }
                 if(st.getGroup().equals(group4)){
                     group4.averageRateSum+=st.getAverageMark();
                     group4.rate=group4.averageRateSum/group4.numberOfStudents;
                 }
                 if(st.getGroup().equals(group5)){
                     group5.averageRateSum+=st.getAverageMark();
                     group5.rate=group5.averageRateSum/group5.numberOfStudents;

                 }

             }


             System.out.println(group1.averageRateSum+" "+group1.numberOfStudents+" Average rate A: "+group1.rate);
             System.out.println(group2.averageRateSum+" "+group2.numberOfStudents+" Average rate B: "+group2.rate);
             System.out.println(group3.averageRateSum+" "+group3.numberOfStudents+" Average rate C: "+group3.rate);
             System.out.println(group4.averageRateSum+" "+group4.numberOfStudents+" Average rate D: "+group4.rate);
             System.out.println(group5.averageRateSum+" "+group5.numberOfStudents+" Average rate E: "+group5.rate);
             return group.rate;
         }


           public void makeJsonObject() throws IOException {

             JSONObject obj = new JSONObject();

                 try(FileWriter file=new FileWriter("src/main/resources/file.json")) {
                    // JSONArray jsonArray=new JSONArray();

                     for(Student student:setOfStudents) {
                     obj.put("id", student.getId());
                     obj.put("name", student.getFio());
                     obj.put("group", student.getGroup().getTitle());
                     obj.put("avMark",student.getAverageMark());
//                         jsonArray.add(obj);
                       //  file.write(GSON.toJson(obj));
                         GSON.toJson(obj,file);
                         System.out.println("Successfully Copied JSON Object to File...");
                         System.out.println("Person: " + obj);
                 }

                   //  System.out.println(jsonArray);
             }
           }


         //поиск студента
         public Student searchStudentByFio( String fio) {
             for (Student st : setOfStudents) {
                 if (st.getFio().equals(fio)) {
                     System.out.println("Student found: " + fio + "," + "id " + st.getId()+ " group: " + st.getGroup().getTitle());
                 }
                return st;
             }

             return null;
         }

         public void printSetOfStudents(Student student) {
             for(Student st:setOfStudents) {
                 System.out.println("id: "+st.getId()+" fio: " + st.getFio() + " " + "marks" + st.getMarks()+" "
                         +"averageMark: "+st.getAverageMark()+" "+st.getGroup().getTitle());
             }
             }


         //генерируем оценки всем студентам
         public void generateMarks(int n) {
             for (Student student : setOfStudents) {
                 ArrayList<Integer> marksForStudents = new ArrayList<Integer>();
                 for (int i = 0; i < n; i++) {
                     marksForStudents.add((int) (Math.random() * 4 + 2));
                 }
                 student.setMarks(marksForStudents);
             }
         }



         public ArrayList findFailureStudents() {
            ArrayList<Student> failureStudents=new ArrayList<>();
             for (Student st : setOfStudents) {
            if(st.getAverageMark()<3.5){
                if(st.getGroup().equals(group1)){
                    group1.numberOfStudents--;
                    failureStudents.add(st);
                }
                if(st.getGroup().equals(group2)){
                    group2.numberOfStudents--;
                    failureStudents.add(st);
                }
                if(st.getGroup().equals(group3)){
                    group3.numberOfStudents--;
                    failureStudents.add(st);
                }
                if(st.getGroup().equals(group4)){
                    group4.numberOfStudents--;
                    failureStudents.add(st);
                }
                if(st.getGroup().equals(group5)){
                    group5.numberOfStudents--;
                    failureStudents.add(st);
                }



            }
            }
             System.out.println(failureStudents);
              return failureStudents;
         }


         public void removeFailureStudents(){
             ArrayList<Student>newList=new ArrayList<>(setOfStudents.size());
             for(Iterator<Student>i=setOfStudents.iterator();i.hasNext(); ){
                 Student current=i.next();
                 if(current.getAverageMark()<3.5){
                     i.remove();
                     current.setGroup(null);


                 }
             }
         }

         //добавление нового студента

         public void addNewStudent(String newStudent, int id, Group group, String title) {
             Student student=new Student();
            // group.setTitle(title);
             student.setId(id);
             student.setFio(newStudent);
             student.setGroup(new Group(title));
             student.setMarks(new ArrayList<Integer>());
             setOfStudents.add(student);
             group.numberOfStudents++;
         }

         public void printStudInGroup() {

             System.out.println("in printGroup meth");
             for (Student st :setOfStudents) {
                 if(st.getGroup().equals(group1))
                     System.out.println( "in group: "+group1.getTitle()+" "+st.getFio());

             }
         }

     }

         class Group {
             private String title;
             List<Student> studentsInGroup = new ArrayList<>();
             int numberOfStudents = 0;
             double averageRateSum=0.0;
             double rate=0.0;
             Student head=new Student();
             Student student=new Student();



             Group(String title) {
                 this.title = title;
                 this.studentsInGroup = studentsInGroup;
                 this.numberOfStudents = studentsInGroup.size();
                 this.averageRateSum=averageRateSum;
                 this.rate=rate;
                 this.head = head;
             }

             public Group() {

             }

             public void setTitle(String title) {
                 this.title = title;
             }

             public String getTitle() {
                 return title;
             }

             public void setStudentsInGroup(List<Student>studentsInGroup) {
                 this.studentsInGroup = studentsInGroup;
             }

             public List<Student> getStudentsInGroup() {
                 return studentsInGroup;
             }

             public void setHead(Student head) {
                 this.head = head;
             }

             public Student getHead() {
                 return head;
             }

             public double getAverageRate() {
                 return averageRateSum;
             }

             public void setAverageRate(double averageRate) {
                 this.averageRateSum = averageRate;
             }

             public double getRate() {
                 return rate;
             }

             public void setRate(double rate) {
                 this.rate = rate;
             }


             //удаление студента
             public void removeStudent(String fio) {
                 for (Student st : studentsInGroup) {
                     if (st.getFio().equals(fio)) {
                         studentsInGroup.remove(st);
                         st.setGroup(null);
                     } else try {
                         throw new Exception("Student not found!");
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }
             }

         }

         class Student implements Serializable{
            private String fio;
            private int id;
            private Group group;
            private ArrayList<Integer> marks=new ArrayList<>();
            private int numOfMarks;
            private double averageMark;
            private int sumOfMarks;



             public Student() {
                 this.fio = fio;
                 this.id = id;
                 this.group = group;
                 this.marks = marks;
                 this.numOfMarks = marks.size();
                 this.averageMark=averageMark;
             }

             public Student(Student student) {

             }


             public void setFio(String fio) {
                 this.fio = fio;
             }

             public String getFio() {
                 return fio;
             }

             public void setId(int id) {
                 this.id = id;
             }

             public int getId() {
                 return id;
             }

             public void setGroup(Group group) {
                 this.group = group;
             }

             public Group getGroup() {
                 return group;
             }

             public void setMarks(ArrayList<Integer>marks) {
                 this.marks=marks;
             }

             public ArrayList<Integer> getMarks() {
                 return marks;
             }


             public int getNumOfMarks() {
                 return marks.size();
             }

             public int getSumOfMarks() {
                 return sumOfMarks;
             }

             public void setSumOfMarks(ArrayList<Integer>marks) {
                 int sumOfMarks=0;
                 for(Integer mark: marks){
                     sumOfMarks+=mark;
                 }
             }

             public double getAverageMark() {
                 double sum=0.0d;
              for(Integer mark:marks){
               sum+=mark;
              }
   //              System.out.println("avMark "+sum/(double)marks.size());
              return sum/(double)marks.size();
             }


             @Override
             public String toString() {
                 return fio + " " + id + " " + group.getTitle();
             }


//             @Override
//             public int compare(Student o1, Student o2) {
//                 return o1.getFio().compareTo(o2.getFio());
//
//             }


         }



