package dekanatDemo;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

class Decanat {
    private ArrayList<Student> allStudents;
    private static int lastID;
    private ArrayList<Group> groups;

    Decanat(File studentsFile, File groupsFile) {
        //первичная инициализация
        this();
        decanatIni(studentsFile, groupsFile);
    }

    Decanat(File data){
        allStudents = new ArrayList<Student>();
        groups = new ArrayList<Group>();
        //вторичная инициализация
        this.readFromXML(data);
    }

    Decanat(){
        allStudents = new ArrayList<Student>();
        groups = new ArrayList<Group>();
        lastID = 0;
    }

    private void decanatIni(File studentsFile, File groupsFile) {
        try {
            //пытаемся прочесть из файла студентов
            readStudentsFromXML(studentsFile);
            //пытаемся прочесть из файла групп
            readGroupFromXML(groupsFile);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromXML(File data) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(data);
            Element root = doc.getDocumentElement();
            lastID = Integer.parseInt(root.getAttribute("lastID"));
            //распечатать корневой тег
            System.out.println(root);

            NodeList groupsList = getFilds(root,"group");
            for (int i = 0; i < groupsList.getLength(); i++) {
                //каждый элемете группа
                Node group = groupsList.item(i);
                //данные группы хранятся в атрибутах
                NamedNodeMap atribGroup = group.getAttributes();
                //добавитьгруппу
                Group newGroup = new Group(atribGroup.getNamedItem("title").getNodeValue());
                groups.add(newGroup);

                //получить список студентов в группе
                NodeList studentsList = getFilds(group, "student");
                for (int j = 0; j < studentsList.getLength(); j++) {
                    //каждый элемент студент
                    Node student = studentsList.item(j);
                    //данные студента хранятся в атрибутах
                    NamedNodeMap atribStudent = student.getAttributes();
                    //добавить студента в общий список
                    Student newStudent = new Student(atribStudent);
                    allStudents.add(newStudent);
                    //добавить студента в группу
                    addStudentToGroup(newStudent,newGroup);
                }
                //добавить группе ссылку на старосту
                newGroup.head = newGroup.findStudent(Integer.parseInt(atribGroup.getNamedItem("head").getNodeValue()));
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (DecanatException e) {
            System.out.println(e.toString());
        }
    }

    private void readGroupFromXML(File gr) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(gr);
        //прочитьать все теги с именем group
        NodeList groupList = document.getElementsByTagName("group");
        //занести названия групп в список групп Деканата
        for (int i = 0; i < groupList.getLength(); i++) {
            NamedNodeMap atrGroup = groupList.item(i).getAttributes();
            groups.add(new Group(atrGroup.getNamedItem("title").getNodeValue()));
        }
    }

    private void readStudentsFromXML(File studentsFile) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(studentsFile);
        //проситать все теги с названием student
        NodeList studentList = document.getElementsByTagName("student");
        //занесит данные в список студентовв Деканате
        for (int i = 0; i < studentList.getLength(); i++) {
            NamedNodeMap atrSpudent = studentList.item(i).getAttributes();
            allStudents.add(new Student(++lastID,atrSpudent.getNamedItem("surname").getNodeValue(),atrSpudent.getNamedItem("name").getNodeValue(),
                    atrSpudent.getNamedItem("secondname").getNodeValue()));
        }
    }

    ArrayList<Student> getAllStudents() {
        return allStudents;
    }

    ArrayList<Group> getGroups() {
        return groups;
    }

    private NodeList getFilds(Node node,String nameElements){
        return ((Element)node).getElementsByTagName(nameElements);
    }

    void writeDataToXML(File data) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();
            //создаем корневой тег с именем database
            Element root = document.createElement("database");
            root.setAttribute("lastID",Integer.toString(lastID));
            document.appendChild(root);
            for (Group group :groups){
                //перебор по коллекции групп
                Element gr = document.createElement("group");
                gr.setAttribute("title",group.getTitle());
                gr.setAttribute("head",Integer.toString(group.getHead().getID()));
                root.appendChild(gr);
                for (Student next: group.getStudents()) {
                    //перебор по коллекции студентов
                    //формируем данные о студенте как атрибуты тега student
                    StringBuffer[] fio = next.getFIO();
                    Element student = document.createElement("student");
                    student.setAttribute("surname", fio[0].toString());
                    student.setAttribute("name", fio[1].toString());
                    student.setAttribute("secondname", fio[2].toString());
                    student.setAttribute("ID", Integer.toString(next.getID()));
                    //прицепить тег к группе
                    gr.appendChild(student);
                }
            }
            document.normalizeDocument();
            //инициализировать запись в файл XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            //для нормального вида
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            //совет он лайн духов
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            //записать в xml формат
            transformer.transform(new DOMSource(document),new StreamResult(data));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

//======================================================================================================================

    Student getNewStudent(String surname, String name, String secondname) {
        Student student = new Student(++lastID, surname, name, secondname);
        allStudents.add(student);
        return student;
    }

    void deductStudentsFromGroup(Group group, Student student) throws DecanatException {
        if (group==null) throw new DecanatException("no data about group");
        if (student==null) throw new DecanatException("no data about student");
        student.group = null;
        group.students.remove(student);
        if (student == group.head) makeHeadOfGroup(group);
    }

    void setRandomMarks(Student student) throws DecanatException {
        if (student==null) throw new DecanatException("no data about student");
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            //рандом от 2 до 5
            addMarkToStudent(student,rand.nextInt(4)+2);
        }
    }

    void addMarkToStudent(Student student, int mark) throws DecanatException {
        if (mark<2 || mark>5) throw new DecanatException("incorrect mark");
        if (student==null) throw new DecanatException("no data about student");
        double d = student.averageMark *student.marks.size();
        student.marks.add(mark);
        student.averageMark = (d + mark) / student.marks.size();
        student.averageMark = Math.rint(student.averageMark * 100) / 100;
    }

    void transferStudentToGroup(Student student, Group group) throws DecanatException {
        if (group==null) throw new DecanatException("no data about group");
        if (student==null) throw new DecanatException("no data of student");
        deductStudentsFromGroup(student.group,student);
        addStudentToGroup(student,group);
    }

//======================================================================================================================

    Group getNewGroup(String title) {
        Group newGroup = new Group(title);
        groups.add(newGroup);
        return newGroup;
    }

    Group findGroup(String title) {
        for (Group gr:groups ) {
            if(gr.getTitle().equals(title)) return gr;
        }
        return null;
    }

    void addStudentToGroup(Student student, Group group) throws DecanatException {
        if (group==null) throw new DecanatException("no data about group");
        if (student==null) throw new DecanatException("no data about student");
        group.students.add(student);
        student.group = group;
    }

    void makeHeadOfGroup(Group group) throws DecanatException {
        if (group==null) throw new DecanatException("no data about group");
        double maxAverMark=0.0;
        for (Student student:group.students) {
            if (maxAverMark< student.getAverageMark()) {
                maxAverMark = student.getAverageMark();
                group.head=student;
            }
        }
    }

    ArrayList<Student> getStudentsFromGroup(Group group) {
        return group.students;
    }

//======================================================================================================================

    class Student {

        private int ID;
        private StringBuffer[] FIO;
        private Group group;
        private ArrayList<Integer> marks;

        private double averageMark;

        private Student(int ID, String surname, String name, String secondname) {
            this.ID = ID;
            this.FIO = new StringBuffer[3];
            this.FIO[0]= new StringBuffer(surname);
            this.FIO[1]= new StringBuffer(name);
            this.FIO[2]= new StringBuffer(secondname);
            marks = new ArrayList<Integer>();
            averageMark = 0.0;
            group = null;
        }

        private Student(NamedNodeMap atribStudent) {
            this(Integer.parseInt(atribStudent.getNamedItem("ID").getNodeValue()),
                    atribStudent.getNamedItem("surname").getNodeValue(),
                    atribStudent.getNamedItem("name").getNodeValue(),
                    atribStudent.getNamedItem("secondname").getNodeValue()
            );
        }

        ArrayList<Integer> getMarks() {
            return marks;
        }

        int getID() {
            return ID;
        }

        double getAverageMark() {
            return averageMark;
        }

        String getGroupTitle() throws DecanatException {
            if(this.group==null) {
                throw new DecanatException("not data of group");
            }
            else return this.group.title;
        }

        StringBuffer[] getFIO() {
            return FIO;
        }
    }

    class Group {

        private String title;
        private ArrayList<Student> students;
        private int countStudents;
        private Student head;

        Group(String title) {
            this.title = title;
            students = new ArrayList<Student>();
            countStudents = 0;
            head = null;
        }

        Group(NamedNodeMap atribGroup) {
            this(atribGroup.getNamedItem("title").getNodeValue());
            this.head = findStudent(Integer.parseInt(atribGroup.getNamedItem("head").getNodeValue()));
        }

        String getTitle() {
            return title;
        }

        int getCountStudents() {
            return students.size();
        }

        Student getHead() {
            return this.head;
        }

        double getAverageMark() {
            double summ = 0.0;
            for (Student student : students) {
                summ += student.getAverageMark();
            }
            return Math.rint(summ / students.size() * 100) / 100;
        }

        Student getIdLowAverageMark() {
            double averageMark = 5.0;
            int id=0;
            for (Student next : students) {
                if (averageMark > next.averageMark){
                    averageMark=next.averageMark;
                    id = next.ID;
                }
            }
            return findStudent(id);
        }

        ArrayList<Student> getStudents() {
            return Group.this.students;
        }

        private Student findStudent(Integer id) {
            for (Student next : students) {
                if (next.getID() == id) return next;
            }
            return null;
        }

        private Student findStudent(String surname) {
            for (Student next : students) {
                StringBuffer[] fio = next.getFIO();
                if (surname.equals(fio[1].toString())) return next;
            }
            return null;
        }
    }
}
